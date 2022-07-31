package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private Random random;

    @Test
    public void testPhase1(String[] args) {
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(playWithInputString("N234S"));
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code.
     * The input string will be a series of characters (for example, "n123sswwdasdassadwas",
     * "n123sss:q", "lwww". The game should behave exactly as if the user typed these characters
     * into the game after playing playWithKeyboard. If the string ends in ":q", the same world
     * should be returned as if the string did not end with q. For example "n123sss" and "n123sss:q"
     * should return the same world. However, the behavior is slightly different. After playing
     * with "n123sss:q", the game should save, and thus if we then called playWithInputString with
     * the string "l", we'd expect to get the exact same world back again.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // fill the initial world with nothing, because it cannot be null
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        fillRegion(finalWorldFrame, 0, 0, WIDTH, HEIGHT, Tileset.NOTHING);

        // generate object random based on the input
        int seed = parseInput(input);
        random = new Random(seed);

        return finalWorldFrame;
    }

    /**
     * number, locations and sizes of rooms should be random
     */
    private TETile[][] getRooms(TETile[][] world) {
        // calculate the number of rooms(10~19)
        int number = RandomUtils.uniform(random, 10, 20);
        List<Room> roomList = new ArrayList<>();

        // generate rooms
        for (int i = 0; i < number; i++) {
            Room room = new Room();
            room.randomLocation(random, WIDTH, HEIGHT);
            room.randomSize(random, WIDTH, HEIGHT);
            int x = room.getX();
            int y = room.getY();
            int width = room.getWidth();
            int height = room.getHeight();

            if (isOverlap(world, x, y, width, height)) {
                i--;
                continue;
            }

            roomList.add(room);
            fillRegion(world, x, y, width, height, Tileset.FLOOR);
        }

        return null;
    }

    /**
     * hallways should include turns or intersections
     * number, locations and length of hallways should be random
     */
    private TETile[][] getHallways(TETile[][] world) {
        return null;
    }


    private TETile[][] getWalls(TETile[][] world) {
        return null;
    }

    /** fill a region in the world with tile */
    private void fillRegion(TETile[][] world, int x, int y, int width, int height, TETile tile) {
        for (int i = x; i < width; i++) {
            for (int j = y; j < height; j++) {
                world[i][j] = tile;
            }
        }
    }

    /** whether two regions are overlapped */
    private boolean isOverlap(TETile[][] world, int x, int y, int width, int height) {

        return false;
    }

    private int parseInput(String input) {

    }
}
