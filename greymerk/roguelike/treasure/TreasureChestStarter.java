package greymerk.roguelike.treasure;

import greymerk.roguelike.treasure.loot.Equipment;
import greymerk.roguelike.treasure.loot.Loot;
import greymerk.roguelike.treasure.loot.Potion;
import greymerk.roguelike.treasure.loot.PotionEffect;
import greymerk.roguelike.treasure.loot.PotionMixture;
import greymerk.roguelike.treasure.loot.Quality;
import greymerk.roguelike.treasure.loot.provider.ItemSpecialty;
import greymerk.roguelike.config.RogueConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemWritableBook;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NBTTagString;
import net.minecraft.src.TileEntityChest;

public class TreasureChestStarter extends TreasureChestBase{

	
	private ItemStack getStarterLoot(int choice){
		
		switch (choice){
		case 4: return new ItemStack(Item.pickaxeStone);
		case 3: return new ItemStack(Item.swordStone);
		case 2: return Loot.getLoot(Loot.FOOD, rand, 0);
		case 1: return ItemSpecialty.getRandomItem(Equipment.LEGS, rand, Quality.WOOD);
		default: return new ItemStack(Block.torchWood, 3 + rand.nextInt(6));
		}
		
	}	
	
	@Override
	protected void fillChest(TileEntityChest chest, int level) {

		int size = chest.getSizeInventory();
				
		int quantity = RogueConfig.getBoolean(RogueConfig.GENEROUS) ? 18 : 3;

		for (int i = 0; i < quantity; i++) {
			ItemStack item;
			item = getStarterLoot(i % 5);
			chest.setInventorySlotContents(rand.nextInt(size), item);
		}
		

		
		chest.setInventorySlotContents(0, book());
	}
	
	private ItemStack book(){
		ItemStack book = new ItemStack(Item.writableBook);
		
		book.setTagInfo("author", new NBTTagString("author", "greymerk"));
		book.setTagInfo("title", new NBTTagString("title", "Journal"));
		
		String page1 = 
				"Monsters invading from the depths, " +
				"perhaps we dug too deeply. " +
				"Our stuff is still down there, " +
				"we're running out of food and " +
				"have to leave soon before the tower " +
				"is overtaken as well\n\n" +
				"-greymerk\n";
		
		String page2 = 
				"Roguelike Dungeons v1.3.2\n" +
				"May 21th 2014\n\n" + 
				"Credits\n\n" +
				"Author: Greymerk\n\n" +
				"Bits: Drainedsoul\n\n" +
				"Ideas: Eniko @enichan";
	
		
		NBTTagList pages = new NBTTagList();
		pages.appendTag(new NBTTagString("1", page1));
		pages.appendTag(new NBTTagString("2", page2));
		
		book.setTagInfo("pages", pages);
		book.itemID = Item.writtenBook.itemID;
		
		return book;
	}
}
