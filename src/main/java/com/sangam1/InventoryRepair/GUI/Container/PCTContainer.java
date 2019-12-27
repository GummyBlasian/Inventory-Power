package com.sangam1.InventoryRepair.GUI.Container;

import java.util.Optional;

import com.sangam1.InventoryRepair.Main;
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
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class PCTContainer extends Container {
	
	@ObjectHolder("portable-crafting")
	public static ContainerType<PCTContainer> TYPE;

	private PlayerEntity playerEntity;
	private CraftingInventory crafting_matrix;
	private CraftResultInventory craft_result;
	private final IWorldPosCallable pos;
	private World world;
	private PlayerEntity player;

	public PCTContainer(int window_id, World world, PlayerEntity player, PlayerInventory playerInv) {
		super(TYPE, window_id);
		this.playerEntity = player;
		this.pos = IWorldPosCallable.of(world, player.getPosition());
		this.world = world;
		this.player = player;
		crafting_matrix = new CraftingInventory(this, 3, 3);
		craft_result = new CraftResultInventory();
		layoutPlayerInventorySlots(playerInv);

		onCraftMatrixChanged(crafting_matrix);
	}

	@Override
	public ContainerType<?> getType() {
		return TYPE;
	}

	public PCTContainer(int windowId, PlayerInventory playerInv, PacketBuffer data) {
		this(windowId, playerInv.player.world, playerInv.player, playerInv);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return player.getHeldItemMainhand().getItem() == ItemList.portableCrafting;
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

	private void layoutPlayerInventorySlots(PlayerInventory playerInv) {
		crafting_matrix.openInventory(player);
		
		addSlot(new CraftingResultSlot(player, crafting_matrix, craft_result, 0, 124, 35));
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				addSlot(new Slot(crafting_matrix, j + i * 3, 30 + j * 18, 17 + i * 18));
			}
		}
		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				addSlot(new Slot(player.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			addSlot(new Slot(player.inventory, l, 8 + l * 18, 142));
		}
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
        PlayerInventory inv = playerIn.inventory;
        if (!this.world.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
            	ItemStack itemstack = this.crafting_matrix.removeStackFromSlot(i);
                if (!itemstack.isEmpty())
                {
                	if (inv.hasItemStack(itemstack)) {
                		playerIn.inventory.addItemStackToInventory(itemstack);
                	} else {
                		playerIn.dropItem(itemstack, false);
                	}
                }
            }
        } else {
            for (int i = 0; i < 9; ++i)
            {
            	ItemStack itemstack = this.crafting_matrix.removeStackFromSlot(i);
                if (!itemstack.isEmpty())
                {
                	if (inv.hasItemStack(itemstack)) {
                		playerIn.inventory.addItemStackToInventory(itemstack);
                	} else {
                		playerIn.dropItem(itemstack, false);
                	}
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
