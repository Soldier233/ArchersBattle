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
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

import java.util.List;

public class FireBall extends Skill implements Listener {

    public FireBall(String skillName) {
        super(skillName);
    }

    public void register() {
        ItemStack selector = new ItemStack(Material.BLAZE_ROD);
        ItemMeta im = selector.getItemMeta();
        im.setDisplayName("§a火球术");
        selector.setItemMeta(im);
        this.setSelector(selector);
        ItemStack show = new ItemStack(Material.BLAZE_ROD);
        im = show.getItemMeta();
        im.setDisplayName("§a火球术§7(右键使用)");
        show.setItemMeta(im);
        this.setShow(show);
        this.setSkillType(SkillType.ITEM);
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        SkillManager.getInstance().register(this);
    }

    @Override
    public void launch(Player p, Vector vector) {
        Fireball fb = p.launchProjectile(Fireball.class);
        if (vector == null) {
            fb.setVelocity(p.getEyeLocation().getDirection().multiply(2));
        } else {
            fb.setVelocity(vector.multiply(2));
        }
        fb.setMetadata("skill_type", new FixedMetadataValue(Main.getInstance(), getName()));
        CooldownManager.getInstance().add(p);
    }

    @Override
    public void launchList(Player p, List<Vector> vectors) {
        p.sendMessage(String.valueOf(vectors.size()));
        for(Vector v:vectors){
            Fireball fb = p.launchProjectile(Fireball.class);
            fb.setDirection(v.multiply(2));
            fb.setMetadata("skill_type", new FixedMetadataValue(Main.getInstance(), getName()));
        }

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
            launch(p, null);
        } else {
            long left = CooldownManager.getInstance().getLeft(p, 3000L);
            if (left == 0) {
                launch(p, null);
            } else {
                p.sendMessage(Messages.prefix + Messages.Cooldown.replace("%time%", String.valueOf(left)));
            }
        }
    }

}
