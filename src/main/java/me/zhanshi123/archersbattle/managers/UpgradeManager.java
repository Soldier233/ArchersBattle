package me.zhanshi123.archersbattle.managers;

import me.zhanshi123.archersbattle.Upgrade;
import me.zhanshi123.archersbattle.skill.Skill;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class UpgradeManager {
    private static UpgradeManager instance;
    HashMap<Skill, Upgrade> data = new HashMap<>();

    public UpgradeManager() {
        instance = this;
    }

    public static UpgradeManager getInstance() {
        return instance;
    }

    public boolean containPlayer(Player p) {
        boolean contain = false;
        for (Map.Entry<Skill, Upgrade> u : data.entrySet()) {
            if (u.getValue().getPlayer().getName().equalsIgnoreCase(p.getName())) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    public void removeUpgrade(Player p) {
        for (Map.Entry<Skill, Upgrade> u : data.entrySet()) {
            if (u.getValue().getPlayer().getName().equalsIgnoreCase(p.getName())) {
                data.remove(u);
                break;
            }
        }
    }

    public void removeUpgrade(Skill skill) {
        for (Map.Entry<Skill, Upgrade> u : data.entrySet()) {
            if (u.getKey().equals(skill)) {
                data.remove(u);
                break;
            }
        }
    }

    public void add(Upgrade upgrade, Skill skill) {
        removeUpgrade(skill);
        data.put(skill, upgrade);
    }

    public Map.Entry<Skill, Upgrade> getEntry(Player p, Skill skill) {
        Map.Entry<Skill, Upgrade> entry = null;
        for (Map.Entry<Skill, Upgrade> u : data.entrySet()) {
            if (u.getValue().getPlayer().getName().equalsIgnoreCase(p.getName())) {
//                System.out.println(u.getValue().getPlayer().getName());
//                System.out.println(u.getValue().getLevel());
//                System.out.println(u.getKey().toString());
//                System.out.println(skill.toString());
                if (u.getKey().equals(skill)) {
                    entry = u;
                    break;
                }
            }
        }
        return entry;
    }

    public Upgrade getUpgrade(Player p) {
        Upgrade upgrade = null;
        for (Map.Entry<Skill, Upgrade> u : data.entrySet()) {
            if (u.getValue().getPlayer().getName().equalsIgnoreCase(p.getName())) {
                upgrade = u.getValue();
                break;
            }
        }
        return upgrade;
    }
}
