package org.wasteutils.WasteUtils.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.wasteutils.WasteUtils.Main;

public class CommandMatchMaking {
    public static Boolean CmdHandler(CommandSender sender, Command command, String s, String[] strings, Main plugin) {
        if (command.getName().equalsIgnoreCase("matchmaking") && strings.length > 0) {
            if (strings[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("wasteutils.matchmaking.reload")) {
                    sender.sendMessage(plugin.addPrefix(plugin.lang.getString("matchmaking.reload")));
                    plugin.loadConfigLang();
                    return true;
                } else {
                    sender.sendMessage(plugin.addPrefix(plugin.lang.getString("plugin.nopermission")));
                    return false;
                } //reload cmd branch end
            } else if (strings[0].equalsIgnoreCase("version")) {
                sender.sendMessage("WasteUtils Matchmaking module 0.0.1");//version branch end
            } else if (strings[0].equalsIgnoreCase("start")) {
                sender.sendMessage(sender.getName());
                if (sender.getName() == "CONSOLE") {
                    sender.sendMessage(plugin.addPrefix(plugin.lang.getString("plugin.isconsole")));
                }
            } else {
                sender.sendMessage(plugin.addPrefix(plugin.lang.getString("plugin.notfound")));
                return false;//nf end
            }
        } else {
            sender.sendMessage("Matchmaking without subcommand");//main command end
            return true;
        }
        return false;//impossible, because it needs to be <0.
    }
}
