package commands;

import mvc.Model.Line;
import mvc.Model.Point;

/**
 * It is a good practice to edit values of point's coordinates because some
 * users can edit point coordinate and our line value shouldn't be changed
 * 
 * 
 */

public class CmdUpdateLine implements Command {
	Line oldLine;
	Line newLine;
	Line originalLine;

	public CmdUpdateLine(Line oldLine, Line newLine) {
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		originalLine = oldLine.clone();

		oldLine.getStartPoint().setX(newLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(newLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(newLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(newLine.getEndPoint().getY());
		oldLine.setOutlineColor(newLine.getOutlineColor());
	}

	@Override
	public void unexecute() {
		oldLine.getStartPoint().setX(originalLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(originalLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(originalLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(originalLine.getEndPoint().getY());
		oldLine.setOutlineColor(originalLine.getOutlineColor());
	}

	@Override
	public String toString() {
		return "Update_" + originalLine.toString() + ";" + oldLine.toString();
	}

}
