package commandsExecutors;

import java.awt.event.MouseEvent;
import java.util.Observable;

import commands.CmdAddShape;
import commands.CmdUpdatePoint;
import mvc.Controller.OptionsController;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Shape;
import mvc.View.DialogPoint;
import mvc.View.Frame;

public class PointCommandsExecutor extends Observable {
	private OptionsController optionsController;
	private DialogPoint dialogPoint;
	private Model model;
	private Frame frame;
		
	public PointCommandsExecutor(DialogPoint dialogPoint, OptionsController optionsController) {
		this.dialogPoint = dialogPoint;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		frame = optionsController.getFrame();
	}
	
	public void addPoint(MouseEvent e) {
		Point newPoint = new Point(e.getX(), e.getY());
		newPoint.setOutlineColor(optionsController.getOutlineColor());
		CmdAddShape cmdAddShape = new CmdAddShape(newPoint, model);
		optionsController.commandExecuteHelper(cmdAddShape);
		optionsController.deselectAll();
		
		frame.getView().repaint();
		sendChanges();
	}
	
	public void updatePoint() {
		Shape selectedShape = optionsController.getSelectedShapes().get(0);
		Point newPoint;
		DialogPoint dialogPoint = new DialogPoint();
		dialogPoint.setTbX(Integer.toString(((Point) selectedShape).getX()));
		dialogPoint.setTxtY(Integer.toString(((Point) selectedShape).getY()));
		dialogPoint.setPnlColor(((Point) selectedShape).getOutlineColor());
		dialogPoint.setVisible(true);
		if (dialogPoint.isOk()) {
			newPoint = new Point();
			newPoint.setX(Integer.parseInt(dialogPoint.getTbX()));
			newPoint.setY(Integer.parseInt(dialogPoint.getTxtY()));
			newPoint.setOutlineColor(dialogPoint.getPnlColor());
			optionsController.setOutlineColor(dialogPoint.getPnlColor());

			CmdUpdatePoint cmdUpdatePoint = new CmdUpdatePoint((Point) selectedShape, newPoint);
			optionsController.commandExecuteHelper(cmdUpdatePoint);
		}
		this.frame.getView().repaint();
	}
	
	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
}
