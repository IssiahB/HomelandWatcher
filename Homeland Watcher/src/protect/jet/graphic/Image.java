package protect.jet.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	public static BufferedImage getImage(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
