package dev.mazyar8.quest.task;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev.mazyar8.quest.config.Config;

public class Task {

	private String name, description;
	private Material material;
	private int slot;
	
	public Task(String name, String description, Material material, int slot) {
		this.name = name;
		this.description = description;
		this.material = material;
		this.slot = slot;
	}
	
	public void update(Player p) {
		
	}
	
	public void reward(Player p) {
		p.sendMessage(Config.TASK_REWARD_MESSAGE.replace("[player]", p.getName()).replace("[task]", this.getName()));
		PlayerTaskManager.endTask(p, this);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Material getMaterial() {
		return material;
	}

	public int getSlot() {
		return slot;
	}
	
}
