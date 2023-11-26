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
    public static File testRooms = new File("gameData/rooms.txt"); // Mike: testing rooms.txt

	public static File items = new File("gameData/Items.txt");

	public static File mons = new File("gameData/monsters.txt"); //Mo
	public static File testMons = new File("testData/testMonsters.txt"); //Mo


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
        game.populateItems(items);
        game.populateMons(mons);
        game.setFirstRoom();
        game.populateHelp(new File("gameData/GameHelp.txt"));

        //Game Start
        controller.startGame();
    }





}
