package org.acsn1.particles.events.bukkit;

import org.acsn1.particles.Particles;
import org.acsn1.particles.particle.GreatParticle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        GreatParticle particle = Particles.getInstance().getParticleMenu().getParticle(player);
        if(particle==null) return;
        particle.remove(player);
    }

}
