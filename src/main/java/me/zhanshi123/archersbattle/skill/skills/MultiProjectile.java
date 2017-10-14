package me.zhanshi123.archersbattle.skill.skills;

import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.Upgrade;
import me.zhanshi123.archersbattle.managers.SkillManager;
import me.zhanshi123.archersbattle.managers.UpgradeManager;
import me.zhanshi123.archersbattle.skill.Skill;
import me.zhanshi123.archersbattle.skill.SkillType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
    public void launch(Player p, Vector vector) {
    }

    public void doUpgrade(Player player){
        Map.Entry<Skill, Upgrade> entry=UpgradeManager.getInstance().getEntry(player,this);
        if(entry==null){
            UpgradeManager.getInstance().add(new Upgrade(player,2),this);
        }
        else {
            int level = UpgradeManager.getInstance().getUpgrade(player).getLevel();
            UpgradeManager.getInstance().removeUpgrade(player);
            UpgradeManager.getInstance().add(new Upgrade(player, level + 1), this);
        }
    }

    public void register() {
        ItemStack selector = new ItemStack(Material.ARROW);
        ItemMeta im = selector.getItemMeta();
        im.setDisplayName("§a前方射出的武器+1");
        selector.setItemMeta(im);
        this.setSelector(selector);
        this.setSkillType(SkillType.UPGRADE);
        SkillManager.getInstance().register(this);
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        //TODO 删除调试，重写技能判断，重写位置计算
        if (e.isCancelled())
            return;
        if (!(e.getEntity().getShooter() instanceof Player))
            return;
        Player p = (Player) e.getEntity().getShooter();
        System.out.println("获取玩家实例");
//        Skill skill = Skill.getSkillByProjectile(e.getEntity());
        Skill skill=SkillManager.getInstance().getSkillByName("火球术");
        System.out.println("获取技能实例");
        Map.Entry<Skill, Upgrade> entry = UpgradeManager.getInstance().getEntry(p, skill);
        System.out.println("获取等级情况");
        List<Vector> vectors = new ArrayList<>();
//        int level = entry.getValue().getLevel();
        int level=5;
        Location loc = p.getEyeLocation();
        System.out.println("开始计算发射位置");
        for (int i = 1; i <= 5; i++) {
            switch (i) {
                case 1:
                    continue;
                case 2:
                    if(level>=2){
                        Location location = loc.clone();
                        location.setYaw((loc.getYaw() - 10) % 180);
                        vectors.add(location.getDirection());
                    }
                    continue;
                case 3:
                    if(level>=3){
                        Location location = loc.clone();
                        location.setYaw((loc.getYaw() + 10) % 180);
                        vectors.add(location.getDirection());
                    }
                    continue;
                case 4:
                    if(level>=4){
                        Location location = loc.clone();
                        location.setYaw((loc.getYaw() - 20) % 180);
                        vectors.add(location.getDirection());
                    }
                    continue;
                case 5:
                    if(level>=5){
                        Location location = loc.clone();
                        location.setYaw((loc.getYaw() + 20) % 180);
                        vectors.add(location.getDirection());
                    }
                    continue;
                default:
                    break;
            }
        }
        System.out.println("发射");
        skill.launchList(p,vectors);
        System.out.println(vectors.size());
//        if (entry != null) {
//            int level = entry.getValue().getLevel();
//
//            Location loc = p.getLocation();
//            for (int i = 0; i <= 5; i++) {
//                switch (i) {
//                    case 1:
//                        break;
//                    case 2:
//                        if(level<=2){
//                            Location location = loc.clone();
//                            location.setYaw((loc.getYaw() - 10) % 180);
//                            vectors.add(location.toVector());
//                        }
//                        break;
//                    case 3:
//                        if(level<=3){
//                            Location location = loc.clone();
//                            location.setYaw((loc.getYaw() + 10) % 180);
//                            vectors.add(location.toVector());
//                        }
//
//                    case 4:
//                        if(level<=4){
//                            Location location = loc.clone();
//                            location.setYaw((loc.getYaw() - 20) % 180);
//                            vectors.add(location.toVector());
//                        }
//                        break;
//                    case 5:
//                        if(level<=5){
//                            Location location = loc.clone();
//                            location.setYaw((loc.getYaw() + 20) % 180);
//                            vectors.add(location.toVector());
//                        }
//                        break;
//                }
//            }
//        }
    }
}
