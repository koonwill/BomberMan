import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observer;
import java.util.List;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame implements Observer {

    private int size = 1000;
    private World world;
    private Renderer renderer;
    private Gui gui;

    private List<Command> commands = new ArrayList<Command>();

    Image unBreakablePic = new ImageIcon("img/unbreakable.png").getImage();
    Image BreakablePic = new ImageIcon("img/breakable.png").getImage();
    Image PlayerPic = new ImageIcon("img/player.png").getImage();

    public Window() {
        super();
        addKeyListener(new Controller());
        setLayout(new BorderLayout());
        renderer = new Renderer();
        add(renderer, BorderLayout.CENTER);
        gui = new Gui();
        add(gui, BorderLayout.SOUTH);
        world = new World();
        world.addObserver(this);
        setSize(size, size);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void update(Observable o, Object arg) {
        renderer.repaint();

        if (world.isGameOver()) {
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
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, size, size);

            int tileSize = 20 * world.getScale();
            for (int i = 0; i < World.getCol(); i++) {
                for (int j = 0; j < World.getRow(); j++) {
                    // 1 = unbreakable block
                    // 2 = breakable block
                    // 3 = bomb
                    // 4 = item
                    g.drawImage(unBreakablePic, 14 * tileSize, 8 * tileSize, tileSize, tileSize, null, null);
                    if (map[j][i] == 1) {
                        g.drawImage(unBreakablePic, i * tileSize, j * tileSize, tileSize, tileSize, null, null);
                    }
                    if (map[j][i] == 2) {
                        g.drawImage(BreakablePic, i * tileSize, j * tileSize, tileSize, tileSize, null, null);
                    }
                    if (map[j][i] == 3) {
                        // TODO Implement bomb explosion
                    }
                }
            }
        }

        private void paintPlayer(Graphics g) {
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
