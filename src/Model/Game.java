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
    } // By Mike

    public void searchRoom(String[] input){ // By Mike
        Boolean hasItem = true;
        Boolean hasMonster = true;
        Boolean hasPuzzle = true;
        String output = "";

        String[] listString = currentRoom.getName().split(" ");
        if (input.length < 3 || input.length >= 4){
            System.out.println("Invalid room name.");
            return;
        } else if (!(listString[0].equalsIgnoreCase(input[1]) && listString[1].equalsIgnoreCase(input[2]))){
            System.out.println("Invalid room name.");
            return;
        }

        String s = currentRoom.getDesc().replace(".", ".\n");
        System.out.println("You have searched " + currentRoom.getName() + ".\n" + s);

        if (currentRoom.getRoomItems().size() == 0){
            hasItem = false;
        }
        if (currentRoom.getMonster() == null){
            hasMonster = false;
        }
        if (currentRoom.getRoomPuzzle() == null){
            hasPuzzle = false;
        }

        if (hasItem && hasMonster && hasPuzzle){
            System.out.println("Here are the items, monsters, and puzzles, that you need to use, defeat, and solve.");
            for (int i = 0; i < currentRoom.getRoomItems().size(); i++) {
                output = currentRoom.getItem(i).getName() + ", " + output;
            }
            output = output + currentRoom.getMonster().getName();
            output = output + ", " + currentRoom.getRoomPuzzle().getName();
            System.out.println(output);
        } else if (hasItem) {
            System.out.print("Here are the items you need to use.");
            for (int i = 0; i < currentRoom.getRoomItems().size(); i++) {
                System.out.print(currentRoom.getItem(i).getName() + ", ");
            }
        } else if (!hasItem) {
            System.out.println("There are no items you may use.");
        }
        if (hasMonster && !hasPuzzle){
            System.out.println("The room has " + currentRoom.getMonster().getName() + " but no puzzles to solve.");
        } else {
            if (hasMonster){
                System.out.println("The " + currentRoom.getMonster().getName() + " monster in this room.");
            } else {
                System.out.println("There are no monsters in this room.");
            }
            if (hasPuzzle){
                System.out.println("There is the " + currentRoom.getRoomPuzzle().getName() + " puzzle in this room.");
            } else {
                System.out.println("There are no puzzles to solve in this room.");
            }
        }

    }

    public void isVisitedRoom(){ // Mike: forgive me for this but, they specifically said to add this command
        if (currentRoom.isVisited()){
            System.out.println("You have visited this room before.");
        } else {
            System.out.println("This room looks new.");
        }
    }

}
