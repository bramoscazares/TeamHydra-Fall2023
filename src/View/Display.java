package View;

import java.util.ArrayList;

public class Display {


    public static void gameSaveSuccess() {
        System.out.println("Game saved successfully.");
    }

    public static void gameLoadSuccess() {
        System.out.println("Game loaded successfully.");

    }

    public void printSeperator(){ // Method: Brian
        System.out.println("\n--------------------------------------------------------------------------------------------------------------");
    }

    public void showRoom(){}

    public void printInvaldInput() {  // Method: Brian
        System.out.println("Invalid input. Try again.");

    }


    public void printInvalidDirection() { // Method: Mike for when a room does not exist in the direction imput
        System.out.println("You cannot move in that direction.");
    }

    public void printHelp(ArrayList<String> gameHelpArrayList) {
        printSeperator();
        for(String s: gameHelpArrayList) {
            System.out.println(s);
        }
        printSeperator();
    }

    public void printHelpReturn() {
        System.out.println("\nType 'return' to exit help.");
    }
}
