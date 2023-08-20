package dev.mazyar8.quest.event;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import dev.mazyar8.quest.npc.NPC;
import dev.mazyar8.quest.npc.NPCManager;

public class EntityInteractEvent extends Event {

	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e) {
		Entity entity = e.getRightClicked();
		if (!NPCManager.isNPC(entity))
			return;
		
		NPC npc = NPCManager.getNPCByEntity(entity);
		if (npc != null)
			npc.onInteract(e.getPlayer());
		e.setCancelled(true);
	}
	
}
