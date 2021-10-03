package commandsExecutors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.Command;
import io.LoadSerialized;
import io.LoadTextual;
import io.Save;
import io.SaveLog;
import io.SaveManager;
import io.SaveSerialized;
import mvc.Controller.OptionsController;
import mvc.Model.Model;
import mvc.Model.Shape;
import mvc.View.Frame;

public class FilesCommandsExecutor extends Observable {
	private Frame frame;
	private Model model;
	private String filePath;
	private SaveManager saveManager;
	private List<Command> undoCommands;
	private List<Command> redoCommands;
	private OptionsController optionsController;
	
	public FilesCommandsExecutor(Model model, Frame frame, OptionsController optionsController) {
		this.frame = frame;
		this.model = model;
		saveManager = new SaveManager();
		undoCommands = new ArrayList<Command>();
		redoCommands = new ArrayList<Command>();
		this.optionsController = optionsController;
	}
	
	public void openFileAsSerialized() {
		LoadSerialized loadManager = new LoadSerialized();
		List<Shape> shapes = null;
		try {
			shapes = loadManager.load();
			if (shapes != null) {
				saveManager.setSaver(new SaveSerialized());
				filePath = loadManager.getPath();
				model.setShapes(shapes);
				this.frame.clearLogList();
				frame.getView().repaint();
				undoCommands.clear();
				redoCommands.clear();
				this.frame.getMenuFiles().setSaveButtonEnabled(true);
				sendChanges();
				optionsController.enableButtons();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not loaded.", "Error!", JOptionPane.WARNING_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not loaded.", "Error!", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File not loaded.", "Error!", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void openFileAsTextual() {
		DefaultListModel<String> helperDlmStrings = new DefaultListModel<String>();
		for (int i = 0; i < this.frame.getLogList().getSize(); i++) {
			helperDlmStrings.addElement(this.frame.getLogList().get(i));
		}
		List<Shape> helperListOfShapes = new ArrayList<Shape>();
		for (Shape s : this.model.getShapes()) {
			helperListOfShapes.add(s);
		}
		LoadTextual loadManager = new LoadTextual(this.model);
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Open file");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("Text file", "txt"));
		int result = jFileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			filePath = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
				String line;
				this.frame.clearLogList();
				this.model.removeAll();
				while ((line = bufferedReader.readLine()) != null) {
					loadManager.load(line);
					this.frame.addToLogList(line);
				}
				bufferedReader.close();
				redoCommands = new ArrayList<Command>();
				undoCommands = loadManager.getFullCommands();
				saveManager.setSaver(new SaveLog());
				this.frame.repaint();
				this.frame.getMenuFiles().setSaveButtonEnabled(true);
				optionsController.sendChanges();
				optionsController.enableButtons();
			} catch (FileNotFoundException e) {
				this.frame.setLogList(helperDlmStrings);
				this.model.setShapes(helperListOfShapes);
				JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				this.frame.setLogList(helperDlmStrings);
				this.model.setShapes(helperListOfShapes);
				JOptionPane.showMessageDialog(null, "Format problem.", "Error", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				this.frame.setLogList(helperDlmStrings);
				this.model.setShapes(helperListOfShapes);
				JOptionPane.showMessageDialog(null, "File not loaded.", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void saveFile() {
		List<Object> helperListOfObjects = new ArrayList<>();
		DefaultListModel<String> helperDlmStrings = this.frame.getLogList();
		if (saveManager.getSaver() instanceof SaveLog) {
			for (int i = 0; i < helperDlmStrings.size(); i++) {
				helperListOfObjects.add(helperDlmStrings.get(i));
			}
		} else {
			for (Shape shape : this.model.getShapes()) {
				helperListOfObjects.add(shape);
			}
		}
		saveManager.save(filePath, helperListOfObjects);
	}

	private void saveFileAs(Save saveObject) {
		saveManager.setSaver(saveObject);
		filePath = saveManager.saveAs();
		this.frame.getMenuFiles().setSaveButtonEnabled(true);
	}

	public void saveFileAsTextual() {
		List<String> helperListOfObjects = new ArrayList<String>();
		DefaultListModel<String> helperDlmStrings = this.frame.getLogList();
		for (int i = 0; i < helperDlmStrings.size(); i++) {
			helperListOfObjects.add(helperDlmStrings.get(i));
		}
		Save saveObject = new SaveLog(helperListOfObjects);
		saveFileAs(saveObject);
	}

	public void saveFileAsSerialized() {
		Save saveObject = new SaveSerialized(this.model.getShapes());
		saveFileAs(saveObject);
	}

	public void newFile() {
		undoCommands.clear();
		redoCommands.clear();
		this.model.removeAll();
		this.frame.clearLogList();
		optionsController.sendChanges();
		optionsController.enableButtons();
		this.frame.getMenuFiles().setSaveButtonEnabled(false);
		filePath = null;
		this.frame.repaint();
	}
	
	public void sendChanges() {
		this.setChanged();
		this.notifyObservers(model.getShapes());
	}
}
