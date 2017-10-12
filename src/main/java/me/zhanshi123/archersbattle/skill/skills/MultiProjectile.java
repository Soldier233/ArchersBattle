package me.zhanshi123.archersbattle.skill.skills;

import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.Upgrade;
import me.zhanshi123.archersbattle.managers.SkillManager;
import me.zhanshi123.archersbattle.managers.UpgradeManager;
import me.zhanshi123.archersbattle.skill.Skill;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiProjectile extends Skill implements Listener {

    public MultiProjectile(String skillName) {
        super(skillName);
    }

    @Override
    public void launch(Player p, Vector vector) { }

    public void register() {
        ItemStack selector = new ItemStack(Material.ARROW);
        ItemMeta im = selector.getItemMeta();
        im.setDisplayName("§a前方射出的武器+1");
        selector.setItemMeta(im);
        this.setSelector(selector);

        SkillManager.getInstance().register(this);
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        if(e.isCancelled())
            return;
        if(!(e.getEntity().getShooter() instanceof Player))
            return;
        Player p=(Player)e.getEntity().getShooter();
        Skill skill= Skill.getSkillByProjectile(e.getEntity());
        Map.Entry<Skill,Upgrade> entry= UpgradeManager.getInstance().getEntry(p,skill);
        List<Vector> vectors = new ArrayList<>();
        if(entry!=null){
            int level=entry.getValue().getLevel();
            Location loc=p.getLocation();
            for(int i=0;i<=5;i++){
                switch(i){
                    case 1:
                        break;
                    case 2:
                        Location location=loc.clone();
                        location.setYaw((loc.getYaw()-10)%180);
                        vectors.add(location.toVector());
                        break;
                    case 3:
                        location=loc.clone();
//                        location.setYaw(loc.get);
                }
            }

        }
    }
}
