package mvc.Controller;

import java.awt.event.MouseEvent;
import java.util.Observable;

import commandsExecutors.CircleCommandsExecutor;
import commandsExecutors.DonutCommandsExecutor;
import commandsExecutors.HexagonCommandsExecutor;
import commandsExecutors.LineCommandsExecutor;
import commandsExecutors.PointCommandsExecutor;
import commandsExecutors.RectangleCommandsExecutor;
import commandsExecutors.SelectionCommandsExecutor;
import constants.Constants;
import mvc.Model.Circle;
import mvc.Model.Donut;
import mvc.Model.HexagonAdapter;
import mvc.Model.Line;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Rectangle;
import mvc.Model.Shape;
import mvc.View.DialogCircle;
import mvc.View.DialogDonut;
import mvc.View.DialogHexagon;
import mvc.View.DialogLine;
import mvc.View.DialogPoint;
import mvc.View.DialogRectangle;
import mvc.View.Frame;

public class Controller extends Observable {
	private Frame frame;
	private Model model;
	private String mode = Constants.NORMAL;
	private Point startPoint = null;
	private OptionsController optionsController;
	private PointCommandsExecutor pointCommandsExecutor;
	private LineCommandsExecutor lineCommandsExecutor;
	private RectangleCommandsExecutor rectangleCommandsExecutor;
	private DonutCommandsExecutor donutCommandsExecutor;
	private CircleCommandsExecutor circleCommandsExecutor;
	private HexagonCommandsExecutor hexagonCommandsExecutor;
	private DialogPoint dialogPoint;
	private DialogLine dialogLine;
	private DialogRectangle dialogRectangle;
	private DialogDonut dialogDonut;
	private DialogCircle dialogCircle;
	private DialogHexagon dialogHexagon;
	private SelectionCommandsExecutor selectionCommandsExecutor;

	public Controller(OptionsController optionsController) {
		this.optionsController = optionsController;
		model = optionsController.getModel();
		frame = optionsController.getFrame();
		dialogPoint = new DialogPoint();
		dialogLine = new DialogLine();
		dialogRectangle = new DialogRectangle();
		dialogDonut = new DialogDonut();
		dialogCircle = new DialogCircle();
		dialogHexagon = new DialogHexagon();
		pointCommandsExecutor = new PointCommandsExecutor(dialogPoint, optionsController);
		lineCommandsExecutor = new LineCommandsExecutor(dialogLine, optionsController);
		rectangleCommandsExecutor = new RectangleCommandsExecutor(dialogRectangle, optionsController);
		donutCommandsExecutor = new DonutCommandsExecutor(dialogDonut, optionsController);
		circleCommandsExecutor = new CircleCommandsExecutor(dialogCircle, optionsController);
		hexagonCommandsExecutor = new HexagonCommandsExecutor(dialogHexagon, optionsController);
		selectionCommandsExecutor = new SelectionCommandsExecutor(model, frame);
	}

	public void mouseClicked(MouseEvent e) {
		boolean flag = false;

		if (mode == Constants.SELECT) {
			this.handleSelectMode(e);
		} else if (mode == Constants.POINT) {
			pointCommandsExecutor.addPoint(e);
		} else if (mode == Constants.LINE) {
			lineCommandsExecutor.addLine(e);
		} else if (mode == Constants.RECTANGLE) {
			rectangleCommandsExecutor.addRectangle(e);
		} else if (mode == Constants.CIRCLE) {
			circleCommandsExecutor.addCircle(e);
		} else if (mode == Constants.DONUT) {
			donutCommandsExecutor.addDonut(e);
		} else if (mode == Constants.HEXAGON) {
			hexagonCommandsExecutor.addHexagon(e);
		}
		if (flag == false) {
			startPoint = null;
		}
		frame.getView().repaint();
		sendChanges();
	}

	public void handleEdit() {
		Shape selectedShape = optionsController.getSelectedShapes().get(0);
		if (selectedShape instanceof Point) {
			pointCommandsExecutor.updatePoint();
		} else if (selectedShape instanceof Line) {
			lineCommandsExecutor.updateLine();
		} else if (selectedShape instanceof Rectangle) {
			rectangleCommandsExecutor.updateRectangle();
		} else if (selectedShape instanceof Donut) {
			donutCommandsExecutor.updateDonut();
		} else if (selectedShape instanceof Circle) {
			circleCommandsExecutor.updateCircle();
		} else if (selectedShape instanceof HexagonAdapter) {
			hexagonCommandsExecutor.updateHexagon();
		}
	}

	public void handleSelectMode(MouseEvent e) {
		selectionCommandsExecutor.handleSelectMode(e);
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}

	public OptionsController getOptionsController() {
		return optionsController;
	}
}