package org.creativecraft.example.integrations;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.creativecraft.example.ExamplePlugin;
import org.bukkit.entity.Player;

public class PlaceholderApi extends PlaceholderExpansion {
    private ExamplePlugin plugin;
    private String identifier = "example";

    public PlaceholderApi(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {
        if (s.equalsIgnoreCase("name")) {
            return "example";
        }

        return null;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors());
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }
}
