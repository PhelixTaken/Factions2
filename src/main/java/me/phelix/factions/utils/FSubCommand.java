package me.phelix.factions.utils;

import me.phelix.factions.Main;
import me.phelix.factions.utils.entity.MemoryFPlayers;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class FSubCommand {

    private final String cmd;
    private final Role role;
    private final boolean facNeeded;
    public FPlayer fme;
    public Faction myFaction;

    public FSubCommand(String cmd, Role roles, boolean facNeeded) {
        this.cmd = cmd;
        this.role = roles;
        this.facNeeded = facNeeded;
    }

    public boolean setCommandSender(CommandSender sender, Main plugin) {
        if (sender instanceof Player) {
            this.fme = plugin.getMemoryFPlayers().getByPlayer((Player) sender);
            if(fme.hasFaction()){
                this.myFaction = fme.getFaction();
            }
            return true;
        } else {
            this.fme = null;
            this.myFaction = null;
        }
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public boolean canExecute(Main plugin, FPlayer fPlayer) {
        if (role == null) return true;
        if (facNeeded && !fme.hasFaction()) {
            fme.sendMessage(ChatColor.RED + "You must be in a faction to do that!");
            return false;
        }
        if (!facNeeded && !fme.hasFaction()) {
            return true;
        }
        if (!fPlayer.getRole().isAtleast(role))
            fPlayer.sendMessage(ChatColor.RED + "You don't have the permission to do that!");
        return fPlayer.getRole().isAtleast(role);
    }

    public String getCmd() {
        return cmd;
    }

}
