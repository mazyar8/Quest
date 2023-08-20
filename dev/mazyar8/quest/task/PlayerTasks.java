package dev.mazyar8.quest.task;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class PlayerTasks {

	private Player player;
	private List<Task> tasks = new ArrayList<>();
	
	public PlayerTasks(Player player) {
		this.player = player;
	}

	public boolean add(Task e) {
		return tasks.add(e);
	}

	public Player getPlayer() {
		return player;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
}
