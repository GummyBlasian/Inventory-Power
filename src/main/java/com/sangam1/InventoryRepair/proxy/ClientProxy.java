package com.sangam1.InventoryRepair.proxy;

import com.sangam1.InventoryRepair.References.StartupRef;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(StartupRef.MODID + ":" + id, "inventory"));
	}
}
