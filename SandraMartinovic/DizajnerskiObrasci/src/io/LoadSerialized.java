package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import mvc.Model.Shape;

public class LoadSerialized {
	private ArrayList<Shape> shapes;
	private String path;

	public List<Shape> load() throws FileNotFoundException, IOException, ClassNotFoundException {
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Open file");
		int result = jFileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getAbsolutePath();
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
			shapes = (ArrayList<Shape>) objectInputStream.readObject();
			objectInputStream.close();
		}
		return shapes;
	}

	public String getPath() {
		return path;
	}

}
