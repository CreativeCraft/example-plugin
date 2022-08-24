package org.creativecraft.example.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.HelpEntry;
import co.aikar.commands.annotation.*;
import org.creativecraft.example.ExamplePlugin;
import org.bukkit.command.CommandSender;

@CommandAlias("%example")
@Description("An example plugin.")
public class ExampleCommand extends BaseCommand {
    private final ExamplePlugin plugin;

    public ExampleCommand(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * An example command.
     *
     * @param sender The command sender.
     */
    @Subcommand("example")
    @CommandPermission("example.use")
    @Description("Just an example command.")
    public void onExample(CommandSender sender) {
        plugin.sendMessage(sender, "Hello world.");
    }

    /**
     * Retrieve the plugin help.
     *
     * @param sender The command sender.
     */
    @HelpCommand
    @Syntax("[page]")
    @Description("View the plugin help.")
    public void onHelp(CommandSender sender, CommandHelp help) {
        plugin.sendRawMessage(sender, plugin.localize("messages.help.header"));

        for (HelpEntry entry : help.getHelpEntries()) {
            plugin.sendRawMessage(
                sender,
                plugin
                    .localize("messages.help.format")
                    .replace("{command}", entry.getCommand())
                    .replace("{parameters}", entry.getParameterSyntax())
                    .replace("{description}", plugin.localize("messages." + entry.getCommand().split("\\s+")[1] + ".description"))
            );
        }

        plugin.sendRawMessage(sender, plugin.localize("messages.help.footer"));
    }

    /**
     * Reload the plugin confirmation.
     *
     * @param sender The command sender.
     */
    @Subcommand("reload")
    @CommandPermission("example.admin")
    @Description("Reload the plugin configuration.")
    public void onReload(CommandSender sender) {
        try {
            plugin.reload();
            plugin.sendMessage(sender, plugin.localize("messages.reload.success"));
        } catch (Exception e) {
            plugin.sendMessage(sender, plugin.localize("messages.reload.failed"));
        }
    }
}
