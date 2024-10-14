package dev.piotrulla.paymc.commons.shared;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.logging.Logger;

public final class ItemPersistentDataUtil {

    private static final Logger LOGGER = Logger.getLogger(ItemPersistentDataUtil.class.getName());

    private ItemPersistentDataUtil() {
    }

    public static boolean hasData(ItemStack itemStack, NamespacedKey namespacedKey) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        return container.has(namespacedKey, PersistentDataType.INTEGER);
    }

    public static int getValue(ItemStack itemStack, NamespacedKey namespacedKey) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        return container.get(namespacedKey, PersistentDataType.INTEGER);
    }

    public static void addData(ItemStack itemStack, NamespacedKey namespacedKey, int value) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        container.set(namespacedKey, PersistentDataType.INTEGER, value);

        itemStack.setItemMeta(itemMeta);

    }

    public static ItemStack removeData(ItemStack itemStack, NamespacedKey namespacedKey) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            LOGGER.severe("Nie można usunąć PersistentDataType z itemstack'a");
            LOGGER.severe(itemStack.toString());

            return itemStack;
        }

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        container.remove(namespacedKey);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
