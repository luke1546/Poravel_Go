package com.poravelgo.chat.model;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
    @NotNull
    private String roomId; // 채팅방 아이디
    private String roomName; // 채팅방 이름 
    private int userCount; // 채팅방 인원수
    private int maxUserCount; // 채팅방 최대 인원 제한

    private String roomPwd; // 채팅방 삭제시 필요한 pwd
    private boolean secretCheck; // 채팅방 잠금 여부
    private ChatType chatType; //  채팅 타입 여부

    // ChatRoomDto 클래스는 하나로 가되 서비스를 나누었음
    public ConcurrentMap<String, ?> userList = new ConcurrentHashMap<>();

}