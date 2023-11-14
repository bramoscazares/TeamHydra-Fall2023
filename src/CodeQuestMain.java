import Controller.Controller;
import Model.Game;
import View.Display;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 */

public class CodeQuestMain {
    //Main Class that will start the game.
    public static File testRooms = new File("rooms.txt"); // Mike: testing rooms.txt
    public static File testMon = new File("testData/testMonsters.txt"); // Mike: testing rooms.txt


    public static void main(String[] args) throws FileNotFoundException {


        //MVC Model Setup
        Game game = new Game();
        Display display = new Display();
        Controller controller = new Controller(game,display);

        //Game setup
//        controller.addGameFiles(rooms,items,puzzles,commands,players,monsters);
//        controller.setupGame();

        //TEST SETUP
        game.populateRooms(testRooms);
        game.populateMons(testMon);
        game.setFirstRoom();

        //Game Start
        controller.startGame();
    }





}
