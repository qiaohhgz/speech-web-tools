package com.jim.socket.speech.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jim.socket.speech.AppContext;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * JimQiao
 * 2015-05-15 22:20
 */
@ServerEndpoint("/window.ws")
public class WindowApp extends AbstractApp {
    private static Set<Session> clients = AppContext.getInstance().getClients();

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        if (clients.contains(session) == false) {
            clients.add(session);
            printSessions();
        }
        if (message == null || message.isEmpty()) {
            return;
        }
        System.out.println("window on msg " + message);
        for (Session client : clients) {
            if (client.equals(session) == false) {
                System.out.println("sent to client " + client.getId());
                send(client, message);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client connected");
        clients.add(session);
//        try {
//            HashMap<Object, String> map = new HashMap<>();
//            map.put("action","id");
//            map.put("id", session.getId());
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(map);
//            System.out.println(json);
//            send(session, json);
//        } catch (IOException e) {
//            System.out.println("发送ID失败" + session.getId());
//        }
        printSessions();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection closed");
        clients.remove(session);
        printSessions();
    }

    @OnError
    public void onError(Session session, Throwable ex) {
        System.out.println("Connection about");
        clients.remove(session);
        printSessions();
    }

    private void printSessions() {
        System.out.printf("Current clients is (%d)%n", clients.size());
    }
}
