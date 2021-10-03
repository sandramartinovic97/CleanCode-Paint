package commands;

import mvc.Model.Shape;

public class CmdSelectShape implements Command {
	private Shape shape;

	public CmdSelectShape(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void execute() {
		shape.setSelected(true);
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
	}

	@Override
	public String toString() {
		return "Select_" + shape;
	}

}
