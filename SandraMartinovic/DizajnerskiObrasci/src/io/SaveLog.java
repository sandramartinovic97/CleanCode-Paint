package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveLog implements Save {
	private List<String> strings;
	
	public SaveLog() {}

	public SaveLog(List<String> strings) {
		this.strings = strings;
	}

	@Override
	public String saveAs() {
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Save file");
		int result = jFileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath() + ".txt";
			try {
				FileWriter fw = new FileWriter(path);
				for (String string : strings) {
					fw.write(string + System.lineSeparator());
				}
				fw.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File is not saved.", "Failed!", JOptionPane.WARNING_MESSAGE);
				return null;
			}
			return path;
		} else {
			return null;
		}

	}

	public void setList(List<String> strings) {
		this.strings = strings;
	}

	@Override
	public void save(String path, List<Object> objects) {
		try {
			FileWriter fileWriter = new FileWriter(path);
			for (Object object : objects) {
				fileWriter.write((String) object + System.lineSeparator());
			}
			fileWriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File not found.", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
}
