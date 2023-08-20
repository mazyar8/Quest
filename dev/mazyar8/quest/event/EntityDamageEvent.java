package dev.mazyar8.quest.event;

import org.bukkit.event.EventHandler;

import dev.mazyar8.quest.npc.NPCManager;

public class EntityDamageEvent extends Event {

	@EventHandler
	public void onEntityDamage(org.bukkit.event.entity.EntityDamageEvent e) {
		if (NPCManager.isNPC(e.getEntity()))
			e.setCancelled(true);
	}
	
}
