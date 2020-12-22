package byog;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Room {
    private Position pos;
    private int width;
    private int height;

    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    //Room 当成对象来构造
    public Room(Position pos, int w, int h) {
        this.pos = pos;
        this.width = w;
        this.height = h;
    }

    public Position getPos() {
        return pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //addRoom 方法
    public static void addRoom(TETile[][] world, Position pos, int width, int height) {
        for(int x = 0; x < width; ++x) {
            world[pos.xPos + x][pos.yPos] = Tileset.WALL;
            world[pos.xPos + x][pos.yPos + height - 1] = Tileset.WALL;
        }
        for(int y = 0; y < height; ++y) {
            world[pos.xPos][pos.yPos + y] = Tileset.WALL;
            world[pos.xPos + width - 1][pos.yPos + y] = Tileset.WALL;
        }
    }
    public static ArrayList<Room> drawRoom(TETile[][] world, WorldGeneratorParam wgp) {
        Random random = new Random(wgp.seed() + 312); //更改seed使得room不同！
        int roomNumber = 20;
        int maxW = 8;
        int maxH = 6;

        ArrayList<Room> roomArrayList = new ArrayList<>();
        for(int i = 0; i < roomNumber; ++i) {
            int roomWidth = random.nextInt(maxW) + 3;
            int roomHeight = random.nextInt(maxH) + 3;
            //这种设定方式真的让人吃惊？？ 直接防止边境越界？？ 你个智障
            int roomPx = random.nextInt(WIDTH - roomWidth);
            int roomPy = random.nextInt(HEIGHT - roomHeight);
            Position startP = new Position(roomPx, roomPy);
            Room.addRoom(world, startP, roomWidth, roomHeight);
            Room newRoom = new Room(startP, roomWidth, roomHeight);
            roomArrayList.addLast(newRoom);
        }
        return  roomArrayList;
    }

    public static void fillRoom(TETile[][] world, Position pos, int width, int height) {
        for(int i = 1; i < width - 1; ++i) {
            for(int j = 1; j < height - 1; ++j) {
                world[pos.xPos + i][pos.yPos + j] = Tileset.FLOOR;
            }
        }
    }

    public static void fillRoomList(TETile[][] world, ArrayList<Room> roomList) {
        for(int i = 0; i < roomList.size(); ++i) {
            Room room = roomList.get(i);
            fillRoom(world, room.pos, room.width, room.height);
        }
    }

    //计算内边距 —-> 定位hallway
    public static Position InnerPos(Room r, WorldGeneratorParam wgp) {
        Random random = new Random(wgp.seed() + 10);
        int innerX = random.nextInt(r.width - 2) + r.pos.xPos + 1;
        int innerY = random.nextInt(r.height - 2) + r.pos.yPos + 1;
        Position pos = new Position(innerX, innerY);
        return pos;
    }

    public static ArrayList<Room> sortRoomList(ArrayList<Room> roomList) {
        ArrayList<Room> newRoomList = new ArrayList<>();
        int roomListSize = roomList.size();
        for (int i = 0; i < roomListSize; i += 1) {
            int minRoom = smallestRoom(roomList);
            newRoomList.addLast(roomList.remove(minRoom));
        }
        return newRoomList;
    }

    //找到相对位置最小的room么？？？ 淦 有个屁的用啊
    public static int smallestRoom(ArrayList<Room> roomList) {
        int min = 110; int minIndex = 0;
        for (int i = 0; i < roomList.size(); i += 1) {
            int positSum = roomList.get(i).getPos().xPos + roomList.get(i).getPos().yPos;
            if (positSum < min) {
                min = positSum;
                minIndex = i;
            }
        }
        return minIndex;
    }
}
