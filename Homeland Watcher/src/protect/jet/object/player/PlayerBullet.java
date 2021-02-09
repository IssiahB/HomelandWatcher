package protect.jet.object.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;

import protect.jet.audio.GameSoundEffects;
import protect.jet.graphic.PlayerGraphics;
import protect.jet.object.BulletObject;

public class PlayerBullet extends BulletObject {
	private Clip audio = GameSoundEffects.getPlayerLaser();
	private int playerBulletSpeed = 15;
	private int playerBulletDamage = 50;

	public PlayerBullet(int xLoc, int y) {
		super(xLoc, y, 10, 25, PlayerGraphics.getPlayerAmmo());
		bulletSpeed = playerBulletSpeed;
		bulletDamage = playerBulletDamage;
		audio.start();
	}

	public void update() {
		y -= bulletSpeed;

		if ((y + height) < 0)
			isDestroyed = true;
	}

	public void render(Graphics2D g) {
		g.drawImage(ammoImage, XLOC, y, width, height, null);
	}
}
