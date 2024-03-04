package org.acsn1.particles.events;

import com.google.common.collect.Sets;
import org.acsn1.particles.Particles;
import org.acsn1.particles.events.bukkit.JoinListener;
import org.acsn1.particles.events.bukkit.ParticleListener;
import org.acsn1.particles.events.bukkit.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.Collections;
import java.util.Set;

public class ListenerProvider {

    private final Set<Listener> listeners = Sets.newHashSet();

    public ListenerProvider() {

        addListener(new JoinListener(), new QuitListener(), new ParticleListener());

        listeners.forEach(event-> Bukkit.getPluginManager().registerEvents(event, Particles.getInstance()));

    }


    private void addListener(Listener... listener) {
        Collections.addAll(listeners, listener);
    }
}
