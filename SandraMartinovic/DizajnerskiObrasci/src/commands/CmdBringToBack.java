package commands;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdBringToBack implements Command {
	private Model model;
	private Shape shape;
	private int currentIndex;

	public CmdBringToBack(Model model, int index) {
		super();
		this.model = model;
		this.currentIndex = index;
	}

	@Override
	public void execute() {
		shape = model.getIndexOfShape(currentIndex);
		model.getShapes().remove(shape);
		model.getShapes().add(0, shape);
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
		model.addShapeOnPosition(shape, currentIndex);
	}

	@Override
	public String toString() {
		return "Bring to end_" + currentIndex;
	}

}
