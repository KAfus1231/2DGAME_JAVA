package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// работа с клавиатурой
public class Keyboard implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override // обработка полного нажатия
    public void keyTyped(KeyEvent e)
    {
    }

    @Override // обработка нажатия
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode(); // получение нажатой клавиши

        switch(code)
        {
            case KeyEvent.VK_W: upPressed = true; break;
            case KeyEvent.VK_S: downPressed = true; break;
            case KeyEvent.VK_A: leftPressed = true; break;
            case KeyEvent.VK_D: rightPressed = true; break;
        }
    }

    @Override // обработка отпускания
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();

        switch(code)
        {
            case KeyEvent.VK_W: upPressed = false; break;
            case KeyEvent.VK_S: downPressed = false; break;
            case KeyEvent.VK_A: leftPressed = false; break;
            case KeyEvent.VK_D: rightPressed = false; break;
        }
    }
}
