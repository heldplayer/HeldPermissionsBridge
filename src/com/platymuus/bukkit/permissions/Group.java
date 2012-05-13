package com.platymuus.bukkit.permissions;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Group {
	private PermissionsPlugin main;
	private String name;

	protected Group(PermissionsPlugin plugin, String name) {
		this.main = plugin;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public List<String> getPlayers() {
		return main.permsPlugin.getPlayersInGroup(name);
	}

	public List<Player> getOnlinePlayers() {
		ArrayList<Player> result = new ArrayList<Player>();
		for (String user : getPlayers()) {
			Player player = Bukkit.getServer().getPlayer(user);
			if ((player != null) && (player.isOnline())) {
				result.add(player);
			}
		}
		return result;
	}

	public PermissionInfo getInfo() {
		return new PermissionInfo(main, name, true);
	}

	public boolean equals(Object o) {
		return (o != null) && ((o instanceof Group)) && (this.name.equalsIgnoreCase(((Group) o).getName()));
	}

	public String toString() {
		return "Group{name=" + this.name + "}";
	}

	public int hashCode() {
		return this.name.hashCode();
	}
}