package protect.jet.gui.screen;

import java.awt.Color;
import java.awt.image.BufferedImage;

import protect.jet.Main;
import protect.jet.Main.GameState;
import protect.jet.audio.GameMusic;
import protect.jet.audio.GameMusic.SongType;
import protect.jet.gui.ScreenObject;
import protect.jet.gui.widget.ButtonWidget;
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
		ButtonWidget endlessBtn = new ButtonWidget(100, 300, 200, 40, "Endless Mode") {
			public void onClick() {
				LevelHandler.setCurrentLevel(new LevelEndless());
				GameMusic.setSong(SongType.BasicBattle, true);
				GameMusic.playSong();
				Main.setCurrentState(GameState.game);
		}
		};
		
		ButtonWidget levelSelectBtn = new ButtonWidget(100, 400, 200, 40, "Level Select") {
			public void onClick() {
					LevelHandler.setCurrentLevel(new LevelEndless());
					GameMusic.setSong(SongType.BasicBattle, true);
					GameMusic.playSong();
					Main.setCurrentState(GameState.game);
			}
		};
		
		widgets.add(endlessBtn);
		widgets.add(levelSelectBtn);
	}

}
