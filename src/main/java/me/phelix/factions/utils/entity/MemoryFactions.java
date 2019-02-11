package me.phelix.factions.utils.entity;

import me.phelix.factions.Main;
import me.phelix.factions.utils.FPlayer;
import me.phelix.factions.utils.Faction;
import me.phelix.factions.utils.FactionFile;
import me.phelix.factions.utils.Factions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoryFactions extends Factions {
    public Map<String, Faction> factions = new HashMap<>();

    @Override
    public Faction getFactionByName(String name) {
        return factions.get(name);
    }

    @Override
    public Faction createFaction(Main plugin, String name, FPlayer player) {
        FactionFile factionFile = new FactionFile(plugin, name);
        factionFile.createFile(player);
        Faction faction = new MemoryFaction(plugin, name);
        player.checkConfig();
        faction.checkConfig();
        factions.put(faction.getFName(), faction);
        return faction;
    }

    @Override
    public void removeFaction(Main plugin, String id) {
        File file = new File(plugin.getDataFolder() + "/Factions", id + ".yml");
        file.delete();
        factions.remove(id);
    }

    @Override
    public ArrayList<Faction> getAllFactions() {
        return new ArrayList<>(factions.values());
    }

}
