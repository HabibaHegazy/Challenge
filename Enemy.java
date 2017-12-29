
package challengetankgame;

import java.util.Vector;

/**
 *
 * @author habiba
 */
class Enemy extends Tank implements Runnable {

	Vector<Fire> fireTank = new Vector<Fire>();

	public Enemy(int x, int y) {
		super(x, y);
		this.direct = 270;
	}

        @Override
	public void shotEnemy() {
        	Fire fire = new Fire(x + 9, y + 28, 270, this.speed*6);
		Thread thread = new Thread(fire);
		thread.start();
		fireTank.add(fire);
	}

        @Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int randomlyChoose = (int)(Math.random()*10);
			for (int i = 0; i < 30; i++) {
				if (randomlyChoose == 0 && this.fireTank.size() < 3)
					this.shotEnemy();
				if (y < 380)
					this.y += this.speed;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

