package object;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

// КЛАСС ДЛЯ СОЗДАНИЯ ОБЪЕКТОВ
public class SuperObject {

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // ХИТБОКС
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX; // ПОЛОЖЕНИЕ КАМЕРЫ
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // ГРАНИЦЫ ОТРИСОВКИ
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // ПОЛОЖЕНИЕ ИГРОКА ВСЕГДА ПРИВЯЗАНО К КАМЕРЕ
        }
    }
}
