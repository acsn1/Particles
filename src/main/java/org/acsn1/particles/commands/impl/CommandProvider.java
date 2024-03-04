package org.acsn1.particles.commands.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.acsn1.particles.Particles;
import org.acsn1.particles.commands.ParticleCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;
import java.util.Set;

public class CommandProvider implements CommandExecutor, TabCompleter {

    private final Set<GreatCommand> commands = Sets.newHashSet();

    public CommandProvider() {

        commands.add(new ParticleCommand());

        commands.forEach(cmd-> Particles.getInstance().getCommand(cmd.getName()).setExecutor(this));

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        for(GreatCommand greatCommand : commands) {
            if(greatCommand.getName().equalsIgnoreCase(command.getName())) {
                greatCommand.executeCommand(sender, args);
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        for(GreatCommand greatCommand : commands) {
            if(greatCommand.getName().equalsIgnoreCase(command.getName())) {
                return greatCommand.onTabCompleteList(args);
            }
        }

        return Lists.newArrayList();
    }
}
