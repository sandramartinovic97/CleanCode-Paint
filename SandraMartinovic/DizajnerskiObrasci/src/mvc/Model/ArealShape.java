package mvc.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class ArealShape extends Shape implements Serializable {
	private static final long serialVersionUID = 1L;
	private Color innerColor;

	public abstract void fill(Graphics graphics);

	public abstract double area();

	public ArealShape() {

	}

	public ArealShape(Color innerColor) {
		this.innerColor = innerColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

}
