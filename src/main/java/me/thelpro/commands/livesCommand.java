package me.thelpro.commands;

import me.thelpro.Lives5;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class livesCommand implements CommandExecutor {

    public static @NotNull Lives5 plugin = Lives5.plugin;
    public static FileConfiguration config = plugin.getConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        if (!(args.length == 0)) {
            if (player.isOp()) {
                if (args[0].equals("decrease")) {
                    if (!config.contains(args[1])) {
                        config.addDefault(args[1], 4);
                        plugin.saveConfig();
                    } else {
                        config.set(args[1], config.getInt(args[1]) - 1);
                        plugin.saveConfig();
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Removed one life from &3&l" + args[1] + "&r. Now has &4&l" + config.get(args[1]) + "&r lives."));
                    return true;
                }
                if (args[0].equals("increase") || args.length == 2) {
                    if (!config.contains(args[1])) {
                        config.addDefault(args[1], 6);
                        plugin.saveConfig();
                    } else {
                        config.set(args[1], config.getInt(args[1]) + 1);
                        plugin.saveConfig();

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Added one life for &3&l" + args[1] + "&r. Now has &4&l" + config.get(args[1]) + "&r lives."));
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to preform this command.");
                return true;
            }
        }
        } else {
            if (config.getInt(player.getName()) == 1) {
                player.sendMessage(ChatColor.RED + "You're on your last life! Better be careful!");
                return true;
            }
            player.sendMessage("You have " + ChatColor.DARK_AQUA + ChatColor.BOLD + config.get(player.getName()) + ChatColor.RESET + " lives left");
            return true;
        }

        return true;
    }
}
