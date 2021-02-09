package protect.jet.gui.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jarzarr.code.window.Window;

import protect.jet.Main;
import protect.jet.Main.GameState;
import protect.jet.audio.GameMusic;
import protect.jet.audio.GameMusic.SongType;
import protect.jet.gui.ScreenObject;
import protect.jet.gui.widget.ButtonWidget;
import protect.jet.handle.MenuHandler;
import protect.jet.handle.ObjectHandler;

public class PauseScreen extends ScreenObject {

	public PauseScreen() {
		super(Color.black);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(Graphics2D g) {
		widgets.stream().forEach(obj -> {
			obj.render(g);
		});
	}

	@Override
	public void setup() {
		ButtonWidget resumeBtn = new ButtonWidget((Window.getFrame().getWidth()/2)-100, 200, 200, 40, "Resume") {
			public void onClick() {
				Main.setCurrentState(GameState.game);
			}
		};
		
		ButtonWidget exitBtn = new ButtonWidget((Window.getFrame().getWidth()/2)-100, 400, 200, 40, "Exit") {
			public void onClick() {
				MenuHandler.setCurrentMenu(MenuHandler.getScreenFromList(MenuHandler.MainMenuType));
				Main.setCurrentState(GameState.menu);
				ObjectHandler.removeAll();
				GameMusic.setSong(SongType.MenuMusic, true);
				GameMusic.playSong();
			}
		};
		
		widgets.add(resumeBtn);
		widgets.add(exitBtn);
	}

}
