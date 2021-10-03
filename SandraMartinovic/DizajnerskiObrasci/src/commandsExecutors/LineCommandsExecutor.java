package commandsExecutors;

import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.CmdAddShape;
import commands.CmdUpdateLine;
import mvc.Controller.OptionsController;
import mvc.Model.Line;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Shape;
import mvc.View.DialogLine;
import mvc.View.Frame;

public class LineCommandsExecutor extends Observable {
	private OptionsController optionsController;
	private DialogLine dialogLine;
	private Model model;
	private Frame frame;
	private Point startPoint = null;
	
	public LineCommandsExecutor(DialogLine dialogLine, OptionsController optionsController) {
		this.dialogLine = dialogLine;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		frame = optionsController.getFrame();
	}
	
	public void addLine(MouseEvent e) {
		boolean flag = false;
		
		if (startPoint == null) {
			startPoint = new Point(e.getX(), e.getY());
			flag = true;
		} else if (startPoint.equals(new Point(e.getX(), e.getY()))) {
			JOptionPane.showMessageDialog(new JFrame(),
					"It is not possible to draw line with two same points. Please choose the other one.", "Error",
					JOptionPane.WARNING_MESSAGE);
		} else {
			Line newLine = new Line(startPoint, new Point(e.getX(), e.getY()));
			newLine.setOutlineColor(optionsController.getOutlineColor());
			CmdAddShape cmdAddShape = new CmdAddShape(newLine, model);
			optionsController.commandExecuteHelper(cmdAddShape);
			startPoint = null;
			optionsController.deselectAll();
		}
		
		frame.getView().repaint();
		sendChanges();
	}
	
	public void updateLine() {
		Shape selectedShape = optionsController.getSelectedShapes().get(0);
		Line newLine;
		DialogLine dialogLine = new DialogLine();
		dialogLine.setTxtStartCoordX(Integer.toString(((Line) selectedShape).getStartPoint().getX()));
		dialogLine.setTxtStartCoordY(Integer.toString(((Line) selectedShape).getStartPoint().getY()));
		dialogLine.setTxtEndCoordX(Integer.toString(((Line) selectedShape).getEndPoint().getX()));
		dialogLine.setTxtEndCoordY(Integer.toString(((Line) selectedShape).getEndPoint().getY()));
		dialogLine.setPnlLineColor(((Line) selectedShape).getOutlineColor());
		dialogLine.setVisible(true);
		if (dialogLine.isOk()) {
			newLine = new Line();
			newLine.setStartPoint(new Point((Integer.parseInt(dialogLine.getTxtStartCoordX())),
					(Integer.parseInt(dialogLine.getTxtStartCoordY()))));
			newLine.setEndPoint(new Point((Integer.parseInt(dialogLine.getTxtEndCoordX())),
					(Integer.parseInt(dialogLine.getTxtEndCoordY()))));
			newLine.setOutlineColor(dialogLine.getPnlLineColor());
			optionsController.setOutlineColor(dialogLine.getPnlLineColor());
			CmdUpdateLine cmdUpdateLine = new CmdUpdateLine((Line) selectedShape, newLine);
			optionsController.commandExecuteHelper(cmdUpdateLine);
		}
		this.frame.getView().repaint();
	}
	
	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
}
