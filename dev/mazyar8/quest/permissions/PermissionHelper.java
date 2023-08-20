package dev.mazyar8.quest.permissions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class PermissionHelper {
	
	public static final Permission ADMINISTRATOR = new Permission("quest.admin");
	public static final Permission COMMAND_CREATE = new Permission("quest.command.create");
	public static final Permission COMMAND_DELETE = new Permission("quest.command.delete");
	public static final Permission COMMAND_USE = new Permission("quest.command.use");
	public static final Permission NPC_QUEST = new Permission("quest.npc.quest");

	public PermissionHelper() {
		addPermission(ADMINISTRATOR);
		addPermission(COMMAND_CREATE);
		addPermission(COMMAND_DELETE);
		addPermission(COMMAND_USE);
		addPermission(NPC_QUEST);
	}
	
	/** add new permission */
	public void addPermission(Permission permission) {
		Bukkit.getPluginManager().addPermission(permission);
	}
	
	/** check if player has that permission or administrator permission, it returns true */
	public static boolean hasPermission(Player player, Permission permission) {
		return player.hasPermission(PermissionHelper.ADMINISTRATOR) || player.hasPermission(permission);
	}
	
}
