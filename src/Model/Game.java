package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {

    private LinkedList<Room> roomLinkedList = new LinkedList<>();

    private Player player = new Player("P1","Generic","This is a generic description.",1,100,100,false);

    private Room currentRoom;
    private FileInputStream inputStream;
    private Scanner fileIn;

    public void populateRooms(File file) throws FileNotFoundException { //ENTIRE METHOD : BRIAN
        //Scans file
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()){
            String[] tempArray = fileIn.nextLine().split("=");

            int roomNum = Integer.parseInt(tempArray[0]);
            String roomName = tempArray[1];
            String roomDescription = tempArray[2];
            boolean roomVisited = Boolean.parseBoolean(tempArray[3]);
            int roomNorth = Integer.parseInt(tempArray[4]);
            int roomEast = Integer.parseInt(tempArray[5]);  // Mike: swapped these because when I put the codequest rooms in it was in nesw format
            int roomSouth = Integer.parseInt(tempArray[6]);
            int roomWest = Integer.parseInt(tempArray[7]);

            //Adds room to room array  Mike: swapped order of these because when I put the codequest rooms in it was in nesw format
            this.roomLinkedList.add(new Room(roomNum,roomName,roomDescription,roomVisited,roomNorth,roomEast,roomSouth,roomWest));
        }

    }

    public void setFirstRoom(){
        currentRoom = roomLinkedList.get(0);
    }

    public boolean move(char direction){ // By Mike
        switch(direction) {
            case 'n':
                if (currentRoom.getNorth() > 0){ //checks for if a room exist in direction
                    currentRoom.setVisited(true); // if there is a room current room will chang so mark this room as visited
                    currentRoom = roomLinkedList.get( currentRoom.getNorth()-1); // move to the room found earlier
                    return true; //make sure we pass back that we moved in the boolean return
                } else {return false;} // return false if we couldn't move due to room not existing
            case 'e':
                if (currentRoom.getEast() > 0){
                    currentRoom.setVisited(true);
                    currentRoom = roomLinkedList.get(currentRoom.getEast()-1);
                    return true;
                } else {return false;}
            case 's':
                if (currentRoom.getSouth() > 0){
                    currentRoom.setVisited(true);
                    currentRoom = roomLinkedList.get(currentRoom.getSouth()-1);
                    return true;
                } else {return false;}
            case 'w':
                if (currentRoom.getWest() > 0){
                    currentRoom.setVisited(true);
                    currentRoom = roomLinkedList.get(currentRoom.getWest()-1);
                    return true;
                } else {return false;}
            default:
                return false;
        }
    }

    public void printRoomName(){ // Mike: better than a block of code in game controller and display to do the same thing
        System.out.println(currentRoom.getName());
    }

}
