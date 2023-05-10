import java.util.Observable;
import java.util.Random;

public class World extends Observable {
    private Player player;
    private final static double BREAKABLE_WALL_CHANCE = 4;
    private final static double ITEM_CHANCE = 2;
    private final int scale = 3;
    private final static int row = 9;
    private final static int col = 15;
    private final int WIDTH = col * 20 * scale;
    private final int HEIGHT = row * 20 * scale;

    private boolean isGameOver = false;
    private int[][] map = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

    // private List<Bomb> bombsList = new ArrayList<>();
    // private List<Player> playersList = new ArrayList<>();
    // private List<Bomb> explosionList = new ArrayList<>();
    // private List<Bomb> explosionCoords = new ArrayList<>();

    public World() {
        this.map = initMap(map);
    }

    public void start(){
        // TODO Implement this
    }

    public int[][] getMap() {
        return map;
    }

    // 1 = unbreakable block
    // 2 = breakable block
    // 3 = bomb
    // 4 = item (Optional)
    private int[][] initMap(int[][] map) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 0) {
                    if (new Random().nextInt(10) < BREAKABLE_WALL_CHANCE) {
                        map[i][j] = 2;
                        // if (new Random().nextInt(10) < ITEM_CHANCE) {
                        // map[i][j] = 4;
                        // }
                    }
                }
            }
        }
        // set spawn point for 2 players
        // at topleft and bottom right of the map
        map[1][1] = 0;
        map[8][14] = 0;

        return map;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public static int getRow() {
        return row;
    }

    public static int getCol() {
        return col;
    }

    public int getScale() {
        return scale;
    }

    public Player getPlayer() {
        return player;
    }

    public void turnPlayerNorth() {
        player.turnNorth();
    }

    public void turnPlayerSouth() {
        player.turnSouth();
    }

    public void turnPlayerWest() {
        player.turnWest();
    }

    public void turnPlayerEast() {
        player.turnEast();
    }

    public boolean isGameOver() {
        // return true
        return !isGameOver;
    }
}
