package me.phelix.factions.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public interface FPlayer {

    public Faction getFaction();

    public boolean hasFaction();

    public void setFaction(Faction faction);

    public Role getRole();

    public void setRole(Role role);

    public String getRolePrefix();

    public String getFName();

    public FileConfiguration getConfig();

    public File getFile();

    public String getId();

    public void setId(String id);

    public boolean isOnline();

    public Player getPlayer();

    public void sendMessage(String string);

    public void sendMessage(int i);

    public void sendMessage(double d);

    public void sendMessage(float f);

    public void sendMessage(long l);

    public void checkConfig();

    public String getFactionId();

    public void setFactionId(String id);

    public String getName();


}
