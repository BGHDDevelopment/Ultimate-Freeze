package me.noodles.ss.freezecommand;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BuyerCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("freezebuyer")) {
            sender.sendMessage(ChatColor.GOLD + "This copy of freeze is registered to: %%__USER__%%");
            sender.sendMessage(ChatColor.GOLD + "Download ID: %%__NONCE__%%");
            sender.sendMessage(ChatColor.GOLD + "Plugin page: %%__RESOURCE__%%");
            sender.sendMessage(ChatColor.RED + "Please report any leaked versions to BGHDDevelopment.");
        }
        return true;
    }
}
