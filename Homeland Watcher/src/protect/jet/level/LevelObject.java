package protect.jet.level;

import java.awt.Font;
import java.awt.Graphics2D;

import protect.jet.handle.ObjectHandler;
import protect.jet.object.enemy.EnemyFormation;
import protect.jet.object.player.Player;

public abstract class LevelObject {
	private int levelLives = 3;
	
	protected int currentWave = 1;
	protected EnemyFormation currentFormation = null;
	protected boolean levelOver = false;
	protected Player player;
	
	protected Font textFont = new Font("TimesNewRoman", Font.BOLD, 30);
	
	public LevelObject() {
		ObjectHandler.add((this.player = new Player()));
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	
	public void removeLevelLife() {
		if (levelLives <= 1) {
			levelOver = true;
			levelLives = 0;
		}
		
		levelLives--;
	}
	
	public int getLevelLives() {
		return levelLives;
	}

	public boolean isLevelOver() {
		return levelOver;
	}

	public void setLevelOver(boolean levelOver) {
		this.levelOver = levelOver;
	}

	public EnemyFormation getCurrentFormation() {
		return currentFormation;
	}

	public void setCurrentFormation(EnemyFormation currentFormation) {
		this.currentFormation = currentFormation;
	}

	public Player getPlayer() {
		return player;
	}
}
