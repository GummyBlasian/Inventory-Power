package com.github.GummyBlasian.InventoryPower;

import com.github.GummyBlasian.InventoryPower.Item.ItemGoCraftTable;
import com.github.GummyBlasian.InventoryPower.References.ItemsList;
import com.github.GummyBlasian.InventoryPower.References.StartupRef;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = StartupRef.MODID)
public final class Registry {

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll
		(
				//Items
				/*done*/		ItemsList.gocraft = new ItemGoCraftTable(StartupRef.MODID + ".gocraft").setCreativeTab(Main.creativeTab)
				);

		System.out.println("InventoryPower: Item Registration Complete");
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(ItemsList.gocraft, 0, new ModelResourceLocation(ItemsList.gocraft.getRegistryName(), "inventory"));
	}


}