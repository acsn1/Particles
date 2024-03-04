package org.acsn1.particles.commands.impl;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;

import java.util.List;

@Getter @Setter
public abstract class GreatCommand {

    private final String name;
    private final String permission;

    public GreatCommand(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }


    public abstract void executeCommand(CommandSender sender, String[] args);
    public abstract List<String> onTabCompleteList(String[] args);


}
