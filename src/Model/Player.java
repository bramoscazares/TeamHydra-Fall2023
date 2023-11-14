package Model;

import java.util.ArrayList;

public class Player extends Entity{

    protected Item equippedItem;
    
    protected ArrayList<Item> playerInventory = new ArrayList<>();

    public Player(String objectId, String name, String description, int roomLocation, int healthPoints, int attackPoints, boolean defeat) {
        super(objectId, name, description, roomLocation, healthPoints, attackPoints, defeat);
    }


    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }
    
    
}
