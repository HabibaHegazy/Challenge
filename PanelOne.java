
package challengetankgame;

import static challengetankgame.ChallengeTankGame.frame;
import static challengetankgame.Tank.change;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author habiba
 */
class mainPanel extends JPanel implements KeyListener, Runnable {
        int TanksNumber = 8;
        int mylife = 9;
        Tank hero = null;
        
	Vector<Enemy> enemys = new Vector<Enemy>();
        Vector<Bomb> bombs = new Vector<Bomb>();

	public mainPanel() {
		hero = new Tank (250, 320);
            for (int i = 0; i < TanksNumber; i++) {
		Enemy enemyTank = new Enemy((int) (Math.random() * 480),(int) (Math.random()*40));
		Thread thread = new Thread(enemyTank);
		thread.start();
		enemys.add(enemyTank);
            }
            this.setBackground(Color.WHITE);
	}

        @Override
	public void paint(Graphics g) {
                super.paint(g);
                if (mylife > 0)
                    this.drawTank(hero.x, hero.y, g, hero.direct, 0);

		for (int i = 1; i <= hero.FireEnemy.size(); i++) {
                    if (hero.FireEnemy.get(i - 1).isLive) {
			g.setColor(Color.red);
			g.draw3DRect(hero.FireEnemy.get(i - 1).x, hero.FireEnemy.get(i - 1).y, 2, 2,false);
			} else {
				hero.FireEnemy.remove(i - 1);
				i--;
			}
		}
		for (int i = 0; i < enemys.size(); i++) {
                    if (enemys.get(i).isLive) {
                        this.drawTank(enemys.get(i).x, enemys.get(i).y, g, enemys.get(i).direct, 1);
                        
                        for (int x = 1; x <= enemys.get(i).fireTank.size(); x++) {
				if (enemys.get(i).fireTank.get(x - 1).isLive == true) {
                                    g.setColor(Color.black);
                                    g.draw3DRect(enemys.get(i).fireTank.get(x - 1).x, enemys.get(i).fireTank.get(x - 1).y, 2, 2, false);
				} else {
                                    enemys.get(i).fireTank.remove(x - 1);
                                    x--;
				}
                            }
                    } else {
			enemys.remove(i);
			i--;
                    }
                }
            for (int i = 0; i < bombs.size(); i++) {
                    bombs.get(i);
                    bombs.remove(i);
                    i--;
		}
    }

	public void hittank(Fire fire, Tank tank) {
            if (fire.x >= tank.x && fire.x <= tank.x + 20 && fire.y >= tank.y && fire.y <= tank.y + 30) {
		fire.isLive = false;
		tank.isLive = false;
		TanksNumber--;

		Bomb newbomb = new Bomb(tank.x, tank.y);
		bombs.add(newbomb);
            }
	}

	public void hitmytank(Fire fire, Tank tank) {
            if (fire.x >= tank.x && fire.x <= tank.x + 20 && fire.y >= tank.y && fire.y <= tank.y + 30) {
                    fire.isLive = false;
                    mylife--;
                    
                    Bomb newbomb = new Bomb(tank.x, tank.y);
                    bombs.add(newbomb);
            }
        }
     
	public void drawTank(int x, int y, Graphics g, int direct, int type) {
		switch (type) {
		case 0:
			g.setColor(Color.BLACK);
                	g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			g.fillOval(x + 4, y + 10, 10, 10);
			g.drawLine(x + 9, y + 15, x + 9, y - 4);
			break;
		case 1:
                        g.setColor(Color.RED);
                        g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			g.fillOval(x + 4, y + 10, 10, 10);
			g.drawLine(x + 9, y + 15, x + 9, y + 28);
			break;

		}
	}

        @Override
	public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_LEFT) && hero.x > 0) {
			hero.direct = 180;
			hero.x-=hero.speed+8;
			this.repaint();
		}

		else if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && hero.x < 480) {
			hero.direct = 0;
			hero.x+=hero.speed+8;
			this.repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				hero.shotEnemy();
				this.repaint();
			
		}
	}

        @Override
	public void keyReleased(KeyEvent e) {
	}

        @Override
	public void keyTyped(KeyEvent e) {

	}

        @Override
	public void run() {

		while (!change) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
                        for (int i = 0; i < enemys.size(); i++) {
				for (int j = 0; j < hero.FireEnemy.size(); j++)
					this.hittank(hero.FireEnemy.get(j), enemys.get(i));
			}

			for (int i = 0; i < enemys.size(); i++) {
				Enemy enemy = enemys.get(i);
				for (int j = 0; j < enemy.fireTank.size(); j++)
					this.hitmytank(enemy.fireTank.get(j), hero);
			}
			this.repaint();
			if (mylife == 0){
				change = true;
                                int num = JOptionPane.showOptionDialog(null, "you have Lost the Game", "Lost Game", 
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                if(num == 0){
                                   frame.dispose();
                                   System.exit(0);
                                }
                        } 
                        if (enemys.isEmpty()){
				change = true;
                                int num = JOptionPane.showOptionDialog(null, "you have Won the Game", "Won Game", 
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                if(num == 0){
                                   frame.dispose();
                                   System.exit(0);
                                }
                        }
		}
	}
}