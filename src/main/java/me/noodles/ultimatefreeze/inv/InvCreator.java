package me.noodles.ultimatefreeze.inv;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InvCreator {

	    public static Inventory Main;
	    
	    static {
	        InvCreator.Main = Bukkit.createInventory(null, 27, InvNames.Main);
	    }
}
