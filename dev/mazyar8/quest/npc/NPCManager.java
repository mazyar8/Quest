package dev.mazyar8.quest.npc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class NPCManager {
	
	public static List<NPC> npcList = new ArrayList<>();
	private static List<Class<? extends NPC>> npcClassList = new ArrayList<>();
	
	public NPCManager() {
		npcClassList.add(QuestNPC.class);
	}
	
	/** spawn new NPC to show quest GUI */
	public void spawnQuestNPC(Location loc) {
		Villager entity = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		QuestNPC npc = new QuestNPC(entity);
		npcList.add(npc);
	}
	
	public void update() {
		for (NPC n : npcList)
			n.update();
	}
	
	public static boolean isNPC(Entity e) {
		for (NPC n : npcList)
			if (n.getEntity().equals(e))
				return true;
		return false;
	}
	
	public static NPC getNPCByEntity(Entity e) {
		for (NPC n : npcList)
			if (n.getEntity().equals(e))
				return n;
		return null;
	}
	
	public Class<? extends NPC> getNPCClassByName(String className) {
		for (Class<? extends NPC> c : npcClassList)
			if (c.getName().equals(className))
				return c;
		return null;
	}
	
	public Entity getEntityByUUID(World world, UUID uuid) {
		for (Entity e : world.getEntities())
			if (e.getUniqueId().equals(uuid))
				return e;
		return null;
	}
	
	/** types: all, near, nearest */
	public static void deleteNPC(Player player, String type) {
		List<NPC> lastNPCList = new ArrayList<>(npcList);
		switch (type.toLowerCase()) {
		case "all":
			npcList.clear();
			break;
		case "near":
			npcList = npcList.stream().filter(n -> n.getEntity().getLocation().distance(player.getLocation()) > 4).collect(Collectors.toList());
			break;
		case "nearest":
			if (!npcList.isEmpty()) {
				ArrayList<NPC> npcArrayList = new ArrayList<>(npcList);
				npcArrayList.sort(Comparator.comparingDouble(npc -> npc.getEntity().getLocation().distance(player.getLocation())));
				NPC npc = npcArrayList.get(0);
				npcList = npcList.stream().filter(n -> !n.equals(npc)).collect(Collectors.toList());
			}
			break;
		}
		for (NPC n : lastNPCList.stream().filter(npc -> !npcList.contains(npc)).collect(Collectors.toList()))
			n.getEntity().remove();
	}

}
