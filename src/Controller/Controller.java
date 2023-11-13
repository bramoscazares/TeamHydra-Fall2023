package Controller;

import Model.Game;
import View.Display;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


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
        } else { // Mike: Stopped adding in front of this else
            display.printInvaldInput();
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





}
