package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static void main(String[] args) {
        int width = 50;
        int height = 50;

        // initialize the world
        TERenderer renderer = new TERenderer();
        renderer.initialize(width, height);

        TETile[][] world = new TETile[width][height];
        Position position = new Position(20, 10);

        initializeTETiles(world);

        tesselation(world, position, 3, 3);

        renderer.renderFrame(world);
    }

    /**
     * add a hexagon consists of multiple hexagons to a given position in a world
     * position marks the bottom hexagon
     */
    public static void tesselation(TETile[][] world, Position p, int eachLength, int wholeLength) {
        // outside: loop the left columns from middle(0) to left
        for (int i = 0; i < wholeLength; i++) {
            Position bottomPoint = getLeftTopPoint(p, eachLength, i);
            for (int j = 0; j < 2 * wholeLength - 1 - i; j++) {
                Position position = getUpPoint(bottomPoint, eachLength, j);
                addHexagon(world, position, eachLength, getRandomTETile());
            }
        }

        // loop the right columns from middle(0) to right
        for (int i = 1; i < wholeLength; i++) {
            Position bottomPoint = getRightTopPoint(p, eachLength, i);
            for (int j = 0; j < 2 * wholeLength - 1 - i; j++) {
                Position position = getUpPoint(bottomPoint, eachLength, j);
                addHexagon(world, position, eachLength, getRandomTETile());
            }
        }
    }

    private static Position getUpPoint(Position p, int eachLength, int j) {
        Position position = new Position();
        position.setX(p.getX());
        position.setY(p.getY() + eachLength * 2 * j);
        return position;
    }
    private static Position getLeftTopPoint(Position p, int eachLength, int i) {
        Position position = new Position();
        position.setX(p.getX() - i * (eachLength * 2 - 1));
        position.setY(p.getY() + i * eachLength);
        return position;
    }
    private static Position getRightTopPoint(Position p, int eachLength, int i) {
        Position position = new Position();
        position.setX(p.getX() + i * (eachLength * 2 - 1));
        position.setY(p.getY() + i * eachLength);
        return position;
    }

    /**
     * add hexagon regions into a world at a given position
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        // get the rectangle containing the hexagon
        Position minPoint = getMinPoint(p, s);
        Position maxPoint = getMaxPoint(p, s);
        int xMin = minPoint.getX();
        int yMin = minPoint.getY();
        int xMax = maxPoint.getX();
        int yMax = maxPoint.getY();

        // draw the hexagon
        int count = 0;
        for (int i = xMin; i <= xMax; i++) {
            for (int j = getYStart(yMin, count, s) ; j <= getYEnd(yMax, count, s); j++) {
                world[i][j] = t;
            }
            count++;
        }
    }

    // get the lowest point of the rectangle containing the hexagon
    private static Position getMinPoint(Position p, int s) {
        Position position = new Position();
        position.setX(p.getX() - s + 1);
        position.setY(p.getY());
        return position;
    }
    // get the top right corner of the rectangle containing the hexagon
    private static Position getMaxPoint(Position p, int s) {
        Position position = new Position();
        position.setX(p.getX() + 2 * (s - 1));
        position.setY(p.getY() + 2 * s - 1);
        return position;
    }

    // get the coordinate of y
    private static int getYStart(int yMin, int count, int s) {
        if (count < s) {
            int y = yMin + s - count - 1;
            return y;
        } else if (count < 2 * s - 2) {
            return yMin;
        } else {
            int y = yMin + count - 2 * s + 2;
            return y;
        }
    }
    private static int getYEnd(int yMax, int count, int s) {
        if (count < s) {
            int y = yMax - s + count + 1;
            return y;
        } else if (count < 2 * s - 2) {
            return yMax;
        } else {
            int y = yMax - count + 2 * s - 2;
            return y;
        }
    }

    /**
     * initialize the world
     */
    public static void initializeTETiles(TETile[][] world) {
        int width = world.length;
        int height = world[0].length;

        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    /**
     * choose one TETile randomly
     */
    public static TETile getRandomTETile() {
        Random random = new Random();
        int tileNum = random.nextInt(5);
        switch (tileNum) {
            case 0:
                return Tileset.FLOWER;
            case 1:
                return Tileset.TREE;
            case 2:
                return Tileset.GRASS;
            case 3:
                return Tileset.SAND;
            case 4:
                return Tileset.MOUNTAIN;
            default:
                return Tileset.NOTHING;
        }
    }
}
