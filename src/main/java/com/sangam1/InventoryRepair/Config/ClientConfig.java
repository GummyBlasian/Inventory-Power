package com.sangam1.InventoryRepair.Config;

import com.sangam1.InventoryRepair.Main;

import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

final class ClientConfig {

	final BooleanValue LargeArmorHUD;
	final BooleanValue SmallArmorHUD;
	final BooleanValue ArmorHUD;
	final BooleanValue LookingAtHUD;
	final BooleanValue TimeHUD;
	final BooleanValue PotionsHUD;
	final BooleanValue AutoRepair;
	final BooleanValue DataHUD;

	ClientConfig(final Builder builder) {
		builder.push("general");
		LargeArmorHUD = builder
				.comment("Large sized Armor HUD (true to enable, false to disable)")
				.translation(Main.MODID + ".config.LargeArmorHUD")
				.define("LargeArmorHUD", false);
		SmallArmorHUD = builder
				.comment("Small sized Armor HUD (true to enable, false to disable)")
				.translation(Main.MODID + ".config.SmallArmorHUD")
				.define("SmallArmorHUD", true);
		ArmorHUD = builder
				.comment("Armor HUD (true to enable, false to disable)")
				.translation(Main.MODID + ".config.ArmorHUD")
				.define("ArmorHUD", true);
		LookingAtHUD = builder
				.comment("Looking At HUD (true to enable, false to disable)")
				.translation(Main.MODID + ".config.LookingAtHUD")
				.define("LookingAtHUD", true);
		TimeHUD = builder
				.comment("Time HUD (true to enable, false to disable)")
				.translation(Main.MODID + ".config.TimeHUD")
				.define("TimeHUD", true);
		PotionsHUD = builder
				.comment("Potions HUD (true to enable, false to disable)")
				.translation(Main.MODID + ".config.PotionsHUD")
				.define("PotionsHUD", true);
		AutoRepair = builder
				.comment("Auto Repair (true to enable, false to disable)")
				.translation(Main.MODID + ".config.AutoRepair")
				.define("AutoRepair", true);
		DataHUD = builder
				.comment("Biome/Location/Other data HUD (true to enable, false to disable)")
				.translation(Main.MODID + ".config.DataHUD")
				.define("DataHUD", true);
		builder.pop();
	}

}