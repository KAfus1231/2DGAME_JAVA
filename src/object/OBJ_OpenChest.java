package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_OpenChest extends SuperObject {
    public OBJ_OpenChest()
    {
        name = "OpenChest";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/openChest.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
