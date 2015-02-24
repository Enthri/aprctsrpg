package util;

import java.util.*;

public class Item {
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	
	public Item() {
		itemList.add(this);
	}
	
	public static Item getRandomItem() {
		return itemList.get(new Random().nextInt(itemList.size()));
	}



	public static final Potion basicPotion = new Potion(10);
	public static final Sword basicSword = new Sword(30);
	public static final Sword basicWand = new Sword(25);
	public static final Boots basicBoot = new Boots(1.2);
	public static final Armor basicArmor = new Armor(0.1);
	public static final Boots normBoots = new Boots(1.3);
	public static final Armor normArmor = new Armor(0.3);
	public static final Sword normSword = new Sword(40);
	public static final Potion normPotion = new Potion(30);
	public static final Boots advBoots = new Boots(1.5);
	public static final Armor advArmor = new Armor(0.5);
	public static final Sword advSword = new Sword(50);
	public static final Potion advPotion = new Potion(50);
	
	
	
}
