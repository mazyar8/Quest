package dev.mazyar8.quest.npc;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class NPC {
	
	private Entity entity;
	
	public NPC(Entity entity, String displayName) {
		this.entity = entity;
		this.entity.setCustomName(displayName);
		this.entity.setCustomNameVisible(true);
	}
	
	public void update() {
		entity.setVelocity(new Vector());
	}
	
	public void onInteract(Player p) {
		
	}

	public Entity getEntity() {
		return entity;
	}

}
