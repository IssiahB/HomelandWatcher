package protect.jet.handle;

import java.awt.Graphics2D;

import protect.jet.level.LevelObject;

public class LevelHandler {
	private static LevelObject currentLevel = null;
	
	public static void setCurrentLevel(LevelObject level) {
		if (level == null) 
			return;
		
		currentLevel = level;
	}
	
	public static LevelObject getCurrentLevel() {
		return currentLevel;
	}
	
	public static void updateCurrentLevel() {
		currentLevel.update();
	}
	
	public static void renderCurrentLevel(Graphics2D g) {
		currentLevel.render(g);
	}
}
