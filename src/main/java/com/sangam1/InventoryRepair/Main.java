package com.sangam1.InventoryRepair;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sangam1.InventoryRepair.API.HarvestLevelAPI;
import com.sangam1.InventoryRepair.Config.ConfigHandler;
import com.sangam1.InventoryRepair.Events.Client.InGameGuiEvent;
import com.sangam1.InventoryRepair.Events.Server.AutoRepairEvent;
import com.sangam1.InventoryRepair.Events.Server.NewPlayerEvent;
import com.sangam1.InventoryRepair.GUI.Armor_GUI_Event;
import com.sangam1.InventoryRepair.GUI.LookingAtGUI;
import com.sangam1.InventoryRepair.GUI.Potions_GUI;
import com.sangam1.InventoryRepair.References.DifferentBlocks;
import com.sangam1.InventoryRepair.References.ItemGroup_IRGroup;
import com.sangam1.InventoryRepair.proxy.ClientProxy;
import com.sangam1.InventoryRepair.proxy.IProxy;
import com.sangam1.InventoryRepair.proxy.ServerProxy;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("sangamir")
public class Main {

	public static Main instance;
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "sangamir";
	public static final ItemGroup IRGroup = new ItemGroup_IRGroup();

	public static ItemStack Clock;
	public static LookingAtGUI handler;

	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

	public Main() {
		instance = this;

		handler = new LookingAtGUI();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);

		MinecraftForge.EVENT_BUS.register(this);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT);

		Main.LOGGER.info(Main.MODID +" : " + "setup done!");
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		proxy.init();
		APISetup();
		Main.LOGGER.info(Main.MODID +" : " + "setup done!");
	}

	private void APISetup() {
		HarvestLevelAPI.setup();
	}

	private void onLoadComplete(final FMLClientSetupEvent event) 
	{
		DifferentBlocks.init();
		if (!ConfigHandler.CONFIG_VERSION.get().equals(ConfigHandler.CURRENT_VERSION)) {
			ConfigHandler.resetConfig();

			ConfigHandler.CONFIG_VERSION.set(ConfigHandler.CURRENT_VERSION);
			ConfigHandler.CONFIG_VERSION.save();
		}

		Clock = new ItemStack(Items.CLOCK);

		gui_registry();

		MinecraftForge.EVENT_BUS.register(new AutoRepairEvent());
		MinecraftForge.EVENT_BUS.register(new InGameGuiEvent());
		MinecraftForge.EVENT_BUS.register(new NewPlayerEvent());

		Main.LOGGER.info(Main.MODID +" : " + "onLoadComplete done!");
	}

	private void gui_registry() {
		MinecraftForge.EVENT_BUS.register(new LookingAtGUI());
		MinecraftForge.EVENT_BUS.register(new Armor_GUI_Event());
		MinecraftForge.EVENT_BUS.register(new Potions_GUI());
	}

}