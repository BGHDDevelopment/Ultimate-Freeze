package me.noodles.ultimatefreeze.commands;

import me.noodles.ultimatefreeze.UltimateFreeze;
import me.noodles.ultimatefreeze.utilities.Common;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-10-20 03:09
 */
public final class ReloadCommand implements TabExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            Common.tell(sender, "You need to be a player to access this command.");
            return true;
        }

        final Player player = (Player) sender;

        if (!player.hasPermission("UltimateFreeze.reload")) {
            Common.tell(player, UltimateFreeze.getPlugin().getmessagesConfig().getString("NoPermission"));
            return true;
        }

        UltimateFreeze.getPlugin().reloadConfigFiles();

        return false;
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        return null;
    }

}
