package entity;

import Main.GamePanel;
import Main.KeyInputs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyInputs keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyInputs keyInputs) {
        this.gamePanel = gamePanel;
        this.keyH = keyInputs;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tilesSize / 2);
        screenY = gamePanel.screenHeight / 2- (gamePanel.tilesSize / 2);

        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBox.width = 32;
        hitBox.height = 32;

        setDefault();
        getPlayerImage();
    }
    public void setDefault (){
        worldX = gamePanel.tilesSize*23;
        worldY = gamePanel.tilesSize*21;
        speed = 5;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/playerImages/New Piskel-1.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/playerImages/New Piskel-2.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/playerImages/New Piskel-7.png.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/playerImages/New Piskel-1.png.pngw"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/playerImages/New Piskel-5.png.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/playerImages/New Piskel-6.png.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/playerImages/New Piskel-3.png.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/playerImages/New Piskel-4.png.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.up == true || keyH.down == true || keyH.left == true || keyH.right == true) {
            if (keyH.up) {
                direction = "up";

            } else if (keyH.down) {
                direction = "down";

            } else if (keyH.left) {
                direction = "left";

            } else if (keyH.right) {
                direction = "right";

            }
            //Check Collision with Tile
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            //if Collision = false, move
            if(collisionOn == false) {
                switch (direction) {
                    case "up": worldY -= speed;
                    break;
                    case "down": worldY += speed;
                    break;
                    case "left": worldX -= speed;
                    break;
                    case "right": worldX += speed;
                    break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum==1) {
                    image = up1;
                }
                if (spriteNum==2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum==1) {
                    image = down1;
                }
                if (spriteNum==2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum==1) {
                    image = left1;
                }
                if (spriteNum==2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum==1) {
                    image = right1;
                }
                if (spriteNum==2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tilesSize, gamePanel.tilesSize, null);
    }



}

