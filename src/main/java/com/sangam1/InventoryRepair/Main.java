package com.sangam1.InventoryRepair;

import java.io.File;

import com.sangam1.InventoryRepair.Events.LookTickEvent;
import com.sangam1.InventoryRepair.Events.OnTickEvent;
import com.sangam1.InventoryRepair.Events.ServerTickEvent;
import com.sangam1.InventoryRepair.Events.ServerTickEventOnly;
import com.sangam1.InventoryRepair.GUI.RenderGUIHandler;
import com.sangam1.InventoryRepair.References.DifferentBlocks;
import com.sangam1.InventoryRepair.References.ItemsList;
import com.sangam1.InventoryRepair.References.StartupRef;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = StartupRef.MODID, version = StartupRef.VERSION, updateJSON = StartupRef.UPDATEURL, name = StartupRef.MODID, guiFactory = StartupRef.GUIFACTORY)
public class Main {
	
	public static final CreativeTabs sangam1ir = new CreativeTabs(StartupRef.MODID + "sangam1ir"){
	    @Override @SideOnly(Side.CLIENT) public ItemStack getTabIconItem() {
	        return new ItemStack(ItemsList.ItemAutoSmelter);
	    }
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		StartupRef.configDir = new File(e.getModConfigurationDirectory() + "/" + StartupRef.MODID);
		StartupRef.configDir.mkdirs();
		ConfigHandler.init(new File(StartupRef.configDir.getPath(), StartupRef.MODID + ".cfg"));
		MinecraftForge.EVENT_BUS.register(new OnTickEvent());
		MinecraftForge.EVENT_BUS.register(new LookTickEvent());
		MinecraftForge.EVENT_BUS.register(new RenderGUIHandler());
		MinecraftForge.EVENT_BUS.register(new ServerTickEventOnly());
		MinecraftForge.EVENT_BUS.register(new ServerTickEvent());
		DifferentBlocks.init();
		ModItems.createItems();
		RecipeRegister.Register();
		System.out.println("InventoryRepair: Pre-Init Complete");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		System.out.println("InventoryRepair: Init Complete");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		System.out.println("InventoryRepair: Post-Init Complete");
	}
}