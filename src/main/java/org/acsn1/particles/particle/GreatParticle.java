package org.acsn1.particles.particle;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.acsn1.particles.Particles;
import org.acsn1.particles.events.custom.ParticleEquipEvent;
import org.acsn1.particles.events.custom.ParticleTerminateEvent;
import org.acsn1.particles.utils.ChatUtils;
import org.acsn1.particles.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class GreatParticle {

    private final String name;
    private final Particle particle;
    private final ParticleStructure particleStructure;
    private final Material material;
    private final String permission;
    private Particle.DustOptions options;
    private List<Player> players;
    private int taskId;

    public GreatParticle(String name, Particle particle, ParticleStructure particleStructure, Material material, String permission) {
        this.name = name;
        this.particle = particle;
        this.particleStructure = particleStructure;
        this.material = material;
        this.permission = permission;
        this.players = Lists.newArrayList();
        this.init();
    }

    public GreatParticle setOptions(Particle.DustOptions options) {
        this.options = options;
        return this;
    }

    public ItemStack buildItem() {
        return new ItemBuilder(material, 1).setName(ChatUtils.color("&5"+name)).hideAttributes().hideEnchants().build();
    }

    public void apply(@NotNull Player... player) {
        Arrays.stream(player).forEach(p-> {
            ParticleEquipEvent equipEvent = new ParticleEquipEvent(p, this);
            Bukkit.getPluginManager().callEvent(equipEvent);

            players.add(equipEvent.getPlayer());
        });
    }

    public void remove(@NotNull Player... player) {
        Arrays.stream(player).forEach(p-> {
            ParticleTerminateEvent terminateEvent = new ParticleTerminateEvent(p, this);
            Bukkit.getPluginManager().callEvent(terminateEvent);

            players.remove(terminateEvent.getPlayer());
        });
    }

    private void init() {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Particles.getInstance(), ()-> {

            if(this.players.isEmpty()) return;
            players.forEach(player-> {
                Location above = player.getEyeLocation().clone().add(0,0.5,0);
                spawnParticle(player, above, 0.5);
            });


        }, 0L, 8L);
    }

    private void spawnParticle(Player player, Location location, double radius) {

        switch (particleStructure) {

            case CIRCLE:
                for(int i = 0; i <= 90; i++) {
                    double x = location.getX() + Math.cos(i) * radius;
                    double z = location.getZ() + Math.sin(i) * radius;

                    Location loc = new Location(location.getWorld(), x, location.getY(), z);

                    if (options != null) {
                        player.spawnParticle(particle, loc, 1, options);
                        continue;
                    }
                    player.spawnParticle(particle, loc, 1);
                }
                break;

            case SELF:

                player.spawnParticle(particle, location, 1);
                break;
        }


    }

    public void terminate() {
        Bukkit.getScheduler().cancelTask(taskId);
    }


}
