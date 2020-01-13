package com.github.GummyBlasian.InventoryPower;

import org.apache.logging.log4j.Logger;

import com.github.GummyBlasian.InventoryPower.Config.ConfigHelper;
import com.github.GummyBlasian.InventoryPower.Config.ConfigHolder;
import com.github.GummyBlasian.InventoryPower.GUI.Container.PCTContainer;
import com.github.GummyBlasian.InventoryPower.Item.ItemPortableCrafting;
import com.github.GummyBlasian.InventoryPower.Item.ItemPortableFurnace;
import com.github.GummyBlasian.InventoryPower.References.ItemList;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;


@EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class Registry {	

	//Config Registry
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
		final ModConfig config = event.getConfig();
		if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
			ConfigHelper.bakeClient(config);
		} else if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
			ConfigHelper.bakeServer(config);
		}
	}

	public static final ItemGroup IRGroup = Main.IRGroup;
	public static final Logger LOGGER = Main.LOGGER;
	public static final String MODID = Main.MODID;

	/* New Items require:
	 * a model
	 * a texture
	 */
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll
		(
				//Items
				ItemList.portableCrafting = new ItemPortableCrafting(new Item.Properties().group(IRGroup).maxStackSize(1)).setRegistryName(locationItems("portable_crafting")),
				ItemList.portableFurnace = new ItemPortableFurnace(new Item.Properties().group(IRGroup).maxStackSize(1)).setRegistryName(locationItems("portable_furnace"))
				
				);

		Main.LOGGER.info(Main.MODID +" : " + "Items Registered!");
	}

	@SubscribeEvent
	public static void registerContainer(final RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().registerAll(

				IForgeContainerType.create(PCTContainer::new).setRegistryName("portable_crafting"),
				IForgeContainerType.create(PCTContainer::new).setRegistryName("portable_furnace")

				);

		Main.LOGGER.info(Main.MODID +" : " + "Containers Registered!");
	}

	private static ResourceLocation locationItems (String name) {
		return new ResourceLocation(MODID, name);
	}

	@SuppressWarnings("unused")
	private static ResourceLocation locationGUI (String name) {
		return new ResourceLocation(MODID, name);
	}
}