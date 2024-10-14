package dev.piotrulla.paymc.commons.vault;

import org.bukkit.OfflinePlayer;

import java.util.logging.Logger;

public class NoneVaultService implements VaultService {

    private static final Logger LOGGER = Logger.getLogger(NoneVaultService.class.getName());
    private static final String MESSAGE = "Vault service is not available, please install a vault plugin and a compatible economy plugin.";

    @Override
    public double getBalance(OfflinePlayer player) {
        LOGGER.warning(MESSAGE);
        return 0;
    }

    @Override
    public void deposit(OfflinePlayer player, double amount) {
        LOGGER.warning(MESSAGE);
    }

    @Override
    public void withdraw(OfflinePlayer player, double amount) {
        LOGGER.warning(MESSAGE);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        LOGGER.warning(MESSAGE);
        return true;
    }
}
