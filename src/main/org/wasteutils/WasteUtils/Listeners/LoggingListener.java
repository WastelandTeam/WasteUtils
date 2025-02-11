package org.wasteutils.WasteUtils.Listeners;

/*
This listener java file is intended to support the logging feature
in the server.
感谢mao_mao_shen和xianyu_1145（他俩啥也没干，单纯想感谢）
*/

/*
Todo:Cancel the High-risk command and inform users
ToDo:Log inventory change
*/

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.wasteutils.WasteUtils.Main;

import java.util.logging.Level;

import static org.bukkit.Bukkit.getLogger;


public class LoggingListener implements Listener {
    private final Main plugin;

    public LoggingListener(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void serverCommandListener(ServerCommandEvent e) {
        String command = e.getCommand();
        getLogger().log(Level.INFO, this.plugin.addPrefix(this.plugin.lang.getString("log.command")) + command);
    }
}
