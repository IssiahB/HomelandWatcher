package protect.jet.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerGraphics {
	public static final int TOTAL_AMOUNT_OF_SHIPS = 3;

	public static final int JET_PIXEL_WIDTH = 39;
	public static final int JET_PIXEL_HEIGHT = 36;

	private static BufferedImage laser = null;
	private static BufferedImage[] playerShips = null;

	public static void setup() {
		playerShips = new BufferedImage[TOTAL_AMOUNT_OF_SHIPS];

		try {
			laser = ImageIO.read(new File("res/sprites/playerLaser.jpg"));
			BufferedImage shipSpriteSheet = ImageIO.read(new File("res/sprites/PlayerShip.png"));

			for (int i = 0; i < TOTAL_AMOUNT_OF_SHIPS; i++) {
				playerShips[i] = shipSpriteSheet.getSubimage((JET_PIXEL_WIDTH * i) + 1, 0, JET_PIXEL_WIDTH - 1,
						JET_PIXEL_HEIGHT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getPlayerAmmo() {
		if (laser == null)
			setup();
		
		return laser;
	}

	public static BufferedImage[] getPlayerShips() {
		if (playerShips == null)
			setup();
		
		return playerShips;
	}
}
