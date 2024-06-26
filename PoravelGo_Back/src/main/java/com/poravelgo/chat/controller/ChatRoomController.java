package com.poravelgo.chat.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poravelgo.chat.model.ChatRoom;
import com.poravelgo.chat.model.ChatRoomMap;
import com.poravelgo.chat.model.ChatType;
import com.poravelgo.chat.model.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

	// ChatService Bean 가져오기
	private final ChatService chatService;

	// 채팅방 생성
	// 채팅방 생성 후 다시 / 로 return
	@PostMapping("/chat/createroom")
	public String createRoom(
				@RequestParam("roomName") String name,
				@RequestParam("roomPwd") String roomPwd,
				@RequestParam("secretCheck") String secretCheck,
				@RequestParam(value = "maxUserCount", defaultValue = "2") String maxUserCount,
				@RequestParam("chatType") String chatType,
				RedirectAttributes rttr) {

		// log.info("chk {}", secretChk);

		// 매개변수 : 방 이름, 패스워드, 방 잠금 여부, 방 인원수
		ChatRoom room;

		room = chatService.createChatRoom(name, roomPwd, Boolean.parseBoolean(secretCheck), Integer.parseInt(maxUserCount),
				chatType);

		log.info("CREATE Chat Room [{}]", room);

		rttr.addFlashAttribute("roomName", room);
		return "redirect:/";
	}

	@GetMapping("/chat/rooms")
	public ResponseEntity<List<ChatRoom>> findAllRooms() {
		return ResponseEntity.ok(chatService.findAllRoom());
	}
	
	
	
	// 채팅방 입장 화면
	// 파라미터로 넘어오는 roomId 를 확인후 해당 roomId 를 기준으로
	// 채팅방을 찾아서 클라이언트를 chatroom 으로 보낸다.
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! security dependency 추가
	@GetMapping("/chat/room")
	public String roomDetail(Model model, String roomId) {

		log.info("roomId {}", roomId);

		ChatRoom room = ChatRoomMap.getInstance().getChatRooms().get(roomId);

		model.addAttribute("room", room);

		if (ChatType.MSG.equals(room.getChatType())) {
			return "chatroom";
		} else {
			model.addAttribute("uuid", UUID.randomUUID().toString());

			return "rtcroom";
		}
	}

	// 채팅방 비밀번호 확인
	@PostMapping("/chat/confirmPwd/{roomId}")
	@ResponseBody
	public boolean confirmPwd(@PathVariable String roomId, @RequestParam String roomPwd) {

		// 넘어온 roomId 와 roomPwd 를 이용해서 비밀번호 찾기
		// 찾아서 입력받은 roomPwd 와 room pwd 와 비교해서 맞으면 true, 아니면 false
		return chatService.confirmPwd(roomId, roomPwd);
	}

	// 채팅방 삭제
	@GetMapping("/chat/delRoom/{roomId}")
	public String delChatRoom(@PathVariable String roomId) {

		// roomId 기준으로 chatRoomMap 에서 삭제, 해당 채팅룸 안에 있는 사진 삭제
		chatService.deleteChatRoom(roomId);

		return "redirect:/";
	}

	// 유저 카운트
	@GetMapping("/chat/chkUserCnt/{roomId}")
	@ResponseBody
	public boolean chUserCnt(@PathVariable String roomId) {

		return chatService.checkRoomUserCount(roomId);
	}
}