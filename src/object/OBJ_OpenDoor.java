package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_OpenDoor extends SuperObject {

    public OBJ_OpenDoor()
    {
        name = "OpenDoor";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/openDoor.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        collision = false;
    }
}
