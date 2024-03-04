package org.acsn1.particles.events.custom;

import lombok.Getter;
import lombok.Setter;
import org.acsn1.particles.particle.GreatParticle;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter @Setter
public class ParticleEquipEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private GreatParticle particle;

    public ParticleEquipEvent(@NotNull Player player, @NotNull GreatParticle particle) {
        this.player = player;
        this.particle = particle;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


}
