package mvc.View;

import java.awt.Graphics;

import javax.swing.JPanel;
import mvc.Model.Model;
import mvc.Model.Shape;

import java.util.ListIterator;

public class View extends JPanel {
	private static final long serialVersionUID = 1L;
	private Model model = new Model();

	public void paint(Graphics graphics) {
		super.paint(graphics);
		ListIterator<Shape> shapes = model.getShapes().listIterator();
		while (shapes.hasNext()) {
			shapes.next().draw(graphics);
		}
	}

	public void setModel(Model Model) {
		this.model = Model;
	}

}
