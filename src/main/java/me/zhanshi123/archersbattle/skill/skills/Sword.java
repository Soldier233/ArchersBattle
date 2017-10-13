package me.zhanshi123.archersbattle.skill.skills;

import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.managers.CooldownManager;
import me.zhanshi123.archersbattle.managers.SkillManager;
import me.zhanshi123.archersbattle.messages.Messages;
import me.zhanshi123.archersbattle.skill.Skill;
import me.zhanshi123.archersbattle.skill.SkillType;
import me.zhanshi123.archersbattle.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Sword extends Skill implements Listener {

    public Sword(String skillName) {
        super(skillName);
    }

    public void register() {
        ItemStack selector = new ItemStack(Material.WOOD_SWORD);
        ItemMeta im = selector.getItemMeta();
        im.setDisplayName("§a木剑");
        selector.setItemMeta(im);
        this.setSelector(selector);

        ItemStack show = new ItemStack(Material.WOOD_SWORD);
        im = show.getItemMeta();
        im.setDisplayName("§a木剑");
        im.spigot().setUnbreakable(true);
        show.setItemMeta(im);
        this.setShow(show);
        this.setSkillType(SkillType.ITEM);
        SkillManager.getInstance().register(this);
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void launch(Player p, Vector vector) {
        CooldownManager.getInstance().add(p);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!Utils.isInArena(e.getPlayer()))
            return;
        if (!SkillManager.getInstance().getSkillByShow(e.getPlayer().getItemInHand()).equals(this))
            return;
        Player p = e.getPlayer();
        if (!CooldownManager.getInstance().exists(p)) {
            launch(p, (Vector) null);
        } else {
            long left = CooldownManager.getInstance().getLeft(p, 3000L);
            if (left == 0) {
                launch(p, null);
            } else {
                p.sendMessage(Messages.prefix + Messages.Cooldown.replace("%time%", String.valueOf(left)));
                e.setCancelled(true);
            }
        }
    }
}
