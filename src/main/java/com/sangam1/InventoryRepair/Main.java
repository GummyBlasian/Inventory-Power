package com.sangam1.InventoryRepair;

import java.io.File;

import com.sangam1.InventoryRepair.Events.Client.LookTickEvent;
import com.sangam1.InventoryRepair.Events.Client.OnTickEvent;
import com.sangam1.InventoryRepair.Events.Client.RenderModeButtonsTickEvent;
import com.sangam1.InventoryRepair.Events.Server.ServerGamemodeTickEvent;
import com.sangam1.InventoryRepair.Events.Server.ServerTickEvent;
import com.sangam1.InventoryRepair.Events.Server.ServerTickEventOnly;
import com.sangam1.InventoryRepair.GUI.RenderGUIHandler;
import com.sangam1.InventoryRepair.References.CreativeTabsList;
import com.sangam1.InventoryRepair.References.DifferentBlocks;
import com.sangam1.InventoryRepair.References.StartupRef;
import com.sangam1.InventoryRepair.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = StartupRef.MODID, version = StartupRef.VERSION, updateJSON = StartupRef.UPDATEURL, name = StartupRef.MODID, guiFactory = StartupRef.GUIFACTORY)
public class Main {

	@SidedProxy(serverSide = "com.sangam1.InventoryRepair.proxy.CommonProxy", clientSide = "com.sangam1.InventoryRepair.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(StartupRef.MODID)
	public static Main instance;

	public static final CreativeTabsList creativeTab = new CreativeTabsList();

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		StartupRef.configDir = new File(e.getModConfigurationDirectory() + "/" + StartupRef.MODID);
		StartupRef.configDir.mkdirs();
		ConfigHandler.init(new File(StartupRef.configDir.getPath(), StartupRef.MODID + ".cfg"));
		ModItems.init();
		MinecraftForge.EVENT_BUS.register(new OnTickEvent());
		//MinecraftForge.EVENT_BUS.register(new ArmorDurabilityTickEvent());
		MinecraftForge.EVENT_BUS.register(new LookTickEvent());
		//MinecraftForge.EVENT_BUS.register(new RenderModeButtonsTickEvent());
		MinecraftForge.EVENT_BUS.register(new RenderGUIHandler());
		MinecraftForge.EVENT_BUS.register(new ServerTickEventOnly());
		MinecraftForge.EVENT_BUS.register(new ServerTickEvent());
		//MinecraftForge.EVENT_BUS.register(new ServerGamemodeTickEvent());
		DifferentBlocks.init();
		RecipeRegister.Register();
		System.out.println("InventoryRepair: Pre-Init Complete");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
		System.out.println("InventoryRepair: Init Complete");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		System.out.println("InventoryRepair: Post-Init Complete");
	}
}