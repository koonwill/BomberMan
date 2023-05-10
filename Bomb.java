public class Bomb{
    private final static int bombSize = 20;
    private int explosiveRadius;
    private final static int startCountdown = 100;
    private int timeExplode = startCountdown;
    private int row;
    private int col;

    public Bomb(int row, int col, int explosiveRadius){
        this.row = row;
        this.col = col;
        this.explosiveRadius = explosiveRadius;
    }

    public static int getbombSize(){
        return bombSize;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getExplosiveRadius() {
        return explosiveRadius;
    }

    public int getTimeExplode() {
        return timeExplode;
    }

    public void setTimeExplode(int timeExplode) {
        this.timeExplode = timeExplode;
    }

    public void countdown(){
        timeExplode--;
    }

    public boolean isExploded(){
        return timeExplode == 0;
    }


}