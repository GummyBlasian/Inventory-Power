package com.github.GummyBlasian.InventoryPower.GUI.Container;

import com.github.GummyBlasian.InventoryPower.GUI.Slot.CustomFurnaceFuelSlot;
import com.github.GummyBlasian.InventoryPower.GUI.Slot.FurnaceInputSlot;
import com.github.GummyBlasian.InventoryPower.Inventory.FurnaceBurnInventory;
import com.github.GummyBlasian.InventoryPower.Inventory.FurnaceResultInventory;
import com.github.GummyBlasian.InventoryPower.Main;
import com.github.GummyBlasian.InventoryPower.References.ItemList;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;
import java.util.Optional;

@ObjectHolder(Main.MODID)
public class PFContainer extends Container {

	@ObjectHolder("portable_furnace")
	public static ContainerType<PFContainer> TYPE;

	private PlayerEntity playerEntity;
	private CraftingInventory furnaceInventory;
	private CraftResultInventory furnace_result;
	private final IWorldPosCallable pos;
	private World world;
	private PlayerEntity player;

	private float burn = 0;

	/**
	 * INPUT = 0;
	 * FUEL = 1;
	 * OUTPUT = 2;
	 **/

	public PFContainer(int window_id, World world, PlayerEntity player, PlayerInventory playerInv) {
		super(TYPE, window_id);
		this.playerEntity = player;
		this.pos = IWorldPosCallable.of(world, player.getPosition());
		this.world = world;
		this.player = player;
		furnaceInventory = new CraftingInventory(this, 3, 1);
		furnace_result = new CraftResultInventory();
		layoutPlayerInventorySlots(playerInv);
		getBurnFromItem();
		onCraftMatrixChanged(furnaceInventory);
	}

	public PFContainer(int windowId, PlayerInventory playerInv, PacketBuffer data) {
		this(windowId, playerInv.player.world, playerInv.player, playerInv);
	}

	@Override
	public ContainerType<?> getType() {
		return TYPE;
	}

	private void getBurnFromItem(){
		CompoundNBT nbt;
		if(canInteractWith(player)){
				nbt = player.getHeldItemMainhand().getOrCreateChildTag("burn");
				if (!nbt.contains("burn"))
					nbt.putFloat("burn", 0);
				burn = nbt.getFloat("burn");
		}
	}

	private void setBurnFromItem(){
		CompoundNBT nbt;
		if(canInteractWith(player)){
			nbt = player.getHeldItemMainhand().getChildTag("burn");
			nbt.putFloat("burn", burn);
		}
	}

	public float getBurn(){
		return burn;
	}

	public void addBurn(float value){
		burn += value;
	}

	public void removeBurn(float value){
		burn -= value;
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return player.getHeldItemMainhand().getItem() == ItemList.portableFurnace;
	}

	/**
	 * only called when player shift-clicks
	 *
	 * @param playerIn
	 * @param index
	 * @return itemstack
	 */
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

		addSlot(new Slot(furnaceInventory, 0, 56, 17)); //input slot
		addSlot(new CustomFurnaceFuelSlot(this, furnaceInventory, 1, 56, 53)); //fuel slot
		addSlot(new CraftingResultSlot(player, furnaceInventory, furnace_result, 2, 116, 35)); //output slot

		for (int k = 0; k < 3; ++k) {
			for (int i = 0; i < 9; ++i) {
				addSlot(new Slot(player.inventory, i + k * 9 + 9, 8 + i * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			addSlot(new Slot(player.inventory, l, 8 + l * 18, 142));
		}

	}

	/**
	 * Only called by MC in the opening of the container
	 *
	 * @param inventory
	 */
	@Override
	public void onCraftMatrixChanged(final IInventory inventory) {
		System.out.println("called");
		pos.consume((world, pos) -> {
			if (!world.isRemote) {
				ServerPlayerEntity playerMp = (ServerPlayerEntity) playerEntity;
				ItemStack stack = ItemStack.EMPTY;
				Optional<FurnaceRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.SMELTING, furnaceInventory, world);
				if (optional.isPresent()) {
					FurnaceRecipe icraftingrecipe = optional.get();
					if (furnace_result.canUseRecipe(world, playerMp, icraftingrecipe)) {
						System.out.println(icraftingrecipe.getCookTime());
						if(burn >= icraftingrecipe.getCookTime()) {
							stack = icraftingrecipe.getCraftingResult(furnaceInventory);
							removeBurn(icraftingrecipe.getCookTime());
						}
					}
				}
				furnace_result.setInventorySlotContents(2, stack);
				playerMp.connection.sendPacket(new SSetSlotPacket(windowId, 2, stack));
			}
		});
	}

	@Override
	public void onContainerClosed(PlayerEntity player) {
		super.onContainerClosed(player);
		PlayerInventory inv = player.inventory;
		setBurnFromItem();
		if (!this.world.isRemote) {
			for (int i = 0; i < 3; ++i) {
				ItemStack itemstack = this.furnaceInventory.removeStackFromSlot(i); //.getStackInSlot(i);
				if (!itemstack.isEmpty()) {
					if (inv.hasItemStack(itemstack)) {
						player.inventory.addItemStackToInventory(itemstack);
					} else {
						player.dropItem(itemstack, true);
					}
				}
			}
		} else {
			for (int i = 0; i < 3; ++i) {
				ItemStack itemstack = this.furnaceInventory.removeStackFromSlot(i); //.getStackInSlot(i);
				if (!itemstack.isEmpty()) {
					if (inv.hasItemStack(itemstack)) {
						player.inventory.addItemStackToInventory(itemstack);
					} else {
						player.dropItem(itemstack, true);
					}
				}
			}
		}

	}

	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
		return slotIn.inventory != this.furnace_result && super.canMergeSlot(stack, slotIn);
	}
}
