package com.github.GummyBlasian.InventoryPower.API;

import java.util.HashMap;
import java.util.Map;

public class HarvestLevelAPI {
	
	private static Map<Integer,String> level = new HashMap<Integer,String>();
	
	public static void setup() {
		level.put(-1, "Hand");
		level.put(0, "Wood");
		level.put(1, "Stone");
		level.put(2, "Iron");
		level.put(3, "Diamond");
	}
	
	public static String getLevel(int a) {
		return level.get(a);
	}

}
