package Model;

public class Item extends GameObject{
	protected String type;
	protected boolean equipable;
	protected boolean usable;
	protected int attackPoints;
	protected int healthPoints;
	
    public Item(String objectId, String name, String description, int roomLocation, String type, boolean equipable, boolean usable, int attackPoints, int healthPoints) {
        super(objectId, name, description, roomLocation);
        this.type = type;
        this.equipable = equipable;
        this.usable = usable;
        this.attackPoints = attackPoints;
        this.healthPoints = healthPoints;
    }
    
    public String getType() {
        return type;
    }
    
    public boolean getEquipable() {
    	return equipable;
    }
    
    public boolean getUsable() {
    	return usable;
    }
    
    public int getAttackPoints() {
    	return attackPoints;
    }
    
    public int getHealthPoints() {
    	return healthPoints;
    }

}
