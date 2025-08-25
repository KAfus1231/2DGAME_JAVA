package Main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tiles.TileManager;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    // НАСРОЙКИ ЭКРАНА
    final int originalTileSize = 16; // 16x16 плиток
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 пикселей
    public final int screenHeight = tileSize * maxScreenRow; // 576 пикселей

    // НАСТРОЙКИ МИРА
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    Keyboard key = new Keyboard();
    Thread gameThread;

    public Player player = new Player(this, key);
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[14];

    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    public GamePanel() // настройка игры
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // размер окна
        this.setBackground(Color.BLACK); // фон
        this.setDoubleBuffered(true); // двойная буфф
        this.addKeyListener(key);
        this.setFocusable(true); // разрешаем ввод обработку ввода
    }

    public void setupGame ()
    {
        aSetter.setObject();
        aSetter.setNPC();
    }

    public void startGameThread() // метод начала игры
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = (double) 1000000000 /FPS; // 0.16666 секунд
        double nextDrawTime = System.nanoTime() + drawInterval; //

        while(gameThread != null) // пока поток активен
        {
            update();

            repaint();

            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // приравниваем к наносекундам

                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() // метод обновления
    {
       player.update();

       for(int i = 0; i < npc.length; i++)
       {
           if(npc[i] != null)
           {
               npc[i].update();
           }
       }
    }
    public void paintComponent(Graphics g) // метод отрисовки
    {
        super.paintComponent(g); // обращаемся к Jpanel и передаем туда g

        Graphics2D g2 = (Graphics2D)g; // переделываем g в 2Dg
        // ПЛИТКИ
        tileM.draw(g2);

        // ОБЪЕКТЫ
        for(int i = 0; i < obj.length; i++)
        {
            if(obj[i] != null)
                obj[i].draw(g2, this);
        }

        //NPC
        for(int i = 0; i < npc.length; i++)
        {
            if(npc[i] != null)
            {
                npc[i].draw(g2);
            }
        }

        // ИГРОК
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();
    }

}
