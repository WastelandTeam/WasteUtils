package org.wasteutils.WasteUtils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Shortcuts {
    public FileConfiguration loadLang(){
        String langFileName = getConfig().getString("message.lang") + ".yml";
        File langFile = new File(getDataFolder() + File.separator + "message", langFileName);
        if (!langFile.exists()) {
            saveResource("message" + File.separator + langFileName, false);
        }
        return YamlConfiguration.loadConfiguration(langFile);
    }
}