package org.wasteutils.WasteUtils.Commands;

/* 感谢mao_mao_shen和xianyu_1145（他俩啥也没干，单纯想感谢）
R.I.P for debug messages in 1738652268
Douyin @X1a0D1
Bilibili @xiaodi001
*/

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.wasteutils.WasteUtils.Main;


public class CommandWasteUtils {
    public static Boolean CmdHandler(CommandSender sender, Command command, String s, String[] strings, Main plugin) {
        if (command.getName().equalsIgnoreCase("wasteutils") && strings.length > 0) {
            if (strings[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("wasteutils.reload")) {
                    sender.sendMessage(plugin.addPrefix(plugin.lang.getString("plugin.reload")));
                    plugin.loadConfigLang();
                    return true;
                } else {
                    sender.sendMessage(plugin.addPrefix(plugin.lang.getString("plugin.nopermission")));
                    return false;
                } //reload cmd branch end
            } else if (strings[0].equalsIgnoreCase("version")) {
                sender.sendMessage("Running Wasteutils 0.0.3");
            } else {
                sender.sendMessage(plugin.addPrefix(plugin.lang.getString("plugin.notfound")));
                return false;
            }
        } else {
            return false;
        }
        return false;
    }
}
