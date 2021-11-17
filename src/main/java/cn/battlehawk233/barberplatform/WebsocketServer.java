package cn.battlehawk233.barberplatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/message/{sid}")
public class WebsocketServer {
    public static final Logger logger = LoggerFactory.getLogger(WebsocketServer.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        logger.info("new websocket connection established: " + sid);

    }
}
