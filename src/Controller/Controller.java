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

            System.out.println("What would you like to do? Type 'help' for commands."); //Brian
            String userInput = input.nextLine();//Brian

            //Passes user input to command processing method
            userCommand(userInput);//Brian

            display.printSeperator(); //Brian
        }

    }

    public void userCommand(String input){ //Entire Method: Brian
        input = input.toLowerCase();  //Brian
        String item = splitCommand(input);  //Brian

        if (input.equalsIgnoreCase("quit")){  //Brian
            gameOver = true;  //Brian
        } else if (input.equalsIgnoreCase("help")){  //Brian
            help(); //Brian
        } else {
            display.printInvaldInput(); //Brian
        }

    }

    public String splitCommand(String string){ //Brian
        String[] listString = string.split(" "); //Brian

        if(listString.length>1){ return listString[1]; } //Brian

        return string; //Brian
    }

    public void setupGame() throws FileNotFoundException {
        game.populateRooms(gameFiles.get(0));
        game.setFirstRoom();
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
