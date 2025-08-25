package entity;

import Main.GamePanel;
import Main.Keyboard;
import object.OBJ_OpenChest;
import object.OBJ_OpenDoor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity
{
    Keyboard key;
    // положение камеры
    public final int screenX;
    public final int screenY;
    public int hasKey = 0; // КОЛИЧЕСТВО КЛЮЧЕЙ У ИГРОКА
    public int lifeCounter = 0;
    public boolean hit = false;

    public Player(GamePanel gp, Keyboard key)
    {
        super(gp);

        this.key = key;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(16, 16, 16, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() // настройка игрока
    {
        worldX = gp.tileSize * 23; // ПОЛОЖЕНИЕ ЧЕЛИКСА
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down"; // установка направления по умолчанию

        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage()
    {
        try // загрузка спрайтов ходьбы
        {
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/walk/00_Walk.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/walk/04_Walk.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/walk/08_Walk.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/walk/12_Walk.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/walk/01_Walk.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/walk/05_Walk.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/walk/09_Walk.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/walk/13_Walk.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/walk/02_Walk.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/walk/06_Walk.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/walk/10_Walk.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/walk/14_Walk.png"));

            right1= ImageIO.read(getClass().getResourceAsStream("/player/walk/03_Walk.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/walk/07_Walk.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/walk/11_Walk.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/walk/15_Walk.png"));
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void movement()
    {
        // ПРОВЕРКА СТОЛКНОВЕНИЙ
        collisionOn = false;
        gp.collisionCheck.checkTile(this);

        // ПРОВЕРКА СТОЛКНОВЕНИЙ С ОБЪЕКТАМИ
        int objIndex = gp.collisionCheck.checkObject(this, true);
        pickUpObject(objIndex);

        //ПРОВЕРКА СТОЛКНОВЕНИЙ С NPC
        int npcIndex = gp.collisionCheck.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        gp.eHandler.checkEvent();

        boolean isMoving = false;
        if(key.upPressed)
        {
            direction = "up";
            if(!collisionOn)
                worldY -= speed;
            isMoving = true;
        }
        if(key.downPressed)
        {
            direction = "down";
            if(!collisionOn)
                worldY += speed;
            isMoving = true;
        }
        if(key.leftPressed)
        {
            direction = "left";
            if(!collisionOn)
                worldX -= speed;
            isMoving = true;
        }
        if(key.rightPressed)
        {
            direction = "right";
            if(!collisionOn)
                worldX += speed;
            isMoving = true;
        }

        // обновление кадров игрока
        if(isMoving)
        {
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

    public void update() // обновление игрока
    {
       movement();
       if(life == 0)
           gp.ui.lose = true;
    }

    public void interactNPC(int i)
    {
        if(hit)
        {
            lifeCounter++;
            if(lifeCounter > 60)
            {
                hit = false;
                lifeCounter = 0;
            }
        }

        if(i != 999 && hit == false)
        {
            System.out.println("вы ударились в npc");
            hit = true;
            life -= 1;
        }
    }

    public void pickUpObject(int index) // ВЗАИМОДЕЙСТВИЕ С ОБЪЕКТАМИ
    {
        if(index != 999)
        {
            String objName = gp.obj[index].name; // ПОЛУЧЕНИЕ ИМЕНИ ОБЪЕКТА

            switch(objName)
            {
                case "Key":
                    hasKey++;
                    gp.obj[index] = null; // УБИРАЕМ КЛЮЧ
                    gp.ui.showMessage("Вы получили ключ!");
                    break;
                case "Door":
                    if(hasKey > 0)
                    {
                        int newX = gp.obj[index].worldX;
                        int newY = gp.obj[index].worldY;
                        gp.obj[index] = new OBJ_OpenDoor();
                        gp.obj[index].worldX = newX;
                        gp.obj[index].worldY = newY;
                        hasKey--;
                    }
                    else
                        gp.ui.showMessage("Вам нужен ключ!");
                    break;
                case "Boots":
                    speed += 1;
                    gp.obj[index] = null; // БОТЫ ПОДНИМАЮТ СКОРОСТЬ ПЕРСОНАЖА
                    gp.ui.showMessage("Скорость возросла");
                    break;
                case "Chest":
                    int newX = gp.obj[index].worldX;
                    int newY = gp.obj[index].worldY;
                    gp.obj[index] = new OBJ_OpenChest();
                    gp.obj[index].worldX = newX;
                    gp.obj[index].worldY = newY;
                    gp.ui.gameFinish = true;
            }

        }
    }
}













