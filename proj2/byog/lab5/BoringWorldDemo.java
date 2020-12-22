package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


/**
 *  Draws a world that is mostly empty except for a small region.
 */
public class BoringWorldDemo {
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;

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
//
//        // fills in a block 14 tiles wide by 4 tiles tall
//        for (int x = 20; x < 35; x += 1) {
//            for (int y = 5; y < 10; y += 1) {
//                world[x][y] = Tileset.WALL;
//            }
//        }

//        HexWorld.addHexagon(world, new HexWorld.Position(10,10), 3, Tileset.WALL);
//        HexWorld.addHexagon(world,HexWorld.topRightNeighbor(new HexWorld.Position(10,10),3),3,Tileset.TREE);
//        HexWorld.addHexagon(world,HexWorld.topLeftNeighbor(new HexWorld.Position(10,10),3),3,Tileset.TREE);
//        HexWorld.addHexagon(world,HexWorld.bottomRightNeighbor(new HexWorld.Position(10,10),3),3,Tileset.TREE);
//        HexWorld.addHexagon(world,HexWorld.bottomLeftNeighbor(new HexWorld.Position(10,10),3),3,Tileset.TREE);
//        HexWorld.addHexagon(world,HexWorld.topNeighbor(new HexWorld.Position(10,10),3),3,Tileset.TREE);
//        HexWorld.addHexagon(world,HexWorld.bottomNeighbor(new HexWorld.Position(10,10),3),3,Tileset.TREE);
//        HexWorld.Position p = new HexWorld.Position(world.length/2,world[0].length/2);
//        HexWorld.addHexagon(world, p, 3, Tileset.WALL);
//
//        HexWorld.addHexagon(world,HexWorld.topRightNeighbor(p,3),3,Tileset.WALL);
//        HexWorld.addHexagon(world,HexWorld.topLeftNeighbor(p,3),3,Tileset.WALL);
//        HexWorld.addHexagon(world,HexWorld.bottomRightNeighbor(p,3),3,Tileset.WALL);
//        HexWorld.addHexagon(world,HexWorld.bottomLeftNeighbor(p,3),3,Tileset.WALL);
//        HexWorld.addHexagon(world,HexWorld.topNeighbor(p,3),3,Tileset.WALL);
//        HexWorld.addHexagon(world,HexWorld.bottomNeighbor(p,3),3,Tileset.WALL);

        HexWorld.TesselationofHexagons(world, new HexWorld.Position(world.length/2 ,world[0].length/2),2,4);
        System.out.println("postion: "+ world[0].length/2 + "pos2 :" + world.length/2);
        // draws the world to the screen
        ter.renderFrame(world);
    }


}
