package dev.mazyar8.quest.task;

import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev.mazyar8.quest.config.Config;

public class FlowerTask extends Task {

	public FlowerTask() {
		super("Flower Finder", "you should find yellow flower.", Material.YELLOW_FLOWER, Config.TASK_SLOT_FLOWER);
	}
	
	@Override
	public void update(Player p) {
		if (p.getInventory().contains(Material.YELLOW_FLOWER))
			reward(p);
	}
	
	@Override
	public void reward(Player p) {
		super.reward(p);
		p.playEffect(EntityEffect.VILLAGER_HEART);
	}

}
