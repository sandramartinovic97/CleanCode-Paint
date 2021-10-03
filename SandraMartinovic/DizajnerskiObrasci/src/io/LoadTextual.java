package io;

import java.util.ArrayList;
import java.util.List;
import commands.CmdRedo;
import commands.CmdUndo;
import commands.Command;
import mvc.Model.Model;

public class LoadTextual {
	private List<Command> undoCommands;
	private List<Command> redoCommands;
	private List<Command> fullCommands;
	private CommandParser parser;
	private Model model;
	private int currentUndoPostiton = -1;

	public LoadTextual(Model model) {
		this.model = model;
		this.parser = new CommandParser(this.model);
		this.fullCommands = new ArrayList<Command>();
		this.undoCommands = new ArrayList<Command>();
		this.redoCommands = new ArrayList<Command>();
	}

	public void load(String line) throws Exception {
		Command command;
		if (line.equals("Undo")) {
			command = new CmdUndo(undoCommands.get(currentUndoPostiton));
			command.unexecute();
			fullCommands.add(command);
			redoCommands.add(command);
			currentUndoPostiton--;
		} else if (line.equals("Redo")) {
			command = new CmdRedo(redoCommands.get(redoCommands.size() - 1));
			fullCommands.add(command);
			command.execute();
			redoCommands.remove(redoCommands.size() - 1);
			currentUndoPostiton++;
		} else {
			command = parser.parseCommand(line);
			fullCommands.add(command);
			command.execute();
			undoCommands.add(command);
			redoCommands.clear();
			currentUndoPostiton = undoCommands.size() - 1;
		}
	}

	public List<Command> getFullCommands() {
		return fullCommands;
	}

	public List<Command> getRedoCommands() {
		return redoCommands;
	}

	public List<Command> getUndoCommands() {
		return undoCommands;
	}

}
