package me.noodles.ultimatefreeze.utilities;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-12 22:16
 */
public final class Common {

    private Common() {}

    public static void tell(final CommandSender sender, final String... messages) {
        Arrays.stream(messages).map(Common::translate).forEach(sender::sendMessage);
    }

    public static String translate(final String value) {
        return ChatColor.translateAlternateColorCodes('&', value);
    }

}
