package dev.mazyar8.quest.config;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import dev.mazyar8.quest.Quest;

public class Config {
	
	public static String GUI_NAME = "Quest";
	public static int GUI_SIZE = 27;
	public static String NPC_QUEST_NAME = ChatColor.AQUA + "QUEST";
	public static int TASK_SLOT_WATER = 0;
	public static int TASK_SLOT_FLOWER = 1;
	public static String TASK_REWARD_MESSAGE = "well done [player], you have completed the [task] task.";
	public static String ERROR_COMMAND_NO_PERMISSION = ChatColor.RED + "you don't have permission to use this command.";
	public static String ERROR_COMMAND_JUST_PLAYERS_ALLOW_TO_USE = ChatColor.RED + "just players allow to use this command.";
	public static String ERROR_NPC_NO_PERMISSION = ChatColor.RED + "you don't have permission to use this npc.";
	
	public Config(FileConfiguration fc) {
		for (Field f : getClass().getFields()) {
			String path = f.getName().toLowerCase().replace("_", ".");
			try {
				if (fc.isSet(path))
					f.set(this, fc.get(path));
				else
					fc.set(path, f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		Quest.getQuest().saveConfig();
	}

}
