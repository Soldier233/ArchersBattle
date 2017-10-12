package me.zhanshi123.archersbattle.skill;

import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.managers.SkillManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

import java.util.List;

public abstract class Skill {
    String skillName;
    ItemStack show, selector;
    SkillType type;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public String getName() {
        return skillName;
    }

    public abstract void launch(Player p, Vector vector);

    public ItemStack getShow() {
        return show;
    }

    public ItemStack getSelector() {
        return selector;
    }

    public void setShow(ItemStack show) {
        this.show = show;
    }

    public void setSelector(ItemStack selector) {
        this.selector = selector;
    }

    public void setSkillType(SkillType type) {
        this.type = type;
    }

    public SkillType getSkillType() {
        return type;
    }

    public static Skill getSkillByProjectile(Projectile projectile){
        List<MetadataValue> value= projectile.getMetadata("skill_type");
        return SkillManager.getInstance().getSkillByName(getMetadataValue(value));
    }

    private static String getMetadataValue(List<MetadataValue> values){
        String text=null;
        for(MetadataValue v:values){
            if(v.getOwningPlugin().getName().equalsIgnoreCase(Main.getInstance().getName())){
                text= (String) v.value();
                break;
            }
        }
        return text;
    }
}
