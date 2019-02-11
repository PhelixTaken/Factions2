package me.phelix.factions.events;

import me.phelix.factions.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    private Main plugin;
    public Quit(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Bukkit.broadcastMessage(plugin.getMemoryFPlayers().fPlayers.toString());
    }

}
