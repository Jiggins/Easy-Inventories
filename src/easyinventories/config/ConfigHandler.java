package easyinventories.config;

import java.io.File;

import easyinventories.ModInfo;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	private static int nextId;

	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();

		//Blocks
		ModInfo.SORTING_ID = config.getBlock(ModInfo.BLOCKSORTING_KEY, ModInfo.DEFAULT_BLOCK_ID).getInt();
		nextId = ModInfo.SORTING_ID;
		config.save();
	}

	public static int nextID() {
		return ++nextId;
	}

}
