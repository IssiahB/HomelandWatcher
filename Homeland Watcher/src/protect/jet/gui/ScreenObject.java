package protect.jet.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.jarzarr.code.controls.MouseControl;
import org.jarzarr.code.controls.MouseInterface;
import org.jarzarr.code.window.Window;

import protect.jet.Main;
import protect.jet.Main.GameState;

public abstract class ScreenObject implements MouseInterface {
	protected List<WidgetObject> widgets = new ArrayList<WidgetObject>();

	protected BufferedImage backgroundImage = null;
	protected Color backgroundColor = Color.black;

	public ScreenObject(BufferedImage background) {
		this.backgroundImage = background;
		MouseControl.addMouse(this);
	}

	public ScreenObject(Color background) {
		this.backgroundColor = background;
		MouseControl.addMouse(this);
	}

	public abstract void setup();

	public void update() {
		widgets.parallelStream().forEach(widget -> {
			widget.update();
		});
	}

	public void render(Graphics2D g) {
		if (backgroundImage == null) {
			g.setColor(backgroundColor);
			g.fillRect(0, 0, Window.getFrame().getWidth(),
					Window.getFrame().getHeight());
		} else
			g.drawImage(backgroundImage, 0, 0, Window.getCanvas().getWidth(),
					Window.getCanvas().getHeight(), null);
		
		widgets.stream().forEach(obj -> {
			obj.render(g);
		});
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Main.getCurrentState() == GameState.game)
			return;
		if (e.getButton() == 1)
			widgets.parallelStream().forEach(widget -> {
				widget.whenPressed(e.getX(), e.getY());
			});
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Main.getCurrentState() == GameState.game)
			return;

		widgets.parallelStream().forEach(widget -> {
			widget.whenReleased(e.getX(), e.getY());
		});
	}
}
