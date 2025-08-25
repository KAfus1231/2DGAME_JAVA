package tiles;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public int animationTime = 0;
    public int frame = 1;

    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[20]; // количество плиток
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void loadMap() // создание карты
    {
        try
        {
            InputStream is = getClass().getResourceAsStream("/map/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // импортируем текстовый файл

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine();

                while(col < gp.maxWorldCol)
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getTileImage() // загрузка тайлов
    {
        try
        {
            tile[0] = new Tile(); // ТРАВКА
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/floor1.png"));

            tile[1] = new Tile(); // СТЕНКА
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walls/wall1.png"));
            tile[1].collision = true;

            tile[2] = new Tile(); // ВОДИЧКА
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water" + "1" + ".png"));
            tile[2].collision = true;

            tile[3] = new Tile(); // ГРЯЗЮКА
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/dirt.png"));

            tile[4] = new Tile(); // ДЕРЕВЦЕ
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walls/tree2WithGrass.png"));
            tile[4].collision = true;

            tile[5] = new Tile(); // ПЕСОЧЕК
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/sand.png"));

            tile[6] = new Tile(); // МЕРТВОЕ ДЕРЕВЦЕ
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walls/tree1WithGrass.png"));
            tile[6].collision = true;

            //ТАЙЛИКИ РЕЛЬЕФА
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/relief1.png"));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/relief2.png"));
            tile[8].collision = false;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/relief3.png"));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/relief4.png"));
            tile[10].collision = false;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/relief5.png"));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/relief6.png"));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/relief7.png"));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/relief8.png"));
            tile[14].collision = true;

            //ТАЙЛЫ ТРОПИНКИ
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/тропинка2.png"));
            tile[15].collision = false;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/тропинка1.png"));
            tile[16].collision = false;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/тропинка3.png"));
            tile[17].collision = false;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/тропинка4.png"));
            tile[18].collision = false;

            //СТУПЕНЬКИ
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor/ступеньки.png"));
            tile[19].collision = false;

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void animationOBJ()
    {
        if(animationTime > 10)
        {
            animationTime = 0;
            frame++;
            if(frame > 4)
                frame = 1;
            try {
                tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water" + frame + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        animationTime++;
    }

    public void draw(Graphics2D g2) // метод рисования карты из c++
    {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize; // ПЛИТКИ В МИРЕ
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX; // ПОЛОЖЕНИЕ КАМЕРЫ
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // ГРАНИЦЫ ОТРИСОВКИ
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); // ПОЛОЖЕНИЕ ИГРОКА ВСЕГДА ПРИВЯЗАНО К КАМЕРЕ
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol)
            {
                worldCol = 0;
                worldRow++;
            }
        }

        animationOBJ();
    }
}














