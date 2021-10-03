package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends ArealShape implements Serializable {
	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;

	public Circle() {

	}

	public Circle(Point center, int radius) {
		setCenter(center);
		setRadius(radius);
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}
	
	public Circle(Point center, int radius, Color outlineColor, Color innerColor) {
		this(center, radius);
		setOutlineColor(outlineColor);
		setInnerColor(innerColor);
	}

	public Circle(Point center, int radius, boolean selected, Color outlineColor, Color innerColor) {
		this(center, radius, selected);
		setOutlineColor(outlineColor);
		setInnerColor(innerColor);
	}

	@Override
	public void draw(Graphics graphics) {
		fill(graphics);
		Color outlineColor = getOutlineColor();

		if (outlineColor != null)
			graphics.setColor(outlineColor);
		else
			graphics.setColor(Color.BLACK);
		graphics.drawOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius() * 2,
				this.getRadius() * 2);
		graphics.setColor(Color.BLACK);

		if (isSelected()) {
			drawSelectedSquare(graphics);
		}
	}

	@Override
	public void drawSelectedSquare(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
		graphics.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
		graphics.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
		graphics.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
		graphics.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
		graphics.setColor(Color.BLACK);
	}

	public boolean contains(Point point) {
		return center.calculateDistance(point.getX(), point.getY()) <= radius;
	}

	public boolean equals(Circle circle) {
		return center.equals(circle.getCenter()) && radius == circle.getRadius()
				&& getOutlineColor().equals(circle.getOutlineColor()) && getInnerColor().equals(circle.getInnerColor());
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void fill(Graphics graphics) {
		Color innerColor = getInnerColor();
		if (innerColor != null) {
			graphics.setColor(innerColor);
			graphics.fillOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);
		}
		graphics.setColor(Color.BLACK);

	}

	@Override
	public double area() {
		return radius * radius * Math.PI;
	}

	@Override
	public void moveTo(int x, int y) {
		center.setX(x);
		center.setY(y);
	}

	@Override
	public Circle clone() {
		Circle circle = new Circle();
		circle.setCenter(new Point(getCenter().getX(), getCenter().getY()));
		try {
			circle.setRadius(radius);
		} catch (Exception e) {
			e.printStackTrace();
		}
		circle.setOutlineColor(getOutlineColor());
		circle.setInnerColor(getInnerColor());
		circle.setSelected(isSelected());
		return circle;
	}

	public String toString() {
		return "Circle:Center-" + center + ", radius=" + radius + ",outline color=" + getOutlineColor().getRGB()
				+ ",inner color=" + getInnerColor().getRGB() + ",selected=" + isSelected();
	}

}
