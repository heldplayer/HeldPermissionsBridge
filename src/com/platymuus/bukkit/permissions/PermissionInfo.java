
package com.platymuus.bukkit.permissions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            for (String key : this.main.permsPlugin.getGroups(this.path, true)) {
                Group group = this.main.getGroup(key);
                if (group != null) {
                    result.add(group);
                }
            }
        }

        return result;
    }

    public Map<String, Boolean> getPermissions() {
        return this.main.permsPlugin.getPermissions((this.isGroup ? "groups." : "users.") + this.path);
    }

    public Set<String> getWorlds() {
        List<String> worlds = this.main.permsPlugin.getAllWorlds((this.isGroup ? "groups." : "users.") + this.path);

        return new HashSet<String>(worlds);
        //HashMap<String, HashMap<String, Boolean>> worldPerms = main.permsPlugin.getWorldPermissions((isGroup ? "groups." : "users.") + path);

        //return worldPerms.keySet();
    }

    /*
     * public List<String> getWorlds() {
     * return main.permsPlugin.getAllWorlds((isGroup ? "groups." : "users.") +
     * path);
     * }
     */

    public Map<String, Boolean> getWorldPermissions(String world) {
        return this.main.permsPlugin.getWorldPermissions(this.path, world);
    }
}
