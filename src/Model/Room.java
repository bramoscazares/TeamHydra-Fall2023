package Model;

import java.util.ArrayList;

/* By group agreement and assessment of prexisting code we have all agreed to use Brian's clean and easy to read code for the basis of the group assignment
* The following code is in large part property of Brian Ramos Cazares, with adjustments made by Michael Hopkins
*/
public class Room {

    private int id;

    private String name;

    private String desc;

    private boolean visited;

    private int north;

    private int east;

    private int south;

    private int west;

    private ArrayList<Item> roomItems = new ArrayList<>();

    private Puzzle roomPuzzle;

    private Monster monster;

    // Constructor
    public Room(int room, String name, String desc, boolean visited, int north, int east, int south, int west) {
        this.id = room;
        this.name = name;
        this.desc = desc;
        this.visited = visited;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }



    // Getters and Setters

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDesc() {return desc;}

    public void setDesc(String desc) {this.desc = desc;}

    public boolean isVisited() {return visited;}

    public void setVisited(boolean visited) {this.visited = visited;}

    public int getNorth() {return north;}

    public void setNorth(int north) {this.north = north;}

    public int getEast() {return east;}

    public void setEast(int east) {this.east = east;}

    public int getSouth() {return south;}

    public void setSouth(int south) {this.south = south;}

    public int getWest() {return west;}

    public void setWest(int west) {this.west = west;}

    public ArrayList<Item> getRoomItems() {return roomItems;}

    public void addItem(Item plusItem) { // Mike: didn't have a thing for adding items to rooms woops
        roomItems.add(plusItem);
    }

    public Item getItem(int itemSpotInArray) {return  roomItems.get(itemSpotInArray);}

    public void setRoomItems(ArrayList<Item> roomItems) {
        this.roomItems = roomItems;
    }

    public Puzzle getRoomPuzzle() {
        return roomPuzzle;}

    public void setRoomPuzzle(Puzzle roomPuzzle) {
        this.roomPuzzle = roomPuzzle;}

    public Monster getMonster() {
        return monster;}

    public void setMonster(Monster monster) {
        this.monster = monster;}

    // toString override
    @Override
    public String toString(){return id + " " + name;}

}
