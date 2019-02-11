package me.phelix.factions.utils.entity;

import me.phelix.factions.Main;
import me.phelix.factions.utils.FPlayer;
import me.phelix.factions.utils.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class MemoryFPlayers extends FPlayers {

    public Map<String, FPlayer> fPlayers = new HashMap<>();
    private Main plugin;

    public MemoryFPlayers(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public void setPlayer(Player player){
        fPlayers.put(player.getUniqueId().toString(), new MemoryFPlayer(plugin, player.getUniqueId().toString()));
    }

    public Collection<FPlayer> getOnlinePlayers(){
        Set<FPlayer> entities = new HashSet<>();
        for(Player player : Bukkit.getOnlinePlayers()){
            entities.add(this.getByPlayer(player));
        }
        return entities;
    }

    @Override
    public FPlayer getByPlayer(Player player){
        return getById(player.getUniqueId().toString());
    }

    @Override
    public List<FPlayer> getAllFPlayers(){
        return new ArrayList<>(fPlayers.values());
    }

    @Override
    public FPlayer getByOfflinePlayer(OfflinePlayer player){
        return getById(player.getUniqueId().toString());
    }

    @Override
    public FPlayer getById(String id){
        return fPlayers.get(id);
    }

}
