package org.acsn1.particles.file;

import org.acsn1.particles.Particles;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileWrapper {

    private final String dir;
    private final String name;
    private File file;
    private YamlConfiguration config;

    public FileWrapper(String dir, String name) {
        this.dir = dir;
        this.name = name;

        File folder = new File(Particles.getInstance().getDataFolder() + File.separator + this.dir);
        if (!folder.exists()) folder.mkdirs();

        this.file = new File(folder.getPath(), name + ".yml");
        if (!this.file.exists()) {
            this.createFile(this.file);
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);

    }

    public void createFile(File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            InputStream in = Particles.getInstance().getResource(this.name + ".yml");

            int i;
            try {
                while((i = in.read()) != -1) {
                    out.write(i);
                }
            } finally {
                if (in != null) {
                    in.close();
                }

            }
        } catch (IOException var10) {
            Particles.getInstance().getLogger().severe("Failed to create " + this.name + ".yml");
        }

    }

    public void reload(Plugin plugin) {
        File folder = new File(this.dir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        this.file = new File(folder.getPath(), this.name + ".yml");
        if (!this.file.exists()) {
            this.createFile(this.file);
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

}
