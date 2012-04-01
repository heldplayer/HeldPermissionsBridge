package com.platymuus.bukkit.permissions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.heldplayer.permissions.Permissions;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PermissionsPlugin extends JavaPlugin {
	public Permissions permsPlugin;

	public void onEnable() {
		Plugin perms = getServer().getPluginManager().getPlugin("HeldPermissions");
		
		if (perms != null) {
			permsPlugin = (Permissions) perms;
		} else {
			getServer().getPluginManager().disablePlugin(this);
		}

		getLogger().info("[Spoof] Enabled!");
	}

	public void onDisable() {
		getLogger().info("[Spoof] Disabled!");
	}

	public Group getGroup(String groupName) {
		return new Group(this, groupName);
	}

	public List<Group> getGroups(String playerName) {
		ArrayList<Group> result = new ArrayList<Group>();
		
		List<String> groups = permsPlugin.getGroups(playerName, true);
		
		for(String group : groups){
			result.add(new Group(this, group));
		}

		return result;
	}

	public PermissionInfo getPlayerInfo(String playerName) {
		return new PermissionInfo(this, playerName, false);
	}

	public List<Group> getAllGroups() {
		ArrayList<Group> result = new ArrayList<Group>();
		
		for(Iterator<String> groups = permsPlugin.getAllGroups().iterator(); !groups.hasNext(); ){
			String group = groups.next();
			
			result.add(new Group(this, group));
		}

		return result;
	}
}