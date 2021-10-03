package commandsExecutors;

import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.CmdAddShape;
import commands.CmdUpdateDonut;
import mvc.Controller.OptionsController;
import mvc.Model.Donut;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Shape;
import mvc.View.DialogDonut;
import mvc.View.Frame;

public class DonutCommandsExecutor extends Observable {
	private OptionsController optionsController;
	private DialogDonut dialogDonut;
	private Model model;
	private Frame frame;
	
	public DonutCommandsExecutor(DialogDonut dialogDonut, OptionsController optionsController) {
		this.dialogDonut = dialogDonut;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		frame = optionsController.getFrame();
	}
	
	public void addDonut(MouseEvent e) {
		Point center = new Point(e.getX(), e.getY());
		DialogDonut dialogDonut = new DialogDonut();
		dialogDonut.setTxtCoordinateX(Integer.toString(center.getX()));
		dialogDonut.setTxtCoordinateY(Integer.toString(center.getY()));
		dialogDonut.setTxtCoordinateXEditable(false);
		dialogDonut.setTxtCoordinateYEditable(false);
		dialogDonut.setPnlDonutInnerColor(optionsController.getInnerColor());
		dialogDonut.setPnlDonutOutlineColor(optionsController.getOutlineColor());
		dialogDonut.setVisible(true);
		try {
			if (dialogDonut.isOk()) {
				int innerRadius = Integer.parseInt(dialogDonut.getTxtInner());
				int outerRadius = Integer.parseInt(dialogDonut.getTxtOutter());
				Donut donut = new Donut(center, outerRadius, innerRadius);
				donut.setOutlineColor(dialogDonut.getPnlDonutOutlineColor());
				donut.setInnerColor(dialogDonut.getPnlDonutInnerColor());
				optionsController.setInnerColor(dialogDonut.getPnlDonutInnerColor());
				optionsController.setOutlineColor(dialogDonut.getPnlDonutOutlineColor());
				CmdAddShape cmdAddShape = new CmdAddShape(donut, model);
				optionsController.commandExecuteHelper(cmdAddShape);
				optionsController.deselectAll();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Check if all fields are numeric values!",
					"Error", JOptionPane.WARNING_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Outer and inner radius must be greater than zero and inner radius must be smaller than outer!",
					"Error", JOptionPane.WARNING_MESSAGE);
		}
		
		frame.getView().repaint();
		sendChanges();
	}
	
	public void updateDonut() {
		Shape selectedShape = optionsController.getSelectedShapes().get(0);
		Donut newDonut;
		DialogDonut dialogDonut = new DialogDonut();
		dialogDonut.setTxtCoordinateX(Integer.toString(((Donut) selectedShape).getCenter().getX()));
		dialogDonut.setTxtCoordinateY(Integer.toString(((Donut) selectedShape).getCenter().getY()));
		dialogDonut.setTxtInner(Integer.toString(((Donut) selectedShape).getInnerRadius()));
		dialogDonut.setTxtOutter(Integer.toString(((Donut) selectedShape).getRadius()));
		dialogDonut.setPnlDonutOutlineColor((((Donut) selectedShape).getOutlineColor()));
		dialogDonut.setPnlDonutInnerColor((((Donut) selectedShape).getInnerColor()));
		dialogDonut.setVisible(true);
		try {
			if (dialogDonut.isOk()) {
				newDonut = new Donut();
				newDonut.setCenter(new Point(Integer.parseInt(dialogDonut.getTxtCoordinateX()),
						Integer.parseInt(dialogDonut.getTxtCoordinateY())));
				newDonut.setRadius(Integer.parseInt(dialogDonut.getTxtOutter()));
				newDonut.setInnerRadius(Integer.parseInt(dialogDonut.getTxtInner()));
				newDonut.setOutlineColor(dialogDonut.getPnlDonutOutlineColor());
				newDonut.setInnerColor(dialogDonut.getPnlDonutInnerColor());
				optionsController.setInnerColor(dialogDonut.getPnlDonutInnerColor());
				optionsController.setOutlineColor(dialogDonut.getPnlDonutOutlineColor());
				CmdUpdateDonut cmdUpdateDonut = new CmdUpdateDonut((Donut) selectedShape, newDonut);
				optionsController.commandExecuteHelper(cmdUpdateDonut);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Outer radius must be greater than inner radius and both of them must be greater than zero!",
					"Error", JOptionPane.WARNING_MESSAGE);
		}
		this.frame.getView().repaint();
	}
	
	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
}
