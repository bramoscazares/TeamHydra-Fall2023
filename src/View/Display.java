package View;

import java.util.ArrayList;

public class Display {


    public void printSeperator(){ // Method: Brian
        System.out.println("\n--------------------------------------------------------------------------------------------------------------");
    }


    public void printInvaldInput() {  // Method: Brian
        System.out.println("Invalid input. Try again.");

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
