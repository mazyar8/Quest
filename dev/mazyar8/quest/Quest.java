package dev.mazyar8.quest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import dev.mazyar8.quest.command.QuestCommand;
import dev.mazyar8.quest.config.Config;
import dev.mazyar8.quest.event.EntityDamageEvent;
import dev.mazyar8.quest.event.EntityInteractEvent;
import dev.mazyar8.quest.event.EntityTargetEvent;
import dev.mazyar8.quest.event.Event;
import dev.mazyar8.quest.event.InventoryClickEvent;
import dev.mazyar8.quest.event.PlayerJoinEvent;
import dev.mazyar8.quest.gui.GuiManager;
import dev.mazyar8.quest.gui.GuiQuest;
import dev.mazyar8.quest.npc.NPC;
import dev.mazyar8.quest.npc.NPCManager;
import dev.mazyar8.quest.permissions.PermissionHelper;
import dev.mazyar8.quest.task.PlayerTaskManager;
import dev.mazyar8.quest.task.TaskManager;

public class Quest extends JavaPlugin {
	
	public Config config;
	public PermissionHelper permissionHelper;
	public GuiManager guiManager;
	public NPCManager npcManager;
	public TaskManager taskManager;
	public PlayerTaskManager playerTaskManager;
	public GuiQuest gui;
	
	private static Quest quest;

	@Override
	public void onEnable() {
		quest = this;
		addCommand("q", new QuestCommand());
		addEvent(new EntityDamageEvent());
		addEvent(new EntityInteractEvent());
		addEvent(new EntityTargetEvent());
		addEvent(new InventoryClickEvent());
		addEvent(new PlayerJoinEvent());
		this.init();
		this.load();
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				npcManager.update();
				playerTaskManager.update();
			}
			
		}, 0, 0);
	}
	
	@Override
	public void onDisable() {
		this.save();
	}
	
	public void init() {
		this.config = new Config(this.getConfig());
		this.permissionHelper = new PermissionHelper();
		this.guiManager = new GuiManager();
		this.npcManager = new NPCManager();
		this.taskManager = new TaskManager();
		this.playerTaskManager = new PlayerTaskManager();
		this.gui = new GuiQuest(Config.GUI_NAME, Config.GUI_SIZE);
	}
	
	public void load() {
		if (!getConfig().isSet("npc.list")) return;
		for (String s : getConfig().getStringList("npc.list")) {
			String[] args = s.split(",");
			String className = args[0], worldName = args[2];
			UUID uuid = UUID.fromString(args[1]);
			Class<? extends NPC> c = npcManager.getNPCClassByName(className);
			Entity entity = npcManager.getEntityByUUID(getServer().getWorld(worldName), uuid);
			try {
				NPC npc = (NPC) c.getConstructors()[0].newInstance(new Object[] {entity});
				NPCManager.npcList.add(npc);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save() {
		List<String> arrayList = new ArrayList<>();
		for (NPC n : NPCManager.npcList) {
			arrayList.add(n.getClass().getName() + "," + n.getEntity().getUniqueId().toString() + "," + n.getEntity().getWorld().getName());
		}
		getConfig().set("npc.list", arrayList);
		saveConfig();
	}
	
	/** add new command */
	public void addCommand(String cmd, CommandExecutor executor) {
		Bukkit.getPluginCommand(cmd).setExecutor(executor);
	}
	
	/** add new event */
	public void addEvent(Event e) {
		Bukkit.getPluginManager().registerEvents(e, this);
	}

	public static Quest getQuest() {
		return quest;
	}
	
}
