package org.creativecraft.example;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.CommandReplacements;
import co.aikar.commands.MessageType;
import de.themoep.minedown.MineDown;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.creativecraft.example.commands.ExampleCommand;
import org.creativecraft.example.config.MessagesConfig;
import org.creativecraft.example.integrations.PlaceholderApi;
import org.creativecraft.example.listener.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {
    public static ExamplePlugin plugin;
    private MessagesConfig messagesConfig;

    @Override
    public void onEnable() {
        plugin = this;

        registerConfigs();
        registerListeners();
        registerCommands();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderApi(this).register();
        }

        // new MetricsLite(this, 00000);
    }

    @Override
    public void onLoad() {
        //
    }

    @Override
    public void onDisable() {
        //
    }

    /**
     * Register the plugin commands.
     */
    public void registerCommands() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        CommandReplacements replacements = commandManager.getCommandReplacements();

        replacements.addReplacement("example", getConfig().getString("command", "example"));

        commandManager.setFormat(MessageType.ERROR, ChatColor.GREEN, ChatColor.WHITE, ChatColor.GRAY);
        commandManager.setFormat(MessageType.SYNTAX, ChatColor.GREEN, ChatColor.WHITE, ChatColor.GRAY);
        commandManager.setFormat(MessageType.HELP, ChatColor.GREEN, ChatColor.WHITE, ChatColor.GRAY);
        commandManager.setFormat(MessageType.INFO, ChatColor.GREEN, ChatColor.WHITE, ChatColor.GRAY);

        commandManager.registerCommand(new ExampleCommand(this));
        commandManager.enableUnstableAPI("help");
    }

    /**
     * Register the plugin configuration.
     */
    public void registerConfigs() {
        getConfig().addDefault("command", "example");

        getConfig().options().copyDefaults(true);

        saveConfig();

        messagesConfig = new MessagesConfig(this);
    }

    /**
     * Register the plugin event listeners.
     */
    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
    }

    /**
     * Retrieve a localized message.
     *
     * @param  key The locale key.
     * @return String
     */
    public String localize(String key) {
        String message = messagesConfig.getMessages().getString(key);

        return ChatColor.translateAlternateColorCodes(
            '&',
            message == null ? key + " is missing." : message
        );
    }

    /**
     * Send a message formatted with MineDown.
     *
     * @param sender The command sender.
     * @param value  The message.
     */
    public void sendMessage(CommandSender sender, String value) {
        sender.spigot().sendMessage(
            MineDown.parse(messagesConfig.getMessages().getString("messages.generic.prefix") + value)
        );
    }

    /**
     * Send a raw message formatted with MineDown.
     *
     * @param sender The command sender.
     * @param value  The message.
     */
    public void sendRawMessage(CommandSender sender, String value) {
        sender.spigot().sendMessage(
            MineDown.parse(value)
        );
    }

    /**
     * Reload the plugin configuration.
     */
    public void reload() {
        registerConfigs();
        reloadConfig();
    }
}
