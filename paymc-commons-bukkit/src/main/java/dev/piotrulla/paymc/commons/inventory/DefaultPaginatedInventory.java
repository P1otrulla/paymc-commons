package dev.piotrulla.paymc.commons.inventory;

import dev.piotrulla.paymc.commons.intrange.IntRange;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class DefaultPaginatedInventory {

    private DefaultInventory inventory;

    private int pageSize;

    private DefaultItem nextPage;
    private DefaultItem previousPage;

    public DefaultPaginatedInventory(
            String title, int rows, int pageSize, ItemStack closeItem, int closeSlot,
            ItemStack backItem, int backSlot, ItemStack nextPageItem, int nextPageSlot,
            ItemStack previousPageItem, int previousPageSlot, Map<IntRange, ItemStack> decorations
    ) {
        this.inventory = new DefaultInventory(
                title, rows, closeItem, closeSlot,
                backItem, backSlot, decorations
        );
        this.pageSize = pageSize;
        this.nextPage = new DefaultItem(nextPageItem, nextPageSlot);
        this.previousPage = new DefaultItem(previousPageItem, previousPageSlot);
    }

    public DefaultPaginatedInventory(DefaultInventory inventory, int pageSize, DefaultItem nextPage, DefaultItem previousPage) {
        this.inventory = inventory;
        this.pageSize = pageSize;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
    }

    public DefaultInventory inventory() {
        return this.inventory;
    }

    public int pageSize() {
        return this.pageSize;
    }

    public DefaultItem nextPage() {
        return this.nextPage;
    }

    public DefaultItem previousPage() {
        return this.previousPage;
    }
}
