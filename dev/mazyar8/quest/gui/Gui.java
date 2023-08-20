package dev.mazyar8.quest.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Gui {
	
	private Inventory inventory;
	
	public Gui(String title, int size) {
		this.inventory = Bukkit.createInventory(null, size, title);
		GuiManager.add(this);
	}
	
	public void showGUI(Player p) {
		p.openInventory(this.inventory);
	}
	
	public void onGUIClick(Player player, Inventory inventory, InventoryAction action, ItemStack itemstack, int slot) {
		
	}

	public Inventory getInventory() {
		return this.inventory;
	}
	
}
