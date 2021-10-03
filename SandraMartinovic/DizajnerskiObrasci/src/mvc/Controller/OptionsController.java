package mvc.Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.CmdRemoveShape;
import commands.Command;
import commandsExecutors.OptionsCommandsExecutor;
import commandsExecutors.SelectionCommandsExecutor;
import mvc.Model.Model;
import mvc.Model.Shape;
import mvc.View.Frame;

public class OptionsController extends Observable {
	private Frame frame;
	private Model model;
	private List<Command> undoCommands;
	private List<Command> redoCommands;
	private OptionsCommandsExecutor optionsCommandsExecutor;
	private SelectionCommandsExecutor selectionCommandsExecutor;

	public OptionsController(Model model, Frame frame) {
		this.frame = frame;
		this.model = model;
		undoCommands = new ArrayList<Command>();
		redoCommands = new ArrayList<Command>();
		optionsCommandsExecutor = new OptionsCommandsExecutor(model, frame);
		selectionCommandsExecutor = new SelectionCommandsExecutor(model, frame);
	}

	public void handleDelete() {
		ArrayList<Shape> shapes = getSelectedShapes();
		if (shapes.size() != 0) {
			if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete shape/shapes?", "Sure?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(shapes, model);
				commandExecuteHelper(cmdRemoveShape);
				frame.getView().repaint();
				sendChanges();
				enableButtons();
			}
		}
	}

	public ArrayList<Shape> getSelectedShapes() {
		Iterator<Shape> iteratorOfShapes = model.getShapes().listIterator();
		ArrayList<Shape> helperListOfShapes = new ArrayList<Shape>();
		while (iteratorOfShapes.hasNext()) {
			Shape shape = iteratorOfShapes.next();
			if (shape.isSelected())
				helperListOfShapes.add(shape);
		}
		return helperListOfShapes;
	}

	public void commandExecuteHelper(Command command) {
		optionsCommandsExecutor.commandExecuteHelper(command);
	}

	public void enableButtons() {
		optionsCommandsExecutor.enableButtons();
	}

	public void undo() {
		optionsCommandsExecutor.undo();
	}

	public void redo() {
		optionsCommandsExecutor.redo();
	}

	public void bringToFront() {
		optionsCommandsExecutor.bringToFront();
	}

	public void bringToEnd() {
		optionsCommandsExecutor.bringToEnd();
	}

	public void toFront() {
		optionsCommandsExecutor.toFront();
	}

	public void toBack() {
		optionsCommandsExecutor.toBack();
	}
	
	public void deselectAllCommand() {
		selectionCommandsExecutor.deselectAllCommand();
	}

	public void deselectAll() {
		selectionCommandsExecutor.deselectAll();
	}

	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}

	public Model getModel() {
		return model;
	}

	public Frame getFrame() {
		return frame;
	}
	
	public void setOutlineColor(Color color) {
		frame.setOutlineColor(color);
	}

	public Color getOutlineColor() {
		return frame.getPnlOutlineColor();
	}

	public void setInnerColor(Color color) {
		frame.setInnerColor(color);
	}

	public Color getInnerColor() {
		return frame.getPnlInnerColor();
	}
}
