package org.acsn1.particles.gui;

import com.google.common.collect.Sets;
import org.acsn1.particles.Particles;
import org.acsn1.particles.gui.impl.Menu;
import org.acsn1.particles.particle.GreatParticle;
import org.acsn1.particles.particle.ParticleStructure;
import org.acsn1.particles.utils.ChatUtils;
import org.acsn1.particles.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ParticleMenu extends Menu implements Listener {

    private final Set<GreatParticle> particles = Sets.newHashSet();

    public ParticleMenu() {
        super("&d&lParticles", 45);

        // Adding particles in the menu
        particles.add(new GreatParticle("RedStone", Particle.REDSTONE, ParticleStructure.CIRCLE, Material.REDSTONE, "particle.redstone")
                .setOptions(new Particle.DustOptions(Color.fromRGB(244, 57, 57), 1)));

        particles.add(new GreatParticle("Rain", Particle.DRIP_WATER, ParticleStructure.CIRCLE, Material.WATER_BUCKET, "particle.rain"));
        particles.add(new GreatParticle("Lava", Particle.DRIP_LAVA, ParticleStructure.CIRCLE, Material.LAVA_BUCKET, "particle.lava"));
        particles.add(new GreatParticle("Heart", Particle.HEART, ParticleStructure.SELF, Material.POPPY, "particle.heart"));

        particles.forEach(particle-> {
            super.addItem(particle.buildItem());
        });

        super.setItem(new ItemBuilder(Material.BARRIER, 1).setName("&cReset").hideEnchants().hideAttributes().build(), 31);

        // Fill the last line
        super.fillLastLine(Material.BLACK_STAINED_GLASS_PANE);

        Bukkit.getPluginManager().registerEvents(this, Particles.getInstance());
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player player)) return;
        if(event.getView().getTopInventory() != super.getInventory()) return;
        if(event.getClickedInventory() != super.getInventory()) return;
        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if(item==null || item.getType() == Material.AIR) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!meta.hasDisplayName()) return;

        switch(event.getSlot()) {
            default:
                String particleName = ChatColor.stripColor(meta.getDisplayName());
                GreatParticle particle = getParticle(particleName);
                if(particle==null) return;

                if(!player.hasPermission(particle.getPermission())) {
                    player.sendMessage(ChatUtils.color("&cYou do not have &e&o"+particle.getPermission() + " &cpermission!"));
                    return;
                }

                if(getParticle(player)!=null) {
                    player.sendMessage(ChatUtils.color("&cYou have already an active particle!"));
                    return;
                }

                particle.apply(player);
                player.sendMessage(ChatUtils.color("&aYou have enabled &e&o"+particle.getName() + " &aparticle on you!"));
                break;
            case 31:
                if(item.getType() != Material.BARRIER) return;
                particle = getParticle(player);
                if(particle==null) {
                    player.sendMessage(ChatUtils.color("&cYou do not have an active particle!"));
                    return;
                }

                particle.remove(player);
                player.sendMessage(ChatUtils.color("&cYour active particle &e&o"+particle.getName() + " &chas been removed from you!"));

                break;
        }

    }

    public void terminateAll() {
        particles.forEach(GreatParticle::terminate);
    }

    public GreatParticle getParticle(@NotNull Player player) {
        for(GreatParticle particle : particles) {
            if(particle.getPlayers().contains(player)) {
                return particle;
            }
        }
        return null;
    }

    public GreatParticle getParticle(@NotNull String name) {
        for(GreatParticle particle : particles) {
            if(particle.getName().equalsIgnoreCase(name)) {
                return particle;
            }
        }
        return null;
    }

}
