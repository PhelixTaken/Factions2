package me.phelix.factions.commands;

import me.phelix.factions.Main;
import me.phelix.factions.utils.FSubCommand;
import me.phelix.factions.utils.Faction;
import me.phelix.factions.utils.Factions;
import me.phelix.factions.utils.Role;
import me.phelix.factions.utils.entity.MemoryFactions;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class FCreate extends FSubCommand {

    private Main plugin;
    public FCreate(Main plugin){
        super("create", Role.NONE, false);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 2){
            if(!fme.hasFaction()){
                Faction faction = plugin.getMemoryFactions().createFaction(plugin, args[1], fme);
                faction.setLeader(fme);
                fme.setRole(Role.LEADER);
                fme.sendMessage("Successfully created the faction!");
            } else {
                fme.sendMessage(ChatColor.RED + "You are already in a faction!");
            }
        }
    }
}
