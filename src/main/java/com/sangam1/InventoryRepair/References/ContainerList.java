package com.sangam1.InventoryRepair.References;

import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.GUI.Container.PCTContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class ContainerList {
	
	@ObjectHolder("portableCrafting")
	public static final ContainerType<PCTContainer> PCT_CONTAINER = null;
	
}
