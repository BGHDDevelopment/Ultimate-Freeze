package me.noodles.ss.freezecommand;

import me.noodles.ss.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FreezeCommand implements Listener, CommandExecutor {


    Inventory freezeinv;
    ArrayList<String> Freeze;
    public static Main plugin;

    public FreezeCommand() {
        this.freezeinv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.RED + "You are frozen, DO NOT log out");
        this.Freeze = new ArrayList<String>();
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("freeze")) {
            if (sender.hasPermission("freeze.freeze")) {
                if (args.length == 0)
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("InvalidUsage")));

                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("NoPlayer")));
                    }
                    if (target.hasPermission("freeze.override")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("NoFreeze")));
                        return true;

                    }
                    else if (!this.Freeze.contains(target.getName())) {
                        this.Freeze.add(target.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("PlayerFrozen").replace("%target%", target.getName())));
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("Frozen")));
                        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000000, 0));                }
                    else {
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
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("NoPermission")));
            }
        }

        return false;
    }

    private static String getColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (this.Freeze.contains(p.getName())) {
            p.teleport(e.getFrom());
                ItemStack paper = new ItemStack(Material.getMaterial(Main.plugin.getguiConfig().getString("GUIFreezeItem")));
                ItemStack glass = new ItemStack(Material.getMaterial(Main.plugin.getguiConfig().getString("GUISurroundingItem")));
                ItemMeta paperm = paper.getItemMeta();
                ItemMeta glassm = glass.getItemMeta();
                glassm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getguiConfig().getString("GUISurroundingName")));
                glass.setItemMeta(glassm);
                paperm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getguiConfig().getString("ItemName")));
                ArrayList<String> papermlore = new ArrayList<String>();
                ArrayList<String> glassmlore = new ArrayList<String>();
                List<String> stringList = Main.plugin.getguiConfig().getStringList("GUISurroundingLore");
                IntStream.range(0, stringList.size()).forEach(i -> glassmlore.add(getColor(stringList.get(i))));
                List<String> stringList2 = Main.plugin.getguiConfig().getStringList("GUIFreezeLore");
                IntStream.range(0, stringList.size()).forEach(i -> papermlore.add(getColor(stringList2.get(i))));
                paperm.setLore(papermlore);
                glassm.setLore(glassmlore);
                paper.setItemMeta(paperm);
                this.freezeinv.setItem(0, glass);
                this.freezeinv.setItem(1, glass);
                this.freezeinv.setItem(2, glass);
                this.freezeinv.setItem(3, glass);
                this.freezeinv.setItem(4, glass);
                this.freezeinv.setItem(5, glass);
                this.freezeinv.setItem(6, glass);
                this.freezeinv.setItem(7, glass);
                this.freezeinv.setItem(8, glass);
                this.freezeinv.setItem(9, glass);
                this.freezeinv.setItem(10, paper);
                this.freezeinv.setItem(11, paper);
                this.freezeinv.setItem(12, paper);
                this.freezeinv.setItem(13, paper);
                this.freezeinv.setItem(14, paper);
                this.freezeinv.setItem(15, paper);
                this.freezeinv.setItem(16, paper);
                this.freezeinv.setItem(17, glass);
                this.freezeinv.setItem(18, glass);
                this.freezeinv.setItem(19, glass);
                this.freezeinv.setItem(20, glass);
                this.freezeinv.setItem(21, glass);
                this.freezeinv.setItem(22, glass);
                this.freezeinv.setItem(23, glass);
                this.freezeinv.setItem(24, glass);
                this.freezeinv.setItem(22, glass);
                this.freezeinv.setItem(25, glass);
                this.freezeinv.setItem(26, glass);
                p.openInventory(this.freezeinv);

            }

            }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (Main.plugin.getConfig().getBoolean("StopBlockBreak.Enabled") == true) {
            Player p = e.getPlayer();
            if (this.Freeze.contains(p.getName())) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (Main.plugin.getConfig().getBoolean("StopBlockPlace.Enabled") == true) {
            Player p = e.getPlayer();
            if (this.Freeze.contains(p.getName())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (Main.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
            if (Freeze.contains(e.getEntity())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (Main.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
        if (e.getDamager() instanceof Player) {
            if (e.getEntity() instanceof Player) {
                Player p = (Player) e.getEntity();
                if (this.Freeze.contains(p.getName())) {
                    e.setCancelled(true);
                }
            }
        }
        }
    }


    @EventHandler
    public void onBow(EntityDamageByEntityEvent e) {
        if (Main.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
        if(e.getDamager() instanceof Arrow) {
            if (e.getEntity() instanceof Player) {
                Player p = (Player) e.getEntity();
                if (this.Freeze.contains(p.getName())) {
                    e.setCancelled(true);
                }
            }
        }
        }
    }

    @EventHandler
    public void onCommand(final PlayerCommandPreprocessEvent e) {
        if (Main.plugin.getConfig().getBoolean("StopCommandUsage.Enabled") == true) {
            Player p = e.getPlayer();
            if (this.Freeze.contains(p.getName())) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("NoCommands")));
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (Main.plugin.getConfig().getBoolean("StopFoodLevelChange.Enabled") == true) {
            if (e.getEntity() instanceof Player) {
                Player p = (Player) e.getEntity();
                if (this.Freeze.contains(p.getName())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onFight(EntityDamageByEntityEvent e) {
        if (Main.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
        if (e.getDamager() instanceof Player) {
            if (e.getEntity() instanceof Player) {
                Player p = (Player) e.getDamager();
                if (this.Freeze.contains(p.getName())) {
                    e.setCancelled(true);
                }
            }
        }
        }
    }



    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "You are frozen, DO NOT log out")) {
            e.setCancelled(true);
            if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "You are frozen, DO NOT log out")) {
                if (e.getCurrentItem() == null) {
                    return;
                }
            }
        }

    }



    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (this.Freeze.contains(p.getName())) {
            for (Player pl : Main.plugin.getServer().getOnlinePlayers()) {
                if (pl.hasPermission("freeze.quitmessage")) {
                    pl.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("PlayerLeft").replace("%target%", p.getName())));
                }
            }
        }
    }
}

