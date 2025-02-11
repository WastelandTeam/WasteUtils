package org.wasteutils.WasteUtils.commands;

/* 感谢mao_mao_shen和xianyu_1145（他俩啥也没干，单纯想感谢）
R.I.P for debug messages in 1738652268
*/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.wasteutils.WasteUtils.Main;


public class CommandWasteUtils implements CommandExecutor {
    private final Main plugin;

    public CommandWasteUtils(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("wasteutils") && strings.length > 0) {

            if (strings[0].equalsIgnoreCase("reload")) {

                if (sender.hasPermission("wasteutils.reload")){

                    sender.sendMessage(this.plugin.addPrefix(this.plugin.lang.getString("plugin.reload")));
                    this.plugin.loadConfig();
                    return true;
                } else {

                    sender.sendMessage(this.plugin.addPrefix(this.plugin.lang.getString("plugin.nopermission")));
                    return false;
                }
            } else {

                sender.sendMessage(this.plugin.addPrefix(this.plugin.lang.getString("plugin.notfound")));
                return false;
            }
        } else {

            return false;
        }
    }
}
