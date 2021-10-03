package commandsExecutors;

import java.awt.event.MouseEvent;
import java.util.Observable;

import commands.CmdAddShape;
import commands.CmdUpdateHexagon;
import mvc.Controller.OptionsController;
import mvc.Model.HexagonAdapter;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Shape;
import mvc.View.DialogHexagon;
import mvc.View.Frame;

public class HexagonCommandsExecutor extends Observable {
	private OptionsController optionsController;
	private DialogHexagon dialogHexagon;
	private Model model;
	private Frame frame;

	public HexagonCommandsExecutor(DialogHexagon dialogHexagon, OptionsController optionsController) {
		this.dialogHexagon = dialogHexagon;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		frame = optionsController.getFrame();
	}
	
	public void addHexagon(MouseEvent e) {
		Point center = new Point(e.getX(), e.getY());
		DialogHexagon dialogHexagon = new DialogHexagon();
		dialogHexagon.setTxtCoordinateX(Integer.toString(center.getX()));
		dialogHexagon.setTxtCoordinateY(Integer.toString(center.getY()));
		dialogHexagon.setTxtCoordinateXEditable(false);
		dialogHexagon.setTxtCoordinateYEditable(false);
		dialogHexagon.setPnlInnerColor(optionsController.getInnerColor());
		dialogHexagon.setPnlOutlineColor(optionsController.getOutlineColor());
		dialogHexagon.setVisible(true);
		if (dialogHexagon.isOk()) {
			HexagonAdapter hexagon = new HexagonAdapter(center, Integer.parseInt(dialogHexagon.getTxtRadius()));
			hexagon.setInnerColor(dialogHexagon.getPnlInnerColor());
			hexagon.setOutlineColor(dialogHexagon.getPnlOutlineColor());
			optionsController.setOutlineColor(dialogHexagon.getPnlOutlineColor());
			optionsController.setInnerColor(dialogHexagon.getPnlInnerColor());
			CmdAddShape cmdAddShape = new CmdAddShape(hexagon, model);
			optionsController.commandExecuteHelper(cmdAddShape);
			optionsController.deselectAll();
		}
		
		frame.getView().repaint();
		sendChanges();
	}
	
	public void updateHexagon() {
		Shape selectedShape = optionsController.getSelectedShapes().get(0);
		HexagonAdapter newHexagon;
		DialogHexagon dialogHexagon = new DialogHexagon();
		dialogHexagon.setTxtCoordinateX(Integer.toString(((HexagonAdapter) selectedShape).getX()));
		dialogHexagon.setTxtCoordinateY(Integer.toString(((HexagonAdapter) selectedShape).getY()));
		dialogHexagon.setTxtRadius(Integer.toString(((HexagonAdapter) selectedShape).getRadius()));
		dialogHexagon.setPnlInnerColor(((HexagonAdapter) selectedShape).getInnerColor());
		dialogHexagon.setPnlOutlineColor(((HexagonAdapter) selectedShape).getOutlineColor());
		dialogHexagon.setVisible(true);
		if (dialogHexagon.isOk()) {
			int coordX = Integer.parseInt(dialogHexagon.getTxtCoordinateX());
			int coordY = Integer.parseInt(dialogHexagon.getTxtCoordinateY());
			int radius = Integer.parseInt(dialogHexagon.getTxtRadius());
			newHexagon = new HexagonAdapter(new Point(coordX, coordY), radius);
			newHexagon.setInnerColor(dialogHexagon.getPnlInnerColor());
			newHexagon.setOutlineColor(dialogHexagon.getPnlOutlineColor());
			optionsController.setInnerColor(dialogHexagon.getPnlOutlineColor());
			optionsController.setOutlineColor(dialogHexagon.getPnlInnerColor());
			CmdUpdateHexagon cmdUpdateHexagon = new CmdUpdateHexagon((HexagonAdapter) selectedShape, newHexagon);
			optionsController.commandExecuteHelper(cmdUpdateHexagon);
		}
		this.frame.getView().repaint();
	}
	
	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
}
