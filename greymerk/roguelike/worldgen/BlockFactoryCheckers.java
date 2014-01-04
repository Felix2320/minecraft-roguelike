package greymerk.roguelike.worldgen;

import net.minecraft.src.World;

public class BlockFactoryCheckers implements IBlockFactory {

	private MetaBlock fillOne;
	private MetaBlock fillTwo;
	
	public BlockFactoryCheckers(MetaBlock fillOne, MetaBlock fillTwo){
		this.fillOne = fillOne;
		this.fillTwo = fillTwo;
	}
	
	
	@Override
	public void setBlock(World world, int x, int y, int z) {
		setBlock(world, x, y, z, true, true);

	}

	@Override
	public void setBlock(World world, int x, int y, int z, boolean fillAir, boolean replaceSolid) {
		
		if (x % 2 == 0) {
			if(z % 2 == 0){
				if(y % 2 == 0){
					WorldGenPrimitive.setBlock(world, x, y, z, fillOne);
				} else {
					WorldGenPrimitive.setBlock(world, x, y, z, fillTwo);
				}
			} else {
				if(y % 2 == 0){
					WorldGenPrimitive.setBlock(world, x, y, z, fillTwo);
				} else {
					WorldGenPrimitive.setBlock(world, x, y, z, fillOne);
				}
			}
		} else {
			if(z % 2 == 0){
				if(y % 2 == 0){
					WorldGenPrimitive.setBlock(world, x, y, z, fillTwo);
				} else {
					WorldGenPrimitive.setBlock(world, x, y, z, fillOne);
				}
			} else {
				if(y % 2 == 0){
					WorldGenPrimitive.setBlock(world, x, y, z, fillOne);
				} else {
					WorldGenPrimitive.setBlock(world, x, y, z, fillTwo);
				}
			}
		}
	}

	@Override
	public MetaBlock getMetaBlock() {
		return fillOne;
	}

}
