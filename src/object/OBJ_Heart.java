package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {
    public OBJ_Heart()
    {
        name = "Heart";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart2.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart3.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
