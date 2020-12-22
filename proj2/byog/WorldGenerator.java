package byog;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldGenerator {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    public static void Generator() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        WorldGeneratorParam wgp = new WorldGeneratorParam();
        ArrayList<Room> roomList = Room.drawRoom(world, wgp);
        ArrayList<Room> arrayList = Room.sortRoomList(roomList);
        ArrayList<Hallway> hallwayArrayList = new ArrayList<>();
        for(int i = 0; i < arrayList.size() - 1; ++i) {
            hallwayArrayList.addLast(Hallway.drawHallway(world, arrayList.get(i), arrayList.get(i + 1),wgp));
        }
        Hallway.fillHallway(world, hallwayArrayList);
        Room.fillRoomList(world,arrayList);
        ter.renderFrame(world);
    }
    public static void main(String[] args) {
        Generator();
    }
}
