package util;

public class Tank extends EntityPlayer{
	
	public Tank(){
		super(0.3,150,0.35,15);
		this.maxHealth = this.health;
		this.abilities = new TankAbilties(this);
	}
	
}
