package com.sangam1.InventoryRepair.Config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

public final class ConfigHolder {

	public static final ForgeConfigSpec CLIENT_SPEC;
	static final ClientConfig CLIENT;
	public static final ForgeConfigSpec SERVER_SPEC;
	static final ServerConfig SERVER;
	
	static {
		{
			final Pair<ClientConfig, ForgeConfigSpec> specPair = new Builder().configure(ClientConfig::new);
			CLIENT = specPair.getLeft();
			CLIENT_SPEC = specPair.getRight();
		}
		{
			final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
			SERVER = specPair.getLeft();
			SERVER_SPEC = specPair.getRight();
		}
	}
}