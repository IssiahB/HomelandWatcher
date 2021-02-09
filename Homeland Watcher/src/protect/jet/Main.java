package protect.jet;

import java.awt.Graphics2D;

import org.jarzarr.code.EngineLoop;
import org.jarzarr.code.EngineLoopInterface;
import org.jarzarr.code.controls.KeyboardControl;
import org.jarzarr.code.window.Window;

import protect.jet.audio.GameMusic;
import protect.jet.audio.GameMusic.SongType;
import protect.jet.audio.GameSoundEffects;
import protect.jet.graphic.Backgrounds;
import protect.jet.graphic.EnemyGraphics;
import protect.jet.graphic.PlayerGraphics;
import protect.jet.gui.screen.PauseScreen;
import protect.jet.handle.LevelHandler;
import protect.jet.handle.MenuHandler;
import protect.jet.handle.ObjectHandler;
import protect.jet.object.BulletObject;

public class Main implements EngineLoopInterface {

	private static GameState currentState = GameState.menu;

	public static enum GameState {
		menu, game, pause, gameover;
	}

	public Main() {
		Window.createDecoratedWindow("Homeland Watcher",
				PlayerGraphics.getPlayerShips()[1]);
		EngineLoop loop = new EngineLoop(this);
		loop.start();
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void cleanup() {
		GameSoundEffects.cleanup();
	}

	@Override
	public void init() {
		MenuHandler.setup();
		Backgrounds.setup();
		EnemyGraphics.setup();
		PlayerGraphics.setup();

		GameMusic.setSong(SongType.MenuMusic, true);
		GameMusic.playSong();
	}

	@Override
	public void render(Graphics2D g) {
		// background
		g.drawImage(Backgrounds.getBackground(3, 5), 0, 0,
				Window.getFrame().getWidth(), Window.getFrame().getHeight(),
				null);

		if (currentState == GameState.game || currentState == GameState.pause) {
			ObjectHandler.renderObjects(g);
			BulletObject.renderAll(g);
			LevelHandler.renderCurrentLevel(g);
			if (currentState == GameState.pause) {
				MenuHandler.getCurrentMenu().render(g);
			}
		}

		if (currentState == GameState.menu) {
			MenuHandler.getCurrentMenu().render(g);
		}
	}

	@Override
	public void update() {
		if (currentState == GameState.game) {
			ObjectHandler.updateObjects();
			BulletObject.updateAll();
			LevelHandler.updateCurrentLevel();
			if (KeyboardControl.escKey) {
				setCurrentState(GameState.pause);
				MenuHandler.setCurrentMenu(MenuHandler.getScreenFromList(MenuHandler.PauseMenuType));
			}
		}
		
		if (currentState == GameState.pause) {
			MenuHandler.getCurrentMenu().update();
		}

		if (currentState == GameState.menu) {
			MenuHandler.getCurrentMenu().update();
		}
	}

	public static GameState getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(GameState currentState) {
		Main.currentState = currentState;
	}
}
