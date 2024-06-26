package com.poravelgo.chat.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.poravelgo.chat.model.ChatRoom;
import com.poravelgo.chat.model.ChatRoomMap;
import com.poravelgo.chat.model.ChatType;
import com.poravelgo.chat.model.WebSocketMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RtcChatService {

	// repository substitution since this is a very simple realization

	public ChatRoom createChatRoom(String roomName, String roomPwd, boolean secretChk, int maxUserCnt) {
		// roomName 와 roomPwd 로 chatRoom 빌드 후 return
		ChatRoom room = ChatRoom.builder()
				.roomId(UUID.randomUUID().toString()).roomName(roomName).roomPwd(roomPwd) // 채팅방
				.secretCheck(secretChk) // 채팅방 잠금 여부
				.userCount(0) // 채팅방 참여 인원수
				.maxUserCount(maxUserCnt) // 최대 인원수 제한
				.build();

		room.setUserList(new ConcurrentHashMap<String, WebSocketSession>());

		// msg 타입이면 ChatType.MSG
		room.setChatType(ChatType.RTC);

		// map 에 채팅룸 아이디와 만들어진 채팅룸을 저장
		ChatRoomMap.getInstance().getChatRooms().put(room.getRoomId(), room);

		return room;
	}

	public Map<String, WebSocketSession> getClients(ChatRoom room) {
		// 공부하기 좋은 기존 코드
		// unmodifiableMap : read-only 객체를 만들고 싶을 때 사용
		// Collections emptyMap() : 결과를 반환할 시 반환할 데이터가 없거나 내부조직에 의해 빈 데이터가 반환되어야 하는 경우
		// NullPointException 을 방지하기 위하여 반환 형태에 따라 List 나 Map 의 인스턴스를 생성하여 반환하여 처리해야하는
		// 경우
		// size 메서드 등을 체크하고 추가적인 값을 변경하지 않는 경우 Collections.emptyMap() 를 사용하면 매번 동일한 정적
		// 인스턴스가
		// 변환되므라 각 호출에 대한 불필요한 인스턴스 생성하지 않게 되어 메모리 사용량을 줄일 수 있다

		Optional<ChatRoom> roomDto = Optional.ofNullable(room);

//        return (Map<String, WebSocketSession>) Optional.ofNullable(room)
//                .map(r -> Collections.unmodifiableMap(r.getUserList()))
//                .orElse(Collections.emptyMap());

		return (Map<String, WebSocketSession>) roomDto.get().getUserList();
	}

	public Map<String, WebSocketSession> addClient(ChatRoom room, String name, WebSocketSession session) {
		Map<String, WebSocketSession> userList = (Map<String, WebSocketSession>) room.getUserList();
		userList.put(name, session);
		return userList;
	}

	// userList 에서 클라이언트 삭제
	public void removeClientByName(ChatRoom room, String userUUID) {
		room.getUserList().remove(userUUID);
	}

	// 유저 카운터 return
	public boolean findUserCount(WebSocketMessage webSocketMessage) {
		ChatRoom room = ChatRoomMap.getInstance().getChatRooms().get(webSocketMessage.getData());
//        log.info("ROOM COUNT : [{} ::: {}]",room.toString(),room.getUserList().size());
		return room.getUserList().size() > 1;
	}
}