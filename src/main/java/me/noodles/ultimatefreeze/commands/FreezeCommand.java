package me.noodles.ultimatefreeze.commands;

import me.noodles.ultimatefreeze.UltimateFreeze;
import me.noodles.ultimatefreeze.utilities.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.List;

public class FreezeCommand implements TabExecutor {
    private final String PERMISSION = "ultimatefreeze.freeze";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("freeze")) {
            if (sender.hasPermission(PERMISSION)) {
                if (args.length == 0)
                    Common.tell(sender, UltimateFreeze.plugin.getmessagesConfig().getString("InvalidUsage"));

                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        Common.tell(sender, UltimateFreeze.plugin.getmessagesConfig().getString("NoPlayer"));
                        return true;
                    }
                    if (target.equals(sender)) {
                        Common.tell(sender, UltimateFreeze.plugin.getmessagesConfig().getString("SelfFreeze"));
                        return true;
                    }
                    if (target.hasPermission("ultimatefreeze.override")) {
                        Common.tell(sender, UltimateFreeze.plugin.getmessagesConfig().getString("NoFreeze"));
                        return true;

                    } else if (!UltimateFreeze.getPlugin().isUserFrozen(target)) {
                        UltimateFreeze.getPlugin().addFrozenUser(target);
                        Common.tell(sender, UltimateFreeze.plugin.getmessagesConfig().getString("PlayerFrozen").replace("%target%", target.getName()));
                        Common.tell(target, UltimateFreeze.plugin.getmessagesConfig().getString("Frozen"));
                        if (UltimateFreeze.plugin.getConfig().getBoolean("GiveBlindness.Enabled") == true) {
                            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000000, 0));
                        }

                        } else {
                            Common.tell(sender, UltimateFreeze.plugin.getmessagesConfig().getString("PlayerUnFrozen").replace("%target%", target.getName()));
                            Common.tell(target, UltimateFreeze.plugin.getmessagesConfig().getString("UnFrozen"));
                            target.getActivePotionEffects().clear();
                            for (PotionEffect pe : target.getActivePotionEffects()) {
                                target.removePotionEffect(pe.getType());
                                target.closeInventory();
                                UltimateFreeze.getPlugin().removeFrozenUser(target);
                            }
                        }
                    }
                } else {
                    Common.tell(sender, UltimateFreeze.plugin.getmessagesConfig().getString("NoPermission"));
                }

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length == 1 && sender.hasPermission(PERMISSION)) ? null : Collections.emptyList();
    }

}
