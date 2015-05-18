package com.jim.socket.speech.websocket;

import javax.websocket.Session;
import java.io.IOException;

/**
 * JimQiao
 * 2015-05-15 22:21
 */
public class AbstractApp {
    protected void send(Session session, String content) throws IOException {
        if (session.isOpen())
            session.getBasicRemote().sendText(content);
    }
}
