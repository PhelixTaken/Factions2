package me.phelix.factions.commands;

import me.phelix.factions.Main;
import me.phelix.factions.utils.FSubCommand;
import me.phelix.factions.utils.entity.MemoryFPlayers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class FMainCommand implements CommandExecutor {

    private final Set<FSubCommand> commands = new HashSet<>();
    private Main plugin;
    public FMainCommand(Main plugin) {
        this.plugin = plugin;
        commands.add(new FCreate(plugin));
        commands.add(new FWho(plugin));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("f")) {
            if (!(sender instanceof Player)) {
                System.out.println("You must be a player to perform this action!");
                return true;
            }
            if (args.length >= 1) {
                commands.stream()
                        .filter(subcmd0 -> subcmd0.getCmd().equalsIgnoreCase(args[0]))
                        .filter(subcmd1 -> subcmd1.setCommandSender(sender, plugin))
                        .filter(subcmd2 -> subcmd2.canExecute(plugin, plugin.getMemoryFPlayers().getByPlayer((Player) sender)))
                        .findFirst()
                        .ifPresent(subcmd2 -> subcmd2.execute(sender, args));
            }
        }
        return true;
    }

}
