package util;

/*
 * ryan chen
 */
public class Pistol extends Item {
    private double damage;
    private String name = "Pistol";
    /*
     * defualt construction
     */
    public Pistol(){
        super();
    }
    /*
     * requires damage and speed (speed works as a times the amount the current speed of the character is) care in use of SpeedX, no more than 2.3
     */
    public Pistol(double damage){
        this.damage = 50;
        
    }
    /*
     * returns damage of object
     */
    public double getDamage(){
        return damage;
    }
    /*
     * returns name of the object so it can be displayed in the backpack and battle menu
     */
    public String getName(){
        return name;
    }
}