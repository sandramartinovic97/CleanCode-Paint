package commands;

import mvc.Model.Model;
import mvc.Model.Shape;

public class CmdBringToFront implements Command {
	private Model model;
	private Shape shape;
	private int currentIndex;

	public CmdBringToFront(Model model, int currentIndex) {
		super();
		this.model = model;
		this.currentIndex = currentIndex;
	}

	@Override
	public void execute() {
		this.shape = model.getIndexOfShape(currentIndex);
		model.removeShape(shape);
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
		model.addShapeOnPosition(shape, currentIndex);
	}

	@Override
	public String toString() {
		return "Bring to front_" + currentIndex;
	}

}
