package me.noodles.ultimatefreeze.listeners;

import me.noodles.ultimatefreeze.UltimateFreeze;
import me.noodles.ultimatefreeze.inv.InvCreator;
import me.noodles.ultimatefreeze.inv.InvNames;
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
        if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
            p.teleport(e.getFrom());
            if (UltimateFreeze.plugin.getConfig().getBoolean("UseGUI.Enabled") == true) {

                ItemStack paper = new ItemStack(Material.getMaterial(UltimateFreeze.plugin.getguiConfig().getString("GUIFreezeItem")));
                ItemStack glass = new ItemStack(Material.getMaterial(UltimateFreeze.plugin.getguiConfig().getString("GUISurroundingItem")));
                ItemMeta paperm = paper.getItemMeta();
                ItemMeta glassm = glass.getItemMeta();
                glassm.setDisplayName(ChatColor.translateAlternateColorCodes('&', UltimateFreeze.plugin.getguiConfig().getString("GUISurroundingName")));
                glass.setItemMeta(glassm);
                paperm.setDisplayName(ChatColor.translateAlternateColorCodes('&', UltimateFreeze.plugin.getguiConfig().getString("GUIFreezeName")));


                ArrayList<String> papermlore = new ArrayList<>();
                List<String> stringList = UltimateFreeze.plugin.getguiConfig().getStringList("GUIFreezeLore");
                IntStream.range(0, stringList.size()).forEach(i -> papermlore.add(getColor(stringList.get(i))));
                paperm.setLore(papermlore);

                ArrayList<String> glassmlore = new ArrayList<>();
                List<String> stringList2 = UltimateFreeze.plugin.getguiConfig().getStringList("GUISurroundingLore");
                IntStream.range(0, stringList2.size()).forEach(i -> glassmlore.add(getColor(stringList2.get(i))));
                glassm.setLore(glassmlore);

                glass.setItemMeta(glassm);
                paper.setItemMeta(paperm);
                InvCreator.Main.setItem(10, paper);
                InvCreator.Main.setItem(11, paper);
                InvCreator.Main.setItem(12, paper);
                InvCreator.Main.setItem(13, paper);
                InvCreator.Main.setItem(14, paper);
                InvCreator.Main.setItem(15, paper);
                InvCreator.Main.setItem(16, paper);
                for (int i = 0; i < 27; ++i) {
                    if (InvCreator.Main.getItem(i) == null) {
                        InvCreator.Main.setItem(i, glass);
                    }
                }
                p.openInventory(InvCreator.Main);
            }
        }
    }

    @EventHandler
    public void onMove2(final PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (UltimateFreeze.plugin.getConfig().getBoolean("UseGUI.Enabled") == false) {
            if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                p.teleport(e.getFrom());
                for (String msg : UltimateFreeze.plugin.getmessagesConfig().getStringList("Messages")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

        @EventHandler
        public void onBlockBreak(BlockBreakEvent e) {
            if (UltimateFreeze.plugin.getConfig().getBoolean("StopBlockBreak.Enabled") == true) {
                Player p = e.getPlayer();
                if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                    e.setCancelled(true);
                }
            }
        }


        @EventHandler
        public void onBlockPlace(BlockPlaceEvent e) {
            if (UltimateFreeze.plugin.getConfig().getBoolean("StopBlockPlace.Enabled") == true) {
                Player p = e.getPlayer();
                if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                    e.setCancelled(true);
                }
            }
        }

        @EventHandler
        public void onPlayerDamage(EntityDamageEvent e) {
            if (UltimateFreeze.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
                if (e.getEntity() instanceof  Player) {
                    Player p = (Player) e.getEntity();
                    if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                        e.setCancelled(true);
                    }
                }
            }
        }

        @EventHandler
        public void onDamage(EntityDamageByEntityEvent e) {
            if (UltimateFreeze.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
                if (e.getDamager() instanceof Player) {
                    if (e.getEntity() instanceof Player) {
                        Player p = (Player) e.getEntity();
                        if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }


        @EventHandler
        public void onBow(EntityDamageByEntityEvent e) {
            if (UltimateFreeze.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
                if(e.getDamager() instanceof Arrow) {
                    if (e.getEntity() instanceof Player) {
                        Player p = (Player) e.getEntity();
                        if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }

        @EventHandler
        public void onCommand(final PlayerCommandPreprocessEvent e) {
            if (UltimateFreeze.plugin.getConfig().getBoolean("StopCommandUsage.Enabled")) {
                final Player p = e.getPlayer();
                if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                    final String message = e.getMessage();
                    final String[] array = message.split(" ");
                    if (!array[0].equalsIgnoreCase("/msg") && !array[0].equalsIgnoreCase("/tell") && !array[0].equalsIgnoreCase("/r") && !array[0].equalsIgnoreCase("/whisper") && !array[0].equalsIgnoreCase("/t") && !array[0].equalsIgnoreCase("/w")) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', UltimateFreeze.plugin.getmessagesConfig().getString("NoCommands")));
                    }
                }
            }
        }

        @EventHandler
        public void onFoodLevelChange(FoodLevelChangeEvent e) {
            if (UltimateFreeze.plugin.getConfig().getBoolean("StopFoodLevelChange.Enabled") == true) {
                if (e.getEntity() instanceof Player) {
                    Player p = (Player) e.getEntity();
                    if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                        e.setCancelled(true);
                    }
                }
            }
        }

        @EventHandler
        public void onFight(EntityDamageByEntityEvent e) {
            if (UltimateFreeze.plugin.getConfig().getBoolean("StopPlayerPVP.Enabled") == true) {
                if (e.getDamager() instanceof Player) {
                    if (e.getEntity() instanceof Player) {
                        Player p = (Player) e.getDamager();
                        if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }


        @EventHandler
        public void onClick(InventoryClickEvent e) {
            if (e.getView().getTitle().equals(null)) {
                return;
            }
            if (e.getView().getTitle().equals(InvNames.Main)) {
                e.setCancelled(true);
            }
            if (e.getView().getTitle().equals(InvNames.Main)) {
                if (e.getCurrentItem() == null) {
                        return;
                    }
                if (e.getView().getTitle().equals(InvNames.Main)) {

                    if (e.isRightClick() || e.isLeftClick()) {
                        e.setCancelled(true);

                    }
                }
            }
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (UltimateFreeze.getPlugin().isUserFrozen(p)) {
            for (Player pl : UltimateFreeze.plugin.getServer().getOnlinePlayers()) {
                if (pl.hasPermission("ultimatefreeze.quitmessage")) {
                    pl.sendMessage(ChatColor.translateAlternateColorCodes('&', UltimateFreeze.plugin.getmessagesConfig().getString("PlayerLeft").replace("%target%", p.getName())));
                    if (UltimateFreeze.plugin.getConfig().getBoolean("BanOnLeave.Enabled") == true) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),(UltimateFreeze.plugin.getConfig().getString("BanCommand").replace("%target%", p.getName())));
                    } else {
                        return;
                    }
                }
            }
        }
    }
}


