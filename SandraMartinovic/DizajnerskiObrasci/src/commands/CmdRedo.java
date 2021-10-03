package commands;

public class CmdRedo implements Command {
	Command command;

	public CmdRedo(Command command) {
		this.command = command;
	}

	public CmdRedo() {

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
		return "Redo";
	}

}
