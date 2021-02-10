package protect.jet.gui.widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import protect.jet.gui.WidgetObject;

public class TextWidget extends WidgetObject {
	private Color color;
	
	public TextWidget(int x, int y, String title, Color color) {
		super(x, y, 0, 0, title);
		this.color = color;
		font = new Font("TimesNewRoman", Font.BOLD, 40);
		isClickable = false;
	}

	@Override
	public void update() {}

	@Override
	public void render(Graphics2D g) {
		g.setFont(font);
		g.setColor(this.color);
		g.drawString(title, x, y);
	}

	@Override
	public void onClick() {}
}
