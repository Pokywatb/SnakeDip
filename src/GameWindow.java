import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {// создаем окно

    public GameWindow(){//делаем конструктор
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // получаем размер экрана (dimension - размеры ширина и длинна)
        setTitle("Snake");//имя окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//метод закрытия, HIDE_ON_CLOSE закрывает окно, но не прекращает процесс
        setSize(400,400);// размер окна
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);// задаем расположение окна по середине экрана.
        add(new SnakeGame());//добавляем панель в окно
        setVisible(true);




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
