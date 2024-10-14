package dev.piotrulla.paymc.commons.inventory.serializer;

import dev.piotrulla.paymc.commons.intrange.IntRange;
import dev.piotrulla.paymc.commons.inventory.DefaultInventory;
import dev.piotrulla.paymc.commons.inventory.DefaultItem;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class DefaultInventorySerializer implements ObjectSerializer<DefaultInventory> {

    @Override
    public boolean supports(@NonNull Class<? super DefaultInventory> type) {
        return type.isAssignableFrom(DefaultInventory.class);
    }

    @Override
    public void serialize(@NonNull DefaultInventory defaultInventory, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("title", defaultInventory.title());
        data.add("rows", defaultInventory.rows());
        data.add("close", defaultInventory.closeItem());
        data.add("back", defaultInventory.backItem());
        data.add("decorations", defaultInventory.decorations());
    }

    @Override
    public DefaultInventory deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new DefaultInventory(
                data.get("title", String.class),
                data.get("rows", Integer.class),
                data.get("close", DefaultItem.class),
                data.get("back", DefaultItem.class),
                data.getAsMap("decorations", IntRange.class, ItemStack.class)
        );
    }
}
