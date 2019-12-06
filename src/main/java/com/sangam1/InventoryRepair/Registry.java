package com.sangam1.InventoryRepair;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.Logger;

import com.sangam1.InventoryRepair.References.ItemList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
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
/*done*/		//ItemList.gocraft = new Item(new Item.Properties().group(IRGroup)).setRegistryName(locationItems("gocraft"))
   	
    	);
    	LOGGER.info("Items registered!");
    	
		ItemsList = event.getRegistry().getEntries();

		//LOGGER.info("MudBall ITEM: " + ItemList.item_mudball.getRegistryName());
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
	
	@SuppressWarnings("unused")
	private static ResourceLocation locationBlock (String name) {
    	return new ResourceLocation(MODID, name);
    }
	
	private static ResourceLocation locationItems (String name) {
    	return new ResourceLocation(MODID +":items/" + name);
    }
	
	@SuppressWarnings("unused")
	private static ResourceLocation locationItemsStack (String name) {
    	return new ResourceLocation(MODID +":blocks/" + name);
    }
}