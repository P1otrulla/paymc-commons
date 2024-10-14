package dev.piotrulla.paymc.commons.shared;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ItemStackBuilder {

    private final ItemStack item;

    public ItemStackBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemStackBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
    }

    public ItemStackBuilder(ItemStack itemStack) {
        this.item = itemStack;
    }

    public ItemStackBuilder(ItemStack itemStack, int amount) {
        itemStack.setAmount(amount);
        this.item = itemStack;
    }

    public ItemStackBuilder name(String name) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder name(Component name) {
        ItemMeta meta = this.item.getItemMeta();
        meta.displayName(name);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder amount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemStackBuilder owner(String owner) {
        SkullMeta meta = (SkullMeta) this.item.getItemMeta();
        meta.setOwner(owner);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder addLore(String lore) {
        ItemMeta meta = this.item.getItemMeta();
        List<String> list = new ArrayList<>();
        if (meta.hasLore()) {
            list = new ArrayList<>(meta.getLore());
        }
        list.add(lore);
        meta.setLore(list);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder addLore(List<String> loreList) {
        ItemMeta meta = this.item.getItemMeta();
        List<String> list = new ArrayList<>();

        if (meta.hasLore()) {
            list = new ArrayList<>(meta.getLore());
        }

        list.addAll(loreList);
        meta.setLore(list);
        item.setItemMeta(meta);

        return this;
    }

    public ItemStackBuilder setLore_(List<String> lore) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder lore(Component lore) {
        ItemMeta meta = this.item.getItemMeta();
        List<Component> list = new ArrayList<>();
        if (meta.hasLore()) {
            list = new ArrayList<>(meta.lore());
        }
        list.add(lore);
        meta.lore(list);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder lore(List<Component> loreList) {
        ItemMeta meta = this.item.getItemMeta();
        List<Component> list = new ArrayList<>();

        if (meta.hasLore()) {
            list = new ArrayList<>(meta.lore());
        }

        list.addAll(loreList);
        meta.lore(list);
        item.setItemMeta(meta);

        return this;
    }

    public ItemStackBuilder setLore(List<Component> lore) {
        ItemMeta meta = this.item.getItemMeta();
        meta.lore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder data(NamespacedKey namespacedKey, int value) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setLocalizedName(namespacedKey.getKey());
        this.item.setItemMeta(meta);

        ItemPersistentDataUtil.addData(this.item, namespacedKey, value);
        return this;
    }

    public ItemStackBuilder data(NamespacedKey namespacedKey) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setLocalizedName(namespacedKey.getKey());
        this.item.setItemMeta(meta);

        ItemPersistentDataUtil.addData(this.item, namespacedKey, 1);

        return this;
    }

    public ItemStackBuilder customData(int data) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setCustomModelData(data);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder enchantment(Enchantment enchant, int level) {
        ItemMeta meta = this.item.getItemMeta();
        meta.addEnchant(enchant, level, true);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder setUnbreakabe(boolean status) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setUnbreakable(status);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return this.item;
    }

    public ItemStackBuilder visibleFlag() {
        ItemMeta meta = build().getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        build().setItemMeta(meta);
        return this;
    }

    public void headMeta(SkullMeta headMeta) {
        ItemStack itemStack = (ItemStack) headMeta;
        itemStack.setItemMeta(headMeta);
    }

    public ItemStackBuilder enchantment(Map<Enchantment, Integer> enchantments) {
        enchantments.forEach(this::enchantment);
        return this;
    }
}
