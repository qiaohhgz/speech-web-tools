package com.jim.socket.speech;

import javax.websocket.Session;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 15-5-14.
 */
public class AppContext {
    private final Set<Session> windows = Collections.synchronizedSet(new HashSet<Session>());
    private final Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    public Set<Session> getClients() {
        return clients;
    }

    public Set<Session> getWindows() {
        return windows;
    }

    private AppContext() {
    }

    private static AppContext ourInstance = new AppContext();

    public static AppContext getInstance() {
        return ourInstance;
    }

}
