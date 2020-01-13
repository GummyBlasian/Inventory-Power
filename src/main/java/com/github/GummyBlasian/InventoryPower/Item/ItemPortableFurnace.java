package com.github.GummyBlasian.InventoryPower.Item;

import com.github.GummyBlasian.InventoryPower.GUI.Container.PFContainer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ItemPortableFurnace extends Item{

	public ItemPortableFurnace(Properties property) {
		super(property);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (!world.isRemote && hand == Hand.MAIN_HAND) {
			NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
				@Override
	            public ITextComponent getDisplayName() {
					return new TranslationTextComponent(getRegistryName().getPath());
	            }

	            @Override
	            public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
	            	return new PFContainer(windowId, world, player, playerInventory);
	            }
	        });
			return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
		}
		return new ActionResult<ItemStack>(ActionResultType.FAIL, player.getHeldItem(hand));
  	}

}
