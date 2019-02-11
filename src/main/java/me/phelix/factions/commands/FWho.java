package me.phelix.factions.commands;

import me.phelix.factions.Main;
import me.phelix.factions.utils.FPlayer;
import me.phelix.factions.utils.FSubCommand;
import me.phelix.factions.utils.Faction;
import me.phelix.factions.utils.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class FWho extends FSubCommand {

    private Main plugin;
    public FWho(Main plugin){
        super("who", Role.NONE, false);
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if(args.length == 1){
            if(!fme.hasFaction()){
                fme.sendMessage(ChatColor.RED + "You don't have a faction!");
                return;
            }
            who(fme, myFaction);
        } else if(args.length == 2){
            who(fme, plugin.getMemoryFactions().getFactionByName(args[1]));
        }
    }

    private void who(FPlayer fPlayer, Faction faction){
        long ms = System.currentTimeMillis();
        List<String> players = new ArrayList<>();
        for(FPlayer fPlayer1 : faction.getAllPlayers()){
            if(!fPlayer1.isOnline()){
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(fPlayer1.getName());
                players.add(offlinePlayer.getName());
            } else {
                players.add(fPlayer1.getName());
            }

        }
        fPlayer.sendMessage(ChatColor.GOLD + "Leader: " + ChatColor.WHITE + faction.getOwner());
        fPlayer.sendMessage(ChatColor.GOLD + "Players: " + ChatColor.WHITE + players.toString().replace("[", "").replace("]", ""));
        fPlayer.sendMessage(System.currentTimeMillis() - ms);
    }

}
