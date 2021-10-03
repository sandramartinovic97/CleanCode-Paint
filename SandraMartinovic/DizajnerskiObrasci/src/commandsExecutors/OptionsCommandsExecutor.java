package commandsExecutors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import commands.CmdBringToBack;
import commands.CmdBringToFront;
import commands.CmdToBack;
import commands.CmdToFront;
import commands.CmdUndo;
import commands.Command;
import mvc.Model.Model;
import mvc.Model.Shape;
import mvc.View.Frame;

public class OptionsCommandsExecutor extends Observable {
	private Model model;
	private Frame frame;
	private List<Command> undoCommands;
	private List<Command> redoCommands;
	
	public OptionsCommandsExecutor(Model model, Frame frame) {
		this.frame = frame;
		this.model = model;
		undoCommands = new ArrayList<Command>();
		redoCommands = new ArrayList<Command>();
	}
	public void undo() {
		redoCommands.add(undoCommands.get(undoCommands.size() - 1));
		Command command = undoCommands.get(undoCommands.size() - 1);
		if (command instanceof CmdUndo) {
			command.execute();
		} else {
			command.unexecute();
		}
		undoCommands.remove(undoCommands.size() - 1);
		enableButtons();
		frame.getView().repaint();
		sendChanges(); 
		this.frame.addToLogList("Undo");
	}

	public void redo() {
		undoCommands.add(redoCommands.get(redoCommands.size() - 1));
		Command command = redoCommands.get(redoCommands.size() - 1);
		if (command instanceof CmdUndo) {
			command.unexecute();
		} else {
			command.execute();
		}
		redoCommands.remove(redoCommands.size() - 1);
		enableButtons();
		frame.getView().repaint();
		sendChanges(); 
		this.frame.addToLogList("Redo");
	}
	
	public void bringToFront() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdBringToFront cmd = new CmdBringToFront(model, currentIndex);
		commandExecuteHelper(cmd);
		sendChanges();
		frame.getView().repaint();
	}
	
	public void bringToEnd() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdBringToBack cmd = new CmdBringToBack(model, currentIndex);
		commandExecuteHelper(cmd);
		sendChanges();
		frame.getView().repaint();
	}
	
	public void toFront() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdToFront cmd = new CmdToFront(model, currentIndex);
		commandExecuteHelper(cmd);
		sendChanges();
		frame.getView().repaint();
	}
	
	public void toBack() {
		ArrayList<Shape> shapes = getSelectedShapes();
		Shape selectedShape = shapes.get(0);
		int currentIndex = model.getShapes().indexOf(selectedShape);
		CmdToBack cmd = new CmdToBack(model, currentIndex);
		commandExecuteHelper(cmd);
		sendChanges();
		frame.getView().repaint();
	}

	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
	
	public void commandExecuteHelper(Command command) {
		command.execute();
		undoCommands.add(command);
		redoCommands.clear();
		enableButtons();
		this.frame.addToLogList(command.toString());
	}
	
	public void enableButtons() {
		this.frame.setUndoButtonEnabled(undoCommands.size() > 0);
		this.frame.setRedoButtonEnabled(redoCommands.size() > 0);
	}
	
	public ArrayList<Shape> getSelectedShapes() {
		Iterator<Shape> iteratorOfShapes = model.getShapes().listIterator();
		ArrayList<Shape> helperListOfShapes = new ArrayList<Shape>();
		while (iteratorOfShapes.hasNext()) {
			Shape s = iteratorOfShapes.next();
			if (s.isSelected())
				helperListOfShapes.add(s);
		}
		return helperListOfShapes;
	}
	
}