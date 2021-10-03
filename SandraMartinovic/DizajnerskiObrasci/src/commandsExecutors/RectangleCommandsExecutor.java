package commandsExecutors;

import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.CmdAddShape;
import commands.CmdUpdateRectangle;
import mvc.Controller.OptionsController;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Rectangle;
import mvc.Model.Shape;
import mvc.View.DialogRectangle;
import mvc.View.Frame;

public class RectangleCommandsExecutor extends Observable {
	private OptionsController optionsController;
	private DialogRectangle dialogRectangle;
	private Model model;
	private Frame frame;
	
	public RectangleCommandsExecutor(DialogRectangle dialogRectangle, OptionsController optionsController) {
		this.dialogRectangle = dialogRectangle;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		frame = optionsController.getFrame();
	}
	
	public void addRectangle(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		DialogRectangle dialogRectangle = new DialogRectangle();
		dialogRectangle.setTxtXCoordinate(Integer.toString(point.getX()));
		dialogRectangle.setTxtYCoordinate(Integer.toString(point.getY()));
		dialogRectangle.setTxtXCoordinateEnabled(false);
		dialogRectangle.setTxtYCoordinateEnabled(false);
		dialogRectangle.setPnlRectangleInnerColor(optionsController.getInnerColor());
		dialogRectangle.setPnlRectangleOutlineColor(optionsController.getOutlineColor());
		dialogRectangle.setVisible(true);
		if (dialogRectangle.isOk()) {
			try {
				if (dialogRectangle.isOk()) {
					int width = Integer.parseInt(dialogRectangle.getTxtWidth());
					int height = Integer.parseInt(dialogRectangle.getTxtHeight());
					Rectangle rectangle = new Rectangle(point, width, height);
					rectangle.setOutlineColor(dialogRectangle.getPnlRectangleOutlineColor());
					rectangle.setInnerColor(dialogRectangle.getPnlRectangleInnerColor());
					optionsController.setInnerColor(dialogRectangle.getPnlRectangleInnerColor());
					optionsController.setOutlineColor(dialogRectangle.getPnlRectangleOutlineColor());
					CmdAddShape cmdAddShape = new CmdAddShape(rectangle, model);
					optionsController.commandExecuteHelper(cmdAddShape);
					optionsController.deselectAll();
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Check if all fields are numeric values!", "Error",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Width and height must be greater than zero!", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		
		frame.getView().repaint();
		sendChanges();
	}
	
	public void updateRectangle() {
		Shape selectedShape = optionsController.getSelectedShapes().get(0);
		Rectangle newRectangle;
		DialogRectangle dialogRectangle = new DialogRectangle();
		dialogRectangle.setTxtXCoordinate(Integer.toString(((Rectangle) selectedShape).getUpperLeftPoint().getX()));
		dialogRectangle.setTxtYCoordinate(Integer.toString(((Rectangle) selectedShape).getUpperLeftPoint().getY()));
		dialogRectangle.setTxtWidth(Integer.toString(((Rectangle) selectedShape).getWidth()));
		dialogRectangle.setTxtHeight(Integer.toString(((Rectangle) selectedShape).getHeight()));
		dialogRectangle.setPnlRectangleInnerColor(((Rectangle) selectedShape).getInnerColor());
		dialogRectangle.setPnlRectangleOutlineColor(((Rectangle) selectedShape).getOutlineColor());
		dialogRectangle.setVisible(true);
		try {
			if (dialogRectangle.isOk()) {
				newRectangle = new Rectangle();
				newRectangle.setUpperLeftPoint(new Point(Integer.parseInt(dialogRectangle.getTxtXCoordinate()),
						Integer.parseInt(dialogRectangle.getTxtYCoordinate())));
				newRectangle.setHeight(Integer.parseInt(dialogRectangle.getTxtHeight()));
				newRectangle.setWidth(Integer.parseInt(dialogRectangle.getTxtWidth()));
				newRectangle.setOutlineColor(dialogRectangle.getPnlRectangleOutlineColor());
				newRectangle.setInnerColor(dialogRectangle.getPnlRectangleInnerColor());
				optionsController.setInnerColor(dialogRectangle.getPnlRectangleInnerColor());
				optionsController.setOutlineColor(dialogRectangle.getPnlRectangleOutlineColor());

				CmdUpdateRectangle cmdUpdateRectangle = new CmdUpdateRectangle((Rectangle) selectedShape, newRectangle);
				optionsController.commandExecuteHelper(cmdUpdateRectangle);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Height and width must be positive numbers!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		this.frame.getView().repaint();
	}
	
	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
}
