package Controller;

import Model.Game;
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
        while (!gameOver) {
            gameLoad();
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
            game.isVisitedRoom();
        } else if (input.equalsIgnoreCase("m-info")){ // Mike: Stopped adding in front of this else
            game.mInfo();
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
        }  else if (input.contains("save")){ //Brian
            saveGame(); //Brian
        }  else if (input.contains("load")){ //Brian
            loadGame(); //Brian
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

    private void gameLoad() {
        File saveFiles = new File("SaveFiles");
        String[] saves = saveFiles.list();
        while (true) {

            display.newOrLoadGame(); //brian
            String userInput = input.nextLine(); //brian

            if (userInput.equalsIgnoreCase("new player")) {
                display.newUserName();

                while (true) {
                    String userName = input.nextLine(); //brian
                    this.saveFile = userName + ".dat";
                    File save = new File(saveFiles, this.saveFile);
                    if (save.exists() || (userName.length() >= 15)) {
                        display.displayInvalidUsername();
                    } else {
                        return;
                    }
                }

            } else if (userInput.equalsIgnoreCase("load player")) {
                if (saves != null && saves.length == 0) {
                   display.noSavesExists();
                } else {
                    display.loadUserName();
                    String userName = input.nextLine(); //brian
                    String saveString = userName + ".dat";
                    File save = new File(saveFiles, saveString);

                    if (save.exists()) {
                        this.saveFile = saveString;
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
