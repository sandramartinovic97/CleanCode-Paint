package commandsExecutors;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import commands.CmdDeselectShape;
import commands.CmdSelectShape;
import commands.Command;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Shape;
import mvc.View.Frame;

public class SelectionCommandsExecutor {
	private Model model;
	private Frame frame;
	private List<Command> undoCommands;
	private List<Command> redoCommands;
	
	public SelectionCommandsExecutor(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
		undoCommands = new ArrayList<Command>();
		redoCommands = new ArrayList<Command>();
	}
	
	public void handleSelectMode(MouseEvent e) {
		boolean flag = false;
		List<Shape> shapes = model.getShapes();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			if (shape.contains(new Point(e.getX(), e.getY()))) {
				flag = true;
				if (shape.isSelected() == false) {
					shape.setSelected(true);
					Command command = new CmdSelectShape(shape);
					commandExecuteHelper(command);
					break;
				} else {
					List<Shape> deselectShapes = new ArrayList<Shape>();
					shape.setSelected(false);
					deselectShapes.add(shape);
					Command command = new CmdDeselectShape(deselectShapes);
					commandExecuteHelper(command);
					break;
				}
			}
		}
		if (flag == false) {
			deselectAllCommand();
		}
	}
	
	public void deselectAllCommand() {
		List<Shape> deselectShapes = new ArrayList<Shape>();
		for (Shape shape : model.getShapes()) {
			if (shape.isSelected()) {
				shape.setSelected(false);
				deselectShapes.add(shape);
			}
		}
		if (deselectShapes.size() > 0) {
			Command command = new CmdDeselectShape(deselectShapes);
			commandExecuteHelper(command);
		}
	}
	
	public void deselectAll() {
		for (Shape shape : model.getShapes()) {
			if (shape.isSelected()) {
				shape.setSelected(false);
			}
		}
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
	
}