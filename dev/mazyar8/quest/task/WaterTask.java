package dev.mazyar8.quest.task;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.mazyar8.quest.config.Config;

public class WaterTask extends Task {

	public WaterTask() {
		super("Water Finder", "you should find water and jump to water to receive a diamond.", Material.WATER_BUCKET, Config.TASK_SLOT_WATER);
	}
	
	@Override
	public void update(Player p) {
		if (p.getWorld().getBlockAt(p.getLocation()).getType().equals(Material.STATIONARY_WATER))
			reward(p);
	}
	
	@Override
	public void reward(Player p) {
		super.reward(p);
		p.getInventory().addItem(new ItemStack(Material.DIAMOND));
	}

}
