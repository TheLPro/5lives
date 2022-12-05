package me.thelpro.events;

import me.thelpro.Lives5;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

public class playerDeathEvent implements Listener {

    public static @NotNull Lives5 plugin = Lives5.plugin;
    public static FileConfiguration config = Lives5.plugin.getConfig();

    @EventHandler
    public void playerDeathEvent(EntityDeathEvent e) {

        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (!config.contains(player.getName())) {
                config.addDefault(player.getName(), 4);
                plugin.saveConfig();
                return;
            }
            if (config.getInt(player.getName()) <= 0) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "essentials:tempban " + player.getName() + " 24h You died 5 times!");
                config.set(player.getName(), 3);
                plugin.saveConfig();
            }
            else {
                config.set(player.getName(), config.getInt(player.getName()) - 1);
                plugin.saveConfig();
            }
            if (player.getKiller() != null) {
                config.set(player.getName(), config.getInt(player.getName()) - 1);
                config.set(player.getKiller().getName(), config.getInt(player.getKiller().getName()) - 1);
                plugin.saveConfig();
                player.getKiller().sendMessage("You have killed " + ChatColor.BLUE + player.getName() + " . Gained " + ChatColor.RED + ChatColor.BOLD + " one life" + ChatColor.RESET + ".");
            }
        }
    }

}
