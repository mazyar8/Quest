package dev.mazyar8.quest.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import dev.mazyar8.quest.task.PlayerTaskManager;
import dev.mazyar8.quest.task.PlayerTasks;

public class PlayerJoinEvent extends Event {

	@EventHandler
	public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (PlayerTasks pt : PlayerTaskManager.getPlayerTasks())
			if (pt.getPlayer().equals(p))
				return;
		PlayerTaskManager.getPlayerTasks().add(new PlayerTasks(p));
	}
	
}
