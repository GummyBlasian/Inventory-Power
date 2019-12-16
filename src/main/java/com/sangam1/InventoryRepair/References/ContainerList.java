package com.sangam1.InventoryRepair.References;

import com.sangam1.InventoryRepair.GUI.Container.PCT_Container;
import com.sangam1.InventoryRepair.Main;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class ContainerList {
	
	@ObjectHolder("gocraft")
	public static final ContainerType<PCT_Container> PCT_CONTAINER = null;
	
}
