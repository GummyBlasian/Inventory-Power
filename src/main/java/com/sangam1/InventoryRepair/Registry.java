package com.sangam1.InventoryRepair;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.Logger;

import com.sangam1.InventoryRepair.GUI.Container.PCT_Container;
import com.sangam1.InventoryRepair.Item.item_portable_crafting;
import com.sangam1.InventoryRepair.References.ItemList;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;


@EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class Registry {

	public static final ItemGroup IRGroup = Main.IRGroup;
	public static final Logger LOGGER = Main.LOGGER;
	public static final String MODID = Main.MODID;
	public static Set<Entry<ResourceLocation,Item>> ItemsList = new HashSet<Entry<ResourceLocation,Item>>();
	public static Set<Entry<ResourceLocation,Block>> BlocksList = new HashSet<Entry<ResourceLocation,Block>>();
	
	/* New Items require:
	 * a model
	 * a texture
	*/
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll
    	(
//Items
    		ItemList.gocraft = new item_portable_crafting(new Item.Properties().group(IRGroup).maxStackSize(1)).setRegistryName(locationItems("gocraft"))
   	
    	);
		
    	LOGGER.info("Items registered!");
    	
		ItemsList = event.getRegistry().getEntries();

		//LOGGER.info("MudBall ITEM: " + ItemList.gocraft.getRegistryName());
		//LOGGER.info("WetSand ITEM: " + ItemList.item_block_wetsand.getRegistryName());

	}
	
	/* New Blocks require:
	 * a blockstates
	 * a model
	 * a texture
	 * an Item (see requirements above)
	*/
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll
    	(
    	
    	);
    	
		//LOGGER.info("WetSand BLOCK: " + BlockList.block_wetsand.getRegistryName());
    	LOGGER.info("Blocks registered!");
    	
		BlocksList = event.getRegistry().getEntries();
	}
	
	@SubscribeEvent
	public static void registerContainer(final RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().registerAll(

				IForgeContainerType.create((windowId, playerInventory, data) -> new PCT_Container(windowId, playerInventory, data)).setRegistryName(locationGUI("portable_crafting_table"))
		
		);
	}
	
	@SuppressWarnings("unused")
	private static ResourceLocation locationBlock (String name) {
    	return new ResourceLocation(MODID, name);
    }
	
	private static ResourceLocation locationItems (String name) {
    	return new ResourceLocation(MODID, name);
    }
	
	private static ResourceLocation locationGUI (String name) {
    	return new ResourceLocation(MODID, name);
    }
	
	@SuppressWarnings("unused")
	private static ResourceLocation locationItemsStack (String name) {
    	return new ResourceLocation(MODID +":blocks/" + name);
    }
}