package me.phelix.factions.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public interface Faction {

    public void invite(FPlayer player);

    public void deinvite(FPlayer player);

    public int getSize();

    public Set<FPlayer> getFPlayers();

    public ArrayList<Player> getOnlineFPlayers();

    public ArrayList<FPlayer> getFPlayersWhereRole(Role role);

    public FPlayer getOwner();

    public boolean isOpen();

    public void setOpen(boolean open);

    public String getDescription();

    public void setDescription(String description);

    public String getId();

    public void setId(String id);

    public void addFPlayer(FPlayer fPlayer);

    public void removeFPlayer(FPlayer fPlayer);

    public boolean isInvited(FPlayer player);

    public String getFName();

    public FileConfiguration getConfig();

    public void checkConfig();

    public void setLeader(FPlayer player);

    public Set<UUID> getPlayers();

    public Set<FPlayer> getAllPlayers();

}
