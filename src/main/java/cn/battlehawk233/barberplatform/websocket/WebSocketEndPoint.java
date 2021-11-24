package cn.battlehawk233.barberplatform.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

// 配置参考
// https://www.iocoder.cn/Spring-Boot/WebSocket/?github

/**
 * WebSocket配置
 */
@Controller
@ServerEndpoint("/message")
public class WebSocketEndPoint {
    public static final Logger logger = LoggerFactory.getLogger(WebSocketEndPoint.class);

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        logger.info("[onOpen][session({}) 接入]", session);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        if (message.equals("ping")){
            session.getBasicRemote().sendText("pong");
        }
        logger.debug("[onOpen][session({}) 接收到一条消息({})]", session, message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.debug("[onClose][session({}) 连接关闭。关闭原因是({})}]", session, closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("[onClose][session({}) 发生异常]", session, throwable);
    }
}
