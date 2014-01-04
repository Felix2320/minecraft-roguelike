package greymerk.roguelike.catacomb.dungeon.room;

import greymerk.roguelike.catacomb.Catacomb;
import greymerk.roguelike.catacomb.dungeon.IDungeon;
import greymerk.roguelike.treasure.TreasureChest;
import greymerk.roguelike.worldgen.BlockFactoryProvider;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.IBlockFactory;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.Spawner;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class DungeonsBrick implements IDungeon {

	
	World world;
	Random rand;
	int originX;
	int originY;
	int originZ;

		
	public DungeonsBrick(){
	}
	
	public boolean generate(World inWorld, Random inRandom, int inOriginX, int inOriginY, int inOriginZ) {
		world = inWorld;
		rand = inRandom;
		originX = inOriginX;
		originY = inOriginY;
		originZ = inOriginZ;
		
		
		
		int stair;
		switch(Catacomb.getLevel(originY)){
		case 3:
			stair = Block.stairsCobblestone.blockID;
			break;
		case 4:
			stair = Block.stairsNetherBrick.blockID;
			break;
		default:
			stair = Block.stairsStoneBrick.blockID;
		}
		
		
		
		MetaBlock southStair = new MetaBlock(stair, WorldGenPrimitive.blockOrientation(Cardinal.SOUTH, true));
		MetaBlock northStair = new MetaBlock(stair, WorldGenPrimitive.blockOrientation(Cardinal.NORTH, true));
		MetaBlock eastStair = new MetaBlock(stair, WorldGenPrimitive.blockOrientation(Cardinal.EAST, true));
		MetaBlock westStair = new MetaBlock(stair, WorldGenPrimitive.blockOrientation(Cardinal.WEST, true));
		
		
		IBlockFactory blocks = BlockFactoryProvider.getRandomizer(Catacomb.getLevel(inOriginY), rand);
		
		// fill air inside
		WorldGenPrimitive.fillRectSolid(world, 	originX - 3, originY, originZ - 3, originX + 3, originY + 3, originZ + 3, 0);
		
		// shell
		WorldGenPrimitive.fillRectHollow(world, originX - 4, originY - 1, originZ - 4, originX + 4, originY + 4, originZ + 4, blocks, false, true);

		// pillars
		WorldGenPrimitive.fillRectSolid(world, originX - 3, originY, originZ - 3, originX - 3, originY + 3, originZ - 3, blocks, true, true);
		WorldGenPrimitive.setBlock(world, originX - 3, originY + 3, originZ - 2, southStair, true, true);
		WorldGenPrimitive.setBlock(world, originX - 2, originY + 3, originZ - 3, eastStair, true, true);
		WorldGenPrimitive.fillRectSolid(world, originX - 3, originY, originZ + 3, originX - 3, originY + 3, originZ + 3, blocks, true, true);
		WorldGenPrimitive.setBlock(world, originX - 2, originY + 3, originZ + 3, eastStair, true, true);
		WorldGenPrimitive.setBlock(world, originX - 3, originY + 3, originZ + 2, northStair, true, true);
		WorldGenPrimitive.fillRectSolid(world, originX + 3, originY, originZ - 3, originX + 3, originY + 3, originZ - 3, blocks, true, true);
		WorldGenPrimitive.setBlock(world, originX + 2, originY + 3, originZ - 3, westStair, true, true);
		WorldGenPrimitive.setBlock(world, originX + 3, originY + 3, originZ - 2, southStair, true, true);
		WorldGenPrimitive.fillRectSolid(world, originX + 3, originY, originZ + 3, originX + 3, originY + 3, originZ + 3, blocks, true, true);
		WorldGenPrimitive.setBlock(world, originX + 2, originY + 3, originZ + 3, westStair, true, true);
		WorldGenPrimitive.setBlock(world, originX + 3, originY + 3, originZ + 2, northStair, true, true);
		
		// roof
		WorldGenPrimitive.fillRectSolid(world, originX - 2, originY + 4, originZ - 2, originX + 2, originY + 4, originZ + 2, 0);
		
		blocks.setBlock(world, originX - 2, originY + 4, originZ - 2);
		WorldGenPrimitive.setBlock(world, originX - 2, originY + 4, originZ - 1, southStair, true, true);
		WorldGenPrimitive.setBlock(world, originX - 1, originY + 4, originZ - 2, eastStair, true, true);
		blocks.setBlock(world, originX - 2, originY + 4, originZ + 2, true, true);
		WorldGenPrimitive.setBlock(world, originX - 1, originY + 4, originZ + 2, eastStair, true, true);
		WorldGenPrimitive.setBlock(world, originX - 2, originY + 4, originZ + 1, northStair, true, true);
		blocks.setBlock(world, originX + 2, originY + 4, originZ - 2, true, true);
		WorldGenPrimitive.setBlock(world, originX + 1, originY + 4, originZ - 2, westStair, true, true);
		WorldGenPrimitive.setBlock(world, originX + 2, originY + 4, originZ - 1, southStair, true, true);
		blocks.setBlock(world, originX + 2, originY + 4, originZ + 2, true, true);
		WorldGenPrimitive.setBlock(world, originX + 1, originY + 4, originZ + 2, westStair, true, true);
		WorldGenPrimitive.setBlock(world, originX + 2, originY + 4, originZ + 1, northStair, true, true);
		
		
		WorldGenPrimitive.fillRectSolid(world, originX - 2, originY + 5, originZ - 2, originX + 2, originY + 5, originZ + 2, blocks, false, true);
		WorldGenPrimitive.setBlock(world, originX, originY + 5, originZ, 0);
		
		blocks.setBlock(world, originX - 1, originY + 5, originZ - 1, false, true);
		blocks.setBlock(world, originX - 1, originY + 5, originZ + 1, false, true);
		blocks.setBlock(world, originX + 1, originY + 5, originZ - 1, false, true);
		blocks.setBlock(world, originX + 1, originY + 5, originZ + 1, false, true);
		
		WorldGenPrimitive.setBlock(world, originX - 1, originY + 5, originZ, eastStair, false, true);
		WorldGenPrimitive.setBlock(world, originX + 1, originY + 5, originZ, westStair, false, true);
		WorldGenPrimitive.setBlock(world, originX, originY + 5, originZ - 1, southStair, false, true);
		WorldGenPrimitive.setBlock(world, originX, originY + 5, originZ + 1, northStair, false, true);
		
		blocks.setBlock(world, originX, originY + 6, originZ, false, true);
		
		
		
		
		// Chests
		List<Coord> space = new ArrayList<Coord>();
		space.add(new Coord(originX - 2, originY, originZ - 3));
		space.add(new Coord(originX - 3, originY, originZ - 2));
		
		space.add(new Coord(originX - 2, originY, originZ + 3));
		space.add(new Coord(originX - 3, originY, originZ + 2));
		
		space.add(new Coord(originX + 2, originY, originZ - 3));
		space.add(new Coord(originX + 3, originY, originZ - 2));
		
		space.add(new Coord(originX + 2, originY, originZ + 3));
		space.add(new Coord(originX + 3, originY, originZ + 2));

		TreasureChest[] types = {TreasureChest.ARMOUR, TreasureChest.WEAPONS, TreasureChest.TOOLS};
		TreasureChest.createChests(inWorld, inRandom, 1, space, types);
		
		placeMobSpawner();

		return true;
	}
	protected void placeMobSpawner() {
		Spawner.generate(world, rand, originX, originY, originZ);
	}

	
	public boolean isValidDungeonLocation(World world, int originX, int originY, int originZ) {
		return false;
	}
	
	public int getSize(){
		return 4;
	}
}
