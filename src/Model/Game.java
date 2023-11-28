package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Game  implements Serializable {

    private LinkedList<Room> roomLinkedList = new LinkedList<>();  //Brian
    private ArrayList<Puzzle> puzzleArrayList = new ArrayList<>();

    public ArrayList<String> gameHelpArrayList = new ArrayList<>(); //Brian

    private ArrayList<Item> itemArrayList = new ArrayList<>();

    private Player player = new Player("P1","Generic","This is a generic description.",1,100,10,false);  //Brian

    private Room currentRoom; //Brian
    private Room prevRoom;
    private transient FileInputStream inputStream; //Brian
    private transient Scanner fileIn; //Brian



    public void populateRooms(File file) throws FileNotFoundException { //ENTIRE METHOD : BRIAN
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
            int roomEast = Integer.parseInt(tempArray[5]);  // Mike: swapped these because when I put the codequest rooms in it was in nesw format
            int roomSouth = Integer.parseInt(tempArray[6]);
            int roomWest = Integer.parseInt(tempArray[7]);
            int portalRoom = Integer.parseInt(tempArray[8]);

            //Adds room to room array  Mike: swapped order of these because when I put the codequest rooms in it was in nesw format
            this.roomLinkedList.add(new Room(roomNum,roomName,roomDescription,roomVisited,roomNorth,roomEast,roomSouth,roomWest, portalRoom));
        }

        inputStream = new FileInputStream("gameData/portal checklist.txt"); //Mike adding a bit to put in what rooms to check for completion to access portals
        fileIn = new Scanner(inputStream);
        while(fileIn.hasNext()){
            String[] tempArray = fileIn.nextLine().split("=");
            int portalRoom = Integer.parseInt(tempArray[0]);
            String[] checkRooms = tempArray[1].split(",");
            int[] roomsCheck = new int[checkRooms.length];
            for (int i = 0; i < checkRooms.length; i++) {
                roomsCheck[i]= Integer.parseInt(checkRooms[i]);
            }

            roomLinkedList.get(portalRoom-1).setPortalCheck(roomsCheck);
        }

    }

    public void populateItems(File file) throws FileNotFoundException {
        //Scans file
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while (fileIn.hasNext()) {
            String[] tempArray = fileIn.nextLine().split("~");

            String itemID = tempArray[0];
            String itemName = tempArray[1];
            String itemDescription = tempArray[2];
            String[] itemRoomLocation = tempArray[3].split(","); // Mike: Changed to set up for parsing multiple instances of same item type in different rooms
            String itemType = tempArray[4];
            boolean equipable = Boolean.parseBoolean(tempArray[5]);
            boolean usable = Boolean.parseBoolean(tempArray[6]);
            int healthPoints = Integer.parseInt(tempArray[7]);
            int attackPoints = Integer.parseInt(tempArray[8]);

            for (int i = 0; i < itemRoomLocation.length; i++) { // Mike: Makes the item multiple times
                this.itemArrayList.add(new Item(itemID, itemName, itemDescription, Integer.parseInt(itemRoomLocation[i]),itemType, equipable, usable, healthPoints,attackPoints));
            }
            
        }
        //for (Item i : itemArrayList) { //Mike: Put the items in the rooms
        //    roomLinkedList.get(i.getRoomLocation()-1).addItem(i);
        //}
        
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

    public void populateMons(File file) throws FileNotFoundException { //From Brian's "populateRooms()", 75% Modified by Mohammed to fit Monsters
        //Scans file
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()){
            String[] tempArray = fileIn.nextLine().split("=");

            //Mo: assigns parts of file line to temp variables, then variables into Monster constructor
            String monID   = tempArray[0];
            String monName = tempArray[1];
            String monDesc = tempArray[2];
            int MonHP      = Integer.parseInt(tempArray[3]);
            int MonATK     = Integer.parseInt(tempArray[4]);
            int LcnRoomID  = Integer.parseInt(tempArray[5]);
            boolean monDefeat = Boolean.parseBoolean(tempArray[6]);// Brian

            this.roomLinkedList.get(LcnRoomID - 1).setMonster(new Monster(monID,monName,monDesc,LcnRoomID,MonHP,MonATK, monDefeat));
        }//end while

    }//end populateMons(), by Mohammed

    public void setFirstRoom(){ //brian
        currentRoom = roomLinkedList.get(0); //brian
    }

    public void pickupItem (String itemName) { //Juan: Entire Method
    	for (Item roomitem : currentRoom.getRoomItems()) {
    		if (roomitem.getName().equalsIgnoreCase(itemName)) {
    			player.getInventory().add(roomitem);
    			currentRoom.getRoomItems().remove(roomitem);
    			System.out.println(roomitem.getName() + " has been picked up from the room and successfully added to the player iventory.");
    			return;
    		}
    	}
    }

    public void fillrooms(){ //ENTIRE METHOD: BRIAN

        //Goes through each room one by one
        for(int i = 0; i < roomLinkedList.size(); i++){

            //Populates Room i with any items that correlate
            for(Item item: itemArrayList){
                if(item.getRoomLocation() == roomLinkedList.get(i).getId()){
                    roomLinkedList.get(i).getRoomItems().add(item);
                }
            }

            //Populates Room i with any puzzles that correlate
            for(Puzzle puzzle: puzzleArrayList){
                if(puzzle.getRoomLocation() == roomLinkedList.get(i).getId()){
                    roomLinkedList.get(i).setRoomPuzzle(puzzle);
                }
            }

            //WHEN MOHAMMED ADDS MONSTERS I WILL ADD THIS
//            //Populates Room i with any monsters that correlate
//            for(Monster monster: monsterArrayList){
//                if(monster.getRoomLocation() == roomLinkedList.get(i).getId()){
//                    roomLinkedList.get(i).setMonster(monster);
//                }
//            }

        }
    } //Adds items and Puzzles into designated rooms by BRIAN

    public void dropItem (String itemName) { //Juan: Entire Method
    	for(Item i: player.playerInventory) {
    		if (i.getName().equalsIgnoreCase(itemName)) {
    			currentRoom.getRoomItems().add(i);
    			player.getInventory().remove(i);
    			System.out.println(i.getName() + " has been dropped.");
    			return;
    		}
    	}
    	System.out.println("This item is not in your inventory.");
    }

    public void openInventory() { //Juan: Entire Method
        System.out.println("Inventory:");
        if (player.playerInventory.isEmpty()) {
            System.out.println("Empty");
        } else {
            for (Item item : player.playerInventory) {
                System.out.println(item.getName());
            }
        }
    }
    public Item exploreItem(String itemName) { //Juan: Entire Method
        for (Item item: player.playerInventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void useItem(String itemName) { //Juan: Entire Method
        for(Item item : player.playerInventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                if (item.type.equalsIgnoreCase("potion")) {
                    player.playerInventory.remove(item);
                    player.setHealthPoints(player.getHealthPoints() + item.getHealthPoints());
                    System.out.println("\nYour health is " + player.getHealthPoints());
                    return;
                } else if (item.type.equalsIgnoreCase("Armor")) {
                    player.playerInventory.remove(item);
                    player.setAttackPoints(player.getHealthPoints() + item.getHealthPoints());
                    System.out.println(item.getName() + " has been successfully equipped.");
                    return;
                } else if (item.type.equalsIgnoreCase("Weapon")) {
                    player.playerInventory.remove(item);
                    player.setAttackPoints(player.getAttackPoints() + item.getAttackPoints());
                    System.out.println(item.getName() + " has been successfully equipped.");
                    return;
                }
            }
            System.out.println("This item is not in your inventory");
        }
    }

    public boolean move(char direction){ // By Mike
        Room tempCurrRoom = currentRoom; //Mo, stores temp, for use in flee/run/prevRoom

        switch(direction) {
            case 'n':
                if (currentRoom.getNorth() > 0){ //checks for if a room exist in direction
                    currentRoom.setVisited(true); // if there is a room current room will chang so mark this room as visited
                    currentRoom = roomLinkedList.get( currentRoom.getNorth()-1); // move to the room found earlier
                    prevRoom = tempCurrRoom; //Mo, sets prevRoom to former current room
                    return true; //make sure we pass back that we moved in the boolean return
                } else {return false;} // return false if we couldn't move due to room not existing
            case 'e':
                if (currentRoom.getEast() > 0){
                    currentRoom.setVisited(true);
                    currentRoom = roomLinkedList.get(currentRoom.getEast()-1);
                    prevRoom = tempCurrRoom; //Mo, just this line
                    return true;
                } else {return false;}
            case 's':
                if (currentRoom.getSouth() > 0){
                    currentRoom.setVisited(true);
                    currentRoom = roomLinkedList.get(currentRoom.getSouth()-1);
                    prevRoom = tempCurrRoom; //Mo, just this line
                    return true;
                } else {return false;}
            case 'w':
                if (currentRoom.getWest() > 0){
                    currentRoom.setVisited(true);
                    currentRoom = roomLinkedList.get(currentRoom.getWest()-1);
                    prevRoom = tempCurrRoom; //Mo, just this line
                    return true;
                } else {return false;}
            default:
                return false;
        }
    }

    public boolean usePortal(){
        boolean passPortal = true;
        if (currentRoom.getPortal() == 0){
            System.out.println("There is no portal in this room.");
            return false;
        } else {
            for (int i = 0; i < currentRoom.getPortalCheck().length; i++) {
                if(roomLinkedList.get(i).getMonster() != null || (roomLinkedList.get(i).getRoomPuzzle() != null && !roomLinkedList.get(i).getRoomPuzzle().isSolved())){
                    passPortal = false;
                }
            }
        }
        if (passPortal){
            currentRoom = roomLinkedList.get(currentRoom.getPortal()-1);
            System.out.println("You pass through the portal into a new realm full of exciting challenges.");
            return true;
        }
        System.out.println("The portal is this room is inactive until you defeat all the challenges in this area.");
        return false;
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
            System.out.print("Here are the items you need to use. ");
            for (int i = 0; i < currentRoom.getRoomItems().size(); i++) {
                output = output + currentRoom.getItem(i).getName() + ", ";
            }
            System.out.println(output);
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

    public void populatePuzzles(File file) throws FileNotFoundException {
        //Scans File
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()){
            String[] tempArray = fileIn.nextLine().split("#");
            String puzzleObjectId = tempArray[0];
            String puzzleName = tempArray[1];
            String puzzleDesc = tempArray[2];
            int puzzleRoomLocation = Integer.parseInt(tempArray[3]);
            String puzzleAnswer = tempArray[4];
            String puzzleHint = tempArray[5];
            int puzzleAttempts = Integer.parseInt(tempArray[6]);
            boolean solved = Boolean.parseBoolean(tempArray[7]);
            //Adds Puzzle to array

            this.puzzleArrayList.add(new Puzzle(puzzleObjectId, puzzleName, puzzleDesc , puzzleRoomLocation, puzzleAnswer, puzzleHint,  puzzleAttempts ,solved));

        }

    } //Adds Puzzles into an ArrayList
    public void hint(){ //Xavier bulk some additions by mike
        boolean bookFound = false;
        if (currentRoom.getRoomPuzzle() == null) System.out.println("There are no puzzles in this room.");
        else
        {
            for (Item item : player.playerInventory) {
                if (item.getObjectId() == "A2"){
                    System.out.println("the hint for this puzzle is: " + currentRoom.getRoomPuzzle().getHint());
                    bookFound = true;
                }
            }
            if (bookFound == false){
                System.out.println("You need the ”Enchanted Puzzle Book” to get the puzzle hint");
            }
        }
    }

    public void explore(){//Xavier bulk some additions by mike
        if (currentRoom.getRoomPuzzle() == null) System.out.println("There are no puzzles in this room.");
        else
        {
            System.out.println("puzzle description: " + currentRoom.getRoomPuzzle().getDescription());
        }
    }

    public void solve(String solution){ //Xavier bulk some additions by mike
        if (currentRoom.getRoomPuzzle() == null) System.out.println("There are no puzzles in this room.");
        else if (solution.equalsIgnoreCase(currentRoom.getRoomPuzzle().getAnswer()))
        {
            System.out.println("Your answer ís correct");
            currentRoom.getRoomPuzzle().setSolved(true);
        } else {
            System.out.println("Your answer is incorrect. Try again");
            for (Item item : player.playerInventory) {
                if (item.getObjectId().matches("A2")){
                    System.out.println("You can use the support item to get the hint");
                }
            }
        }
    }



    public void mInfo() { // Entirely Mo: method for m-info command, returns Info
        Room Lcn = this.currentRoom;
        Monster Mon = Lcn.getMonster();

        //print Monster Details
        System.out.println("Name: " + Mon.getName());
        System.out.println(Mon.getDescription());
        System.out.println("HP: " + Mon.getHealthPoints());
        System.out.println("ATK: " + Mon.getAttackPoints());
        //NEED TO PRINT MONSTER POWERS HERE AS WELL

    }//end mInfo(), Mohammed

    public Integer[] attackSequence() throws monDeathEvent, playerDeathEvent {//Entire, Mo the bro: Returns int[] of Plyr & Mon HP

        Monster mon = currentRoom.getMonster();


        //Plyr attacks Mon.
        // If Mon dies, remove from room, throw to notify Game>combatInterface() of Mon defeat
        // otherwise, it counterattacks
        player.attackIsKilled(mon);

        if (mon.defeat) {
            currentRoom.setMonster(null); //removes mon from room
            throw new monDeathEvent(mon.getName());
        }
        else mon.attackIsKilled(player);


        // If Player dies, throw to notify
        if (player.defeat) {
            throw new playerDeathEvent();
        }


        //Store & return HP stats
        int monHP = mon.getHealthPoints();
        int plyrHP = player.getHealthPoints();
        return new Integer[]{plyrHP, monHP};

    }//end attackSequence(), entire by Mo (i'm such a legend, ik, yes need to thank me)

    public void runAway() {//Entire, Mo: Plyr moves to prevRoom, updates prevRoom
        Room monRoom = currentRoom;
        currentRoom = prevRoom;
        prevRoom = monRoom;
    }//end runAway() by Mo

    public String getRoomMonsterName() {//Entire, Mo: we REALLY need access to Monster name from Controller
        return currentRoom.getMonster().getName();
    }



}//end Class Game
