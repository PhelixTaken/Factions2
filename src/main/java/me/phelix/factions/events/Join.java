package me.phelix.factions.events;

import me.phelix.factions.Main;
import me.phelix.factions.utils.FPlayer;
import me.phelix.factions.utils.entity.MemoryFPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    private Main plugin;
    public Join(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        MemoryFPlayers fPlayer = plugin.getMemoryFPlayers();
        fPlayer.setPlayer(event.getPlayer());
        FPlayer fPlayer1 = fPlayer.getByPlayer(event.getPlayer());
        fPlayer1.checkConfig();
        fPlayer1.sendMessage(fPlayer.fPlayers.values().toString());
    }

}
