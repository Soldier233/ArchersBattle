package me.zhanshi123.archersbattle.listeners;

import me.zhanshi123.archersbattle.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class WorldListener implements Listener {
    @EventHandler
    public void onEE(EntityExplodeEvent e) {
        if (!Utils.isArenaWorld(e.getEntity().getWorld())) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onBE(BlockExplodeEvent e) {
        if (!Utils.isArenaWorld(e.getBlock().getWorld())) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (!Utils.isInArena(e.getPlayer())) {
            return;
        }
        if (!Utils.isArenaWorld(e.getBlock().getWorld())) {
            return;
        }
        if (e.getPlayer().isOp()) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (!Utils.isInArena(e.getPlayer())) {
            return;
        }
        if (!Utils.isArenaWorld(e.getBlock().getWorld())) {
            return;
        }
        if (e.getPlayer().isOp()) {
            return;
        }
        e.setCancelled(true);
    }
}
