package commands;

import mvc.Model.HexagonAdapter;

public class CmdUpdateHexagon implements Command {
	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter originalHexagon;

	public CmdUpdateHexagon(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {
		originalHexagon = oldHexagon.clone();

		oldHexagon.setX(newHexagon.getX());
		oldHexagon.setY(newHexagon.getY());
		oldHexagon.setRadius(newHexagon.getRadius());
		oldHexagon.setInnerColor(newHexagon.getInnerColor());
		oldHexagon.setOutlineColor(newHexagon.getOutlineColor());
	}

	@Override
	public void unexecute() {
		oldHexagon.setX(originalHexagon.getX());
		oldHexagon.setY(originalHexagon.getY());
		oldHexagon.setRadius(originalHexagon.getRadius());
		oldHexagon.setInnerColor(originalHexagon.getInnerColor());
		oldHexagon.setOutlineColor(originalHexagon.getOutlineColor());
	}

	@Override
	public String toString() {
		return "Update_" + originalHexagon.toString() + ";" + oldHexagon.toString();
	}

}
