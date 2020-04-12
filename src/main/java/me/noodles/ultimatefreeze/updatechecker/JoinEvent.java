package me.noodles.ultimatefreeze.updatechecker;

import me.noodles.ultimatefreeze.Main;
import me.noodles.ultimatefreeze.Settings;
import org.bukkit.event.player.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class JoinEvent implements Listener
{

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Main.plugin.getConfig().getBoolean("Update.Enabled") == true) {
            if (p.hasPermission("ultimatefreeze.update")) {
                if (Main.plugin.getConfig().getBoolean("CheckForUpdates.Enabled")) {
                    new UpdateChecker(Main.getPlugin(), 44518).getLatestVersion(version -> {
                        if (!Main.getInstance().getDescription().getVersion().equalsIgnoreCase(version)) {
                            p.sendMessage(ChatColor.GRAY + "****************************************************************");
                            p.sendMessage(ChatColor.RED + "UltimateFreeze is outdated!");
                            p.sendMessage(ChatColor.RED + "Newest version: " + version);
                            p.sendMessage(ChatColor.RED + "Your version: " + ChatColor.BOLD + Settings.VERSION);
                            p.sendMessage(ChatColor.GOLD + "Please Update Here: " + ChatColor.ITALIC + Settings.PLUGIN_URL);
                            p.sendMessage(ChatColor.GRAY + "****************************************************************");
                        }
                    });
                } else {
                    p.sendMessage(ChatColor.RED + "[UltimateFreeze] You disabled checking for updates in the config.yml but did not toggle update notifications!");
                }
            }
        }
    }

    @EventHandler
    public void onDevJoin(PlayerJoinEvent e) { //THIS EVENT IS USED FOR DEBUG REASONS ONLY!
        Player p = e.getPlayer();
        if (p.getName().equals("Noodles_YT")) {
            p.sendMessage(ChatColor.RED + "BGHDDevelopment Debug Message");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GREEN + "This server is using " + Settings.NAME + " version " + Settings.VERSION);
            p.sendMessage(" ");
        }
    }
}
    