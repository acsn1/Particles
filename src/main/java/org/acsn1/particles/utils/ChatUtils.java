package org.acsn1.particles.utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    // Simple color method
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
