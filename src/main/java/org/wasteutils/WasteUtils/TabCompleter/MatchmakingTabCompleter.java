package org.wasteutils.WasteUtils.TabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class MatchmakingTabCompleter implements TabCompleter {
    private static final String[] subcommands = {"view", "version"};

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return List.of(subcommands);
    }
}
