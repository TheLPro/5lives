package me.thelpro.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class livesTab implements TabCompleter {
    List<String> arguments = new ArrayList<String>();

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("increase");
            arguments.add("decrease");
        }
        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            if (sender.isOp()) {
                return result;
            } else {
                return null;
            }
        }

        return null;
    }
}
