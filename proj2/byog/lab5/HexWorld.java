package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 30;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    static class Position {
        private int x;
        private int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.GRASS;
            default:
                return Tileset.SAND;
        }
    }

    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }

    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, new Random());
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }

    public static Position topLeftNeighbor(Position p, int s) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        int ytopR = p.y + s;
        int xtopR = p.x + hexRowOffset(s, s) - s;
        Position topR = new Position(xtopR, ytopR);

        return topR;
    }

    public static Position topRightNeighbor(Position p, int s) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        int ytopL = p.y + s;
        int xtopL = p.x - hexRowOffset(s, s) + s;
        Position topR = new Position(xtopL, ytopL);

        return topR;
    }

    public static Position topNeighbor(Position p, int s) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        int ytop = p.y + 2 * s;
        Position top = new Position(p.x, ytop);

        return top;
    }

    public static Position bottomLeftNeighbor(Position p, int s) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        int ybomR = p.y - s;
        int xbomR = p.x + hexRowOffset(s, s) - s;
        Position bomR = new Position(xbomR, ybomR);

        return bomR;

    }

    public static Position bottomRightNeighbor(Position p, int s) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        int ybomL = p.y - s;
        int xbomL = p.x - hexRowOffset(s, s) + s;
        Position bomL = new Position(xbomL, ybomL);

        return bomL;

    }

    public static Position bottomNeighbor(Position p, int s) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        int ytop = p.y - 2 * s;
        Position bot = new Position(p.x, ytop);

        return bot;
    }


    public static void TesselationofHexagons(TETile[][] world, Position p, int s, int times) {
        //basic case
//        if(p.x < s || p.x + s > world[0].length || p.y < 2 * s || p.y + 2 * s  > world.length ) return;
        if (times == 0) return;
        addHexagon(world, p, s, randomTile());

        TesselationofHexagons(world, topLeftNeighbor(p, s), s, times - 1);
        TesselationofHexagons(world, topRightNeighbor(p, s), s, times - 1);
        TesselationofHexagons(world, topNeighbor(p, s), s, times - 1);
        TesselationofHexagons(world, bottomRightNeighbor(p, s), s, times - 1);
        TesselationofHexagons(world, bottomLeftNeighbor(p, s), s, times - 1);
        TesselationofHexagons(world, bottomNeighbor(p, s), s, times - 1);

    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.SAND;
            }
        }

        TesselationofHexagons(world, new HexWorld.Position(world.length / 2, world[0].length / 2), 2, 2);
        System.out.println("postion: " + world[0].length / 2 + "pos2 :" + world.length / 2);
        // draws the world to the screen
        ter.renderFrame(world);
    }
}


