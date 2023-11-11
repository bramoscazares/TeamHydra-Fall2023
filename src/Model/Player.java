package Model;

public class Player extends Entity{

    protected Item equippedItem;

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
