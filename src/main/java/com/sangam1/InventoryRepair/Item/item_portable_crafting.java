package com.sangam1.InventoryRepair.Item;

import com.sangam1.InventoryRepair.GUI.Container.PCT_Container;

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

public class item_portable_crafting extends Item{

	  	public item_portable_crafting(Properties id) {

	  		super(id);
	        
	    }
	  
	  	@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
			
			if (!world.isRemote && hand == Hand.MAIN_HAND) {
				//System.out.println("Opening");
				//Main.LOGGER.info(new TranslationTextComponent(getTranslationKey()).getFormattedText());
			
				NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
					@Override
		            public ITextComponent getDisplayName() {
						//return new TranslationTextComponent(getTranslationKey());
						//return new StringTextComponent(getType().getRegistryName().getPath());
						return new TranslationTextComponent(getRegistryName().getPath());
		            }

		            @Override
		            public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
		            	return new PCT_Container(windowId, world, player, playerInventory);
		            }
		        });
				return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
			}
			//System.out.println("Nothing");
			return new ActionResult<ItemStack>(ActionResultType.FAIL, player.getHeldItem(hand));
			
		}
	  	
}
