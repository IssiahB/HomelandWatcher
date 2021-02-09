package protect.jet.level.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import org.jarzarr.code.window.Window;

import protect.jet.handle.ObjectHandler;
import protect.jet.level.LevelObject;
import protect.jet.object.enemy.EnemyFormation;
import protect.jet.object.player.Player;

public class LevelEndless extends LevelObject {

	public LevelEndless() {
		currentFormation = new EnemyFormation(5, 5, 30, 10);
		ObjectHandler.add(currentFormation);
	}

	@Override
	public void update() {
		updateFormation();
	}

	@Override
	public void render(Graphics2D g) {
		renderLevelLives(g);
		renderPlayerPoints(g);
		renderLevelTemp(g);
	}

	private int levelTextIterator = 0;
	private int levelTextTime = 180; // every 3 seconds
	private boolean hasLevelBeenRendered = false;
	private Font levelTextFont = new Font("TimesNewRoman", Font.BOLD, 60);

	private void renderLevelTemp(Graphics2D g) {
		if (!hasLevelBeenRendered)
			if (levelTextIterator >= levelTextTime) {
				levelTextIterator = 0;
				hasLevelBeenRendered = true;
			} else {
				levelTextIterator++;
				g.setFont(levelTextFont);
				g.setColor(Color.red);
				g.drawString("Wave " + currentWave,
						(Window.getFrame().getWidth() / 2) - 100,
						Window.getFrame().getHeight() / 2);
			}
	}

	private void renderPlayerPoints(Graphics2D g) {
		g.setFont(textFont);
		g.setColor(Color.red);
		g.drawString("Points: " + Player.getPoints(),
				Window.getFrame().getWidth() - 200, 30);
	}

	private void renderLevelLives(Graphics2D g) {
		g.setFont(textFont);
		g.setColor(Color.red);

		String levelLives = "";
		for (int i = 0; i < getLevelLives(); i++) {
			levelLives = levelLives + " <3 ";
		}
		g.drawString(levelLives, 30, 30);
	}

	private void updateFormation() {
		if (currentFormation.getNumAliveEnemies() < 1) {
			currentWave++;
			hasLevelBeenRendered = false;

			currentFormation = new EnemyFormation(5, 5, 30, 10);
			currentFormation.getFormattedEnemies().stream().forEach(enemy -> {
				enemy.setShotSpeed(
						enemy.getShotSpeed() / (1 + ((currentWave * 2) / 10)));
				enemy.setSpeedCount(
						enemy.getSpeedCount() / (1 + ((currentWave * 2) / 10)));
			});
			ObjectHandler.add(currentFormation);
		}

	}
}
