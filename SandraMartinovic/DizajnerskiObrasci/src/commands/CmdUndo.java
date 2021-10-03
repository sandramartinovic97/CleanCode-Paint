package commands;

public class CmdUndo implements Command {
	Command command;

	public CmdUndo(Command command) {
		this.command = command;
	}

	public CmdUndo() {

	}

	@Override
	public void execute() {
		command.execute();
	}

	@Override
	public void unexecute() {
		command.unexecute();
	}

	@Override
	public String toString() {
		return "Undo";
	}

}
