package protect.jet.gui.widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import protect.jet.gui.WidgetObject;

public class ButtonWidget extends WidgetObject {
	private int buttonMargin = 2;
	private Color backgroundColor = Color.black;
	private Color forgroundColor = Color.green;

	private Rectangle forgroundButton = new Rectangle(x, y, width, height);
	private Rectangle backgroundButton = new Rectangle((x - buttonMargin), (y - buttonMargin),
			(width + (buttonMargin * 2)), (height + (buttonMargin * 2)));

	public ButtonWidget(int x, int y, int width, int height, String title) {
		super(x, y, width, height, title);
		isClickable = true;
		font = new Font("TrebuchetMS", Font.BOLD, 20);
		// Other fonts use "TrebuchetMS"
	}

	@Override
	public void update() {
		updateLocation();
		updateColor();
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(backgroundColor);
		g.fill(backgroundButton);
		
		g.setColor(forgroundColor);
		g.fill(forgroundButton);
		
		g.setColor(backgroundColor);
		g.setFont(font);
		centerTitleInButton(g);
	}

	@Override
	public void onClick() {}
	
	private Color originalColor = forgroundColor;
	private void updateColor() {
		if (isPressed) 
			forgroundColor = originalColor.darker();
		else
			forgroundColor = originalColor;
	}

	private void centerTitleInButton(Graphics2D g) {
		int titleWidth = g.getFontMetrics().stringWidth(title);
		int totalGapSpaceX = (width - titleWidth);
		
		String drawableString = title;
		int extraSpacing = 10;
		// if string is larger than button space
		if (titleWidth > (width - extraSpacing))
			drawableString = "...";

		g.drawString(drawableString, (x + (totalGapSpaceX / 2)),(int) (y + (height / 1.5)));
	}

}
