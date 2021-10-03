package commands;

import java.util.List;

import mvc.Model.Shape;

public class CmdDeselectShape implements Command {
	private List<Shape> shapes;

	public CmdDeselectShape(List<Shape> shapes) {
		this.shapes = shapes;
	}

	@Override
	public void execute() {
		for (Shape shape : shapes) {
			shape.setSelected(false);
		}
	}

	@Override
	public void unexecute() {
		for (Shape shape : shapes) {
			shape.setSelected(true);
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
		return "Deselect_" + string;
	}

}
