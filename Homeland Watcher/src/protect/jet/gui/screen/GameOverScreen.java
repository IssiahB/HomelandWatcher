package protect.jet.gui.screen;

import java.awt.Color;

import org.jarzarr.code.window.Window;

import protect.jet.Main;
import protect.jet.Main.GameState;
import protect.jet.audio.GameMusic;
import protect.jet.audio.GameMusic.SongType;
import protect.jet.gui.ScreenObject;
import protect.jet.gui.widget.ButtonWidget;
import protect.jet.gui.widget.TextWidget;
import protect.jet.handle.MenuHandler;
import protect.jet.handle.ObjectHandler;

public class GameOverScreen extends ScreenObject {

	public GameOverScreen() {
		super(Color.black);
	}

	@Override
	public void setup() {
		TextWidget gameOverTitle = new TextWidget((Window.getFrame().getWidth()/2)-100, 200, "Game Over", Color.red);
		
		ButtonWidget shopBtn = new ButtonWidget((Window.getFrame().getWidth()/2)-100, 300, 200, 40, "Shop") {
			public void onClick() {
				
			}
		};
		
		ButtonWidget exitBtn = new ButtonWidget((Window.getFrame().getWidth()/2)-100, 400, 200, 40, "Exit") {
			public void onClick() {
				MenuHandler.setCurrentMenu(MenuHandler.getScreenFromList(MenuHandler.MainMenuType));
				Main.setCurrentState(GameState.menu);
				ObjectHandler.removeAll();
				GameMusic.playSong(SongType.MenuMusic);
			}
		};
		
		widgets.add(gameOverTitle);
		widgets.add(shopBtn);
		widgets.add(exitBtn);
	}
	
}
