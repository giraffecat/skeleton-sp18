package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    //Position类 用于定位room位子
    public static class Position {
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
        long seed = Long.parseLong(input.replaceAll("[^0-9]",""));
        System.out.println(seed);
        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    public static TETile[][] worldGenerator(Random RANDOM, TETile[][] world) {
        //这里相当与一个worldGeneratorHepler
        //把问题难度降级成 随机生成room 然后用hallway将room连接起来
        int roomNumber = 0;
        while(roomNumber++ < 100) roomGenerator(RANDOM, world);


        return world;
    }

    public static TETile[][] roomGenerator(Random RANDOM, TETile[][] world) {
        //怎么解决overlap部分 想不到怎么解决 那尝试蠢办法遍历？？
        //先尝试随机生成一个room
        int Size = RANDOM.nextInt(10);
        while(Size<4) Size = RANDOM.nextInt(10);
        int Posx = RANDOM.nextInt(WIDTH - Size);
        int Posy = RANDOM.nextInt(HEIGHT - Size);
        Position p = new Position(Posx, Posy);
        if(!checkOverlapped(p, Size, world))world = drawRectangular(p, Size, world);
        return  world;
    }


    public static boolean checkOverlapped(Position p, int Size, TETile[][] world) {
        boolean overlapped = false;
        TETile[][] newWorld =  world;
        for(int i=0; i < Size; i++) {
            for(int j=0; j < Size; j++){
                if(world[p.x + i][p.y + j] != Tileset.SAND) overlapped = true;
            }
        }
        return overlapped;
    }
    public static TETile[][] drawRectangular(Position p, int Size, TETile[][] world) {
        for(int i=0; i < Size; i++) {
            for(int j=0; j < Size; j++){
                if(i == 0 || j == 0 || i == Size - 1 || j == Size - 1) world[p.x + i][p.y + j] = Tileset.WALL;
                else world[p.x + i][p.y + j] = Tileset.FLOOR;
            }
        }
        return world;

    }

    @Test
    public void TestplayWithInputString() {
        playWithInputString("N123");
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.SAND;
            }
        }
        world = roomGenerator(new Random(12321), world);
        TETile.toString(world);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.SAND;
            }
        }
        world = worldGenerator(new Random(123522), world);
        ter.renderFrame(world);

    }

}
