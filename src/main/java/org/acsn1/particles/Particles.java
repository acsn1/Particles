package org.acsn1.particles;

import lombok.Getter;
import org.acsn1.particles.commands.impl.CommandProvider;
import org.acsn1.particles.events.ListenerProvider;
import org.acsn1.particles.file.FileContainer;
import org.acsn1.particles.gui.ParticleMenu;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Particles extends JavaPlugin {

    @Getter private static Particles instance;
    private FileContainer fileContainer;
    private ParticleMenu particleMenu;
    private ListenerProvider listenerProvider;
    private CommandProvider commandProvider;

    @Override
    public void onEnable() {
        instance = this;
        fileContainer = new FileContainer();
        particleMenu = new ParticleMenu();
        listenerProvider = new ListenerProvider();
        commandProvider = new CommandProvider();
    }

    @Override
    public void onDisable() {

        particleMenu.terminateAll();

    }
}
