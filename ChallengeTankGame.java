
package challengetankgame;

import static challengetankgame.Tank.change;
import javax.swing.JFrame;

/**
 *
 * @author habiba
 */
public class ChallengeTankGame implements Runnable{
    static JFrame frame = new JFrame();
    public static void main(String[] args) {
        // TODO code application logic here
        frame.setSize(500, 400);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel panel = new mainPanel();
	Thread thread = new Thread(panel);
	thread.start();
	frame.addKeyListener(panel);
	frame.add(panel);
	frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
public void run() {      
    while (true) {
	try {
           Thread.sleep(100);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
        }
    }
}
