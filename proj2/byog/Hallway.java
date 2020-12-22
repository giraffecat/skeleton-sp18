package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Hallway {
    private Position Vstart;
    private Position Hstart;
    int Vlength;
    int Hlength;
    //这个是返回链表对象时使用的东西！！！所以按自己写法来构造就行咯 嘤嘤嘤


    public Hallway(Position vstart, Position hstart, int vlength, int hlength) {
        Vstart = vstart;
        Hstart = hstart;
        Vlength = vlength;
        Hlength = hlength;
    }

    //先写一个水平Hallway
    public static Position addHorizontalHallway(TETile[][] world, Position start, int width) {
        for(int x = 0; x < width; ++x) {
            world[start.xPos + x][start.yPos + 1] = Tileset.WALL;
            world[start.xPos + x][start.yPos - 1] = Tileset.WALL;
        }
        world[start.xPos + width][start.yPos] = Tileset.FLOWER;
        return new Position(start.xPos + width, start.yPos);
    }

    //然后写一个垂直的Hallway
    public static Position addVerticalHallway(TETile[][] world, Position start, int heigh) {
        for(int y = 0; y < heigh; ++y) {
            world[start.xPos + 1][start.yPos + y] = Tileset.WALL;
            world[start.xPos - 1][start.yPos + y] = Tileset.WALL;
        }
        return  new Position(start.xPos, start.yPos + heigh);
    }

    public static void addCorner(TETile[][] world, Position corner) {
        //3*3 的一个小方块！！！
        for(int i = -1; i < 2; ++i) {
            for(int j = -1; j < 2; ++j) {
                world[corner.xPos + i][corner.yPos + j] = Tileset.WALL;
            }
            world[corner.xPos ][corner.yPos] = Tileset.FLOOR;
        }
    }

    public static void fillVerticalHallway(TETile[][] world, Position p, int h) {
        for (int y = 0; y < Math.abs(h); y++) {
            world[p.xPos][p.yPos + y] = Tileset.FLOOR;
        }
    }

    public static void fillHorizontalHallway(TETile[][] world, Position p, int w) {
        for (int x = 0; x < Math.abs(w); x += 1) {
            world[p.xPos + x][p.yPos] = Tileset.FLOOR;
        }
    }

    //怎么将两个room建立起一个hallway？？
    public static Hallway drawHallway(TETile[][] world, Room room1, Room room2, WorldGeneratorParam wgp) {
        Random random = new Random(wgp.seed() + 100);


        Position pos1 = Room.InnerPos(room1, wgp);
//        world[pos1.xPos][pos1.yPos] = Tileset.FLOWER;

        Position pos2 = Room.InnerPos(room2, wgp);

        int key = random.nextInt(2);

        Position horizontalStart = Position.smallerX(pos1, pos2);

        int hlength = Math.abs(pos2.xPos - pos1.xPos);
        Position horizontalEnd = Hallway.addHorizontalHallway(world, horizontalStart, hlength);

        addCorner(world, horizontalEnd);
        world[horizontalStart.xPos][horizontalStart.yPos] = Tileset.FLOWER;

        int vlength = Math.abs(pos2.yPos - pos1.yPos);
        Position verticalStart = Position.smallerY(horizontalEnd, Position.largerX(pos1, pos2));
        Hallway.addVerticalHallway(world, verticalStart, vlength);

        Hallway hallway = new Hallway(verticalStart, horizontalStart, vlength, hlength);
        return hallway;
        //想办法去耦合！！！

    }

    public static void fillHallway(TETile[][] world, ArrayList<Hallway> hallwayArrayList) {
        for(int i = 0; i < hallwayArrayList.size(); ++i) {
            Hallway hallway = hallwayArrayList.get(i);
            fillVerticalHallway(world, hallway.Vstart, hallway.Vlength);
            fillHorizontalHallway(world, hallway.Hstart, hallway.Hlength);
        }
    }

}
