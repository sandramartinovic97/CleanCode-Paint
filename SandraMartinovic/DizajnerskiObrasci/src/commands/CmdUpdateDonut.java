package commands;

import mvc.Model.Donut;

public class CmdUpdateDonut implements Command {
	Donut oldDonut;
	Donut newDonut;
	Donut originalDonut;

	public CmdUpdateDonut(Donut oldDonut, Donut newDonut) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		originalDonut = oldDonut.clone();

		oldDonut.getCenter().setX(newDonut.getCenter().getX());
		oldDonut.getCenter().setY(newDonut.getCenter().getY());
		try {
			oldDonut.setRadius(newDonut.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldDonut.setInnerRadius(newDonut.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldDonut.setInnerColor(newDonut.getInnerColor());
		oldDonut.setOutlineColor(newDonut.getOutlineColor());
	}

	@Override
	public void unexecute() {
		oldDonut.getCenter().setX(originalDonut.getCenter().getX());
		oldDonut.getCenter().setY(originalDonut.getCenter().getY());
		try {
			oldDonut.setRadius(originalDonut.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldDonut.setInnerRadius(originalDonut.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldDonut.setInnerColor(originalDonut.getInnerColor());
		oldDonut.setOutlineColor(originalDonut.getOutlineColor());
	}

	@Override
	public String toString() {
		return "Update_" + originalDonut.toString() + ";" + oldDonut.toString();
	}

}
