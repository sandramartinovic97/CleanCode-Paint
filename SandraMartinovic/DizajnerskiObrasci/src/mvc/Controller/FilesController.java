package mvc.Controller;

import java.util.Observable;

import commandsExecutors.FilesCommandsExecutor;
import mvc.Model.Model;
import mvc.View.Frame;

public class FilesController extends Observable {
	private Frame frame;
	private Model model;
	private FilesCommandsExecutor filesCommandsExecutor;
	
	public FilesController(Model model, Frame frame, OptionsController optionsController) {
		this.frame = frame;
		this.model = model;
		filesCommandsExecutor = new FilesCommandsExecutor(model, frame, optionsController);
	}
	
	public void openFileAsSerialized() {
		filesCommandsExecutor.openFileAsSerialized();
	}

	public void openFileAsTextual() {
		filesCommandsExecutor.openFileAsTextual();
	}

	public void saveFile() {
		filesCommandsExecutor.saveFile();
	}

	public void saveFileAsTextual() {
		filesCommandsExecutor.saveFileAsTextual();
	}

	public void saveFileAsSerialized() {
		filesCommandsExecutor.saveFileAsSerialized();
	}

	public void newFile() {
		filesCommandsExecutor.newFile();
	}
}
