package dev.piotrulla.paymc.commons.inventory;

import dev.piotrulla.paymc.commons.intrange.IntRange;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.Map;

import static dev.piotrulla.paymc.commons.MiniMessageHolder.MINI_MESSAGE;

public class DefaultInventory {

    private String title;
    private int rows;

    private DefaultItem close;
    private DefaultItem back;

    private Map<IntRange, ItemStack> decorations;

    public DefaultInventory(String title, int rows, ItemStack closeItem, int closeSlot, ItemStack backItem, int backSlot, Map<IntRange, ItemStack> decorations) {
        this.title = title;
        this.rows = rows;
        this.close = new DefaultItem(closeItem, closeSlot);
        this.back = new DefaultItem(backItem, backSlot);
        this.decorations = decorations;
    }

    public DefaultInventory(String title, int rows, DefaultItem closeItem, DefaultItem backItem, Map<IntRange, ItemStack> decorations) {
        this.title = title;
        this.rows = rows;
        this.close = closeItem;
        this.back = backItem;
        this.decorations = decorations;
    }

    public String title() {
        return this.title;
    }

    public Component titleComponent() {
        return MINI_MESSAGE.deserialize(this.title);
    }

    public int rows() {
        return this.rows;
    }

    public DefaultItem closeItem() {
        return this.close;
    }

    public DefaultItem backItem() {
        return this.back;
    }

    private int totalSlots() {
        return this.rows * 9;
    }

    public boolean isValidItem(DefaultItem item) {
        if (item == null) {
            return false;
        }
        int slot = item.slot();

        return slot >= 0 && slot < totalSlots() - 1;
    }

    public Map<IntRange, ItemStack> decorations() {
        return Collections.unmodifiableMap(this.decorations);
    }

}
