package easyinventories.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import easyinventories.EasyInventories;
import easyinventories.ModInfo;

public class BlockSorting extends BlockContainer {

	private final Random random = new Random();

	@SideOnly(Side.CLIENT)
	private Icon blockIcon;

	public BlockSorting(int par1) {
		super(par1, Material.wood);
		
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3F);
		setStepSound(soundWoodFootstep);
		setResistance(blockResistance);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBlockSorting();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, EasyInventories.easyInventories, 0, world, x, y, z);
			IInventory inventory = getInventory(world, x, y, z);
			ItemStack item;
			
			player.addChatMessage("\nInventory: ");
			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				item = inventory.getStackInSlot(i);
				if (item != null) {
					player.addChatMessage(i + ": " + item.stackSize + " x " + item.getDisplayName());
				}
//				else {
//					player.addChatMessage(i + ": ");
//				}
			}	
		}
		return true;
	}

	/**
	 * Gets the inventory of the chest at the specified coords, accounting for blocks or ocelots on top of the chest,
	 * and double chests.
	 */
	public IInventory getInventory(World par1World, int par2, int par3, int par4) {
	    IInventory object = (TileEntityBlockSorting)par1World.getBlockTileEntity(par2, par3, par4);
	
	    if (object == null) return null;
	    return object;
	}

	@Override
	/**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World world, int x, int y, int z, int oldBlockID, int oldMetaData) {
    	if (!world.isRemote) {
    		TileEntity te = new TileEntityBlockSorting();
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
	            float f = this.random.nextFloat() * 0.8F + 0.1F;
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

	/** Sets the icon for each side of the block.
	 * Icons are registered in the registerIcons(IconRegister) method above
	 * and declared private in the class.
	 * 
	 * @param side     | The side of the block ranging from 0-5
	 *                 | If you wanted to place a different texture on the top of the block you would use
	 *                 | if (side == 1) return topIcon else return coderDojoIcon;
	 *                 | 0: Bottom
	 *                 | 1: Top
	 *                 | 2: North
	 *                 | 3: South
	 *                 | 4: West
	 *                 | 5: East
	 * 
	 * @param metadata
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon("easyinventories" + ":" + ModInfo.BLOCKSORTING_TEXTURE);
	}

    
}