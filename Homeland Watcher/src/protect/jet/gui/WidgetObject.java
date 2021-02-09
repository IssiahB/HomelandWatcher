package protect.jet.gui;

import java.awt.Font;
import java.awt.Graphics2D;

public abstract class WidgetObject {
	
	protected boolean isClickable = false;
	protected boolean isPressed = false;
	protected int x, y;
	protected int width, height;
	protected String title;
	protected Font font = new Font("TimesNewRoman", Font.BOLD, 10);
	
	public WidgetObject(int x, int y, int width, int height, String title) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void onClick();
	
	public void whenPressed(int mouseX, int mouseY) {
		if (!isClickable || !isClickedOn(mouseX, mouseY))
			return; // if button not pressed on 
		
		isPressed = true;
	}
	
	public void whenReleased(int mouseX, int mouseY) {
		if (!isClickable)
			return;
		
		// only click if on same button and button was already pressed
		if (isClickedOn(mouseX, mouseY) && isPressed) 
			onClick();
		
		isPressed = false; // always set press to false
	}
	
	protected void updateLocation() {
		// TODO make sure always positioned correctly if screen size change
//		x = (Window.getFrame().getWidth() * x) / 100;
//		y = (Window.getFrame().getWidth() * y) /100;
	}
	
	private boolean isClickedOn(int mouseX, int mouseY) {
		boolean clickedOnX = false;
		boolean clickedOnY = false;
		// mouse in width range
		if ((mouseX > x) && (mouseX < (x + width)))
			clickedOnX = true;
		// mouse in height range
		if ((mouseY > y) && (mouseY < (y + height)))
			clickedOnY = true;
		
		return(clickedOnX && clickedOnY);
	}

	public boolean isClickable() {
		return isClickable;
	}

	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
}
