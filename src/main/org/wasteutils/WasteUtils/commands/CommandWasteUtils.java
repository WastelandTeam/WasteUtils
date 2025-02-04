package org.wasteutils.WasteUtils.commands;

/* 感谢mao_mao_shen和xianyu_1145（他俩啥也没干，单纯想感谢） */

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.wasteutils.WasteUtils.Main;

import java.util.logging.Level;


public class CommandWasteUtils implements CommandExecutor {
    private final Main plugin;

    public CommandWasteUtils(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("wasteutils") && strings.length > 0) {
            Bukkit.getLogger().log(Level.INFO, "Debug: command.getName().equalsIgnoreCase(\"wasteutils\") and strings.length > 0 met.");
            if (strings[0].equalsIgnoreCase("reload")) {
                Bukkit.getLogger().log(Level.INFO, "Debug: strings[0].equalsIgnoreCase(\"reload\") met.");
                if (sender.hasPermission("wasteutils.reload")){
                    Bukkit.getLogger().log(Level.INFO, "Debug: sender.hasPermission(\"wasteutils.reload\") met, you should see the message now");
                    sender.sendMessage(this.plugin.addPrefix(this.plugin.lang.getString("plugin.reload")));
                    this.plugin.loadConfig();
                    return true;
                } else {
                    Bukkit.getLogger().log(Level.INFO, "Debug: sender.hasPermission(\"wasteutils.reload\") didn't met, you should see the message now.");
                    sender.sendMessage(this.plugin.addPrefix(this.plugin.lang.getString("plugin.nopermission")));
                    return false;
                }
            } else {
                Bukkit.getLogger().log(Level.INFO, "Debug: strings[0].equalsIgnoreCase(\"reload\") didn't met, you should see the message now.");
                sender.sendMessage(this.plugin.addPrefix(this.plugin.lang.getString("plugin.notfound")));
                return false;
            }
        } else {
            Bukkit.getLogger().log(Level.INFO, "Debug: hmmmm. command.getName().equalsIgnoreCase(\"wasteutils\") && strings.length > 0 didn't met, in this case, you don't have the possibility to see this message, bug?");
            return false;
        }
    }
}
