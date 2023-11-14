package Model;

public class Monster extends Entity{

    public Monster(String objectId, String name, String description, int roomLocation, int healthPoints, int attackPoints) {
        super(objectId, name, description, roomLocation, healthPoints, attackPoints, false); //Mohammed: Class Entity should initialize "defeat" to false, instead of parameter
    }

}
