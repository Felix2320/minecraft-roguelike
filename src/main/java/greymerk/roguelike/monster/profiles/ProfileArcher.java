package greymerk.roguelike.monster.profiles;

import java.util.Random;

import greymerk.roguelike.monster.IMonsterProfile;
import greymerk.roguelike.monster.MonsterProfile;
import greymerk.roguelike.treasure.loot.Enchant;
import greymerk.roguelike.treasure.loot.TippedArrow;
import greymerk.roguelike.treasure.loot.provider.ItemWeapon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.world.World;

public class ProfileArcher implements IMonsterProfile {

	@Override
	public void addEquipment(World world, Random rand, int level, Entity mob) {
		
		if(rand.nextInt(10) == 0){
			((EntitySkeleton)mob).func_189768_a(SkeletonType.STRAY);
		}
		
		if(Enchant.canEnchant(world.getDifficulty(), rand, level) && rand.nextInt(10) == 0){
			mob.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, TippedArrow.getHarmful(rand, 1));
		}
		
		mob.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemWeapon.getBow(rand, level, Enchant.canEnchant(world.getDifficulty(), rand, level)));
		MonsterProfile.get(MonsterProfile.TALLMOB).addEquipment(world, rand, level, mob);
	}

}
