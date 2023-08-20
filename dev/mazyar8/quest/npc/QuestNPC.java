package dev.mazyar8.quest.npc;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import dev.mazyar8.quest.Quest;
import dev.mazyar8.quest.config.Config;
import dev.mazyar8.quest.permissions.PermissionHelper;

public class QuestNPC extends NPC {

	public QuestNPC(Entity entity) {
		super(entity, Config.NPC_QUEST_NAME);
	}
	
	@Override
	public void onInteract(Player p) {
		if (PermissionHelper.hasPermission(p, PermissionHelper.NPC_QUEST))
			Quest.getQuest().gui.showGUI(p);
		else
			p.sendMessage(Config.ERROR_NPC_NO_PERMISSION);
	}

}
