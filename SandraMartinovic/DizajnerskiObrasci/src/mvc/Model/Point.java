package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Serializable {
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		setY(y);
	}

	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}

	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		setOutlineColor(color);
	}

	@Override
	public void draw(Graphics graphics) {
		Color color = getOutlineColor();
		if (color != null)
			graphics.setColor(color);
		else
			graphics.setColor(Color.BLACK);
		graphics.drawLine(this.x - 1, this.y - 1, this.x + 1, this.y + 1);
		graphics.drawLine(this.x - 1, this.y + 1, this.x + 1, this.y - 1);
		graphics.setColor(Color.BLACK);

		if (isSelected()) {
			drawSelectedSquare(graphics);
		}
	}

	@Override
	public void drawSelectedSquare(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(this.x - 3, this.y - 3, 6, 6);
		graphics.setColor(Color.BLACK);
	}

	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y += byY;
	}

	public boolean contains(Point point) {
		return this.calculateDistance(point.getX(), point.getY()) <= 3;
	}

	public double calculateDistance(int xCoordinate, int yCoordinate) {
		double distanceXsquare = this.x - xCoordinate;
		double distanceYsquare  = this.y - yCoordinate;
		return Math.sqrt(distanceXsquare * distanceXsquare + distanceYsquare  * distanceYsquare );
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Point point) {
		if (getOutlineColor() == null) {
			return this.getX() == point.getX() && this.getY() == point.getY();
		} else {
			return this.getX() == point.getX() && this.getY() == point.getY() && point.getOutlineColor().equals(getOutlineColor());
		}
	}

	@Override
	public void moveTo(int x, int y) {
		setX(x);
		setY(y);
	}

	@Override
	public Point clone() {
		Point point = new Point(this.x, this.y, this.getOutlineColor());
		point.setSelected(isSelected());
		return point;
	}

	@Override
	public String toString() {
		String string = "x=" + x + ",y=" + y;
		if (getOutlineColor() != null) {
			string += ",outline color=" + getOutlineColor().getRGB() + ",selected=" + isSelected();
			return "Point:" + string;
		}
		return string;
	}

}
