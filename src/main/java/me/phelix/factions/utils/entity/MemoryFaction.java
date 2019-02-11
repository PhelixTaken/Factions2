package me.phelix.factions.utils.entity;

import me.phelix.factions.Main;
import me.phelix.factions.utils.FPlayer;
import me.phelix.factions.utils.Faction;
import me.phelix.factions.utils.Role;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MemoryFaction implements Faction {

    private String id;
    private String name;
    private boolean isOpen;
    private Set<FPlayer> fPlayers = new HashSet<>();
    private Set<String> invites = new HashSet<>();
    private File file;
    private FileConfiguration configuration;
    private Main plugin;

    public MemoryFaction(Main plugin, String name){
        this.plugin = plugin;
        this.name = name;
    }


    @Override
    public void invite(FPlayer player) {
        invites.add(player.getId());
    }

    @Override
    public void deinvite(FPlayer player) {
        invites.remove(player.getId());
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Set<FPlayer> getFPlayers() {
        return fPlayers;
    }

    @Override
    public ArrayList<Player> getOnlineFPlayers() {
        ArrayList<Player> online = new ArrayList<>();
        for(Player player : Bukkit.getOnlinePlayers()){
            FPlayer fPlayer = plugin.getMemoryFPlayers().getByPlayer(player);
            if(fPlayer.getFaction() == this){
                online.add(player);
            }
        }
        return online;
    }

    @Override
    public ArrayList<FPlayer> getFPlayersWhereRole(Role role) {
        ArrayList<FPlayer> list = new ArrayList<>();
        for(FPlayer fPlayer : fPlayers){
            if(fPlayer.getRole() == role){
                list.add(fPlayer);
            }
        }
        return list;
    }

    @Override
    public FPlayer getOwner() {
        for(FPlayer fPlayer : fPlayers){
            if(fPlayer.getRole() == Role.LEADER){
                return fPlayer;
            }
        }
        return null;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void setOpen(boolean open) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public void addFPlayer(FPlayer fPlayer) {
        fPlayers.add(fPlayer);
    }

    @Override
    public void removeFPlayer(FPlayer fPlayer) {
        fPlayers.remove(fPlayer);
    }

    @Override
    public boolean isInvited(FPlayer player) {
        return false;
    }

    @Override
    public String getFName() {
        return configuration.getString("FactionName");
    }

    @Override
    public FileConfiguration getConfig() {
        return configuration;
    }

    @Override
    public void checkConfig() {
        File[] factions = new File(plugin.getDataFolder() + "/Factions").listFiles();
        if (factions == null) return;
        for (File file : factions) {
            if(file.getName().replace(".yml", "").equalsIgnoreCase(name)){
                this.configuration = YamlConfiguration.loadConfiguration(file);
            }
        }
    }

    @Override
    public void setLeader(FPlayer player) {

    }

    @Override
    public Set<UUID> getPlayers() {
        Set<UUID> allPlayers = new HashSet<>();
        List<UUID> coleader = getConfig().getStringList("CO-Leader").stream().map
                (UUID::fromString).collect(Collectors.toList());
        List<UUID> moderators = getConfig().getStringList("Moderators").stream().map
                (UUID::fromString).collect(Collectors.toList());
        List<UUID> members = getConfig().getStringList("Members").stream().map
                (UUID::fromString).collect(Collectors.toList());
        List<UUID> recruit = getConfig().getStringList("Recruit").stream().map
                (UUID::fromString).collect(Collectors.toList());
        UUID leader = UUID.fromString(getConfig().getString("Leader"));
        allPlayers.addAll(coleader);
        allPlayers.addAll(moderators);
        allPlayers.addAll(members);
        allPlayers.addAll(recruit);
        allPlayers.add(leader);
        return allPlayers;
    }

    @Override
    public Set<FPlayer> getAllPlayers() {
        return fPlayers;
    }
}
