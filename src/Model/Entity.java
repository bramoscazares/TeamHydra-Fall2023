package Model;

import java.util.ArrayList;

public class Entity extends GameObject{

    protected int healthPoints;
    protected int attackPoints;

    protected boolean defeat;

    protected ArrayList<Item> inventory = new ArrayList<>();

    public Entity(String objectId, String name, String description, int roomLocation, int healthPoints, int attackPoints, boolean defeat) {
        super(objectId, name, description, roomLocation);
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.defeat = defeat;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public boolean isDefeat() {
        return defeat;
    }

    public void setDefeat(boolean defeat) {
        this.defeat = defeat;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
