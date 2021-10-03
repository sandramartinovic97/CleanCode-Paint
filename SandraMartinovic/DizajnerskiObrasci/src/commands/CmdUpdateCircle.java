package commands;

import mvc.Model.Circle;

public class CmdUpdateCircle implements Command {
	Circle oldCircle;
	Circle newCircle;
	Circle originalCircle;

	public CmdUpdateCircle(Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		originalCircle = oldCircle.clone();

		oldCircle.getCenter().setX(newCircle.getCenter().getX());
		oldCircle.getCenter().setY(newCircle.getCenter().getY());
		try {
			oldCircle.setRadius(newCircle.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldCircle.setInnerColor(newCircle.getInnerColor());
		oldCircle.setOutlineColor(newCircle.getOutlineColor());
	}

	@Override
	public void unexecute() {
		oldCircle.getCenter().setX(originalCircle.getCenter().getX());
		oldCircle.getCenter().setY(originalCircle.getCenter().getY());
		try {
			oldCircle.setRadius(originalCircle.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldCircle.setInnerColor(originalCircle.getInnerColor());
		oldCircle.setOutlineColor(originalCircle.getOutlineColor());
	}

	@Override
	public String toString() {
		return "Update_" + originalCircle.toString() + ";" + newCircle.toString();
	}

}
