
/**
 * Position
 */
public class Position {

    private int x;
    private int y;

    public Position(int xValue, int yValue) {
        x = xValue;
        y = yValue;
    }

    public Position() {
        x = 0;
        y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}