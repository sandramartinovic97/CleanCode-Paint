package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Line extends Shape implements Serializable {
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}

	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		setOutlineColor(color);
	}

	@Override
	public void draw(Graphics graphics) {
		if (getOutlineColor() != null)
			graphics.setColor(getOutlineColor());
		else
			graphics.setColor(Color.BLACK);
		graphics.drawLine(this.getStartPoint().getX(), getStartPoint().getY(), this.getEndPoint().getX(),
				this.getEndPoint().getY());
		graphics.setColor(Color.BLACK);

		if (isSelected()) {
			drawSelectedSquare(graphics);
		}
	}

	@Override
	public void drawSelectedSquare(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(getStartPoint().getX() - 3, getStartPoint().getY() - 3, 6, 6);
		graphics.drawRect(getEndPoint().getX() - 3, getEndPoint().getY() - 3, 6, 6);
		graphics.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		graphics.setColor(Color.BLACK);
	}

	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
		int middleByY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
		Point point = new Point(middleByX, middleByY);
		return point;
	}

	public boolean contains(Point point) {
		if ((startPoint.calculateDistance(point.getX(), point.getY()) + endPoint.calculateDistance(point.getX(), point.getY())) - length() <= 0.05)
			return true;
		return false;
	}

	public boolean equals(Shape shape) {
		Line line = (Line) shape;
		return line.startPoint.equals(startPoint) && line.endPoint.equals(endPoint)
				&& line.getOutlineColor().equals(getOutlineColor());
	}

	public double length() {
		return startPoint.calculateDistance(endPoint.getX(), endPoint.getY());
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public void moveTo(int x, int y) {
		startPoint.moveTo(x, y);
		endPoint.moveTo(x - endPoint.getX(), y - endPoint.getY());
	}

	@Override
	public Line clone() {
		Line line = new Line(new Point(startPoint.clone().getX(), startPoint.getY()),
				new Point(endPoint.getX(), endPoint.getY()), getOutlineColor());
		line.setSelected(isSelected());
		return line;
	}

	public String toString() {
		return "Line:startPoint-" + startPoint + ",endPoint-" + endPoint + ",outline color="
				+ getOutlineColor().getRGB() + ",selected=" + isSelected();
	}

}
