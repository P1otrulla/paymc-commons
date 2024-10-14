package dev.piotrulla.paymc.commons.shared;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ItemUtil {

    public static boolean giveItem(Player player, ItemStack... itemStacks) {
        for (ItemStack itemStack : itemStacks) {
            if (player.getInventory().firstEmpty() == -1) {
                player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                return true;
            }
            player.getInventory().addItem(itemStack);
        }
        return false;
    }

    public static boolean removeItem(Player player, ItemStack... itemStacks) {
        for (ItemStack itemStack : itemStacks) {
            if (!player.getInventory().containsAtLeast(itemStack, itemStack.getAmount())) {
                return false;
            }
            player.getInventory().removeItem(itemStack);
        }
        return true;
    }
}