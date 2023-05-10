import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observer;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame implements Observer {

    private int size = 500;
    private World world;
    private Renderer renderer;
    private Gui gui;

    private List<Command> commands = new ArrayList<Command>();

    public Window() {
        super();
        addKeyListener(new Controller());
        setLayout(new BorderLayout());
        renderer = new Renderer();
        add(renderer, BorderLayout.CENTER);
        gui = new Gui();
        add(gui, BorderLayout.SOUTH);
        world = new World();
        // world.addObserver(this);
        setSize(size, size);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void update(Observable o, Object arg){
        renderer.repaint();

        if(world.isGameOver()){
            gui.showGameOverLabel();
        }
    }

    class Renderer extends JPanel {
        public Renderer() {
            setDoubleBuffered(true);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintMap(g);
            paintPlayer(g);
            return;
        }

        private void paintMap(Graphics g) {
            int[][] map = world.getMap();
            g.setColor(new Color(56, 135, 0));
            g.fillRect(0, 0, world.getWIDTH(), world.getHEIGHT());

            int tileSize = 20 * world.getScale();
            for (int i = 0; i < World.getRow(); i++) {
                for (int j = 0; j < World.getCol(); j++) {
                    // 1 = unbreakable block
                    // 2 = breakable block
                    // 3 = bomb
                    // 4 = item
                    if (map[i][j] == 1) {
                        // Image unBreakablePic = new ImageIcon("unbreakable.png").getImage();
                        g.drawImage(unBreakablePic, i * tileSize, j * tileSize, tileSize, tileSize, null);
                    }
                    if (map[i][j] == 2) {
                        g.drawImage(BreakablePic, i * tileSize, j * tileSize, tileSize, tileSize, null);
                    }
                    if (map[i][j] == 3) {
                        // TODO Implement bomb explosion
                    }
                }
            }
        }

        private void paintPlayer(Graphics g) {
            // TODO Maybe not done.
            // draw player
            int tileSize = 20 * world.getScale();
            g.drawImage(PlayerPic, world.getPlayer().getX(), world.getPlayer().getY(), tileSize, tileSize, null);
        }
    }

    class Gui extends JPanel {
        private JLabel gameOverLabel;

        public Gui() {
            setLayout(new FlowLayout());
            // TODO Implement this
            gameOverLabel = new JLabel("Game Over");
            gameOverLabel.setForeground(Color.red);
            gameOverLabel.setVisible(false);
            add(gameOverLabel);
        }

        public void showGameOverLabel() {
            gameOverLabel.setVisible(true);
        }
    }

    class Controller extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                Command command = new CommandTurnNorth(world.getPlayer());
                command.execute();
                commands.add(command);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                Command command = new CommandTurnSouth(world.getPlayer());
                command.execute();
                commands.add(command);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Command command = new CommandTurnWest(world.getPlayer());
                command.execute();
                commands.add(command);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Command command = new CommandTurnEast(world.getPlayer());
                command.execute();
                commands.add(command);
            }
        }

    }

    public static void main(String[] args) {
        Window window = new Window();
        window.setVisible(true);
    }
}
