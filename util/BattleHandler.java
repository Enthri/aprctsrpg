package util;

public class BattleHandler {
	private static double playerDamage,playerHealth,creatureDamage,creatureHealth;
	private static String Exeption;
	/*
	 * takes in the EntityPlayer object and the EntityCreature object, using the existing methods inside, applies the damage to creature
	 * 
	 * this takes care of changing the health
	 */
	public static void playerTurn (EntityPlayer player, EntityCreature creature){
		creature.damageCreature(player);
		creatureHealth = creature.getHealth();
		playerDamage = player.getDamage();
		
	}
	/*
	 * This uses the EntityPLayer object and the EntityCreature object, using the existing methods inside, applies the damage to player
	 * 
	 * This takes care of changing the player health, and takes into effect of the speed, there is a chance nothing will happen at all.
	 * 
	 * if missed, the message will be stored to static variable "exception"
	 */
	
	public static void creatureTurn (EntityPlayer player, EntityCreature creature){
		if (player.speedX > creature.hitChance())
			{
				player.health = player.getHealth();
				Exeption = "It Missed!!!!!";
			}
		else 
			{
				player.damagePlayer(creature);
				playerHealth = player.getHealth();
				creatureDamage = creature.getDamage();
			}
	}

}
