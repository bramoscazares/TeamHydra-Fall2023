package Model;

public class GameObject {

    private String objectId;
    private String name;
    private String description;

    public GameObject(String objectId, String name, String description) {
        this.objectId = objectId;
        this.name = name;
        this.description = description;
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
}
