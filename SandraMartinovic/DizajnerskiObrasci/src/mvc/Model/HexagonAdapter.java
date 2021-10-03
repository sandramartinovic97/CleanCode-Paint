package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends ArealShape implements Serializable {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;

	public HexagonAdapter(Point center, int radius) {
		hexagon = new Hexagon(center.getX(), center.getY(), radius);
	}

	@Override
	public void fill(Graphics graphics) {
	}

	@Override
	public double area() {
		return (3 * Math.sqrt(3) / 2) * Math.pow(hexagon.getR(), 2);
	}

	@Override
	public void drawSelectedSquare(Graphics graphics) {
	}

	@Override
	public boolean contains(Point point) {
		return hexagon.doesContain(point.getX(), point.getY());
	}

	public boolean equals(HexagonAdapter hexagonAdapter) {
		return getX() == hexagonAdapter.getX() && getY() == hexagonAdapter.getY()
				&& getRadius() == hexagonAdapter.getRadius()
				&& getOutlineColor().equals(hexagonAdapter.getOutlineColor())
				&& getInnerColor().equals(hexagonAdapter.getInnerColor());
	}

	@Override
	public void draw(Graphics graphics) {
		hexagon.paint(graphics);

	}

	public int getX() {
		return hexagon.getX();
	}

	public int getY() {
		return hexagon.getY();
	}

	public void setX(int x) {
		hexagon.setX(x);
	}

	public void setY(int y) {
		hexagon.setY(y);
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setInnerColor(Color color) {
		hexagon.setAreaColor(color);
	}

	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	public void setOutlineColor(Color color) {
		hexagon.setBorderColor(color);
	}

	public Color getOutlineColor() {
		return hexagon.getBorderColor();
	}

	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public HexagonAdapter clone() {
		HexagonAdapter hexagonAdapter = new HexagonAdapter(new Point(hexagon.getX(), hexagon.getY()), hexagon.getR());
		hexagonAdapter.setOutlineColor(getOutlineColor());
		hexagonAdapter.setInnerColor(getInnerColor());
		hexagonAdapter.setSelected(isSelected());
		return hexagonAdapter;
	}

	@Override
	public String toString() {
		return "Hexagon:x=" + hexagon.getX() + ",y=" + hexagon.getY() + ",radius=" + hexagon.getR() + ",outline color="
				+ hexagon.getBorderColor().getRGB() + ",inner color=" + hexagon.getAreaColor().getRGB() + ",selected="
				+ isSelected();
	}

}
