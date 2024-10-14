package dev.piotrulla.paymc.commons.inventory.serializer;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import org.checkerframework.checker.nullness.qual.NonNull;

public class DefaultInventorySerdesPack implements OkaeriSerdesPack {

    @Override
    public void register(@NonNull SerdesRegistry registry) {
        registry.register(new DefaultInventorySerializer());
        registry.register(new DefaultItemSerializer());
        registry.register(new DefaultPaginatedInventorySerializer());
    }
}
