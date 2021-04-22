package com.rat.squad.storage.websocket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * WebSocketHandler made for sending updates to front about adding and deleting categories
 * All extended methods implemented
 * Added method which sends all saved sessions message
 */
@Slf4j
public class EventHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    @SneakyThrows
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        Thread.sleep(3000); // simulated delay
        TextMessage msg = new TextMessage("Hello, " + message.getPayload() + "!");
        session.sendMessage(msg);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    public void sendMessageToAllSessions(TextMessage message) {
        sessions.forEach(session -> {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                log.info("Exception caught during sending messages in session :" + e);
            }
        });
    }
}