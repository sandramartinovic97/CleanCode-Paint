package commands;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdAddShape implements Command {
	private Shape shape;
	private Model model;

	public CmdAddShape(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
	}

	@Override
	public String toString() {
		return "Add_" + shape.toString();
	}

}
