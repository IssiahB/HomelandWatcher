package protect.jet.object.enemy;

import java.util.ArrayList;
import java.util.List;

import org.jarzarr.code.window.Window;

import protect.jet.handle.ObjectHandler;

public class EnemyFormation {
	private int row = 0, colm = 0;
	private int rowSpacing = 0, colmSpacing = 0;
	private int numOfAlive;
	
	private List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
	
	public EnemyFormation(int row, int colm, int rowSpacing, int colmSpacing) {
		this.row = row;
		this.colm = colm;
		this.rowSpacing = (Window.getFrame().getHeight() * rowSpacing) / 100;
		this.colmSpacing = (Window.getFrame().getWidth() * colmSpacing) / 100;
		numOfAlive = (row * colm);
		createFormation();
	}

	private void createFormation() {
		for (int y = -1; y > (-row - 1); y--) {
			for (int x = 1; x < (colm + 1); x++) {
				BasicEnemy enemy = new BasicEnemy((x * rowSpacing), (y * colmSpacing));
				enemies.add(enemy);
			}
		}
	}

	public List<BasicEnemy> getFormattedEnemies() {
		if (row == 0 || colm == 0)
			return null;

		return enemies;
	}
	
	public int getNumAliveEnemies() {
		numOfAlive = 0;
		enemies.stream().forEach(enemy -> {
			if (ObjectHandler.contains(enemy))
				numOfAlive++;
		});
		
		return numOfAlive;
	}
}
