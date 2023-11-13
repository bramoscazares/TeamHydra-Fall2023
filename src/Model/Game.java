package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {

    private LinkedList<Room> roomLinkedList = new LinkedList<>();  //Brian

    public ArrayList<String> gameHelpArrayList = new ArrayList<>(); //Brian

    private Player player = new Player("P1","Generic","This is a generic description.",1,100,100,false);  //Brian

    private Room currentRoom;  //Brian
    private FileInputStream inputStream;  //Brian
    private Scanner fileIn;  //Brian

    public void populateRooms(File file) throws FileNotFoundException {
        //ENTIRE METHOD : BRIAN

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
            int roomSouth = Integer.parseInt(tempArray[5]);
            int roomEast = Integer.parseInt(tempArray[6]);
            int roomWest = Integer.parseInt(tempArray[7]);

            //Adds room to room array
            this.roomLinkedList.add(new Room(roomNum,roomName,roomDescription,roomVisited,roomNorth,roomSouth,roomEast,roomWest));
        }

    }

    public void populateHelp(File file) throws FileNotFoundException {
        //Scans File
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()){
            gameHelpArrayList.add(fileIn.nextLine()); //Adds String into array
        }
    } //Adds Commands into an ArrayList

    public void setFirstRoom(){
        currentRoom = roomLinkedList.get(0);
    }


}
