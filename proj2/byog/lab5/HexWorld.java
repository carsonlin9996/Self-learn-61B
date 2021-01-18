package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int SEED = 88;
    private static final Random RANDOM = new Random(SEED);

    private static void fillNothing(TETile[][] world) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    //ith row length.
    public static int RowWidth(int size, int row) {
        int effectiveRow = row;
        if (row >= size) {
            effectiveRow = 2 * size - 1 - effectiveRow;
        }
        return size + 2 * effectiveRow;
    }


    public static int RowOffset(int size, int row) {
        int offset = row;
        if (row >= size) {
            offset = 2 * size - 1 - offset;
        }
        return -offset;
    }

    public static void addHexagon(TETile[][] world, int positionX, int positionY, int size, TETile t) {

        for (int y = 0; y < 2 * size; y++) {
            //TETile t = randomTile();
            int currentRowY = positionY + y;
            int currentX = RowOffset(size, y) + positionX;
            int rowWidth = RowWidth(size, y);

            for(int i = currentX; i < currentX + rowWidth; i++) {

                world[i][currentRowY] = t;
            }
        }

    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            default: return Tileset.NOTHING;
        }
    }


    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillNothing(world);
        addHexagon(world, 10, 10, 3, Tileset.GRASS);

        ter.renderFrame(world);

    }
}
