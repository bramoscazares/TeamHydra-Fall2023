package Model;

import java.util.ArrayList;

public class Room {
    private int room;
    private String roomName;
    private String roomDescription;
    private boolean roomVisited;
    private int roomNorth;
    private int roomSouth;
    private int roomEast;
    private int roomWest;

    private ArrayList<Item> roomInventory = new ArrayList<>();
    private Puzzle roomPuzzle;
    private Monster monster;


    public Room(int room, String roomName, String roomDescription, boolean roomVisited, int roomNorth, int roomSouth, int roomEast, int roomWest) {
        this.room = room;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.roomVisited = roomVisited;
        this.roomNorth = roomNorth;
        this.roomSouth = roomSouth;
        this.roomEast = roomEast;
        this.roomWest = roomWest;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public boolean isRoomVisited() {
        return roomVisited;
    }

    public void setRoomVisited(boolean roomVisited) {
        this.roomVisited = roomVisited;
    }

    public int getRoomNorth() {
        return roomNorth;
    }

    public void setRoomNorth(int roomNorth) {
        this.roomNorth = roomNorth;
    }

    public int getRoomSouth() {
        return roomSouth;
    }

    public void setRoomSouth(int roomSouth) {
        this.roomSouth = roomSouth;
    }

    public int getRoomEast() {
        return roomEast;
    }

    public void setRoomEast(int roomEast) {
        this.roomEast = roomEast;
    }

    public int getRoomWest() {
        return roomWest;
    }

    public void setRoomWest(int roomWest) {
        this.roomWest = roomWest;
    }

    public ArrayList<Item> getRoomInventory(){ return roomInventory; }

    public void setRoomPuzzle(Puzzle puzzle){
        this.roomPuzzle = puzzle;
    }

    public Puzzle getRoomPuzzle(){
        return roomPuzzle;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }


}
