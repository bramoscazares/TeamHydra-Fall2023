package Model;

import java.util.ArrayList;

public class Player extends Entity{

    protected Item weapon;

    protected Item armor;
    
    protected ArrayList<Item> playerInventory = new ArrayList<>();

    public Player(String objectId, String name, String description, int roomLocation, int healthPoints, int attackPoints, boolean defeat) {
        super(objectId, name, description, roomLocation, healthPoints, attackPoints, defeat);
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public Item getArmor() {
        return armor;
    }

    public void setArmor(Item armor) {
        this.armor = armor;
    }

    public ArrayList<Item> getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(ArrayList<Item> playerInventory) {
        this.playerInventory = playerInventory;
    }
}
