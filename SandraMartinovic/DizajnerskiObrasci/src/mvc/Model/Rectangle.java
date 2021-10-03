package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends ArealShape implements Serializable {
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int width;
	private int height;

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, Color color) {
		this(upperLeftPoint, height, width);
		setOutlineColor(color);
	}

	@Override
	public void draw(Graphics graphics) {
		fill(graphics);
		Color outlineColor = getOutlineColor();
		if (outlineColor != null)
			graphics.setColor(outlineColor);
		graphics.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.getHeight());
		graphics.setColor(Color.BLACK);

		if (isSelected()) {
			drawSelectedSquare(graphics);
		}
	}

	@Override
	public void drawSelectedSquare(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
		graphics.drawRect(this.getUpperLeftPoint().getX() - 3 + getWidth(), this.getUpperLeftPoint().getY() - 3, 6, 6);
		graphics.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3 + getHeight(), 6, 6);
		graphics.drawRect(this.getUpperLeftPoint().getX() + getWidth() - 3, this.getUpperLeftPoint().getY() + getHeight() - 3,
				6, 6);
		graphics.setColor(Color.BLACK);
	}

	public boolean contains(Point point) {
		if (this.getUpperLeftPoint().getX() <= point.getX() && point.getX() <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= point.getY()
				&& point.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Rectangle rectangle) {
		return upperLeftPoint.equals(rectangle.getUpperLeftPoint()) && height == rectangle.getHeight()
				&& width == rectangle.getWidth() && getOutlineColor().equals(rectangle.getOutlineColor())
				&& getInnerColor().equals(rectangle.getInnerColor());
	}

	public double area() {
		return width * height;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
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

	@Override
	public void fill(Graphics graphics) {
		Color innerColor = getInnerColor();
		if (innerColor != null) {
			graphics.setColor(innerColor);
			graphics.fillRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.height);
		}
		graphics.setColor(Color.BLACK);
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.setX(x);
		upperLeftPoint.setY(y);
	}

	@Override
	public Rectangle clone() {
		Rectangle rectangle = new Rectangle();
		rectangle.setUpperLeftPoint(new Point(upperLeftPoint.getX(), upperLeftPoint.getY()));
		try {
			rectangle.setHeight(height);
			rectangle.setWidth(width);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rectangle.setOutlineColor(getOutlineColor());
		rectangle.setInnerColor(getInnerColor());
		rectangle.setSelected(isSelected());
		return rectangle;
	}

	public String toString() {
		return "Rectangle:Upper left point-" + upperLeftPoint + ",height=" + height + ",width=" + width
				+ ",outline color=" + getOutlineColor().getRGB() + ",inner color=" + getInnerColor().getRGB()
				+ ",selected=" + isSelected();
	}
}
