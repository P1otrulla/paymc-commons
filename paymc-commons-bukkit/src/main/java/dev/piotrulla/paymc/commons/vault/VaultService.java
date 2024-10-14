package dev.piotrulla.paymc.commons.vault;

import org.bukkit.OfflinePlayer;

public interface VaultService {

    double getBalance(OfflinePlayer player);

    void deposit(OfflinePlayer player, double amount);

    void withdraw(OfflinePlayer player, double amount);

    boolean has(OfflinePlayer player, double amount);
}