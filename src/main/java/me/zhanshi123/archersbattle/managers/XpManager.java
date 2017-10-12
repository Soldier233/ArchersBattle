package me.zhanshi123.archersbattle.managers;

import me.zhanshi123.archersbattle.Arena;
import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.XpGen;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class XpManager {
    private static int TaskID = -1;
    HashMap<XpGen, Integer> data = new HashMap<XpGen, Integer>();

    public XpManager() {
        new BukkitRunnable() {
            public void run() {
                if (TaskID == -1) {
                    TaskID = this.getTaskId();
                }
                for (Arena arena : ArenaManager.getArenas()) {
                    if (arena.getPlayers().size() != 0) {
                        for (XpGen gen : arena.getXpGenerators()) {
                            if (!data.containsKey(gen)) {
                                data.put(gen, 1);
                            } else {
                                int value = data.get(gen);
                                data.remove(gen);
                                data.put(gen, value + 1);
                            }
                            if (data.get(gen) == gen.getInterval()) {
                                ExperienceOrb exp = (ExperienceOrb) gen.getLocation().getWorld().spawnEntity(gen.getLocation(), EntityType.EXPERIENCE_ORB);
                                exp.setExperience(1);
                                data.remove(gen);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 20L, 20L);
    }

    public static int getTaskID() {
        return TaskID;
    }
}
