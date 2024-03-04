package org.acsn1.particles.gui.impl;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.acsn1.particles.utils.ChatUtils;
import org.acsn1.particles.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Getter @Setter
public class Menu {

    private final String title;
    private final int size;
    private final Map<Integer, ItemStack> items;
    private final Inventory inventory;
    private int currentPage = 1;

    public Menu(String title, int size) {
        this.title = ChatUtils.color(title);
        this.size = size;
        this.items = Maps.newHashMap();
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    /*
     * Add an item to the first available slot
     */
    public Menu addItem(ItemStack item) {
        inventory.addItem(item);
        return this;
    }

    /*
     * Add Item to a specific slot
     */
    public Menu setItem(ItemStack item, int slot) {
        inventory.setItem(slot,item);
        return this;
    }

    /*
     * Fill the last line with the specified material
     */
    public Menu fillLastLine(Material material) {
        for(int i = (size-9); i < size; i++) setItem(new ItemBuilder(material, 1).hideAttributes().hideEnchants().build(), i);
        return this;
    }

    /*
     * Show the specified page of the UI
     */
    public Menu show(Player player, int page) {

        items.forEach((k,v)-> {
            setItem(v,k);
        });

        player.openInventory(inventory);
        return this;
    }



}
