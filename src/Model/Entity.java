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


    public void changeHealthPoints(int HPChange) {//entire, Mo
        this.healthPoints += HPChange;

        //Mo, throw an exception. Controller catches and calls View. Also updates Entity boolean "defeat"
        if (this.healthPoints < 0) {
            this.defeat = true; //Mo: idk if we'll ever use this field, but, just in case, might as well.
            throw new RuntimeException(this.getClass().getName() + " Entity " + this.getName() + " healthPoints = 0. Not necessarily an issue");
        }

    }//end changeHealthPoints() by Mo

    public int getAttackPoints() {
        return attackPoints;
    }

    public void changeAttackPoints(int ATKChange) {//Entire, Mo.
        this.attackPoints += ATKChange;
        if (this.attackPoints < 0) throw new RuntimeException(this.getClass().getName() + " Entity " + this.getName() + " attackPoints < 0. Okay, someone did something wrong. Why is ATK < 0?");
        //Mo: normally if ATK < 0 I'd just set it to 0.
        // But, in this case, this should never happen.
        // Something went seriously wrong if it does.
    }//end changeAttackPoints() by Mo

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
