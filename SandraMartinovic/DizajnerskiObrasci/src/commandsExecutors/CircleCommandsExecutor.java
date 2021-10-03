package commandsExecutors;

import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.CmdAddShape;
import commands.CmdUpdateCircle;
import mvc.Controller.OptionsController;
import mvc.Model.Circle;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Shape;
import mvc.View.DialogCircle;
import mvc.View.Frame;

public class CircleCommandsExecutor extends Observable {
	private OptionsController optionsController;
	private DialogCircle dialogCircle;
	private Model model;
	private Frame frame;

	public CircleCommandsExecutor(DialogCircle dialogCircle, OptionsController optionsController) {
		this.dialogCircle = dialogCircle;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		frame = optionsController.getFrame();
	}
	
	public void addCircle(MouseEvent e) {
		Point center = new Point(e.getX(), e.getY());
		DialogCircle dialogCircle = new DialogCircle();
		dialogCircle.setTxtCoordinateXEdit(false);
		dialogCircle.setTxtCoordinateYEdit(false);
		dialogCircle.setTxtCoordinateX(Integer.toString(center.getX()));
		dialogCircle.setTxtCoordinateY(Integer.toString(center.getY()));
		dialogCircle.setPnlCircleInnerColor(optionsController.getInnerColor());
		dialogCircle.setPnlCircleOutlineColor(optionsController.getOutlineColor());
		dialogCircle.setVisible(true);
		try {
			if (dialogCircle.isOk()) {
				int radius = Integer.parseInt(dialogCircle.getTextRadius());
				Circle circle = new Circle(center, radius);
				circle.setOutlineColor(dialogCircle.getPnlCircleOutlineColor());
				circle.setInnerColor(dialogCircle.getPnlCircleInnerColor());
				optionsController.setInnerColor(dialogCircle.getPnlCircleInnerColor());
				optionsController.setOutlineColor(dialogCircle.getPnlCircleOutlineColor());
				CmdAddShape cmdAddShape = new CmdAddShape(circle, model);
				optionsController.commandExecuteHelper(cmdAddShape);
				optionsController.deselectAll();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Check if all fields are numeric values!",
					"Error", JOptionPane.WARNING_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Radius must be greater than zero!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		
		frame.getView().repaint();
		sendChanges();
	}

	public void updateCircle() {
		Shape selectedShape = optionsController.getSelectedShapes().get(0);
		Circle newCircle;
		DialogCircle dialogCircle = new DialogCircle();
		dialogCircle.setTxtCoordinateX(Integer.toString(((Circle) selectedShape).getCenter().getX()));
		dialogCircle.setTxtCoordinateY(Integer.toString(((Circle) selectedShape).getCenter().getY()));
		dialogCircle.setRadius(Integer.toString(((Circle) selectedShape).getRadius()));
		dialogCircle.setPnlCircleInnerColor(((Circle) selectedShape).getInnerColor());
		dialogCircle.setPnlCircleOutlineColor(((Circle) selectedShape).getOutlineColor());
		dialogCircle.setVisible(true);
		try {
			if (dialogCircle.isOk()) {
				newCircle = new Circle();
				newCircle.setCenter(new Point(Integer.parseInt(dialogCircle.getTxtCoordinateX()),
						Integer.parseInt(dialogCircle.getTxtCoordinateY())));
				newCircle.setRadius(Integer.parseInt(dialogCircle.getTextRadius()));
				newCircle.setOutlineColor(dialogCircle.getPnlCircleOutlineColor());
				newCircle.setInnerColor(dialogCircle.getPnlCircleInnerColor());
				optionsController.setInnerColor(dialogCircle.getPnlCircleInnerColor());
				optionsController.setOutlineColor(dialogCircle.getPnlCircleOutlineColor());

				CmdUpdateCircle cmdUpdateCircle = new CmdUpdateCircle((Circle) selectedShape, newCircle);
				optionsController.commandExecuteHelper(cmdUpdateCircle);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive number!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}

		this.frame.getView().repaint();
	}
	
	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
}
