package com.sangam1.InventoryRepair.GUI.Container;

import java.util.Optional;

import com.sangam1.InventoryRepair.References.ContainerList;
import com.sangam1.InventoryRepair.References.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

public class PCT_Container extends Container {

	private PlayerEntity playerEntity;
	private CraftingInventory crafting_matrix;
	private CraftResultInventory craft_result;
	private final IWorldPosCallable pos;
	private World world;

	public PCT_Container(int window_id, World world, PlayerEntity player, PlayerInventory playerInv) {
		super(ContainerList.PCT_CONTAINER, window_id);
		this.playerEntity = player;
		this.pos = IWorldPosCallable.of(world, player.getPosition());
		this.world = world;
		crafting_matrix = new CraftingInventory(this, 3, 3);
		craft_result = new CraftResultInventory();
		layoutPlayerInventorySlots(playerInv);

		onCraftMatrixChanged(crafting_matrix);
	}

	@Override
	public ContainerType<?> getType() {
		return ContainerList.PCT_CONTAINER;
	}

	public PCT_Container(int windowId, PlayerInventory playerInv, PacketBuffer data) {
		this(windowId, playerInv.player.world, playerInv.player, playerInv);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return player.getHeldItemMainhand().getItem() == ItemList.gocraft;
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			int countSlots = 27;
			if (index < countSlots) {
				if (!mergeItemStack(itemstack1, countSlots + 1, this.inventorySlots.size() - 9, false)
						&& !mergeItemStack(itemstack1, this.inventorySlots.size() - 9, this.inventorySlots.size(),
								true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(itemstack1, 0, countSlots, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}

	private int addSlotRange(PlayerInventory handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new Slot(handler, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(PlayerInventory handler, int index, int x, int y, int horAmount, int dx, int verAmount,
			int dy) {
		for (int j = 0; j < verAmount; j++) {
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}	
	
	private int addCraftingSlot(CraftingInventory handler, int index, int x, int y, int dx, int dy) { 
		int x_int = x;
		for(int a = 0; a<3;a++) {
			x = x_int;
			for (int i = 0 ; i < 3 ; i++) { 
			 	addSlot(new Slot(handler, index, x, y));
			 	x += dx; index++;
		 	}
			y += dy;
		}

		addSlot(new CraftingResultSlot(playerEntity, crafting_matrix, craft_result, 0, 120, 35));
		
		return index; 
	 }

	private void layoutPlayerInventorySlots(PlayerInventory playerInv) {
		// Crafting Grid
		addCraftingSlot(crafting_matrix,46,30,5,18,18);
		
		int leftCol = 8, topRow = 84;
		// Player inventory
		addSlotBox(playerInv, 10, leftCol, topRow, 9, 18, 3, 18);

		// Hotbar
		topRow += 58;
		addSlotRange(playerInv, 1, leftCol, topRow, 9, 18);
	}
	
	@Override
	public void onCraftMatrixChanged(final IInventory inventory) {
		pos.consume((world, pos) -> {
			if (!world.isRemote) {
				final ServerPlayerEntity playerMp = (ServerPlayerEntity) playerEntity;
				ItemStack stack = ItemStack.EMPTY;
				final Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.CRAFTING, crafting_matrix, world);
				if (optional.isPresent()) {
					final ICraftingRecipe icraftingrecipe = optional.get();
					if (craft_result.canUseRecipe(world, playerMp, icraftingrecipe)) {
						stack = icraftingrecipe.getCraftingResult(crafting_matrix);
					}
				}
				craft_result.setInventorySlotContents(0, stack);
				playerMp.connection.sendPacket(new SSetSlotPacket(windowId, 0, stack));
			}
		});
	}
	
	@Override
	public void onContainerClosed(PlayerEntity playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.crafting_matrix.removeStackFromSlot(i);

                if (!itemstack.isEmpty())
                {
                    playerIn.dropItem(itemstack, false);
                }
            }
        }
    }
	
	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != this.craft_result && super.canMergeSlot(stack, slotIn);
    }

}
