package protect.jet.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyGraphics {
	public static final int TOTAL_AMOUNT_OF_JETS = 7;
	public static final int SPRITE_SHEET_ROWS = 7;
	public static final int ROW_OFFSET = 5;

	public static final int JET_PIXEL_WIDTH = 51;
	public static final int JET_PIXEL_HEIGHT = 60;
	
	private static BufferedImage enemyAmmo;
	private static BufferedImage enemyShip;

	public static void setup() {
		try {
			enemyShip = ImageIO.read(new File("res/sprites/EnemyShip.png"));
			enemyAmmo = ImageIO.read(new File("res/sprites/enemyLaser.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getShip() {
		return enemyShip;
	}

	public static BufferedImage getEnemyAmmo() {
		return enemyAmmo;
	}
}
