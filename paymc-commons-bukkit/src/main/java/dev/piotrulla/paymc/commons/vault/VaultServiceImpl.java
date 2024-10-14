package dev.piotrulla.paymc.commons.vault;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;

public class VaultServiceImpl implements VaultService {

    private final Economy economy;

    public VaultServiceImpl(Economy economy) {
        this.economy = economy;
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return this.economy.getBalance(player);
    }

    @Override
    public void deposit(OfflinePlayer player, double amount) {
        this.economy.depositPlayer(player, amount);
    }

    @Override
    public void withdraw(OfflinePlayer player, double amount) {
        this.economy.withdrawPlayer(player, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return this.economy.has(player, amount);
    }
}
