package me.thelpro;

import me.thelpro.commands.livesCommand;
import me.thelpro.commands.livesTab;
import me.thelpro.events.playerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Lives5 extends JavaPlugin {

    public static Lives5 plugin;

    @Override
    public void onEnable() {

        plugin = this;
        getConfig().options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new playerDeathEvent(), this);
        getCommand("lives").setExecutor(new livesCommand());
        getCommand("lives").setTabCompleter(new livesTab());
        new me.thelpro.Placeholders().register();

    }
}
