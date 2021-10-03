package commands;

import java.util.List;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdRemoveShape implements Command {
	private List<Shape> shapes;
	private Shape shape;
	private Model model;

	public CmdRemoveShape(List<Shape> shapes, Model model) {
		this.shapes = shapes;
		this.model = model;
	}
	
	public CmdRemoveShape(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		int i = 0;
		for (Shape shape : shapes) {
			shape.setPositionInList(model.getShapes().indexOf(shape) + i);
			model.removeShape(shape);
			i++;
		}
	}

	@Override
	public void unexecute() {
		for (Shape shape : shapes) {
			model.addShapeOnPosition(shape, shape.getPositionInList());
		}
	}

	@Override
	public String toString() {
		String string = "";
		for (Shape shape : shapes) {
			if (!shape.equals(shapes.get(shapes.size() - 1))) {
				string += shape.toString() + ";";
			} else {
				string += shape.toString();
			}
		}
		return "Remove_" + string;
	}

}
