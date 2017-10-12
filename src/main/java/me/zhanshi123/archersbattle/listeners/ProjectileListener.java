package me.zhanshi123.archersbattle.listeners;

import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.utils.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ProjectileListener implements Listener {
    @EventHandler
    public void onHit(ProjectileHitEvent e) {
        if (!Utils.isArenaWorld(e.getEntity().getWorld())) {
            return;
        }
        if (!e.getEntityType().equals(EntityType.ARROW)) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                Entity arrow = e.getEntity();
                arrow.remove();
            }
        }.runTaskLater(Main.getInstance(), 100L);
    }
}
