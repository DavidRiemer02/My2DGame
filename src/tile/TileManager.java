package tile;

import Main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[1000];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tilesImages/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tilesImages/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tilesImages/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tilesImages/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tilesImages/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tilesImages/sand.png"));


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
                String line = br.readLine();
                while (col<gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                } if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void draw (Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow) {

            int tileNumber = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol*gp.tilesSize;
            int worldY = worldRow*gp.tilesSize;
            int screenX = worldX-gp.player.worldX + gp.player.screenX;
            int screenY = worldY-gp.player.worldY + gp.player.screenY;
            if(     worldX + gp.tilesSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tilesSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tilesSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tilesSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNumber].image, screenX, screenY, gp.tilesSize, gp.tilesSize, null);
            }
            worldCol++;

            if(worldCol==gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }


}
