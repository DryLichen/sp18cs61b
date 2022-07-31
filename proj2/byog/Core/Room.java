package byog.Core;

import java.util.Random;

/**
 * specify a room
 */
public class Room {
    // coordinate of the left lower corner of a room
    private int x;
    private int y;
    // coordinate of the right top corner of a room
    private int width;
    private int height;


    public void randomLocation(Random random, int worldWidth, int worldHeight) {
        x = RandomUtils.uniform(random, 0, worldWidth - 3);
        y = RandomUtils.uniform(random, 0, worldHeight - 3);
    }

    public void randomSize(Random random, int worldWidth, int worldHeight) {
        width = RandomUtils.uniform(random, 4, worldWidth - x + 1);
        height = RandomUtils.uniform(random, 4, worldHeight - x + 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
