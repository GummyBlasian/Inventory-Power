package com.github.GummyBlasian.InventoryPower.Config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {

	@SuppressWarnings("unused")
	private static ModConfig clientConfig;
	@SuppressWarnings("unused")
	private static ModConfig serverConfig;

	public static void bakeClient(final ModConfig config) {
		clientConfig = config;

		IRConfig.LargeArmorHUD = ConfigHolder.CLIENT.LargeArmorHUD.get();
		IRConfig.SmallArmorHud = ConfigHolder.CLIENT.SmallArmorHUD.get();
		IRConfig.ArmorHud = ConfigHolder.CLIENT.ArmorHUD.get();
		IRConfig.LookingAtHUD = ConfigHolder.CLIENT.AutoRepair.get();
		IRConfig.TimeHUD = ConfigHolder.CLIENT.DataHUD.get();
		IRConfig.PotionsHUD = ConfigHolder.CLIENT.LookingAtHUD.get();
		IRConfig.AutoRepair = ConfigHolder.CLIENT.PotionsHUD.get();
		IRConfig.DataHUD = ConfigHolder.CLIENT.TimeHUD.get();

	}
	
	public static void bakeServer(final ModConfig config) {
		serverConfig = config;

	}
	
	@SuppressWarnings("unused")
	private static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
		modConfig.getConfigData().set(path, newValue);
		modConfig.save();
	}

}
