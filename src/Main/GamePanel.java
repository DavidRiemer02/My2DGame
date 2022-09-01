package Main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //BILD Einstellungen
    final int originalTilesSize = 16;
    final int scale = 3;

    public final int tilesSize = originalTilesSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tilesSize * maxScreenCol; //768 Pixel
    public final int screenHeight = tilesSize * maxScreenRow;  //576 Pixel
    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tilesSize * maxWorldCol;
    public final int worldHeight = tilesSize * maxWorldRow;


    //FPS
    int FPS = 60;


    TileManager tileManager = new TileManager(this);
    KeyInputs keyH = new KeyInputs();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
                drawCount++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }


}

