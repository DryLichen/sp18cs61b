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

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the bottom left is (0,0) and the top right is (width, height)
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

        // Initialize random number generator
        rand = new Random(seed);
    }

    /** Generate random string of letters of length n */
    public String generateRandomString(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = rand.nextInt(CHARACTERS.length);
            builder.append(CHARACTERS[num]);
        }
        return builder.toString();
    }

    public void drawFrame(String s) {
        // Take the string and display it in the center of the screen
        StdDraw.clear(StdDraw.BLACK);
        Font font = new Font("Monaco", Font.ITALIC, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(20, 20, s);

        // If game is not over, display relevant game information at the top of the screen
        if (!gameOver) {
            Font fontBar = new Font("Monaco", Font.BOLD, 25);
            StdDraw.setFont(fontBar);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(7, 37, "ROUND: " + round);
            if (playerTurn) {
                StdDraw.text(17, 37, "TYPE!");
            } else {
                StdDraw.text(17, 37, "WATCH!");
            }
            StdDraw.text(30, 37, getSentence(ENCOURAGEMENT));
            StdDraw.line(0, 35, width, 35);
        }

        StdDraw.show();
    }

    public void flashSequence(String letters) {
        // Display each character in letters, making sure to blank the screen between letters
        for (char letter : letters.toCharArray()) {
            drawFrame(String.valueOf(letter));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        playerTurn = true;
        drawFrame("Please enter the letters");
        StdDraw.pause(2000);
        // Read n letters of player input
        StringBuilder answer = new StringBuilder();
        while (StdDraw.hasNextKeyTyped()) {
            char c = StdDraw.nextKeyTyped();
            answer.append(c);
            drawFrame(answer.toString());
            StdDraw.pause(1000);
        }
        playerTurn = false;
        return answer.toString();
    }

    /** main content of the game */
    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;

        // Establish Game loop
        while (!gameOver) {
            drawFrame("ROUND: " + round);
            StdDraw.pause(1000);
            String letters = generateRandomString(round);
            drawFrame(letters);
            StdDraw.pause(1000);
            flashSequence(letters);

            String answer = solicitNCharsInput(round);
            if (!letters.equals(answer)) {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
            }
            round++;
        }
    }

    private String getSentence(String[] sentences) {
        Random random = new Random();
        int num = random.nextInt(7);
        return sentences[num];
    }
}
