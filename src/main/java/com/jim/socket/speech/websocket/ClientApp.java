package com.jim.socket.speech.websocket;

import com.jim.socket.speech.AppContext;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;

/**
 * JimQiao
 * 2015-05-15 22:32
 */
@ServerEndpoint("/client.ws")
public class ClientApp extends AbstractApp {
    private static Set<Session> clients = AppContext.getInstance().getWindows();

    public static Set<Session> getClients() {
        return clients;
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        if (clients.contains(session) == false) {
            clients.add(session);
            printSessions();
        }
        sendMessage(message);
    }

    public static void sendMessage(String message) throws IOException {
        if (message == null || message.isEmpty()) {
            return;
        }
        for (Session client : getClients()) {
            if (client.isOpen()) {
                client.getBasicRemote().sendText(message);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client connected");
        clients.add(session);
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
