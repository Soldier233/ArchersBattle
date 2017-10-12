package me.zhanshi123.archersbattle.managers;

import me.zhanshi123.archersbattle.skill.Skill;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkillManager {
    private static SkillManager sm = null;

    public SkillManager() {
        sm = this;
    }

    public static SkillManager getInstance() {
        return sm;
    }

    //Basic Methods End
    private List<Skill> skills = new ArrayList<Skill>();

    public List<Skill> getSkills() {
        return skills;
    }

    public void register(Skill skill) {
        skills.add(skill);
    }

    public Skill getSkillByName(String name) {
        Skill sr = null;
        ;
        for (Skill skill : skills) {
            if (skill.getName().equalsIgnoreCase(name)) {
                sr = skill;
                break;
            }
        }
        return sr;
    }

    public boolean isSkillSelector(ItemStack item) {
        boolean result = false;
        for (Skill s : skills) {
            if (s.getSelector().isSimilar(item)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Skill getSkillByShow(ItemStack item) {
        Skill result = null;
        for (Skill s : skills) {
            ItemStack skill = s.getShow();
            if (skill.isSimilar(item)) {
                result = s;
            }
        }
        return result;
    }

    public Skill getSkillBySelector(ItemStack item) {
        Skill result = null;
        for (Skill s : skills) {
            ItemStack skill = s.getSelector();
            if (skill.isSimilar(item)) {
                result = s;
            }
        }
        return result;
    }

    private Skill getRandomSkill(List<Skill> ss) {
        Skill s = this.skills.get(new Random().nextInt(this.skills.size()));
        if (!ss.contains(s)) {
            return s;
        } else {
            return getRandomSkill(ss);
        }

    }

    public List<Skill> getRandomSkills() {
        List<Skill> skills = new ArrayList<Skill>();
        for (int i = 0; i <= 2; i++) {
            skills.add(getRandomSkill(skills));
        }
        return skills;
    }
}
