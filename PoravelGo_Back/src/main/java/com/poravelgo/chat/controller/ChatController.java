package com.poravelgo.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.poravelgo.chat.model.Chat;
import com.poravelgo.chat.model.ChatRoomMap;
import com.poravelgo.chat.model.service.ChatService;
import com.poravelgo.chat.model.service.MsgChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
	private final SimpMessageSendingOperations template;
	
	private final MsgChatService msgChatService;
	private final ChatService chatService;
	
	@MessageMapping("/chat/enterUser")
	public void enterUser(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor) {
		chatService.plusUserCount(chat.getRoomId());
		
		String userUUID = msgChatService.addUser(ChatRoomMap.getInstance().getChatRooms(), chat.getRoomId(), chat.getSender());
		
		headerAccessor.getSessionAttributes().put("userUUID", userUUID);
		headerAccessor.getSessionAttributes().put("roomId", chat.getRoomId());
		
		chat.setMessage(chat.getSender() + "님 입장");
		template.convertAndSend("/sub/chat/room" + chat.getRoomId(), chat);
	}
	
	@MessageMapping("/chat/sendMessage")
	public void sendMessage(@Payload Chat chat, java.security.Principal principal) {
		log.info("CHAT {}", chat);
		System.out.println(principal);
		chat.setMessage(chat.getMessage());
		template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);
	}
	
	@EventListener
	public void webSocketDisconnectListener(SessionDisconnectEvent event) {
		log.info("DISCONNECTION {}", event);
		
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		
		String userUUID = (String)headerAccessor.getSessionAttributes().get("userUUID");
		String roomId = (String)headerAccessor.getSessionAttributes().get("roomId");
		
		log.info("HEADACCESSOR {}", headerAccessor);
		
		chatService.minusUserCount(roomId);
		
		 // 채팅방 유저 리스트에서 UUID 유저 닉네임 조회 및 리스트에서 유저 삭제
        String userName = msgChatService.findUserNameByRoomIdAndUserUUID(ChatRoomMap.getInstance().getChatRooms(), roomId, userUUID);
        msgChatService.deleteUser(ChatRoomMap.getInstance().getChatRooms(), roomId, userUUID);
		
		if(userName != null) {
			log.info("USER DISCONNECTED : " + userName);
			
			Chat chat = Chat.builder()
						.type(Chat.MessageType.LEAVE)
						.sender(userName)
						.message(userName + " 님 퇴장")
						.build();
			
			template.convertAndSend("/sub/chat/room/" + roomId, chat);
		}
	}
	
	@GetMapping("/chat/userlist")
	@ResponseBody
	public List<String> userList(String roomId){
		return msgChatService.getUserList(ChatRoomMap.getInstance().getChatRooms(), roomId);
	}
	
	@GetMapping("/chat/duplicateName")
	@ResponseBody
	public String isDuplicateName(@RequestParam("roomId") String roomId, @RequestParam("userName") String userName) {
		String userNameCheck = msgChatService.isDuplicateName(ChatRoomMap.getInstance().getChatRooms(), roomId, userName);
		log.info("CHECK DUPLICATE {}", userNameCheck);
		
		return userNameCheck;
	}
	
//    // turn server config
//    @PostMapping("/turnconfig")
//    @ResponseBody
//    public Map<String, String> turnServerConfig(){
//        Map<String, String> turnServerConfig = new HashMap<>();
//        turnServerConfig.put("url", turnServerUrl);
//        turnServerConfig.put("username", turnServerUserName);
//        turnServerConfig.put("credential", turnServerCredential);
//
//        return turnServerConfig;
//    }
}
