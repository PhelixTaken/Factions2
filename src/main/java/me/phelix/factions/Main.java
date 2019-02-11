package me.phelix.factions;

import me.phelix.factions.commands.FMainCommand;
import me.phelix.factions.events.Join;
import me.phelix.factions.events.Quit;
import me.phelix.factions.utils.FPlayer;
import me.phelix.factions.utils.Faction;
import me.phelix.factions.utils.entity.MemoryFPlayer;
import me.phelix.factions.utils.entity.MemoryFPlayers;
import me.phelix.factions.utils.entity.MemoryFaction;
import me.phelix.factions.utils.entity.MemoryFactions;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

public class Main extends JavaPlugin {


    private MemoryFPlayers memoryFPlayers;
    private MemoryFactions memoryFactions;
    public void onEnable(){
        memoryFPlayers = new MemoryFPlayers(this);
        memoryFactions = new MemoryFactions();
        Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Quit(this), this);

        getCommand("f").setExecutor(new FMainCommand(this));

        File[] files = new File(getDataFolder() + "/Factions").listFiles();
        if(files == null) return;
        for(File file : files){
            String name = file.getName().replace(".yml", "");
            memoryFactions.factions.put(name, new MemoryFaction(this, name));
        }

        for(Faction faction : memoryFactions.factions.values()) {
            faction.checkConfig();
            for (UUID uuid : faction.getPlayers()) {
                faction.getAllPlayers().add(new MemoryFPlayer(this,  uuid.toString()));
            }
        }


    }

    public void onDisable(){

    }

    public MemoryFPlayers getMemoryFPlayers(){
        return memoryFPlayers;
    }

    public MemoryFactions getMemoryFactions(){
        return memoryFactions;
    }

}
