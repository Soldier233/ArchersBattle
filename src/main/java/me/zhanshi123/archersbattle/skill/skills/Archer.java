package me.zhanshi123.archersbattle.skill.skills;

import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.managers.CooldownManager;
import me.zhanshi123.archersbattle.managers.SkillManager;
import me.zhanshi123.archersbattle.messages.Messages;
import me.zhanshi123.archersbattle.skill.Skill;
import me.zhanshi123.archersbattle.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

public class Archer extends Skill implements Listener {

    public Archer(String skillName) {
        super(skillName);
    }

    public void register() {
        ItemStack selector = new ItemStack(Material.BOW);
        ItemMeta im = selector.getItemMeta();
        im.setDisplayName("§a弓");
        selector.setItemMeta(im);
        this.setSelector(selector);
        ItemStack show = new ItemStack(Material.BOW);
        im = show.getItemMeta();
        im.setDisplayName("§a弓§7(右键使用)");
        show.setItemMeta(im);
        this.setShow(show);
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        SkillManager.getInstance().register(this);
    }

    @Override
    public void launch(Player p, Vector vector) {
        Arrow arrow = p.launchProjectile(Arrow.class);
        if(vector==null){
            arrow.setVelocity(p.getEyeLocation().getDirection().multiply(2));
        }
        else{
            arrow.setVelocity(vector);
        }
        arrow.setShooter(p);
        arrow.setMetadata("skill_type", new FixedMetadataValue(Main.getInstance(),getName()));
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
            launch(p,null);
        } else {
            long left = CooldownManager.getInstance().getLeft(p, 3000L);
            if (left == 0) {
                launch(p,null);
            } else {
                p.sendMessage(Messages.prefix + Messages.Cooldown.replace("%time%", String.valueOf(left)));
            }
        }
    }
}
