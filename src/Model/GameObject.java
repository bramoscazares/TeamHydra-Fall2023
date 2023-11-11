package Model;

public class GameObject {

    private String objectId;
    private String name;
    private String description;

    private  int roomLocation;


    public GameObject(String objectId, String name, String description, int roomLocation) {
        this.objectId = objectId;
        this.name = name;
        this.description = description;
        this.roomLocation = roomLocation;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRoomLocation() {
        return roomLocation;
    }
}
