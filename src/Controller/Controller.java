package Controller;

import Model.Game;
import View.Display;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Controller {
    private Game game;  //Brian
    private Display display;  //Brian
    private Scanner input;  //Brian

    private ArrayList<File> gameFiles = new ArrayList<>();  //Brian
    private HashMap<String,File> gameFilesAlt = new HashMap<>();  //Brian

    private boolean gameOver = false;

    public Controller(Game game, Display display){
        this.game = game;
        this.display = display;
        this.input = new Scanner(System.in);

    }

    public void startGame() throws FileNotFoundException{
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

    public void userCommand(String input){ //Entire Method: Brian

        input = input.toLowerCase();
        String item = splitCommand(input);
        String[] wordyCommand = longCommand(input);

        if (input.equalsIgnoreCase("quit")){
            gameOver = true;
        } else if (input.equalsIgnoreCase("n")){ // Mike: started adding here, looks for n input to move north
            if(!(game.move('n'))){ // tries to move to the direction and finds out if it was a valid move, two birds one stone
                display.printInvalidDirection();
            }
        } else if (input.equalsIgnoreCase("e")){ //as above but e for east
            if(!(game.move('e'))){
                display.printInvalidDirection();
            }
        } else if (input.equalsIgnoreCase("s")){ //as above but s for south
            if(!(game.move('s'))){
                display.printInvalidDirection();
            }
        } else if (input.equalsIgnoreCase("w")){ //as above but w for weast and for wumbo
            if(!(game.move('w'))){
                display.printInvalidDirection();
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
            game.pickupItem(item); //Juan
        } else if (input.startsWith("drop")){ //Juan
            game.dropItem(item); //Juan
        } else if (input.startsWith("use")){ //Juan
            game.useItem(item); //Juan
        } else if (input.contains("open")){ //Juan
            game.openInventory(); //Juan
        } else if (input.equalsIgnoreCase("attack")) { //Mo, this else block: woooOOOO LETS GET COMBAT AAAAAAAAAA it's 8 am i haven't slept in almsot 24 hours help me i have a multiple miles run in 3 hours
            try {game.combat();}
            catch (NullPointerException nullEx) {
                display.noMonster();
            }//end Mo's block
        } else {
            display.printInvaldInput(); //Brian
        }

    }

    public String splitCommand(String string){ //Brian
        String[] listString = string.split(" "); //Brian

        if(listString.length>1){ return listString[1]; } //Brian

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





}
