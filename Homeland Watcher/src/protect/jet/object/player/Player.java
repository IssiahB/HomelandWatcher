package protect.jet.object.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import org.jarzarr.code.controls.KeyboardControl;
import org.jarzarr.code.window.Window;

import protect.jet.audio.GameSoundEffects;
import protect.jet.graphic.PlayerGraphics;
import protect.jet.handle.LevelHandler;
import protect.jet.object.BulletObject;
import protect.jet.object.GameObject;
import protect.jet.object.enemy.EnemyBullet;

public class Player extends GameObject {
	
	public static int NONE_POINT_TYPE = 0;
	public static int BASIC_POINT_TYPE = 10;
	public static int ADVANCED_POINT_TYPE = 100;

	private int speed = 5;
	private int health = 100;
	private static long points = 0;

	private BufferedImage[] playerJets = PlayerGraphics.getPlayerShips();
	private BufferedImage playerCurrentJet = playerJets[1];

	private Rectangle healthBar;
	private final int HEALTH_BAR_WIDTH = 200;
	private final int HEALTH_BAR_MARGIN = 2;

	private Clip playerExplosion = GameSoundEffects.getPlayerExplosion();
	private Clip playerHit = GameSoundEffects.getPlayerHit();

	private final int shootingSpeed = 20;
	private int shotIterator = shootingSpeed / 2;

	public Player() {
		super(((Window.getFrame().getWidth()/2) - 64/2), ((Window.getFrame().getHeight() * 80) / 100));
		healthBar = new Rectangle(x, (y + height + 20), HEALTH_BAR_WIDTH, 20);
	}

	@Override
	public void update() {
		getShot();
		movementControl();
		shootControl();
		boundControl();
		healthBarPosition();
	}

	@Override
	public void render(Graphics2D g) {
		y = (Window.getFrame().getHeight() * 80) / 100;
		g.drawImage(playerCurrentJet, x, y, width, height, null);
		drawHealthBar(g);
	}

	private void drawHealthBar(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(healthBar.x - HEALTH_BAR_MARGIN, healthBar.y - HEALTH_BAR_MARGIN,
				HEALTH_BAR_WIDTH + (HEALTH_BAR_MARGIN * 2), healthBar.height + (HEALTH_BAR_MARGIN * 2));

		g.setColor(Color.red);
		g.fill(healthBar);
	}

	private void healthBarPosition() {
		healthBar.width = (HEALTH_BAR_WIDTH * health) / 100;
		healthBar.y = (y + height + healthBar.height);
		healthBar.x = (x - (HEALTH_BAR_WIDTH / 3));
	}

	private void movementControl() {
		playerCurrentJet = playerJets[1];

		if (KeyboardControl.aKey && !KeyboardControl.dKey) {
			x -= speed;
			playerCurrentJet = playerJets[0];
		}
		if (KeyboardControl.dKey && !KeyboardControl.aKey) {
			x += speed;
			playerCurrentJet = playerJets[2];
		}
	}

	private void boundControl() {
		if (x < 0)
			x = 0;
		if ((x + width) > (Window.getCanvas().getWidth()))
			x = (Window.getCanvas().getWidth() - width);
	}

	private void shootControl() {
		// limit how many bullets are shot
		if (KeyboardControl.spaceKey) {
			if (shotIterator >= shootingSpeed) {
				shotIterator = 0;
				BulletObject.addBullet(new PlayerBullet((x + (width / 2)) - 5, y));
			} else
				shotIterator++;
		} else {
			shotIterator = shootingSpeed / 4; // how long it takes to start shooting
		}
	}

	public void getShot() {
		if (BulletObject.getBullets().size() > 0)
			BulletObject.getBullets().parallelStream().forEach(bullet -> {
				if ((bullet.getXLOC() + bullet.getWidth() > x) && (bullet.getXLOC() < (x + width)))
					if ((bullet.getY() < (y + height)) && ((bullet.getY() + bullet.getHeight()) > y)) {
						if (bullet instanceof EnemyBullet) {
							// player health and what to do with the bullet that hit
							health -= bullet.getBulletDamage();
							bullet.setDestroyed(true);
							// sound played when player gets hit
							if (health > 0) {
								playerHit.start();
								playerHit.setFramePosition(0);
							}
						}
					}
			});

		// when player has died
		if (health <= 0) {
			playerExplosion.start();
			LevelHandler.getCurrentLevel().setLevelOver(true);
			destroyed = true;
		}
	}

	public static long getPoints() {
		return points;
	}

	public static void addPoints(int pointType) {
		if (pointType == NONE_POINT_TYPE)
			points = 0;
		
		points+=pointType;
	}

}
