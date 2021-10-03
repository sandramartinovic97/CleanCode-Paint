package app;

import javax.swing.JFrame;

import mvc.Controller.Controller;
import mvc.Controller.FilesController;
import mvc.Controller.OptionsController;
import mvc.Model.Model;
import mvc.View.Frame;

public class Application {

	public static void main(String[] args) {
		Model model = new Model();
		Frame frame = new Frame();
		frame.getView().setModel(model);
		OptionsController optionsController = new OptionsController(model, frame);
		Controller controller = new Controller(optionsController);
		FilesController filesController = new FilesController(model, frame, optionsController);
		optionsController.addObserver(frame);
		controller.addObserver(frame);
		filesController.addObserver(frame);
		frame.setOptionsController(optionsController);
		frame.setController(controller);
		frame.setFilesController(filesController);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}