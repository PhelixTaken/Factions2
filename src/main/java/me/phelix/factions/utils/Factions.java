package me.phelix.factions.utils;

import me.phelix.factions.Main;

import java.util.ArrayList;

public abstract class Factions {

    public abstract Faction getFactionByName(String name);

    public abstract Faction createFaction(Main plugin, String name, FPlayer player);

    public abstract void removeFaction(Main main, String id);

    public abstract ArrayList<Faction> getAllFactions();



}
