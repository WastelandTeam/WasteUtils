package org.wasteutils.WasteUtils.Listeners;

/*
This listener java file is intended to support the logging feature
in the server.
*/

/*
Todo:Cancel the High-risk command and inform users
ToDo:Log inventory change
*/

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.logging.Level;


public class LoggingListener implements Listener {
    @EventHandler
    public void serverCommandListener(ServerCommandEvent e) {
        String command = e.getCommand();
        Bukkit.getLogger().log(Level.INFO, "§3WasteUtils §f>> Command executed from CONSOLE: " + command);
    }
}
