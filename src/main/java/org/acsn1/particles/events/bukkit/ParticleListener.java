package org.acsn1.particles.events.bukkit;

import org.acsn1.particles.Particles;
import org.acsn1.particles.events.custom.ParticleEquipEvent;
import org.acsn1.particles.events.custom.ParticleTerminateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ParticleListener implements Listener {

    @EventHandler
    public void onEquip(ParticleEquipEvent event) {
        Particles.getInstance().getLogger().info(event.getPlayer().getName() + " has equipped the particle " + event.getParticle().getName());
    }

    @EventHandler
    public void onRemove(ParticleTerminateEvent event) {
        Particles.getInstance().getLogger().info(event.getPlayer().getName() + " has removed the particle " + event.getParticle().getName());
    }

}
