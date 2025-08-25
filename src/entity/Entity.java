package entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Entity<T> {
    GamePanel gp;
    public int worldX, worldY; // позиция на карте
    public int speed;

    // Изображения
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4,
            left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction; // направление игрока

    public int spriteCounter = 0; // счетчик спрайтов
    public int spriteNum = 1; // номер спрайта
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // ХИТБОКС
    public int solidAreaDefaultX, solidAreaDefaultY; // ХИТБОКС ДЛЯ ДРУГИХ
    public boolean collisionOn = false;
    public int actionLock = 0;

    // СОСТОЯНИЕ ИГРОКА
    public int maxLife;
    public int life;

    private List<T> inventory;

    Entity(GamePanel gp) {
        this.gp = gp;
        this.inventory = new ArrayList<>();
    }

    public void addItemToInventory(T item) {
        inventory.add(item);
    }

    //ИНВЕНТАРЬ
    public List<T> getInventory() {
        return inventory;
    }

    public void setAction() {

    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.collisionCheck.checkTile(this);
        gp.collisionCheck.checkObject(this, false);
        gp.collisionCheck.checkPlayer(this);

        if (!collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX; // ПОЛОЖЕНИЕ КАМЕРЫ
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // ГРАНИЦЫ ОТРИСОВКИ
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) { // основываясь на направлении выбираем изображение
                case "up":
                    if (spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                    if (spriteNum == 3) image = up3;
                    if (spriteNum == 4) image = up4;
                    break;
                case "down":
                    if (spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                    if (spriteNum == 3) image = down3;
                    if (spriteNum == 4) image = down4;
                    break;
                case "left":
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                    if (spriteNum == 3) image = left3;
                    if (spriteNum == 4) image = left4;
                    break;
                case "right":
                    if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                    if (spriteNum == 3) image = right3;
                    if (spriteNum == 4) image = right4;
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // ПОЛОЖЕНИЕ ИГРОКА ВСЕГДА ПРИВЯЗАНО К КАМЕРЕ
        }
    }
}

