package me.noodles.ss.freezecommand;

import me.noodles.ss.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FreezeEvents implements Listener {

    private static String getColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }


    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (FreezeCommand.Freeze.contains(p.getName())) {
            p.teleport(e.getFrom());
            if (Main.plugin.getConfig().getBoolean("UseGUI.Enabled") == true) {

                ItemStack paper = new ItemStack(Material.getMaterial(Main.plugin.getguiConfig().getString("GUIFreezeItem")));
                ItemStack glass = new ItemStack(Material.getMaterial(Main.plugin.getguiConfig().getString("GUISurroundingItem")));
                ItemMeta paperm = paper.getItemMeta();
                ItemMeta glassm = glass.getItemMeta();
                glassm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getguiConfig().getString("GUISurroundingName")));
                glass.setItemMeta(glassm);
                paperm.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getguiConfig().getString("GUIFreezeName")));


                ArrayList<String> papermlore = new ArrayList<>();
                List<String> stringList = Main.plugin.getguiConfig().getStringList("GUIFreezeLore");
                IntStream.range(0, stringList.size()).forEach(i -> papermlore.add(getColor(stringList.get(i))));
                paperm.setLore(papermlore);

                ArrayList<String> glassmlore = new ArrayList<>();
                List<String> stringList2 = Main.plugin.getguiConfig().getStringList("GUISurroundingLore");
                IntStream.range(0, stringList2.size()).forEach(i -> glassmlore.add(getColor(stringList2.get(i))));
                glassm.setLore(glassmlore);

                glass.setItemMeta(glassm);
                paper.setItemMeta(paperm);
                FreezeCommand.freezeinv.setItem(0, glass);
                FreezeCommand.freezeinv.setItem(1, glass);
                FreezeCommand.freezeinv.setItem(2, glass);
                FreezeCommand.freezeinv.setItem(3, glass);
                FreezeCommand.freezeinv.setItem(4, glass);
                FreezeCommand.freezeinv.setItem(5, glass);
                FreezeCommand.freezeinv.setItem(6, glass);
                FreezeCommand.freezeinv.setItem(7, glass);
                FreezeCommand.freezeinv.setItem(8, glass);
                FreezeCommand.freezeinv.setItem(9, glass);
                FreezeCommand.freezeinv.setItem(10, paper);
                FreezeCommand.freezeinv.setItem(11, paper);
                FreezeCommand.freezeinv.setItem(12, paper);
                FreezeCommand.freezeinv.setItem(13, paper);
                FreezeCommand.freezeinv.setItem(14, paper);
                FreezeCommand.freezeinv.setItem(15, paper);
                FreezeCommand.freezeinv.setItem(16, paper);
                FreezeCommand.freezeinv.setItem(17, glass);
                FreezeCommand.freezeinv.setItem(18, glass);
                FreezeCommand.freezeinv.setItem(19, glass);
                FreezeCommand.freezeinv.setItem(20, glass);
                FreezeCommand.freezeinv.setItem(21, glass);
                FreezeCommand.freezeinv.setItem(22, glass);
                FreezeCommand.freezeinv.setItem(23, glass);
                FreezeCommand.freezeinv.setItem(24, glass);
                FreezeCommand.freezeinv.setItem(22, glass);
                FreezeCommand.freezeinv.setItem(25, glass);
                FreezeCommand.freezeinv.setItem(26, glass);
                p.openInventory(FreezeCommand.freezeinv);
            }
        }
    }

    @EventHandler
    public void onMove2(final PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (Main.plugin.getConfig().getBoolean("UseGUI.Enabled") == false) {
            if (FreezeCommand.Freeze.contains(p.getName())) {
                p.teleport(e.getFrom());
                for (String msg : Main.plugin.getmessagesConfig().getStringList("Messages")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

        @EventHandler
        public void onBlockBreak(BlockBreakEvent e) {
            if (Main.plugin.getConfig().getBoolean("StopBlockBreak.Enabled") == true) {
                Player p = e.getPlayer();
                if (FreezeCommand.Freeze.contains(p.getName())) {
                    e.setCancelled(true);
                }
            }
        }


        @EventHandler
        public void onBlockPlace(BlockPlaceEvent e) {
            if (Main.plugin.getConfig().getBoolean("StopBlockPlace.Enabled") == true) {
                Player p = e.getPlayer();
                if (FreezeCommand.Freeze.contains(p.getName())) {
                    e.setCancelled(true);
                }
            }
        }

        @EventHandler
        public void onPlayerDamage(EntityDamageEvent e) {
            if (Main.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
                if (FreezeCommand.Freeze.contains(e.getEntity())) {
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
                        if (FreezeCommand.Freeze.contains(p.getName())) {
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
                        if (FreezeCommand.Freeze.contains(p.getName())) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }

        @EventHandler
        public void onCommand(final PlayerCommandPreprocessEvent e) {
            if (Main.plugin.getConfig().getBoolean("StopCommandUsage.Enabled")) {
                final Player p = e.getPlayer();
                if (FreezeCommand.Freeze.contains(p.getName())) {
                    final String message = e.getMessage();
                    final String[] array = message.split(" ");
                    if (!array[0].equalsIgnoreCase("/msg") && !array[0].equalsIgnoreCase("/tell") && !array[0].equalsIgnoreCase("/r") && !array[0].equalsIgnoreCase("/whisper") && !array[0].equalsIgnoreCase("/t") && !array[0].equalsIgnoreCase("/w")) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("NoCommands")));
                    }
                }
            }
        }

        @EventHandler
        public void onFoodLevelChange(FoodLevelChangeEvent e) {
            if (Main.plugin.getConfig().getBoolean("StopFoodLevelChange.Enabled") == true) {
                if (e.getEntity() instanceof Player) {
                    Player p = (Player) e.getEntity();
                    if (FreezeCommand.Freeze.contains(p.getName())) {
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
                        if (FreezeCommand.Freeze.contains(p.getName())) {
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
        if (FreezeCommand.Freeze.contains(p.getName())) {
            for (Player pl : Main.plugin.getServer().getOnlinePlayers()) {
                if (pl.hasPermission("ultimatefreeze.quitmessage")) {
                    pl.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getmessagesConfig().getString("PlayerLeft").replace("%target%", p.getName())));
                    if (Main.plugin.getConfig().getBoolean("BanOnLeave.Enabled") == true) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),(Main.plugin.getConfig().getString("BanCommand").replace("%target%", p.getName())));
                    } else {
                        return;
                    }
                }
            }
        }
    }
}


