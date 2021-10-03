package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import mvc.Model.Shape;

public class SaveSerialized implements Save {
	private List<Shape> shapes;
	
	public SaveSerialized() {}

	public SaveSerialized(List<Shape> shapes) {
		this.shapes = shapes;
	}

	@Override
	public String saveAs() {
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Save file");
		int result = jFileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
				objectOutputStream.writeObject(shapes);
				objectOutputStream.close();
				return path;
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File not saved", "Error", JOptionPane.WARNING_MESSAGE);
				return null;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File not saved", "Error", JOptionPane.WARNING_MESSAGE);
				return null;
			}
		} else {
			return null;
		}
	}

	public void setList(List<Shape> shapes) {
		this.shapes = shapes;
	}

	@Override
	public void save(String path, List<Object> objects) {

		List<Shape> shapes = new ArrayList<Shape>();
		for (Object object : objects) {
			shapes.add((Shape) object);
		}
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
			objectOutputStream.writeObject(shapes);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
}
