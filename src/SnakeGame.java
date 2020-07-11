import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class SnakeGame extends JPanel implements ActionListener {//создаем  панель для игры
    private final int SIZE = 400;// размер поля
    private final int DOT_SIZE = 20;// размер точки на поле в пикселях
    private final int ALL_DOTS = 400;// сколько может поместиться точек на поле
    private Image dot;
    private Image food;
    private Image gameover;
    private int coordX;// координаты еды по оси х
    private int coordY;// координаты еды по оси y
    private int[] x = new int[ALL_DOTS];//ячейки для хранения положений змейки
    private int[] y = new int[ALL_DOTS];//ячейки для хранения положений змейки
    private int snakeSize;
    private int score;
    private int time;
    private int tick;
    private int length;
    private Timer timer;
    private boolean left;
    private boolean right = true;
    private boolean up;
    private boolean down;
    public boolean process = true; // идет игровой процесс или нет


    public SnakeGame() {
        setBackground(Color.black);
        imageLoad();
        start();
        addKeyListener(new KeyBinder());
        if(!process){setFocusable(false);}
        else {setFocusable(true);}// фокус на игровом поле при нажатии клавиш
    }

    public void start() {
        score = 0;
        time = 0;
        tick = 0;
        length = 3;
        snakeSize = 3; // начальный размер змейки
        for (int i = 0; i < snakeSize; i++) {// задаем стартовое располдожение змейки
            x[i] = 40 - i*DOT_SIZE;
            y[i] = 40;
        }
        timer = new Timer(100, this);
        timer.start();
        addFood();
    }

    public void addFood(){
        coordX = new Random().nextInt(20)*DOT_SIZE-40;//рандомные координаты еды
        coordY = new Random().nextInt(20)*DOT_SIZE-40;
    }

    public void imageLoad(){
        ImageIcon image = new ImageIcon("food.png");
        food = image.getImage();
        ImageIcon image2 = new ImageIcon("dot.png");
        dot = image2.getImage();
        ImageIcon image3 = new ImageIcon("1231.png");
        gameover = image3.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {//перерисоваываем поле
        super.paintComponent(g);
        if(process){
            g.drawImage(food, coordX, coordY, this);
            for (int i = 0; i < snakeSize ; i++) {
                g.drawImage(dot, x[i], y[i], this);

            }
            String string = "Score: " + score + ", Length: " + length + ", Time: " + time;
            g.setColor(Color.white);
            g.drawString(string, (int) (getWidth() / 2 - string.length()*2.5), 10);// рисуем счетчик очков
        }
        else{
            String string = "Game Over.";
            g.setColor(Color.white);
            g.drawString(string, (int) (getWidth() / 2 - string.length()*3), 170);
            String string1 = "Press Q to play again";
            g.setColor(Color.white);
            g.drawString(string1, (int) (getWidth() / 2 - string.length()*6), 190);//
           // g.drawImage(gameover, 0, 0, this);
        }
    }

    public void move(){// прорисовываем движение змейки, изменяя записи массива положения ее частей
        for (int i = snakeSize; i > 0 ; i--) {// смещаем все точки кроме первой
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        }
        if(up){
            y[0] -= DOT_SIZE;
        }
        if(down){
            y[0] += DOT_SIZE;
        }
    }

    public void eatFood(){// съедаем добычу, змейка растет
        if (x[0] == coordX && y[0] == coordY){
            score += 10;
            length ++;
            snakeSize++;
            addFood();
        }
    }

    public void snakeCrush(){// прекращение игры при столкновении с собой или с границей поля
        for (int i = snakeSize; i > 0; i--) {
           if(i > 4 && x[0] == x[i] && y[0] == y[i]){
               process = false;
           }
        }
        if(x[0] > SIZE-40){
            process = false;
        }
        if(x[0] < 0){
            process = false;
        }
        if(y[0] > SIZE-40){
            process = false;
        }
        if(y[0] < 0){
            process = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {// метод для работы таймера
        if(process){
            eatFood();
            snakeCrush();
            move();
            
        }
        repaint();//перерисовывание поля при любых изменениях/ вызывает paintcomponent
        tick++;
        time = tick/10;
    }

    class KeyBinder extends KeyAdapter{// создаем управление змейкой


        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_A && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_D && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_S && !up){
                left = false;
                down = true;
                right = false;
            }
            if(key == KeyEvent.VK_W && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_Q && !process){
                GameWindow game = new GameWindow();

                System.out.println("restart11");
                System.out.println(process);
            }
        }
    }
}
