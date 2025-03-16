package org.wasteutils.WasteUtils.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.wasteutils.WasteUtils.Main;
import org.wasteutils.WasteUtils.SQLiteAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerListener implements Listener {
    private final Main plugin;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void serverCommandListener(PlayerJoinEvent e) throws SQLException {
        UUID uuid = e.getPlayer().getUniqueId();
        String playername = e.getPlayer().getName();
        int uidCount = 0;
        ResultSet count = SQLiteAPI.queryDb("select count(*) from player");
        ResultSet playerQuery = SQLiteAPI.queryDb(String.format("select * from player where uuid = \"%s\"", uuid));
        if (count.next()) {
            uidCount++;
        }
        if (playerQuery.getString("uuid") == null) {
            Bukkit.getConsoleSender().sendMessage(this.plugin.addPrefix(playername + " 还没有注册，正在注册..."));
            uidCount += 1;
            SQLiteAPI.updateDb(String.format("insert into player (\"uuid\", \"uid\", \"playername\") values (\"%s\", %s, \"%s\")", uuid, uidCount, playername));
            SQLiteAPI.updateDb(String.format("insert into mail (\"uid\") values (\"%s\")", uidCount));
            SQLiteAPI.updateDb(String.format("insert into points (\"uid\") values (\"%s\")", uidCount));
        } else {
            Bukkit.getConsoleSender().sendMessage(this.plugin.addPrefix(playername + " 的 uid 是:" + playerQuery.getString("uid")));
        }
    }
}
