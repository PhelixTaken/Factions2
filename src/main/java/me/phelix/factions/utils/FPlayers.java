package me.phelix.factions.utils;

import me.phelix.factions.utils.entity.MemoryFPlayers;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class FPlayers {

    public abstract FPlayer getByPlayer(Player player);

    public abstract Collection<FPlayer> getAllFPlayers();

    public abstract Collection<FPlayer> getOnlinePlayers();

    public abstract FPlayer getByOfflinePlayer(OfflinePlayer player);

    public abstract FPlayer getById(String string);

    public abstract void setPlayer(Player player);

}
