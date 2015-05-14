package com.jim.socket.speech;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by admin on 15-5-14.
 */
public class DataContext {
    public final Set windows = new CopyOnWriteArraySet();
    public final Set clients = new CopyOnWriteArraySet();

    public Set getClients() {
        return clients;
    }

    public Set getWindows() {
        return windows;
    }

    private DataContext() {
    }

    private static DataContext ourInstance = new DataContext();

    public static DataContext getInstance() {
        return ourInstance;
    }

}
