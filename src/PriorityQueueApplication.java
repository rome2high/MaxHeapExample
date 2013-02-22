import javax.swing.JFrame;

public class PriorityQueueApplication {
 public static final int WIDTH = 400;
 public static final int HEIGHT = 500;
  
 public static void main(String[] args) {
  DrawLayout drawLayout = new DrawLayout(WIDTH, HEIGHT);
  JFrame application = new JFrame();
  application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  application.setTitle("Priority Queue Application");
  application.getContentPane().add(drawLayout);
  application.setSize(WIDTH, HEIGHT);
  application.setVisible(true);
 }
}