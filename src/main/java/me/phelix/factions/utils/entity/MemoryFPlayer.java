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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MemoryFPlayer implements FPlayer {

    private String name;
    private String id;
    private File file;
    private FileConfiguration configuration;
    private String factionId;
    private Map<FPlayer, Role> roleMap = new HashMap<>();
    private Main plugin;

    public MemoryFPlayer(Main plugin, String id){
        this.plugin = plugin;
        this.id = id;
        if(!hasFaction()){
            setRole(Role.NONE);
        }
    }

    @Override
    public Faction getFaction() {
        if(this.factionId == null){
            this.factionId = "0";
        }
        return plugin.getMemoryFactions().getFactionByName(this.getFName());
    }

    @Override
    public boolean hasFaction() {
        return configuration != null;
    }

    @Override
    public void setFaction(Faction faction) {
        Faction oldFaction = this.getFaction();
        if(oldFaction != null){
            oldFaction.removeFPlayer(this);
        }
        faction.addFPlayer(this);
        this.factionId = faction.getId();
    }

    @Override
    public Role getRole() {
        return roleMap.get(this);
    }

    @Override
    public void setRole(Role role) {
        roleMap.put(this, role);
    }

    @Override
    public String getRolePrefix() {
        return getRole().getPrefix();
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
    public File getFile(){
        return file;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean isOnline() {
        return getPlayer().isOnline();
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(UUID.fromString(this.getId()));
    }

    @Override
    public void sendMessage(String string) {
        getPlayer().sendMessage(string);
    }

    @Override
    public void sendMessage(int i) {
        getPlayer().sendMessage(String.valueOf(i));
    }

    @Override
    public void sendMessage(double d) {
        getPlayer().sendMessage(String.valueOf(d));
    }

    @Override
    public void sendMessage(float f) {
        getPlayer().sendMessage(String.valueOf(f));
    }

    @Override
    public void sendMessage(long l) {
        getPlayer().sendMessage(String.valueOf(l));
    }

    @Override
    public void checkConfig() {
        File[] factions = new File(plugin.getDataFolder() + "/Factions").listFiles();
        if (factions == null) return;
        for (File file : factions) {
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            List<String> members = configuration.getStringList("Members");
            List<String> coleader = configuration.getStringList("CO-Leader");
            List<String> recruit = configuration.getStringList("Recruit");
            List<String> moderator = configuration.getStringList("Moderators");
            if (members.contains(getPlayer().getUniqueId().toString()) || coleader.contains(getPlayer().getUniqueId().toString()) || recruit.contains(getPlayer().getUniqueId().toString()) || moderator.contains(getPlayer().getUniqueId().toString()) || configuration.getString("Leader").contains(getPlayer().getUniqueId().toString())) {
                this.file = file;
                this.configuration = configuration;
            }
        }
    }

    @Override
    public String getFactionId() {
        return this.factionId;
    }

    @Override
    public void setFactionId(String id) {
        this.factionId = id;
    }

    @Override
    public String getName() {
        return getPlayer().getName();
    }
}
