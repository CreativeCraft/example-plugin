package org.creativecraft.example.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.creativecraft.example.ExamplePlugin;

import java.io.File;

public class MessagesConfig {
    private final ExamplePlugin plugin;
    private FileConfiguration messages;
    private File messagesFile;

    public MessagesConfig(ExamplePlugin plugin) {
        this.plugin = plugin;
        this.register();
    }

    /**
     * Register the messages configuration.
     */
    public void register() {
        messagesFile = new File(plugin.getDataFolder(), "messages.yml");

        if (!messagesFile.exists()) {
            messagesFile.getParentFile().mkdirs();
            plugin.saveResource("messages.yml", false);
        }

        messages = new YamlConfiguration();

        try {
            messages.load(messagesFile);
            setDefaults();
            messages.save(messagesFile);
        } catch (Exception e) {
            //
        }
    }

    /**
     * Register the messages defaults.
     */
    public void setDefaults() {
        messages.addDefault("messages.generic.prefix", "&a&lEx&fample &8> &f");

        messages.addDefault("messages.reload.success", "Plugin has been &asuccessfully&f reloaded.");
        messages.addDefault("messages.reload.failed", "Plugin &cfailed&f to reload. Check console for details.");
        messages.addDefault("messages.reload.description", "Reload the plugin configuration.");

        messages.addDefault("messages.help.header", "&a&m+&8&m                                &a&l Ex&fample &8&m                                &a&m+");
        messages.addDefault("messages.help.format", "&8➝ &a/{command} &7{parameters} &f- {description}");
        messages.addDefault("messages.help.footer", "&a&m+&8&m                                                                             &a&m+");
        messages.addDefault("messages.help.description", "View the plugin help.");

        messages.options().copyDefaults(true);
    }

    /**
     * Retrieve the messages configuration.
     *
     * @return FileConfiguration
     */
    public FileConfiguration getMessages() {
        return messages;
    }

    /**
     * Retrieve the messages file.
     *
     * @return File
     */
    public File getMessagesFile() {
        return messagesFile;
    }
}
