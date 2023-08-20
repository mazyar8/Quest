package dev.mazyar8.quest.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import dev.mazyar8.quest.Quest;
import dev.mazyar8.quest.config.Config;
import dev.mazyar8.quest.npc.NPCManager;
import dev.mazyar8.quest.permissions.PermissionHelper;

public class QuestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String message, String[] args) {
		if (args.length > 0) {
			/** create NPC */
			if (args[0].toLowerCase().equals("create")) {
				if (hasPermission(sender, PermissionHelper.COMMAND_CREATE)) {
					if (sender instanceof Player) {
						Quest.getQuest().npcManager.spawnQuestNPC(((Player) sender).getLocation());
						return true;
					}
					sender.sendMessage(Config.ERROR_COMMAND_JUST_PLAYERS_ALLOW_TO_USE);
				}else {
					sender.sendMessage(Config.ERROR_COMMAND_NO_PERMISSION);
				}
			}
			
			/** delete NCP */
			if (args[0].toLowerCase().equals("delete")) {
				if (hasPermission(sender, PermissionHelper.COMMAND_DELETE)) {
					if (sender instanceof Player) {
						if (args.length > 1) {
							NPCManager.deleteNPC((Player) sender, args[1]);
						}else {
							sender.sendMessage(ChatColor.RED + "Usage: /" + cmd.getName() + " delete <all/near/nearest>");
						}
						return true;
					}
					sender.sendMessage(Config.ERROR_COMMAND_JUST_PLAYERS_ALLOW_TO_USE);
				}else {
					sender.sendMessage(Config.ERROR_COMMAND_NO_PERMISSION);
				}
			}
		}else {
			if (hasPermission(sender, PermissionHelper.COMMAND_USE)) {
				if (sender instanceof Player) {
					Quest.getQuest().gui.showGUI((Player) sender);
					return true;
				}
				sender.sendMessage(Config.ERROR_COMMAND_JUST_PLAYERS_ALLOW_TO_USE);
			}else {
				sender.sendMessage(Config.ERROR_COMMAND_NO_PERMISSION);
			}
		}
		return true;
	}
	
	/** check if sender has that permission or administrator permission, it returns true */
	public boolean hasPermission(CommandSender sender, Permission permission) {
		return sender.hasPermission(PermissionHelper.ADMINISTRATOR) || sender.hasPermission(permission);
	}

}
