package com.github.GummyBlasian.InventoryPower.GUI.Container;

import java.util.List;
import java.util.Optional;

import com.github.GummyBlasian.InventoryPower.GUI.Slot.FurnaceInputSlot;
import com.github.GummyBlasian.InventoryPower.Inventory.FurnaceBurnInventory;
import com.github.GummyBlasian.InventoryPower.Inventory.FurnaceInventory;
import com.github.GummyBlasian.InventoryPower.Inventory.FurnaceResultInventory;
import com.github.GummyBlasian.InventoryPower.Main;
import com.github.GummyBlasian.InventoryPower.References.ItemList;

import com.google.common.collect.Lists;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.NonNullList;
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
		return player.getHeldItemMainhand().getItem() == ItemList.portableFurnace;
	}

	/**
	 * only called when player shift-clicks
	 * @param playerIn
	 * @param index
	 * @return itemstack
	 */
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		System.out.println("transferred");
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 1 && index != 0) {
				if (this.func_217057_a(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 3 && index < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}

		return itemstack;
	}

	private void layoutPlayerInventorySlots(PlayerInventory playerInv) {
		furnaceInventory.openInventory(player);

		addSlots(new FurnaceResultSlot(player, furnaceInventory, 2, 116, 35)); //output slot
		addSlots(new FurnaceFuelSlot(this, furnaceInventory,1,56,53)); //fuel slot
		addSlots(new FurnaceInputSlot(furnaceInventory, 0, 56, 17, this)); //input slot
		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				addSlots(new Slot(player.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			addSlots(new Slot(player.inventory, l, 8 + l * 18, 142));
		}
	}

	/**
	 * Only called by MC in the opening of the container
	 * @param inventory
	 */
	@Override
	public void onCraftMatrixChanged(final IInventory inventory) {
		System.out.println("called");
		pos.consume((world, pos) -> {
			if (!world.isRemote) {
				Main.LOGGER.debug("entered");
				final ServerPlayerEntity playerMp = (ServerPlayerEntity) playerEntity;
				ItemStack stack = ItemStack.EMPTY;
				Optional<FurnaceRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.SMELTING, furnaceInventory, world);
				Main.LOGGER.debug("hehe " + optional.isPresent());
				if (optional.isPresent()) {
					final FurnaceRecipe icraftingrecipe = optional.get();
					Main.LOGGER.debug("looking " + icraftingrecipe.toString());
					if (furnace_result.canUseRecipe(world, playerMp, icraftingrecipe)) {
						stack = icraftingrecipe.getCraftingResult(furnaceInventory);
						Main.LOGGER.debug("option " + stack.toString());
					}
				}
				furnace_result.setInventorySlotContents(2, stack);
				playerMp.connection.sendPacket(new SSetSlotPacket(windowId, 0, stack));
			}
		});
	}

	@Override
	public void onContainerClosed(PlayerEntity player)
    {
        super.onContainerClosed(player);
        PlayerInventory inv = player.inventory;

        if (!this.world.isRemote)
        {
            for (int i = 0; i < 3; ++i)
            {
            	ItemStack itemstack = this.furnaceInventory.getStackInSlot(i);//.removeStackFromSlot(i);
            	System.out.println(i + " " + itemstack.toString());
                if (!itemstack.isEmpty())
                {
                	if (inv.hasItemStack(itemstack)) {
						player.inventory.addItemStackToInventory(itemstack);
                	} else {
						player.dropItem(itemstack, true);
                	}
                }
            }
        } else {
            for (int i = 0; i < 3; ++i)
            {
            	ItemStack itemstack = this.furnaceInventory.getStackInSlot(i); //.removeStackFromSlot(i);
                if (!itemstack.isEmpty())
                {
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
	public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != this.furnace_result && super.canMergeSlot(stack, slotIn);
    }

	public void clear() {
		this.furnaceInventory.clear();
	}


	//test

	private final NonNullList<ItemStack> inventoryItemStack = NonNullList.create();
	public final List<Slot> inventorySlots = Lists.newArrayList();
	private final List<IContainerListener> listeners = Lists.newArrayList();
	private final List<IntReferenceHolder> trackedIntReferences = Lists.newArrayList();

	@Override
	public void detectAndSendChanges() {
		for(int i = 0; i < inventoryItemStack.size(); i++) {
			ItemStack itemstack = inventorySlots.get(i).getStack();
			ItemStack itemstack1 = inventoryItemStack.get(i);
			if (!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
				System.out.println("dif " + itemstack + " to " + itemstack1 + " @ " + i);
				boolean clientStackChanged = !itemstack1.equals(itemstack, true);
				itemstack1 = itemstack.copy();
				inventoryItemStack.set(i, itemstack1);

				if (clientStackChanged)
					for(IContainerListener icontainerlistener : listeners) {
						icontainerlistener.sendSlotContents(this, i, itemstack1);
					}

				if(i == 0 && !world.isRemote){
					System.out.println("entered");
					final ServerPlayerEntity playerMp = (ServerPlayerEntity) playerEntity;
					ItemStack stack = ItemStack.EMPTY;
					Optional<FurnaceRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.SMELTING, furnaceInventory, world);
					System.out.println("hehe " + optional.isPresent());
					if (optional.isPresent()) {
						final FurnaceRecipe icraftingrecipe = optional.get();
						System.out.println("looking " + icraftingrecipe.toString());
						if (furnace_result.canUseRecipe(world, playerMp, icraftingrecipe)) {
							stack = icraftingrecipe.getCraftingResult(furnaceInventory);
							System.out.println("option " + stack.toString());
						}
					}
					furnace_result.setInventorySlotContents(2, stack);
					playerMp.connection.sendPacket(new SSetSlotPacket(windowId, 2, stack));
				}
			}
		}

		for(int j = 0; j < trackedIntReferences.size(); ++j) {
			IntReferenceHolder intreferenceholder = trackedIntReferences.get(j);
			if (intreferenceholder.isDirty()) {
				for(IContainerListener icontainerlistener1 : listeners) {
					icontainerlistener1.sendWindowProperty(this, j, intreferenceholder.get());
				}
			}
		}

	}

	@Override
	public void addListener(IContainerListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
			listener.sendAllContents(this, this.getInventory());
			detectAndSendChanges();
		}
	}

	public Slot addSlots(Slot slotIn) {
		slotIn.slotNumber = inventorySlots.size();
		inventorySlots.add(slotIn);
		//System.out.println(inventoryItemStacks.toString());
		inventoryItemStack.add(ItemStack.EMPTY);
		return slotIn;
	}
}
