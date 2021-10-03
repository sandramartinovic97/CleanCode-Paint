package commands;

import mvc.Model.Model;

public class CmdToBack implements Command {
	private Model model;
	private int indexOfShape;

	public CmdToBack(Model model, int indexOfShape) {
		super();
		this.model = model;
		this.indexOfShape = indexOfShape;
	}

	@Override
	public void execute() {
		model.swap(indexOfShape, indexOfShape - 1);
	}

	@Override
	public void unexecute() {
		model.swap(indexOfShape, indexOfShape - 1);
	}

	@Override
	public String toString() {
		return "Bring back_" + indexOfShape;
	}

}
