package Model;

public class Monster extends Entity{

    public Monster(String objectId, String name, String description, int roomLocation, int healthPoints, int attackPoints, boolean defeat) {
        super(objectId, name, description, roomLocation, healthPoints, attackPoints, defeat);
    }

}
