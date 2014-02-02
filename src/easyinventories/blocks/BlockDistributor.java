package easyinventories.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDistributor extends BlockContainer {

	private Random random = new Random();

	public BlockDistributor(int id) {
		super(id, Material.rock);
		
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3F);
		setStepSound(soundWoodFootstep);
		setResistance(blockResistance);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityDistributor();
	}

	/**
	 * Gets the inventory of the chest at the specified coords, accounting for blocks or ocelots on top of the chest,
	 * and double chests.
	 */
	public IInventory getInventory(World world, int xCoord, int yCoord, int zCoord) {
	    IInventory inventory = (TileEntityDistributor)world.getBlockTileEntity(xCoord, yCoord, zCoord);
	
	    if (inventory == null) return null;
	    return inventory;
	}

	@Override
	/**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World world, int x, int y, int z, int oldBlockID, int oldMetaData) {
    	if (!world.isRemote) {
    		TileEntity te = new TileEntityDistributor();
    		if (te != null && te instanceof IInventory) {
    			IInventory inventory = getInventory(world, x, y, z);
    			dropContents(inventory, world, x, y, z);
    		}
    	}
    }

	public void dropContents(IInventory inventory, World world, int x, int y, int z) {
    	ItemStack item;
    	for (int i = 0; i < inventory.getSizeInventory(); i++) {
			item = inventory.getStackInSlotOnClosing(i);
	    
	    	if (item != null) {
	            float f = this.random .nextFloat() * 0.8F + 0.1F;
	            float f1 = this.random.nextFloat() * 0.8F + 0.1F;
	            float f2 = this.random.nextFloat() * 0.8F + 0.1F;

				while (item.stackSize > 0) {
	                int k1 = this.random.nextInt(21) + 10;

	                if (k1 > item.stackSize) {
	                    k1 = item.stackSize;
	                }

	                item.stackSize -= k1;
	                
					EntityItem droppedItem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(item.itemID, k1, item.getItemDamage()));

	                if (item.hasTagCompound()) {
	                    droppedItem.getEntityItem().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
	                }

	                float f3 = 0.05F;
	                droppedItem.motionX = (double)((float)this.random.nextGaussian() * f3);
	                droppedItem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
	                droppedItem.motionZ = (double)((float)this.random.nextGaussian() * f3);
	                world.spawnEntityInWorld(droppedItem);
	            }
	        }
		}
    }
}
