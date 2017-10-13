package me.zhanshi123.archersbattle.messages;

import me.zhanshi123.archersbattle.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Messages {
    public static String prefix, Cooldown, XpGenAdded, LevelUp, SpawnLocationsNotFound, SpawnAdded, PlayersOnly, ArenasBelow, NotInArena, LeavedArena, AlreadyInArena, JoinedArena, AreanNotFound, ArenaAlreadyExists, ArenaCreated;
    FileConfiguration config;

    public Messages() {
        File f = new File(Main.getInstance().getDataFolder(), "messages.yml");
        config = new YamlConfiguration();
        boolean first = !f.exists();
        if (first) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            config.load(new InputStreamReader(new FileInputStream(f), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (first) {
            config.set("prefix", "§6§lArchersBattle §7>>> §r");
            config.set("commands.AlreadyInArena", "§c你已经在一个游戏中了");
            config.set("commands.JoinedArena", "§a成功加入竞技场%arena%");
            config.set("commands.AreanNotFound", "§c竞技场不存在");
            config.set("commands.ArenaAlreadyExists", "§c竞技场已存在");
            config.set("commands.ArenaCreated", "§a竞技场创建成功");
            config.set("commands.NotInArena", "§c你不在竞技场中");
            config.set("commands.LeavedArena", "§a成功退出了竞技场");
            config.set("commands.ArenasBelow", "§a以下为已加载的竞技场  分别对应 竞技场 游戏人数");
            config.set("commands.PlayersOnly", "§a该命令不能由控制台执行");
            config.set("commands.SpawnAdded", "§a重生点已添加");
            config.set("commands.XpGenAdded", "§a经验生成点已添加");
            config.set("ingame.SpawnLocationsNotFound", "§a未设置任何重生点");
            config.set("ingame.LevelUp", "§a你升级啦！赶快直接用数字键选择你想要的技能吧!");
            config.set("ingame.Cooldown", "§a对不起，请等待%time%毫秒");

            try {
                config.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        prefix = config.getString("prefix");
        AlreadyInArena = config.getString("commands.AlreadyInArena");
        JoinedArena = config.getString("commands.JoinedArena");
        AreanNotFound = config.getString("commands.AreanNotFound");
        ArenaAlreadyExists = config.getString("commands.ArenaAlreadyExists");
        ArenaCreated = config.getString("commands.ArenaCreated");
        NotInArena = config.getString("commands.NotInArena");
        LeavedArena = config.getString("commands.LeavedArena");
        ArenasBelow = config.getString("commands.ArenasBelow");
        PlayersOnly = config.getString("commands.PlayersOnly");
        SpawnAdded = config.getString("commands.SpawnAdded");
        SpawnLocationsNotFound = config.getString("ingame.SpawnLocationsNotFound");
        LevelUp = config.getString("ingame.LevelUp");
        XpGenAdded = config.getString("commands.XpGenAdded");
        Cooldown = config.getString("ingame.Cooldown");
    }
}
