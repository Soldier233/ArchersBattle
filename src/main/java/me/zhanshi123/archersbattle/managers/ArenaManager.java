package me.zhanshi123.archersbattle.managers;

import me.zhanshi123.archersbattle.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    private static List<Arena> arenas = new ArrayList<Arena>();

    //���õľ�����
    public static List<Arena> getArenas() {
        return arenas;
    }

    public static void addArena(Arena arena) {
        arenas.add(arena);
    }

    public static boolean checkArena(Arena arena) {
        return arenas.contains(arena);
    }

    public static void removeArena(Arena arena) {
        arenas.remove(arena);
    }

}
