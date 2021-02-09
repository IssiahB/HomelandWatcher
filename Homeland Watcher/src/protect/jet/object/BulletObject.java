package protect.jet.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import protect.jet.object.enemy.EnemyBullet;
import protect.jet.object.player.PlayerBullet;

public abstract class BulletObject {
	private static List<BulletObject> bullets = new ArrayList<BulletObject>();
	public static enum BulletType {
		PlayerBullet, EnemyBullet;
	}

	protected boolean isDestroyed = false;
	protected BufferedImage ammoImage;
	protected int bulletSpeed = 10;
	protected int bulletDamage = 20;

	protected final int XLOC;
	protected int y;
	
	protected int width, height;

	public BulletObject(int x, int y, int width, int height, BufferedImage image) {
		this.XLOC = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ammoImage = image;
	}

	protected abstract void update();

	protected abstract void render(Graphics2D g);
	
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
	public void setDestroyed(boolean destroy) {
		this.isDestroyed = destroy;
	}

	public BufferedImage getAmmoImage() {
		return ammoImage;
	}

	public void setAmmoImage(BufferedImage ammoImage) {
		this.ammoImage = ammoImage;
	}

	public int getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	public int getBulletDamage() {
		return bulletDamage;
	}

	public void setBulletDamage(int bulletDamage) {
		this.bulletDamage = bulletDamage;
	}

	public int getXLOC() {
		return XLOC;
	}

	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

/////////////////// STATIC METHODS \\\\\\\\\\\\\\\\\\
	public static boolean addBullet(BulletObject bullet) {
		if (bullet == null)
			return false;

		bullets.add(bullet);
		return true;
	}

	public static boolean removeBullet(BulletObject bullet) {
		if (bullet == null)
			return false;
		if (!bullets.contains(bullet))
			return false;

		bullets.remove(bullet);
		return true;
	}
	
	public static void updateAll() {
		for (BulletObject bullet: bullets) {
			if (bullet.isDestroyed) {
				bullets.remove(bullet);
				break;
			} else
				bullet.update();
		}
	}
	
	public static void renderAll(Graphics2D g) {
		bullets.parallelStream().forEach(bullet -> {
			bullet.render(g);
		});
	}

	public static List<BulletObject> getBullets() {
		return bullets;
	}
	
	////////// MISC STATIC METHODS \\\\\\\\\\
	private static boolean hasHit;
	private static BulletObject bulletHitObject = null;
	public static boolean hasBulletHitObject(GameObject obj, BulletType type) {
		hasHit = false;
		
		if (bullets.size() > 0)
			bullets.parallelStream().forEach(bullet -> {
				if ((bullet.getXLOC() + bullet.getWidth() > obj.x) && (bullet.getXLOC() < (obj.x + obj.width)))
					if ((bullet.getY() < (obj.y + obj.height)) && ((bullet.getY() + bullet.getHeight()) > obj.y)) {
						// return true based on what bullet has hit object
						switch (type) {
						case PlayerBullet:
							if (bullet instanceof PlayerBullet) {
								hasHit = true;
								bulletHitObject = bullet;
							}
							break;
						case EnemyBullet:
							if (bullet instanceof EnemyBullet) {
								hasHit = true;
								bulletHitObject = bullet;
							}
							break;
						}
					}
			});
		
		return hasHit;
	}
	
	public static BulletObject getBulletHasHitObject() {
		if (bulletHitObject == null) {
			System.err.println("Must Call \"hasBulletHitObject(GameObject, BulletType)\" before getting that bullet that hit!");
			return null;
		}
		BulletObject bullet = bulletHitObject;
		bulletHitObject = null;
		
		return bullet;
	}
}
