package me.noodles.ss.freezecommand;

import me.noodles.ss.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.ArrayList;


public class FreezeCommand implements CommandExecutor {


    static Inventory freezeinv;
  public static ArrayList<String> Freeze;

    public FreezeCommand() {
        this.Freeze = new ArrayList<>();
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("freeze")) {
            if (sender.hasPermission("ultimatefreeze.freeze")) {
                if (args.length == 0)
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("InvalidUsage")));

                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("NoPlayer")));
                        return true;
                    }
                    if (target.hasPermission("ultimatefreeze.override")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("NoFreeze")));
                        return true;

                    } else if (!this.Freeze.contains(target.getName())) {
                        this.Freeze.add(target.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("PlayerFrozen").replace("%target%", target.getName())));
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("Frozen")));
                        if (Main.plugin.getConfig().getBoolean("GiveBlindness.Enabled") == true) {
                            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000000, 0));
                        }

                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("PlayerUnFrozen").replace("%target%", target.getName())));
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("UnFrozen")));
                            target.getActivePotionEffects().clear();
                            for (PotionEffect pe : target.getActivePotionEffects()) {
                                target.removePotionEffect(pe.getType());
                                target.closeInventory();
                                this.Freeze.remove(target.getName());
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("NoPermission")));
                }

        }
        return false;
    }
}