package net.mcmhsj.archersbattle.managers;

import java.util.ArrayList;
import java.util.List;

import net.mcmhsj.archersbattle.Arena;

public class ArenaManager {
	private static List<Arena> arenas=new ArrayList<Arena>();
	//启用的竞技场
	public static List<Arena> getArenas()
	{
		return arenas;
	}
	public static void addArena(Arena arena)
	{
		arenas.add(arena);
	}
	public static boolean checkArena(Arena arena)
	{
		return arenas.contains(arena);
	}
	public static void removeArena(Arena arena)
	{
		arenas.remove(arena);
	}

}
