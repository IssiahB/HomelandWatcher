package protect.jet.handle;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.jarzarr.code.window.Window;

import protect.jet.object.GameObject;
import protect.jet.object.enemy.EnemyFormation;

public class ObjectHandler {
	
	private static List<GameObject> objectList = new ArrayList<GameObject>();
	
	public static void updateObjects() {
		for (GameObject obj: objectList) {
			if (objectList.contains(obj))
				if (obj.isDestroyed()) {
					objectList.remove(obj);
					break;
				} else 
					obj.update();
		}
	}
	
	public static void renderObjects(Graphics2D g) {
		objectList.stream().forEach(object -> {
			if (objectList.contains(object))
				if (isObjectOnScreen(object))
					object.render(g);
		});
	}
	
	public static boolean add(GameObject obj) {
		if (obj == null)
			return false;
		
		objectList.add(obj);
		return true;
	}
	
	public static boolean add(EnemyFormation form) {
		if (form == null || form.getFormattedEnemies() == null)
			return false;
		
		form.getFormattedEnemies().parallelStream().forEach(enemy -> {
			objectList.add(enemy);
		});
		return true;
	}
	
	public static boolean remove(GameObject obj) {
		if (obj == null)
			return false;
		if (!objectList.contains(obj))
			return false;
		
		objectList.remove(obj);
		return true;
	}
	
	public static void removeAll() {
		objectList.clear();
	}
	
	public static boolean contains(GameObject obj) {
		return (objectList.contains(obj));
	}
	
	public static boolean isObjectOnScreen(GameObject obj) {
		boolean onScreen = true;
		int x = obj.getX(), y = obj.getY();
		int width = obj.getWidth(), height = obj.getHeight();
		// is within screen width
		if (((x + width) < 0)/* left */ || (x > Window.getCanvas().getWidth()) /* right */)
			onScreen = false;
		// is within screen height
		if (((y + height) < 0)/* top */ || (y > Window.getCanvas().getHeight()) /* bottom */)
			onScreen = false;
		
		return onScreen;
	}
}
