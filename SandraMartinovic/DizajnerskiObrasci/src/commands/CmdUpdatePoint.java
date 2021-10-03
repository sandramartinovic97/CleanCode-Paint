package commands;

import mvc.Model.Point;

public class CmdUpdatePoint implements Command {
	Point oldPoint;
	Point newPoint;
	Point originalPoint;

	public CmdUpdatePoint(Point oldPoint, Point newPoint) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
	}

	@Override
	public void execute() {
		originalPoint = oldPoint.clone();
		oldPoint.moveTo(newPoint.getX(), newPoint.getY());
		oldPoint.setOutlineColor(newPoint.getOutlineColor());
	}

	@Override
	public void unexecute() {
		oldPoint.moveTo(originalPoint.getX(), originalPoint.getY());
		oldPoint.setOutlineColor(originalPoint.getOutlineColor());
	}

	@Override
	public String toString() {
		return "Update_" + originalPoint.toString() + ";" + oldPoint.toString();
	}

}
