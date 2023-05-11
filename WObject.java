public abstract class WObject {

    private int x;
    private int y;

    private int dx;
    private int dy;

    private int speed = 5;

    public WObject() {
    }

    public WObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void turnNorth() {
        dx = 0;
        dy = -speed;
    }

    public void turnSouth() {
        dx = 0;
        dy = speed;
    }

    public void turnWest() {
        dx = -speed;
        dy = 0;
    }

    public void turnEast() {
        dx = speed;
        dy = 0;
    }

    public void move() {
        this.x += dx;
        this.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void reset() {
        dx = dy = 0;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
