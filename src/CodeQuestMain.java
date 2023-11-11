import Controller.Controller;
import Model.Game;
import View.Display;

import java.io.File;
import java.io.FileNotFoundException;

public class CodeQuestMain {
    //Main Class that will start the game.

    //File Setup
    public static File rooms = new File("rooms.txt");
    public static  File items = new File("items.txt");
    public static  File puzzles = new File("puzzles.txt");
    public static  File commands = new File("UserManual.txt");
    public static  File players = new File("playerData.txt");
    public static  File monsters = new File("monsters.txt");

    public static void main(String[] args) throws FileNotFoundException {


        //MVC Model Setup
        Game game = new Game();
        Display display = new Display();
        Controller controller = new Controller(game,display);

        //Game setup
//        controller.addGameFiles(rooms,items,puzzles,commands,players,monsters);
//        controller.setupGame();

        //Game Start
        controller.startGame();
    }





}
