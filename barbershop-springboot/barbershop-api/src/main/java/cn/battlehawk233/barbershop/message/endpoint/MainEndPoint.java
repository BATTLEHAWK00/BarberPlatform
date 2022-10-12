package cn.battlehawk233.barbershop.message.endpoint;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@RequiredArgsConstructor
@Component
@ServerEndpoint("/ws")
public class MainEndPoint {
    private static final Logger logger = LoggerFactory.getLogger("WS-Main");

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Websocket opened");
    }

    @OnClose
    public void onClose(Session session) {
        logger.info("Websocket closed");
    }

    @OnError
    public void onError(Session session, Throwable e) {
        logger.error(e.getMessage(), e);
    }

    @OnMessage
    public void onMessage(String msg, Session session) {
    }
}
