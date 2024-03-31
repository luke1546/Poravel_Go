package com.poravelgo.chat.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.poravelgo.chat.model.ChatRoom;
import com.poravelgo.chat.model.ChatRoomMap;
import com.poravelgo.chat.model.ChatType;
import com.poravelgo.file.model.service.FileService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final MsgChatService msgChatService;
    private final RtcChatService rtcChatService;

    // 채팅방 삭제에 따른 채팅방의 사진 삭제를 위한 fileService 선언
    private final FileService fileService;


    // 전체 채팅방 조회
    public List<ChatRoom> findAllRoom(){
        // 채팅방 생성 순서를 최근순으로 반환
        List<ChatRoom> chatRooms = new ArrayList<>(ChatRoomMap.getInstance().getChatRooms().values());
        Collections.reverse(chatRooms);

        return chatRooms;
    }

    // roomID 기준으로 채팅방 찾기
    public ChatRoom findRoomById(String roomId){
        return ChatRoomMap.getInstance().getChatRooms().get(roomId);
    }

    // roomName 로 채팅방 만들기
    public ChatRoom createChatRoom(String roomName, String roomPwd, boolean secretChk, int maxUserCnt, String chatType){

        ChatRoom room;

        // 채팅방 타입에 따라서 사용되는 Service 구분
        if(chatType.equals("msgChat")){
            room = msgChatService.createChatRoom(roomName, roomPwd, secretChk, maxUserCnt);
        }else{
            room = rtcChatService.createChatRoom(roomName, roomPwd, secretChk, maxUserCnt);
        }

        return room;
    }

    // 채팅방 비밀번호 조회
    public boolean confirmPwd(String roomId, String roomPwd) {
//        String pwd = chatRoomMap.get(roomId).getRoomPwd();

        return roomPwd.equals(ChatRoomMap.getInstance().getChatRooms().get(roomId).getRoomPwd());

    }

    // 채팅방 인원+1
    public void plusUserCount(String roomId){
        log.info("cnt {}",ChatRoomMap.getInstance().getChatRooms().get(roomId).getUserCount());
        ChatRoom room = ChatRoomMap.getInstance().getChatRooms().get(roomId);
        room.setUserCount(room.getUserCount()+1);
    }

    // 채팅방 인원-1
    public void minusUserCount(String roomId){
        ChatRoom room = ChatRoomMap.getInstance().getChatRooms().get(roomId);
        room.setUserCount(room.getUserCount()-1);
    }

    // maxUserCnt 에 따른 채팅방 입장 여부
    public boolean checkRoomUserCount(String roomId){
        ChatRoom room = ChatRoomMap.getInstance().getChatRooms().get(roomId);


        if (room.getUserCount() + 1 > room.getMaxUserCount()) {
            return false;
        }

        return true;
    }

    // 채팅방 삭제
    public void deleteChatRoom(String roomId){

        try {
            // 채팅방 타입에 따라서 단순히 채팅방만 삭제할지 업로드된 파일도 삭제할지 결정
            ChatRoomMap.getInstance().getChatRooms().remove(roomId);

            if (ChatRoomMap.getInstance().getChatRooms().get(roomId).getChatType().equals(ChatType.MSG)) { // MSG 채팅방은 사진도 추가 삭제
                // 채팅방 안에 있는 파일 삭제
                fileService.deleteFileDir(roomId);
            }

            log.info("삭제 완료 roomId : {}", roomId);

        } catch (Exception e) { // 만약에 예외 발생시 확인하기 위해서 try catch
            System.out.println(e.getMessage());
        }
        
    }

}
/*
 * @Slf4j
 * 
 * @Repository public class ChatService { private Map<String, ChatRoom>
 * chatRooms;
 * 
 * private final MsgChatService msgChatService; private final RtcChatService
 * rtcChatService;
 * 
 * // 채팅방 삭제에 따른 채팅방의 사진 삭제를 위한 fileService 선언
 * 
 * @Autowired FileService fileService;
 * 
 * // ---------------------------------------
 * 
 * // maxUserCnt 에 따른 채팅방 입장 여부 public boolean chkRoomUserCnt(String roomId) {
 * ChatRoom room = chatRooms.get(roomId);
 * 
 * log.info("참여인원 확인 [{}, {}]", room.getUserCount(), room.getMaxUserCount());
 * 
 * if (room.getUserCount() + 1 > room.getMaxUserCount()) { return false; }
 * 
 * return true; }
 * 
 * // 채팅방 비밀번호 조회 public boolean confirmPwd(String roomId, String roomPwd) { //
 * String pwd = chatRoomMap.get(roomId).getRoomPwd(); return
 * roomPwd.equals(chatRooms.get(roomId).getRoomPwd()); }
 * 
 * // 채팅방 삭제 public void delChatRoom(String roomId) { try { // 채팅방 삭제
 * chatRooms.remove(roomId);
 * 
 * // 채팅방 안에 있는 파일 삭제 fileService.deleteFileDir(roomId);
 * 
 * log.info("삭제 완료 roomId : {}", roomId);
 * 
 * } catch (Exception e) { System.out.println(e.getMessage()); } }
 * 
 * @PostConstruct private void init() { chatRooms = new LinkedHashMap<>(); }
 * 
 * // 채팅방 전체 조회(최근 순으로 반환) public List<ChatRoom> findAllRoom() {
 * 
 * List rooms = new ArrayList<>(chatRooms.values()); Collections.reverse(rooms);
 * 
 * return rooms; }
 * 
 * // roomId로 채팅방 찾기 public ChatRoom findRoomById(String roomId) { return
 * chatRooms.get(roomId); }
 * 
 * // 채팅방 생성 public ChatRoom createChatRoom(String roomName) { ChatRoom room =
 * new ChatRoom().create(roomName); chatRooms.put(room.getRoomId(), room);
 * 
 * return room; }
 * 
 * // 입장한 유저 수 public void addUserCount(String roomId) { ChatRoom room =
 * chatRooms.get(roomId); room.setUserCount(room.getUserCount() + 1); }
 * 
 * // 퇴장한 유저 수 public void minusUserCount(String roomId) { ChatRoom room =
 * chatRooms.get(roomId); room.setUserCount(room.getUserCount() - 1); }
 * 
 * // 채팅방에 유저 입장 public String addUser(String roomId, String userName) {
 * ChatRoom room = chatRooms.get(roomId); String userUUID =
 * UUID.randomUUID().toString();
 * 
 * room.getUserList().put(userUUID, userName);
 * 
 * return userUUID; }
 * 
 * // 유저 이름 중복 체크 public String isDuplicateName(String roomId, String userName)
 * { ChatRoom room = chatRooms.get(roomId); String tmp = userName;
 * 
 * while (room.getUserList().containsValue(tmp)) { int randNum = (int)
 * (Math.random() * 100) + 1;
 * 
 * tmp = userName + randNum; } return tmp; }
 * 
 * // 유저 퇴장 처리 public void deleteUser(String roomId, String userUUID) { ChatRoom
 * room = chatRooms.get(roomId); room.getUserList().remove(userUUID); }
 * 
 * // 채팅방 유저명 조회 public String getUserName(String roomId, String userUUID) {
 * ChatRoom room = chatRooms.get(roomId); return (String)
 * room.getUserList().get(userUUID); }
 * 
 * // 전체 유저 목록 반환 public List<String> getUserList(String roomId) { List<String>
 * list = new ArrayList<>(); ChatRoom room = chatRooms.get(roomId);
 * 
 * room.getUserList().forEach((k, v) -> list.add((String) v)); return list; } }
 */
