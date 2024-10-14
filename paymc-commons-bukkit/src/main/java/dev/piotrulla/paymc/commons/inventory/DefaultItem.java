package dev.piotrulla.paymc.commons.inventory;

import com.eternalcode.commons.adventure.AdventureUtil;
import dev.piotrulla.paymc.commons.shared.ItemStackBuilder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Collectors;

import static dev.piotrulla.paymc.commons.MiniMessageHolder.MINI_MESSAGE;

public class DefaultItem {

    private ItemStack item;
    private int slot;

    public DefaultItem(ItemStack item, int slot) {
        this.item = item;
        this.slot = slot;
    }

    public ItemStack item() {
        ItemStackBuilder from = new ItemStackBuilder(this.item.clone());

        ItemMeta meta = this.item.getItemMeta();

        if (meta == null) {
            return from.build();
        }

        if (meta.hasDisplayName()) {
            from.name(AdventureUtil.resetItalic(MINI_MESSAGE.deserialize(meta.getDisplayName())));
        }

        if (meta.hasLore()) {
            from.lore(meta.getLore()
                    .stream()
                    .map(line -> AdventureUtil.resetItalic(MINI_MESSAGE.deserialize(line)))
                    .collect(Collectors.toList())
            );
        }

        return from.build();
    }

    public int slot() {
        return this.slot;
    }
}
