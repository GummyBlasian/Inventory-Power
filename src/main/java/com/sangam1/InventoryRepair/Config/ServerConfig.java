package com.sangam1.InventoryRepair.Config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
	
	ServerConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		builder.pop();
	}
	
}
