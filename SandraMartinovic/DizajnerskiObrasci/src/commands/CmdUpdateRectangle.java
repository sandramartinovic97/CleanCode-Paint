package commands;

import mvc.Model.Rectangle;

public class CmdUpdateRectangle implements Command {
	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle originalRectangle;

	public CmdUpdateRectangle(Rectangle oldRectangle, Rectangle newRectangle) {
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		originalRectangle = oldRectangle.clone();

		oldRectangle.getUpperLeftPoint().setX(newRectangle.getUpperLeftPoint().getX());
		oldRectangle.getUpperLeftPoint().setY(newRectangle.getUpperLeftPoint().getY());
		try {
			oldRectangle.setHeight(newRectangle.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldRectangle.setWidth(newRectangle.getWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldRectangle.setOutlineColor(newRectangle.getOutlineColor());
		oldRectangle.setInnerColor(newRectangle.getInnerColor());
	}

	@Override
	public void unexecute() {
		oldRectangle.getUpperLeftPoint().setX(originalRectangle.getUpperLeftPoint().getX());
		oldRectangle.getUpperLeftPoint().setY(originalRectangle.getUpperLeftPoint().getY());
		try {
			oldRectangle.setHeight(originalRectangle.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldRectangle.setWidth(originalRectangle.getWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldRectangle.setOutlineColor(originalRectangle.getOutlineColor());
		oldRectangle.setInnerColor(originalRectangle.getInnerColor());
	}

	@Override
	public String toString() {
		return "Update_" + originalRectangle.toString() + ";" + oldRectangle.toString();
	}

}
