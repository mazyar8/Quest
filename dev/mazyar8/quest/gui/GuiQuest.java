package dev.mazyar8.quest.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.mazyar8.quest.Quest;
import dev.mazyar8.quest.task.PlayerTaskManager;
import dev.mazyar8.quest.task.Task;
import dev.mazyar8.quest.task.TaskManager;

public class GuiQuest extends Gui {

	public GuiQuest(String title, int size) {
		super(title, size);
		
		for (Task t : TaskManager.tasks) {
			ItemStack is = new ItemStack(t.getMaterial());
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(t.getName());
			im.addEnchant(Enchantment.DURABILITY, 1, true);
			im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			List<String> lore = new ArrayList<>();
			lore.add(t.getDescription());
			im.setLore(lore);
			is.setItemMeta(im);
			this.getInventory().setItem(t.getSlot(), is);
		}
	}
	
	@Override
	public void onGUIClick(Player player, Inventory inventory, InventoryAction action, ItemStack itemstack, int slot) {
		if (!(action.equals(InventoryAction.PICKUP_ALL) || action.equals(InventoryAction.PICKUP_HALF)))
			return;
		
		PlayerTaskManager ptm = Quest.getQuest().playerTaskManager;
		Task task = null;
		for (Task t : TaskManager.tasks)
			if (t.getMaterial().equals(itemstack.getType()))
				if (!ptm.hasTask(player, t))
					task = t;
		
		if (task != null) {
			ptm.addTaskToPlayer(player, task);
			player.sendMessage(task.getDescription());
			player.closeInventory();
		}
	}

}
