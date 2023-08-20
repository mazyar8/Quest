package dev.mazyar8.quest.gui;

import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.inventory.Inventory;

public class GuiManager {
	
	public static final CopyOnWriteArrayList<Gui> guis = new CopyOnWriteArrayList<>();
	
	public static void add(Gui gui) {
		guis.add(gui);
	}
	
	public static Gui getGUIByInventory(Inventory inv) {
		for (Gui g : guis)
			if (g.getInventory().equals(inv))
				return g;
		return null;
	}
	
	public static boolean isGUI(Inventory inv) {
		for (Gui g : guis)
			if (g.getInventory().equals(inv))
				return true;
		return false;
	}
	
}
