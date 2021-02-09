package protect.jet.object.enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.sound.sampled.Clip;

import org.jarzarr.code.window.Window;

import protect.jet.audio.GameSoundEffects;
import protect.jet.graphic.EnemyGraphics;
import protect.jet.handle.LevelHandler;
import protect.jet.object.BulletObject;
import protect.jet.object.GameObject;
import protect.jet.object.player.Player;
import protect.jet.object.player.PlayerBullet;

public class BasicEnemy extends GameObject {

	private Clip enemyExplosion = GameSoundEffects.getEnemyExplosion();
	private BufferedImage enemyJet = EnemyGraphics.getShip();
	private int health = 100;

	private int jumpAmount = 15;
	private int speedCount = 60; // every second
	private int speedIterator = 0;

	private int shotSpeed;
	private int shotRanCount = 2000;
	private int shotIterator = 0;
	private Random ran = new Random();

	public BasicEnemy(int x, int y) {
		super(x, y);
		shotSpeed = ran.nextInt(shotRanCount) + ran.nextInt(200);
	}

	@Override
	public void update() {
		updateBullets();
		updateMovement();
		getShot();
		checkLiving();
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(enemyJet, x, y + height, width, -height, null);
	}

	private void checkLiving() {
		if (health <= 0) {
			enemyExplosion.start();
			Player.addPoints(Player.BASIC_POINT_TYPE);
			destroyed = true;
		}
	}

	private void getShot() {
		// Check if player has shot me remove health and destroy bullet
		if (BulletObject.getBullets().size() > 0) {
			BulletObject.getBullets().parallelStream().forEach(bullet -> {
				if ((bullet.getXLOC() + bullet.getWidth() > x) && (bullet.getXLOC() < (x + width))) // if bullet inside
																									// x
					if ((bullet.getY() < (y + height)) && ((bullet.getY() + bullet.getHeight()) > y)) { // if bullet
																										// inside y
						if (bullet instanceof PlayerBullet) {
							health -= bullet.getBulletDamage();
							bullet.setDestroyed(true);
						}
					}
			});
		}
	}

	private void updateBullets() {
		// When to shoot
		if (shotIterator >= shotSpeed) {
			shotSpeed = ran.nextInt(shotRanCount) + ran.nextInt(200);
			shotIterator = 0;
			BulletObject.addBullet(new EnemyBullet(x + (width / 2), y));
		} else {
			shotIterator++;
		}
	}

	private void updateMovement() {
		if (y > Window.getFrame().getHeight()) {
			destroyed = true;
			LevelHandler.getCurrentLevel().removeLevelLife();
		}

		if (speedIterator >= speedCount) {
			y = y + jumpAmount;
			speedIterator = 0;
		} else {
			speedIterator++;
		}
	}

	public BufferedImage getEnemyJet() {
		return enemyJet;
	}

	public void setEnemyJet(BufferedImage enemyJet) {
		this.enemyJet = enemyJet;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * @return a number between 0 and 1
	 */
	public float getSpeedCount() {
		if (speedCount == 0)
			return 0;
		
		return (speedCount / 60);
	}
	
	/**
	 * Determines how many times the enemy jumps in a second
	 * 
	 * @param speed - a number between 0 and 1. One being one jump
	 * per second and 0 being 60 jump a second
	 */
	public void setSpeedCount(float speed) {
		if (speed > 1)
			speed = 1;
		if (speed <= 0) {
			speedCount = 0;
			return;
		}
		
		int percentSpeed = (int) (speed * 100);
		int newSpeed = (60 * percentSpeed) / 100;
		
		speedCount = newSpeed;
	}

	public int getShotSpeed() {
		return shotRanCount;
	}
	
	/**
	 * Determines how quick an enemy bullet is created, by
	 * either setting more time till the bullet is created
	 * or setting less time (2000 max time - 0 min time). 
	 * The number actually used will be chosen randomly
	 * from set shotSpeed
	 * 
	 * @param shotSpeed - the number used to randomly set a
	 * time delay for an enemy bullet to be created
	 */
	public void setShotSpeed(int shotSpeed) {
		if (shotSpeed > 2000)
			shotSpeed = 2000;
		if (shotSpeed < 0)
			shotSpeed = 0;
		this.shotRanCount = shotSpeed;
	}

}
