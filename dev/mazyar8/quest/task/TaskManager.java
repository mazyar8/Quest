package dev.mazyar8.quest.task;

import java.util.concurrent.CopyOnWriteArrayList;

public class TaskManager {

	public static CopyOnWriteArrayList<Task> tasks = new CopyOnWriteArrayList<>();
	
	public TaskManager() {
		tasks.add(new FlowerTask());
		tasks.add(new WaterTask());
	}
	
}
