package org.creativecraft.example.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.creativecraft.example.ExamplePlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    private final ExamplePlugin plugin;

    public EventListener(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        //
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        //
    }
}
