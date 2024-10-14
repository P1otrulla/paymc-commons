package dev.piotrulla.paymc.commons.inventory.serializer;

import dev.piotrulla.paymc.commons.inventory.DefaultInventory;
import dev.piotrulla.paymc.commons.inventory.DefaultItem;
import dev.piotrulla.paymc.commons.inventory.DefaultPaginatedInventory;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.checkerframework.checker.nullness.qual.NonNull;

public class DefaultPaginatedInventorySerializer implements ObjectSerializer<DefaultPaginatedInventory> {

    @Override
    public boolean supports(@NonNull Class<? super DefaultPaginatedInventory> type) {
        return type.isAssignableFrom(DefaultPaginatedInventory.class);
    }

    @Override
    public void serialize(@NonNull DefaultPaginatedInventory paginatedInventory, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("inventory", paginatedInventory.inventory());
        data.add("pageSize", paginatedInventory.pageSize());
        data.add("nextPage", paginatedInventory.nextPage());
        data.add("previousPage", paginatedInventory.previousPage());
    }

    @Override
    public DefaultPaginatedInventory deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new DefaultPaginatedInventory(
                data.get("inventory", DefaultInventory.class),
                data.get("pageSize", Integer.class),
                data.get("nextPage", DefaultItem.class),
                data.get("previousPage", DefaultItem.class)
        );
    }
}
