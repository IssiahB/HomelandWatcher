package protect.jet.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Backgrounds {
	public static final int ROW_OFFSET = 5;
	public static final int COLM_OFFSET = 5;
	public static final int SPRITE_SHEET_ROWS = 7;
	public static final int SPRITE_SHEET_COLMS = 5;

	public static final int BACKGROUND_PIXEL_WIDTH = 240;
	public static final int BACKGROUND_PIXEL_HEIGHT = 128;

	public static final int TOTAL_AMOUNT_OF_BACKGROUNDS = 35;

	private static BufferedImage backgroundSpriteSheet;
	private static BufferedImage[] allBackgrounds;

	public static void setup() {
		try {
			backgroundSpriteSheet = ImageIO.read(new File("res/sprites/Background.png"));
			allBackgrounds = new BufferedImage[TOTAL_AMOUNT_OF_BACKGROUNDS];

			int indexIterator = 0;

			for (int i = 0; i < SPRITE_SHEET_ROWS; i++) {
				for (int j = 0; j < SPRITE_SHEET_COLMS; j++) {
					allBackgrounds[indexIterator++] = backgroundSpriteSheet.getSubimage(
							((j * BACKGROUND_PIXEL_WIDTH) + ((j + 1) * COLM_OFFSET)),
							((i * BACKGROUND_PIXEL_HEIGHT) + ((i + 1) * ROW_OFFSET)), BACKGROUND_PIXEL_WIDTH,
							BACKGROUND_PIXEL_HEIGHT);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getBackground(int row, int colm) {
		if (row < 1 || row > SPRITE_SHEET_ROWS+1)
			return null;
		if (colm < 1 || colm > SPRITE_SHEET_COLMS+1)
			return null;

		return allBackgrounds[((row-1) * 5) + (colm-1)];
	}
}
