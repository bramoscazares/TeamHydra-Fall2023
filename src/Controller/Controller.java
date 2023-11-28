package Controller;

import Model.*;
import View.Display;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Controller {
    private Game game;  //Brian
    private Display display;  //Brian
    private Scanner input;  //Brian

    private ArrayList<File> gameFiles = new ArrayList<>();  //Brian
    private HashMap<String,File> gameFilesAlt = new HashMap<>();  //Brian


    private String saveFile;

    private boolean gameOver = false;

    private boolean Continue = false;

    public Controller(Game game, Display display){
        this.game = game;
        this.display = display;
        this.input = new Scanner(System.in);

    }

    public void startGame() throws FileNotFoundException{
        gameLoad(); //Brian
        display.printSeperator(); //Brian
        while (!gameOver) {

            //Prints room description
            //display.displayRoomInfo(game.getCurrentRoom());
            game.printRoomName(); // Mike: pulling room name from game through controller to pass to display was too much hassle
            System.out.println("What would you like to do? Type 'help' for commands."); //Brian
            String userInput = input.nextLine();//Brian

            //Passes user input to command processing method
            userCommand(userInput);//Brian

            display.printSeperator(); //Brian
        }

    }

    public void userCommand(String input) { //Entire Method: Brian

        input = input.toLowerCase();
        String item = splitCommand(input);
        String[] wordyCommand = longCommand(input);
        String multiword = multiwordIn(input);

        if (input.equalsIgnoreCase("quit")) {
            gameOver = true;
        } else if (input.equalsIgnoreCase("n")) { // Mike: started adding here, looks for n input to move north
            if (!(game.move('n'))) { // tries to move to the direction and finds out if it was a valid move, two birds one stone
                display.printInvalidDirection();
            }
        } else if (input.equalsIgnoreCase("e")) { //as above but e for east
            if (!(game.move('e'))) {
                display.printInvalidDirection();
            }
        } else if (input.equalsIgnoreCase("s")) { //as above but s for south
            if (!(game.move('s'))) {
                display.printInvalidDirection();
            }
        } else if (input.equalsIgnoreCase("w")) { //as above but w for weast and for wumbo
            if (!(game.move('w'))) {
                display.printInvalidDirection();
            }
        } else if (input.startsWith("enter")) {
            if (item.equalsIgnoreCase("portal")){
                if (game.usePortal()){
                    System.out.println("true");
                } else {System.out.println("false"); }
            }
        } else if (wordyCommand[0].equalsIgnoreCase("search")){ // needed a user command thing that worked
            game.searchRoom(wordyCommand);
        } else if (input.equalsIgnoreCase("isVisitedRoom")) {
            game.isVisitedRoom(); // Mike: Stopped adding in front of this else
        } else if (input.equalsIgnoreCase("m-info")){ //else block by Mo
            try {game.mInfo();}
            catch (NullPointerException nullEx) {
                display.noMonster();
            } //end Mo's block
        } else if (input.equalsIgnoreCase("help")){  //Brian
            help(); //Brian
        } else if (input.startsWith("pick")){ //Juan
            game.pickupItem(multiword); //Juan
        } else if (input.startsWith("drop")){ //Juan
            game.dropItem(multiword); //Juan
        } else if (input.startsWith("use")){ //Juan
            game.useItem(multiword); //Juan
        } else if (input.contains("open")){ //Juan
            game.openInventory(); //Juan
        } else if (input.startsWith("hint")){ //Juan
                game.hint(); //Juan
        } else if (input.startsWith("solve")){ //Juan
                game.solve(item); //Juan
        } else if (input.startsWith("explore")){ //Juan
            if(item.equalsIgnoreCase("puzzle")){ //Mike: this needs to be set up to deal with explore puzzle versus explore "item name"
                game.explore();
            }
        }  else if (input.contains("save")){ //Brian
            saveGame(); //Brian
        }  else if (input.contains("load")){ //Brian
            loadGame(); //Brian
        } else if (input.equalsIgnoreCase("attack")) { //Mo, this entire else block and try/catch chain
            try {combatInterface();} //Mo: True if player dies in combat()
            catch (NullPointerException nullMonster) {display.noMonster();} //Mo: careful, catches *any* nullExpn, not just Mons
           //end Mo's Block
        } else {
            display.printInvaldInput(); //Brian
        }

    }

    public String splitCommand(String string){ //Brian
        String[] listString = string.split(" "); //Brian

        if(listString.length>1){ return listString[1]; } //Brian

        return string; //Brian
    }

    public String splitCommand2(String string){ //Brian
        String[] listString = string.split(" "); //Brian

        if(listString.length>1){return listString[1]; } //Brian

        return string; //Brian
    }

    public void addGameFiles(File rooms, File items) {
    	gameFiles.add(rooms);
    	gameFiles.add(items);
    }

    public String[] longCommand(String input){ // From Mike
        String[] listString = input.split(" "); // From Brian above
        return listString;
    }

    public String multiwordIn(String input){ //Mike: I should have made this the first time but it's too late to go back and fix all the misuses
        String[] listString = input.split(" ");
        String secondpart = "";
        int length = listString.length;
        if (listString.length > 1){
            for (int i = 1; i < length; i++) {
                secondpart = secondpart + listString[i];
                if ((i+1)!=length){
                    secondpart = secondpart + " ";
                }
            }
        } else { secondpart = listString[0];}
        return secondpart;
    }

    public void setupGame() throws FileNotFoundException {
        game.populateRooms(gameFiles.get(0));
        game.setFirstRoom();
        //game.populateItems(gameFiles.get(1));
    }

    private void help() {
        //ENTIRE METHOD : Brian

        display.printHelp(game.gameHelpArrayList); //Brian
        display.printHelpReturn();  //Brian
        String userInput = input.nextLine();  //Brian
        while (true){
            if(userInput.equalsIgnoreCase("return")){  //Brian
                break;  //Brian
            } else {  //Brian
              display.printInvaldInput();  //Brian
            }
            userInput = input.nextLine();  //Brian
        }
    }


    public boolean combatInterface() {//Entire, Mo the bro. True when Player dies
        //Player already typed attack at this point. boolean initial lets us skip first input Query
        String userInput = "attack";
        boolean initial = true;
        String monName = game.getRoomMonsterName();


        while (true) {

            //Query User Input ("attack" or "run"), but skip first time
            if (initial) initial = false;
            else {
                display.combatQuery();
                userInput = input.nextLine().toLowerCase();
            }

            //Input determination
            switch (userInput) {
                case "attack":
                    try {
                        Integer[] HPs = game.attackSequence();
                        display.combatStatus(HPs[0], HPs[1]); //TEST, FILL WITH ACTUAL VALUES
                    } catch (monDeathEvent ded) {
                        display.monDeath(monName);
                        return false;
                    } catch (playerDeathEvent ded) {
                        display.playerDeath(monName);
                        return true;
                    }

                    //Technically, there is no print msg upon attack, only a "status" display msg after each sequence showing stats of both.
//                    display.playerAttack();
//                    display.monAttack();
                    break;

                case "run":
                    game.runAway();
                    display.runAway(monName);
                    return false;

                default:
                    display.printInvaldInput();
                    break;
            }//end switch(userInput)
        }//end loop while()
    }//end combat(), entire by Mo (i'm such a legend, ik, yes need to thank me)



    private void saveGame(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.saveFile))) {
            oos.writeObject(game);
            Display.gameSaveSuccess();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nGame save failed.");
        }
    }

    private void loadGame(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.saveFile))) {
            game = (Game) ois.readObject();
            Display.gameLoadSuccess();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("\nGame load failed.");
        }

    }

    private void gameLoad() { //ENTIRE METHOD: Brian

        File saveFiles = new File("SaveFiles");
        String[] saves = saveFiles.list();
        while (true) {

            display.newOrLoadGame(); //brian
            String userInput = input.nextLine(); //brian

            if (userInput.equalsIgnoreCase("new player")) {
                display.newUserName();

                while (true) {
                    String userName = input.nextLine(); //brian
                    String userNameFile = userName + ".dat";
                    File save = new File(saveFiles, userNameFile);
                    if (save.exists() || (userName.length() >= 15)) {
                        display.displayInvalidUsername();
                    } else {
                        this.saveFile = "SaveFiles/"+userNameFile;
                        return;
                    }
                }

            } else if (userInput.equalsIgnoreCase("load player")) {
                if (saves != null && saves.length == 0) {
                   display.noSavesExists();
                } else {
                    display.printSaveFiles(saves);
                    display.loadUserName();
                    String userName = input.nextLine(); //brian
                    String saveString = userName + ".dat";
                    File save = new File(saveFiles, saveString);

                    if (save.exists()) {
                        this.saveFile = "SaveFiles/"+saveString;
                        loadGame();
                        return;
                    } else {
                        display.displayInvalidUsername();
                    }
                }
            } else if (userInput.equalsIgnoreCase("help")){
                display.printHelp(game.gameHelpArrayList);
            } else {
                display.printInvaldInput();
            }
        }
    }





}
