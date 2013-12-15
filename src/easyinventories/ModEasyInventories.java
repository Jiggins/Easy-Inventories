package easyinventories;

import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import easyinventories.blocks.BlockSorting;
import easyinventories.config.ConfigHandler;
import easyinventories.network.PacketHandler;
import easyinventories.proxies.CommonProxy;


@Mod(modid = "EasyInventories", name = "Easy Inventories")
@NetworkMod(channels = {ModInformation.CHANNELS}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class ModEasyInventories {

	public static Block sorting;
	
	@Instance(ModInformation.ID)
	public static ModEasyInventories easyInventories;

	@SidedProxy(clientSide = "easyinventories.proxies.ClientProxy", serverSide = "easyinventories.proxies.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		sorting = new BlockSorting(ModInformation.SORTING_ID);
		GameRegistry.registerBlock(sorting, ModInformation.BLOCKSORTING_KEY);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		LanguageRegistry.addName(sorting, "Sorting Block");

	}
}
