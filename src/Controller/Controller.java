package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Model.Game;
import View.Display;


public class Controller {
    private Game game;
    private Display display;
    private Scanner input;

    private ArrayList<File> gameFiles = new ArrayList<>();

    private boolean gameOver = false;

    public Controller(Game game, Display display){
        this.game = game;
        this.display = display;
        this.input = new Scanner(System.in);

    }

    public void startGame() throws FileNotFoundException{
        while (!gameOver) {

            //monsterCheck(); //Checks if Room has a monster
//            if (!game.playerCheck()) {
//                display.printBadEnding(game.player);
//                gameOver = !gameOver;
//                break;
//            }

            //Prints room description
            //display.displayRoomInfo(game.getCurrentRoom());

            //puzzleCheck(); //Checks if Room has a puzzle

            System.out.println("What would you like to do? Type 'help' for commands.");
            String userInput = input.nextLine();

            //Passes user input to command processing method
            userCommand(userInput);

            display.printSeperator();
        }

    }

    public void userCommand(String input){
        input = input.toLowerCase();
        String item = splitCommand(input);

        if (input.equalsIgnoreCase("quit")){
            gameOver = true;
        } else {
            display.printInvaldInput();
        }

    }

    public String splitCommand(String string){
        String[] listString = string.split(" ");

        if(listString.length>1){ return listString[1]; }

        return string;
    }





}
