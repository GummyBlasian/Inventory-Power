package com.github.GummyBlasian.InventoryPower;

import com.github.GummyBlasian.InventoryPower.Events.Client.ArmorDurabilityTickEvent;
import com.github.GummyBlasian.InventoryPower.Events.Client.LookTickEvent;
import com.github.GummyBlasian.InventoryPower.Events.Client.OnTickEvent;
import com.github.GummyBlasian.InventoryPower.Events.Server.ServerTickEvent;
import com.github.GummyBlasian.InventoryPower.Events.Server.ServerTickEventOnly;
import com.github.GummyBlasian.InventoryPower.GUI.RenderGUIHandler;
import com.github.GummyBlasian.InventoryPower.References.CreativeTabsList;
import com.github.GummyBlasian.InventoryPower.References.DifferentBlocks;
import com.github.GummyBlasian.InventoryPower.References.StartupRef;
import com.github.GummyBlasian.InventoryPower.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = StartupRef.MODID, version = StartupRef.VERSION, updateJSON = StartupRef.UPDATEURL, name = StartupRef.MODID)
public class Main {

	@SidedProxy(serverSide = "com.github.GummyBlasian.InventoryPower.proxy.CommonProxy", clientSide = "com.github.GummyBlasian.InventoryPower.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(StartupRef.MODID)
	public static Main INSTANCE;

	public static final CreativeTabsList creativeTab = new CreativeTabsList();

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new OnTickEvent());
		MinecraftForge.EVENT_BUS.register(new ArmorDurabilityTickEvent());
		MinecraftForge.EVENT_BUS.register(new LookTickEvent());
		MinecraftForge.EVENT_BUS.register(new RenderGUIHandler());
		MinecraftForge.EVENT_BUS.register(new ServerTickEventOnly());
		MinecraftForge.EVENT_BUS.register(new ServerTickEvent());
		DifferentBlocks.init();
		System.out.println("InventoryPower: Pre-Init Complete");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.INSTANCE, new RenderGUIHandler());
		System.out.println("InventoryPower: Init Complete");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		System.out.println("InventoryPower: Post-Init Complete");
	}
}