
package challengetankgame;

import static challengetankgame.ChallengeTankGame.frame;
import javax.swing.JOptionPane;

/**
 *
 * @author habiba
 */
public class Fire implements Runnable {
	public int x, y, direct, speed;
	public boolean isLive = true;

	Fire(int x, int y, int direct,int speed) {
		this.x = x;
		this.y = y;
		this.direct = direct;
		this.speed = speed;
	}

	public void run() {
		while (isLive) {
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch (direct) {
			case 90:
				y -= speed;
				break;
			case 270:
				y += speed;
				break;
			}
			if (y > 2000) {
				isLive = false;
                                int num = JOptionPane.showOptionDialog(null, "you have Lost the Game", "Lost Game", 
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                if(num == 0){
                                   frame.dispose();
                                   System.exit(0);
                                }
			}
		}
	}
}