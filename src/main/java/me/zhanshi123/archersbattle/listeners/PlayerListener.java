package me.zhanshi123.archersbattle.listeners;

import me.zhanshi123.archersbattle.Arena;
import me.zhanshi123.archersbattle.managers.CooldownManager;
import me.zhanshi123.archersbattle.messages.Messages;
import me.zhanshi123.archersbattle.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        if (!Utils.isInArena(p)) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (!Utils.isInArena(p)) {
            return;
        }
        int level = p.getLevel();
        ExperienceOrb exp = (ExperienceOrb) p.getWorld().spawnEntity(p.getLocation(), EntityType.EXPERIENCE_ORB);
        exp.setExperience(level * 2);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getDamager();
        long left = CooldownManager.getInstance().getLeft(p, 3000);
        if (left == 0) {
            CooldownManager.getInstance().add(p);
            return;
        }
        e.setCancelled(true);
        p.sendMessage(Messages.prefix + Messages.Cooldown.replace("%time%", String.valueOf(left)));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Utils.isInArena(p)) {
            Arena a = Utils.getAreana(p);
            a.removePlayer(p);
        }
    }

    @EventHandler
    public void onSpawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (Utils.isInArena(p)) {
            Arena arena = Utils.getAreana(p);
            Location loc = arena.getRandomSpawnLocation();
            if (loc == null) {
                p.sendMessage(Messages.prefix + Messages.SpawnLocationsNotFound);
            } else {
                e.setRespawnLocation(loc);
            }
            Utils.fillInventory(p);
            p.setLevel(0);
            Utils.flushSkillSelection(p);
        }
    }

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        if (!Utils.isInArena(e.getPlayer())) {
            return;
        }
        if (e.getNewLevel() == 0) {
            return;
        }
        if (e.getNewLevel() > 30) {
            e.getPlayer().setLevel(30);
        } else {
            Player p = e.getPlayer();
            Utils.flushSkillSelection(p);
        }
    }
}
