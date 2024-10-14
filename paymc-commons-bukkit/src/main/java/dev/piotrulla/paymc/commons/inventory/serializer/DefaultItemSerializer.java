package dev.piotrulla.paymc.commons.inventory.serializer;

import dev.piotrulla.paymc.commons.inventory.DefaultItem;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class DefaultItemSerializer implements ObjectSerializer<DefaultItem> {

    @Override
    public boolean supports(@NonNull Class<? super DefaultItem> type) {
        return type.isAssignableFrom(DefaultItem.class);
    }

    @Override
    public void serialize(@NonNull DefaultItem defaultItem, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("slot", defaultItem.slot());
        data.add("item", defaultItem.item());
    }

    @Override
    public DefaultItem deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new DefaultItem(data.get("item", ItemStack.class), data.get("slot", Integer.class));
    }
}
