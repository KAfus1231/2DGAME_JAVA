package Main;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial, arial2B;
    BufferedImage keyImage;
    BufferedImage heartFull, heartHalf, heartBlank;
    public boolean messageOn = false;
    public String message = "";
    int messageTime = 0;
    public boolean gameFinish = false;
    public boolean lose = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial = new Font("Arial", Font.PLAIN, 40);
        arial2B = new Font("Arial", Font.BOLD, 60);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

        SuperObject heart = new OBJ_Heart();
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartBlank = heart.image3;
    }
    //ПОЛУЧАЕМ СООБЩЕНИЕ
    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2)
    {
        if(gameFinish)
        {
            g2.setFont(arial);
            g2.setColor(Color.YELLOW);

            String text;
            int textLenght;
            int x;
            int y;

            text = "Вы нашли сокровище!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //РАЗМЕР СТРОКИ
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 - gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Веремя: " + dFormat.format(playTime);
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //РАЗМЕР СТРОКИ
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 + gp.tileSize;
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }

        if(lose)
        {
            g2.setFont(arial);
            g2.setColor(Color.YELLOW);

            String text;
            int textLenght;
            int x;
            int y;

            text = "Вы проиграли!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //РАЗМЕР СТРОКИ
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 - gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Веремя: " + dFormat.format(playTime);
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //РАЗМЕР СТРОКИ
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 + gp.tileSize;
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }

        else {
            g2.setFont(arial);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize * 11, gp.tileSize + 32, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, gp.tileSize * 12 + 10, gp.tileSize * 2 + 28);

            drawPlayerLife(g2);

            //ВРЕМЯ ИГРЫ
            playTime +=(double)1/60;
            g2.drawString("Время:" + dFormat.format(playTime), gp.screenWidth - gp.tileSize*5, gp.tileSize + 15);


            //СООБЩЕНИЕ
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageTime++;

                if (messageTime > 120) // УБИРАЕМ СООБЩЕНИЕ С ЭКРАНА
                {
                    messageTime = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void drawPlayerLife(Graphics2D g2) {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2)
        {
            g2.drawImage(heartBlank, x, y, 32, 32, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while(i < gp.player.life)
        {
            g2.drawImage(heartHalf, x, y, 32, 32, null);
            i++;
            if(i < gp.player.life)
            {
                g2.drawImage(heartFull, x, y, 32, 32, null);
            }
            i++;
            x += gp.tileSize;
        }


    }

}
