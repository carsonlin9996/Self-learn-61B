package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        rand = new Random(seed);
        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        String randString = "";
        while(randString.length() < n){
            char picked = CHARACTERS[rand.nextInt(CHARACTERS.length)];
            randString += Character.toString(picked);
        }
        return randString;
        //TODO: Generate random string of letters of length n
    }

    public void drawFrame(String s) {

        int midWidth = width/2;
        int midHeight = height/2;

        StdDraw.clear();
        StdDraw.clear(StdDraw.BLACK);

        if(!gameOver){
            Font gameNotOver = new Font("Arial", Font.ITALIC, 20);
            StdDraw.setFont(gameNotOver);
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.text(midWidth, height - 1, playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length] );
            StdDraw.line(0, height - 2 , width, height - 2);
        }

        Font gameOverFont = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(gameOverFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(midWidth, midHeight, s);
        StdDraw.show();



        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {

        for(int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            String s = Character.toString(letter);

            StdDraw.pause(100);
            drawFrame(s);
            StdDraw.clear(Color.BLACK);
            StdDraw.pause(500);

        }

        //TODO: Display each character in letters, making sure to blank the screen between letters
    }

    public String solicitNCharsInput(int n) {
        String input = "";
        drawFrame(input);
        while(input.length() < n){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                input += Character.toString(key);
                drawFrame(input);
            }
        }

        //TODO: Read n letters of player input
        StdDraw.pause(500);
        return input;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        gameOver = false;
        round = 1;

        while(!gameOver){
            playerTurn = false;
            drawFrame("Round: " + round + "! Good luck!");
            StdDraw.pause(1500);

            String randomString = generateRandomString(round);
            flashSequence(randomString);

            playerTurn = true;
            String myInput = solicitNCharsInput(round);

            if(!myInput.equals(randomString)){
                gameOver = true;
                drawFrame("Game over!! Final level: " + round);
            }
            else{
                drawFrame("Well done!");
                StdDraw.pause(1500);
                round += 1;
            }
        }

        //TODO: Establish Game loop
    }

}
