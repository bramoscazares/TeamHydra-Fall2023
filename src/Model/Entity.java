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

    public boolean setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;

        //end if{}, Mo
        if (this.healthPoints <= 0) this.defeat = true; //Mo
        return defeat;
    }//end setHealthPoints()


    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
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


    public boolean takeDamage(int HPdmg) {//entire, Mo: True when HP<=0
        this.healthPoints -= HPdmg;

        if (this.healthPoints <= 0) this.defeat = true;
        return this.defeat;
    }//end changeHealthPoints() by Mo

    public boolean attackKill(Entity target) {//Entire, Mo: True when target.isDefeat()
        target.takeDamage(this.attackPoints);
    }//
}
