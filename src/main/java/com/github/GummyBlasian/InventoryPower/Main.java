package com.github.GummyBlasian.InventoryPower;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.GummyBlasian.InventoryPower.API.HarvestLevelAPI;
import com.github.GummyBlasian.InventoryPower.Config.ConfigHolder;
import com.github.GummyBlasian.InventoryPower.Events.Client.InGameGuiEvent;
import com.github.GummyBlasian.InventoryPower.Events.Server.AutoRepairEvent;
import com.github.GummyBlasian.InventoryPower.Events.Server.NewPlayerEvent;
import com.github.GummyBlasian.InventoryPower.GUI.ArmorGUIEvent;
import com.github.GummyBlasian.InventoryPower.GUI.LookingAtGUI;
import com.github.GummyBlasian.InventoryPower.GUI.PotionsGUI;
import com.github.GummyBlasian.InventoryPower.References.DifferentBlocks;
import com.github.GummyBlasian.InventoryPower.References.ItemGroupIRGroup;
import com.github.GummyBlasian.InventoryPower.proxy.ClientProxy;
import com.github.GummyBlasian.InventoryPower.proxy.IProxy;
import com.github.GummyBlasian.InventoryPower.proxy.ServerProxy;

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
	public static final ItemGroup IRGroup = new ItemGroupIRGroup();

	public static ItemStack Clock;
	public static LookingAtGUI handler;

	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

	public Main() {
		instance = this;

		handler = new LookingAtGUI();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);

		MinecraftForge.EVENT_BUS.register(this);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);

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

		Clock = new ItemStack(Items.CLOCK);

		gui_registry();

		MinecraftForge.EVENT_BUS.register(new AutoRepairEvent());
		MinecraftForge.EVENT_BUS.register(new InGameGuiEvent());
		MinecraftForge.EVENT_BUS.register(new NewPlayerEvent());

		Main.LOGGER.info(Main.MODID +" : " + "onLoadComplete done!");
	}

	private void gui_registry() {
		MinecraftForge.EVENT_BUS.register(new LookingAtGUI());
		MinecraftForge.EVENT_BUS.register(new ArmorGUIEvent());
		MinecraftForge.EVENT_BUS.register(new PotionsGUI());
	}

}