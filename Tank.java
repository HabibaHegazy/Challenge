
package challengetankgame;

import java.util.Vector;

/**
 *
 * @author habiba
 */
class Tank {
        public int x = 0;
	public int y = 0;
        public int color;
	public boolean isLive = true;
	public int direct = 0;
	public int speed = 2;
        public static boolean change = false;

        Vector<Fire> FireEnemy = new Vector<Fire>();

        public Tank(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public void shotEnemy() {
		Fire fire = new Fire(x + 9, y - 4, 90, speed*10);
		Thread thread = new Thread(fire);
		thread.start();
		FireEnemy.add(fire);
        }
}