package View;

public class Display {


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

}
