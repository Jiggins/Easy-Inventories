package easyinventories.blocks;

import easyinventories.gui.SlotCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerBlockSorting extends Container {

	private TileEntityBlockSorting sorting;

	public ContainerBlockSorting(InventoryPlayer invPlayer, TileEntityBlockSorting sorting) {
		this.sorting = sorting;
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new SlotCustom(invPlayer, i, 8 + 18 * i, 158));
		}
		
		int count = 9;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlotToContainer(new SlotCustom(invPlayer, count++, 8 + 18 * column, 100 + 18 * row));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new SlotCustom(sorting, i, 8 + 18 * i, 71));
		}
		
		count = 9;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlotToContainer(new SlotCustom(sorting, count++, 8 + 18 * column, 13 + 18 * row));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return sorting.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}
}
