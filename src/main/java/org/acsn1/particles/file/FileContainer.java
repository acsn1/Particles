package org.acsn1.particles.file;

import lombok.Getter;
import lombok.Setter;
import org.acsn1.particles.config.Config;

@Getter @Setter
public class FileContainer {

    private final Config config;

    public FileContainer() {
        config = new Config();
    }

}
