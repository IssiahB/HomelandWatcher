package protect.jet.handle;

import java.util.ArrayList;
import java.util.List;

import protect.jet.graphic.Image;
import protect.jet.gui.ScreenObject;
import protect.jet.gui.screen.GameOverScreen;
import protect.jet.gui.screen.MainScreen;
import protect.jet.gui.screen.PauseScreen;

public class MenuHandler {
	private static List<ScreenObject> screens = new ArrayList<ScreenObject>();
	private static ScreenObject currentMenu = null;
	
	public static final int MainMenuType = 0;
	public static final int PauseMenuType = 1;
	public static final int OverMenuType = 2;
	
	private static void loadMenus() {
		currentMenu = new MainScreen(Image.getImage("res/preview/gamePreview.png"));
		screens.add(currentMenu);
		screens.add(new PauseScreen());
		screens.add(new GameOverScreen());
	}
	
	public static void setup() {
		loadMenus();
		screens.parallelStream().forEach(screen -> {
			screen.setup();
		});
	}
	
	public static ScreenObject getScreenFromList(int screenType) {
		return (screens.get(screenType));
	}

	public static ScreenObject getCurrentMenu() {
		return currentMenu;
	}

	public static void setCurrentMenu(ScreenObject currentMenu) {
		MenuHandler.currentMenu = currentMenu;
	}
}
