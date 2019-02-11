package me.phelix.factions.utils;

import me.phelix.factions.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class FactionFile {

    private File file;
    private FileConfiguration fileConfiguration;
    private String name;
    private Main plugin;

    public FactionFile(Main plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        file = new File(plugin.getDataFolder() + "/Factions", name + ".yml");
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public boolean exists(Player player) {
        return file.exists();
    }

    public void createFile(FPlayer player) {
        try {
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("FactionName", name);
            configuration.set("Leader", player.getPlayer().getUniqueId().toString());
            configuration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return fileConfiguration;
    }

    public void saveConfig() {
        try {
            fileConfiguration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
