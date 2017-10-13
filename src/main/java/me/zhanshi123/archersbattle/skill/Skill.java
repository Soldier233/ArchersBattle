package me.zhanshi123.archersbattle.skill;

import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.managers.SkillManager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
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

    public static Skill getSkillByProjectile(Projectile projectile) {
        List<MetadataValue> value = projectile.getMetadata("skill_type");
        Skill s=SkillManager.getInstance().getSkillByName(getMetadataValue(value));
        return s;
    }

    private static String getMetadataValue(List<MetadataValue> values) {
        String text = null;
        for (MetadataValue v : values) {
            if (v.getOwningPlugin().getName().equalsIgnoreCase(Main.getInstance().getName())) {
                text = (String) v.value();
                break;
            }
        }
        return text;
    }

    public String getName() {
        return skillName;
    }

    public abstract void launch(Player p, Vector vector);

    public ItemStack getShow() {
        return show;
    }

    public void setShow(ItemStack show) {
        this.show = show;
    }

    public ItemStack getSelector() {
        return selector;
    }

    public void setSelector(ItemStack selector) {
        this.selector = selector;
    }

    public SkillType getSkillType() {
        return type;
    }

    public void setSkillType(SkillType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                ", show=" + show +
                ", selector=" + selector +
                ", type=" + type +
                '}';
    }
}
