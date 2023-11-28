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

    public void newOrLoadGame() {
        System.out.println("Are you a new player or returning?");
        System.out.println("Enter 'new player' to start a new game.");
        System.out.println("Enter 'load player' to load a save file.");
        System.out.println("Enter 'help' for more options.\n");
    }

    public void newUserName() {
        System.out.print("Please enter a new username");
        System.out.println();
    }

    public void displayInvalidUsername() {
        System.out.println("This is an invalid Username. Try again.");
    }

    public void noSavesExists() {
        System.out.println("There are currently no save files to load.");
        System.out.println("Please start a new game.");
    }

    public void loadUserName() {
        System.out.print("Enter a username to load a game:");
        System.out.println();
    }

    public void printSaveFiles(String[] saves) {
        printSeperator();
        System.out.println("These are "+ saves.length+" available saves.");
        System.out.println("Usernames:");
        for(String s : saves){
            System.out.println(s.replace(".dat",""));

        }
        System.out.println();
    }

    public void noMonster() {//Entire, Mo
        System.out.println("There is no monster in this room.");
    }//end noMonster(), by Mo

    public void combatQuery() {//Entire, Mo: print during combat to query input attack/run
        System.out.println("Do you want to continue attacking or run away from the monster?");
    }//end combatQuery() by Mo


    public void monDeath(String monName) {//Entire, Mo: upon Monster Death
        System.out.printf("You have defeated %s.\n", monName);
    }//end monDeath() by Mo

    public void playerDeath(String killerName) {//Entire, Mo: upon Player death NEEDS ADJUSTMENT
        System.out.printf("You have been defeated by %s.\n", killerName);
    }//end playerDeath() by Mo

    public void runAway(String monName) {//Entire, Mo: Upon Player Flee
        System.out.printf("You just ran away from %s.", monName);
    }//end runAway() by Mo

    public void combatStatus(int playerHP, int monHP) {//Entire, Mo: print at attackSequence() end, notify player of stats
        System.out.printf("Your health is %d. Monster health is %d. ", playerHP, monHP);
    }//end combatStatus() by Mo
}
