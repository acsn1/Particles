package org.acsn1.particles.utils;

import lombok.Getter;

@Getter
public enum CommandUtils {

    PARTICLE_OPEN("particle", "particle.open")
    ;

    private final String name;
    private final String permission;

    CommandUtils(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

}
