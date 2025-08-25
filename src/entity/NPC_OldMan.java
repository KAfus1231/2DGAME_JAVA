package entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getOldManImage();
    }

    public void getOldManImage()
    {
        try
        {
            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/00_Walk.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/04_Walk.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/08_Walk.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/12_Walk.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/01_Walk.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/05_Walk.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/09_Walk.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/13_Walk.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/02_Walk.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/06_Walk.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/10_Walk.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/14_Walk.png"));

            right1= ImageIO.read(getClass().getResourceAsStream("/NPC/walk/03_Walk.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/07_Walk.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/11_Walk.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/NPC/walk/15_Walk.png"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setAction()
    {
        actionLock++;

        if(actionLock == 10)
        {
            Random random = new Random();
            int i  = random.nextInt(100)+1; // ПОЛУЧАЕМ ЧИСЛО
            if(i <= 25)
            {
                direction = "up";
            }
            if(i > 25 && i <= 50)
            {
                direction = "down";
            }
            if(i > 50 && i <= 75)
            {
                direction = "left";
            }
            if(i > 75 && i <= 100)
            {
                direction = "right";
            }

            actionLock = 0;
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 4)
                spriteNum = 1;
            else
                spriteNum++;
            spriteCounter = 0;
        }

    }

}
