
package easyinventories.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import easyinventories.ModInfo;

public class TileEntityDistributor extends TileEntity implements ISidedInventory {
	
	public ItemStack [] input;
	public ItemStack [] output;
	public int [] inputSlots;
	public int [] outputSlots;

	public TileEntityDistributor() {
		System.out.println("\n\n\n\n\n\nCreating Tile Entity\n\n\n\n\n\n");
		input = new ItemStack[18];
		output = new ItemStack[18];
		inputSlots = new int[18];
		outputSlots = new int[18];

		for (int i = 0; i < 18; i++) {
			inputSlots[i] = i;
			outputSlots[i] = i + 18;
		}
	}

	/**
	 * Writes contents of its inventory to NBT Data
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);
		NBTTagList tagList = new NBTTagList();

		for (int i = 0; i < input.length; i ++) {
			if (this.input[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("InputSlot", (byte)i);
				this.input[i].writeToNBT(tag);
				tagList.appendTag(tag);
			}
		}
		
		for (int i = 0; i < input.length; i ++) {
			if (this.input[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("InputSlot", (byte)i);
				this.input[i].writeToNBT(tag);
				tagList.appendTag(tag);
			}
		}

		nbtTag.setTag(ModInfo.DISTRIBUTOR_KEY, tagList);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList tagList = tag.getTagList(ModInfo.DISTRIBUTOR_KEY);
		this.input = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound slotTag = (NBTTagCompound) tagList.tagAt(i);
			int slot = slotTag.getByte("Slot") & 255;
			if (slot >= 0 && slot < this.input.length) {
				this.input[slot] = ItemStack.loadItemStackFromNBT(slotTag);
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return 36;
	}
	
	public ItemStack getStackInInput(int i) {
		return getStackInSlot(i);
	}
	
	public ItemStack getStackInOutput(int i) {
		return getStackInSlot(i + 18);
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i >= 0 && i < 18) {
			return input[i];
		}
		else if (i >= 18 && i <= 36) {
			return output[i - 18];
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);
		
		if (input[i] == null) return itemstack;
		
		if (itemstack.stackSize <= count) {
			setInventorySlotContents(i, null);
		}
		else {
			itemstack.splitStack(count);
			onInventoryChanged();
		}
	
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemstack = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return itemstack;
	}
	
	public void setInputSlotContents(int i, ItemStack itemstack) {
		setInputSlotContents(i, itemstack);
	}
	
	public void setOutputSlotContents(int i, ItemStack itemstack) {
		setInventorySlotContents(i + 18, itemstack);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (i >= 0 && i < 18) {
			input[i] = itemstack;
		}
		else if (i >= 18 && i <= 36) {
			output[i - 18] = itemstack;
		}
		else {
			if (itemstack == null) {
				System.err.println("Tried to set invalid slot, " + i + " to null");
			}
			else {				
				System.err.println("Tried to set invalid slot, " + i + " when adding " + itemstack.getDisplayName());
			}
		}	
	}

	@Override
	public String getInvName() {
		return "Distributor";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return (i >= 0 && i < 18) ? true : false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return (i >= 18 && i < 36) ? true : false;
	}
}
