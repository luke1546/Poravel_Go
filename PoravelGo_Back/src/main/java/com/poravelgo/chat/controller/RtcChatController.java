package com.poravelgo.chat.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poravelgo.chat.model.WebSocketMessage;
import com.poravelgo.chat.model.service.RtcChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RtcChatController {

    private final RtcChatService rtcChatService;

    @PostMapping("/webrtc/usercount")
    public String webRTC(@ModelAttribute WebSocketMessage webSocketMessage) {
        log.info("MESSAGE : {}", webSocketMessage.toString());
        return Boolean.toString(rtcChatService.findUserCount(webSocketMessage));
    }
}
