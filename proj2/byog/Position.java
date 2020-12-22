package byog;

public class Position {

    //Position 存储横纵坐标！！！
    public int xPos, yPos;
    Position(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public static Position smallerX(Position p1, Position p2) {
        if(p1.xPos < p2.xPos) {
            return p1;
        }else {
            return p2;
        }
    }

    public static Position smallerY(Position p1, Position p2) {
        if(p1.yPos < p2.yPos) {
            return p1;
        }else {
            return p2;
        }
    }

    public static Position largerX(Position p1, Position p2) {
        if(p1.xPos < p2.xPos) {
            return p2;
        }else {
            return p1;
        }
    }

    public static Position largerY(Position p1, Position p2) {
        if(p1.yPos < p2.yPos) {
            return p2;
        }else {
            return p1;
        }
    }
}
