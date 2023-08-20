package dev.mazyar8.quest.event;

import org.bukkit.event.EventHandler;

import dev.mazyar8.quest.npc.NPCManager;

public class EntityTargetEvent extends Event {

	@EventHandler
	public void onEntityTarget(org.bukkit.event.entity.EntityTargetEvent e) {
		if (NPCManager.isNPC(e.getTarget()))
			e.setCancelled(true);
	}
	
}
