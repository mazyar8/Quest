package dev.mazyar8.quest.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

public class PlayerTaskManager {

	private static List<PlayerTasks> playerTasks = new ArrayList<>();
	
	/** return true if player task list contains that task */
	public boolean hasTask(Player player, Task task) {
		for (PlayerTasks pt : playerTasks)
			if (pt.getPlayer().equals(player))
				return pt.getTasks().contains(task);
		return false;
	}
	
	/** add new task to player task list */
	public void addTaskToPlayer(Player player, Task task) {
		for (PlayerTasks pt : playerTasks)
			if (pt.getPlayer().equals(player))
				pt.add(task);
	}
	
	/** update players tasks to check player task state */
	public void update() {
		for (PlayerTasks pt : playerTasks)
			for (Task t : pt.getTasks())
				t.update(pt.getPlayer());
	}
	
	/** remove the task from player task list */
	public static void endTask(Player player, Task task) {
		for (PlayerTasks pt : playerTasks)
			if (pt.getPlayer().equals(player))
				for (Task t : pt.getTasks())
					if (t.equals(task))
						pt.setTasks(pt.getTasks().stream().filter(task1 -> !task1.equals(task)).collect(Collectors.toList()));
	}

	public static List<PlayerTasks> getPlayerTasks() {
		return playerTasks;
	}
	
}
