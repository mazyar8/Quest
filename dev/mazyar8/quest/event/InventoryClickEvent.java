package dev.mazyar8.quest.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;

import dev.mazyar8.quest.gui.Gui;
import dev.mazyar8.quest.gui.GuiManager;

public class InventoryClickEvent extends Event {

	@EventHandler
	public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Gui gui = GuiManager.getGUIByInventory(inv);
		if (!GuiManager.isGUI(inv))
			return;
		
		gui.onGUIClick((Player) e.getWhoClicked(), inv, e.getAction(), e.getCurrentItem(), e.getSlot());
		e.setCancelled(true);
	}
	
}
