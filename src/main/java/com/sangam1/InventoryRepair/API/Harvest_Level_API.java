package com.sangam1.InventoryRepair.API;

import java.util.HashMap;
import java.util.Map;

public class Harvest_Level_API {
	
	private static Map<Integer,String> level = new HashMap<Integer,String>();
	
	public static void setup() {
		level.put(-1, "Hand");
		level.put(0, "Wood");
		level.put(1, "Stone");
		level.put(2, "Iron");
		level.put(3, "Diamond");
	}
	
	public static String get_level(int a) {
		return level.get(a);
	}

}
