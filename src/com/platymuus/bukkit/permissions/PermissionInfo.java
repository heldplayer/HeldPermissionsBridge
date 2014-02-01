
package com.platymuus.bukkit.permissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PermissionInfo {
    private PermissionsPlugin main;
    private String path;
    private boolean isGroup;

    protected PermissionInfo(PermissionsPlugin plugin, String path, boolean isGroup) {
        this.main = plugin;
        this.path = path;
        this.isGroup = isGroup;
    }

    public List<Group> getGroups() {
        ArrayList<Group> result = new ArrayList<Group>();

        if (!this.isGroup) {
            for (String key : this.main.permsPlugin.getManager().getPlayer(this.path).getGroupNames()) {
                Group group = this.main.getGroup(key);
                if (group != null) {
                    result.add(group);
                }
            }
        }

        return result;
    }

    public Map<String, Boolean> getPermissions() {
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();
        if (this.isGroup) {
            this.main.permsPlugin.getManager().getGroup(this.path).buildPermissions(result, null);
        }
        else {
            this.main.permsPlugin.getManager().getPlayer(this.path).buildPermissions(result, null);
        }
        return result;
    }

    public Set<String> getWorlds() {
        if (this.isGroup) {
            return new TreeSet<String>(this.main.permsPlugin.getManager().getGroup(this.path).getWorldNames());
        }
        else {
            return new TreeSet<String>(this.main.permsPlugin.getManager().getGroup(this.path).getWorldNames());
        }
    }

    public Map<String, Boolean> getWorldPermissions(String world) {
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();
        if (this.isGroup) {
            this.main.permsPlugin.getManager().getGroup(this.path).buildPermissions(result, world);
        }
        else {
            this.main.permsPlugin.getManager().getPlayer(this.path).buildPermissions(result, world);
        }
        return result;
    }
}
