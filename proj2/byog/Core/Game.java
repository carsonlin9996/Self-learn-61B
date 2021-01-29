package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        //int sizeOfInput = input.length();
        //ter = new TERenderer();
        //ter.initialize(80,50);
        /*String seedToUse = input.substring(1,sizeOfInput - 1);
        //System.out.println(seedToUse);

        long seed = Long.parseLong(seedToUse);
        DrawRandomMap newMap = new DrawRandomMap(80,50,seed);*/
        DrawRandomMap newMap = processInput(input);
        TETile[][] finalWorldFrame = newMap.generateWorld();
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    private DrawRandomMap processInput(String input){
        if(input == null){
            System.out.println("The input is null!");
        }

        String firstChar = Character.toString(input.charAt(0));

        firstChar = firstChar.toLowerCase();
        int sizeOfInput = input.length();

        String seedToUse = input.substring(1,sizeOfInput - 1);
        long seed = Long.parseLong(seedToUse);
        System.out.println(seedToUse);

        switch(firstChar){
            case "n":
                DrawRandomMap newMap = new DrawRandomMap(80,50,seed);
                return newMap;
            default:
                DrawRandomMap newMap2 = new DrawRandomMap(80,50,seed);
                return newMap2;

        }

    }
}
