package protect.jet.gui.screen;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.jarzarr.code.window.Window;

import protect.jet.Main;
import protect.jet.Main.GameState;
import protect.jet.audio.GameMusic;
import protect.jet.audio.GameMusic.SongType;
import protect.jet.gui.ScreenObject;
import protect.jet.gui.widget.ButtonWidget;
import protect.jet.gui.widget.TextWidget;
import protect.jet.handle.LevelHandler;
import protect.jet.level.levels.LevelEndless;

public class MainScreen extends ScreenObject {

	public MainScreen(BufferedImage background) {
		super(background);
	}

	public MainScreen(Color color) {
		super(color);
	}

	@Override
	public void setup() {
		TextWidget gameTitle = new TextWidget(
				(Window.getFrame().getWidth() / 2) - 200, 150,
				"Homeland  Watcher", Color.red);

		ButtonWidget endlessBtn = new ButtonWidget(100, 250, 200, 40,
				"Endless Mode") {
			public void onClick() {
				LevelHandler.setCurrentLevel(new LevelEndless());
				GameMusic.playSong(SongType.BasicBattle);
				Main.setCurrentState(GameState.game);
			}
		};

		ButtonWidget levelSelectBtn = new ButtonWidget(100, 350, 200, 40,
				"Level Select") {
			public void onClick() {
				
			}
		};

		ButtonWidget shopBtn = new ButtonWidget(100, 450, 200, 40, "Shop") {
			public void onClick() {
				
			}
		};
		
		ButtonWidget exitBtn = new ButtonWidget(100, 550, 200, 40, "Exit") {
			public void onClick() {
				
			}
		};

		widgets.add(gameTitle);
		widgets.add(endlessBtn);
		widgets.add(levelSelectBtn);
		widgets.add(shopBtn);
		widgets.add(exitBtn);
	}

}
