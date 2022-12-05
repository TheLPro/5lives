package me.thelpro;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "5lives";
    }

    @Override
    public @NotNull String getAuthor() {
        return "TheLPro";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {

        Lives5 plugin = Lives5.plugin;
        FileConfiguration config = plugin.getConfig();

        if (params.equals("deaths")) {
            if (!config.contains(player.getName())) {
                config.addDefault(player.getName(), 4);
                plugin.saveConfig();
                return "5";
            } else {
                return config.getString(player.getName());
            }
        }
        return "null";
    }
}
