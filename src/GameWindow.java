import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {// создаем окно

    public GameWindow(){//делаем конструктор
        setTitle("Snake");//имя окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//метод закрытия, HIDE_ON_CLOSE закрывает окно, но не прекращает процесс
        setSize(400,400);// размер окна
        setLocation(400,400);// расположение на экране
        add(new SnakeGame());//добавляем панель в окно
        addKeyListener(new KeyBinder());
        setVisible(true);
        setFocusable(true);

    }
    class KeyBinder extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_Q ){
                System.out.println("restart");
            }
        }
    }

    public static void main(String[] args) {

        GameWindow game = new GameWindow();
    }
}
