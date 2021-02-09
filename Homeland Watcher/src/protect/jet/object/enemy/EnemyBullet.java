package protect.jet.object.enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.jarzarr.code.window.Window;

import protect.jet.graphic.EnemyGraphics;
import protect.jet.object.BulletObject;

public class EnemyBullet extends BulletObject {

	public EnemyBullet(int xLoc, int y) {
		super(xLoc, y, 10, 25, EnemyGraphics.getShip());
	}

	public void update() {
		y += bulletSpeed;

		if (y > Window.getFrame().getHeight())
			isDestroyed = true;
	}

	public void render(Graphics2D g) {
		g.drawImage(ammoImage, XLOC, y, width, height, null);
	}
}
