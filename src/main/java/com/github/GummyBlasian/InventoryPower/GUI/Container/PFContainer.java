package com.github.GummyBlasian.InventoryPower.GUI.Container;

import java.util.Optional;

import com.github.GummyBlasian.InventoryPower.Inventory.FurnaceBurnInventory;
import com.github.GummyBlasian.InventoryPower.Inventory.FurnaceInventory;
import com.github.GummyBlasian.InventoryPower.Inventory.FurnaceResultInventory;
import com.github.GummyBlasian.InventoryPower.Main;
import com.github.GummyBlasian.InventoryPower.References.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class PFContainer extends AbstractFurnaceContainer {

	@ObjectHolder("portable_furnace")
	public static ContainerType<PFContainer> TYPE;

	private PlayerEntity playerEntity;
	private IInventory furnaceInventory;
	private FurnaceBurnInventory furnace_power;
	private FurnaceResultInventory furnace_result;
	private final IWorldPosCallable pos;
	private World world;
	private PlayerEntity player;

	/**
	INPUT = 0;
	FUEL = 1;
	OUTPUT = 2;
	 **/
	protected AbstractCookingRecipe curRecipe;
	protected ItemStack failedMatch = ItemStack.EMPTY;

	public PFContainer(int window_id, World world, PlayerEntity player, PlayerInventory playerInv) {
		super(TYPE, IRecipeType.SMELTING, window_id, playerInv);
		this.playerEntity = player;
		this.pos = IWorldPosCallable.of(world, player.getPosition());
		this.world = world;
		this.player = player;
		furnaceInventory = new Inventory(3);
		furnace_result = new FurnaceResultInventory();
		layoutPlayerInventorySlots(playerInv);
		onCraftMatrixChanged(furnaceInventory);
	}

	@Override
	public ContainerType<?> getType() {
		return TYPE;
	}

	public PFContainer(int windowId, PlayerInventory playerInv, PacketBuffer data) {
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
		furnaceInventory.openInventory(player);

		addSlot(new FurnaceResultSlot(player, furnaceInventory, 2, 116, 35)); //output slot
		addSlot(new FurnaceFuelSlot(this, furnaceInventory,1,56,53)); //fuel slot
		addSlot(new Slot(furnaceInventory, 0, 56, 17)); //input slot
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
				Optional<FurnaceRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.SMELTING, furnaceInventory, world);
				if (optional.isPresent()) {
					final FurnaceRecipe icraftingrecipe = optional.get();
					if (furnace_result.canUseRecipe(world, playerMp, icraftingrecipe)) {
						stack = icraftingrecipe.getCraftingResult(furnaceInventory);
					}
				}
				furnace_result.setInventorySlotContents(0, stack);
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
            	ItemStack itemstack = this.furnaceInventory.removeStackFromSlot(i);
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
            	ItemStack itemstack = this.furnaceInventory.removeStackFromSlot(i);
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
        return slotIn.inventory != this.furnace_result && super.canMergeSlot(stack, slotIn);
    }

    /*
	protected AbstractCookingRecipe getRecipe() {
		ItemStack input = this.getStackInSlot(INPUT);
		if (input.isEmpty() || input == failedMatch) return null;
		if (curRecipe != null && curRecipe.matches(this, world)) return curRecipe;
		else {
			AbstractCookingRecipe rec = world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world).orElse(null);
			if (rec == null) failedMatch = input;
			else failedMatch = ItemStack.EMPTY;
			return curRecipe = rec;
		}
	}
*/

}
