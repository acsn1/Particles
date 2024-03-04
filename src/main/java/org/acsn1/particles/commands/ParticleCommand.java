package org.acsn1.particles.commands;

import com.google.common.collect.Lists;
import org.acsn1.particles.Particles;
import org.acsn1.particles.commands.impl.GreatCommand;
import org.acsn1.particles.particle.GreatParticle;
import org.acsn1.particles.utils.ChatUtils;
import org.acsn1.particles.utils.CommandUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ParticleCommand extends GreatCommand {


    public ParticleCommand() {
        super(CommandUtils.PARTICLE_OPEN.getName(), CommandUtils.PARTICLE_OPEN.getPermission());
    }

    private void sendUsage(Player player) {
        player.sendMessage(ChatUtils.color("&c/particle &8- &fView all available particles."));
        player.sendMessage(ChatUtils.color("&c/particle stop &8- &fTerminate your active particle."));
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage(ChatUtils.color("&cYou must be a player to use this command!"));
            return;
        }

        if(args.length == 0) {
            // OPEN GUI
            Particles.getInstance().getParticleMenu().show(player, 1);
        }
        if(args.length == 1) {
            if(!(args[0].equalsIgnoreCase("stop"))) {
                sendUsage(player);
                return;
            }

            GreatParticle particle = Particles.getInstance().getParticleMenu().getParticle(player);
            if(particle==null) {
                player.sendMessage(ChatUtils.color("&cYou do not have an active particle!"));
                return;
            }

            particle.remove(player);
            player.sendMessage(ChatUtils.color("&cYour active particle &e&o"+particle.getName() + " &chas been removed from you!"));

        }
    }

    @Override
    public List<String> onTabCompleteList(String[] args) {
        return Lists.newArrayList();
    }
}
