package mvc.View;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import constants.Constants;
import mvc.Controller.Controller;
import mvc.Controller.FilesController;

public class MenuFiles {
	private JMenuBar menuBar;
	private JMenu menuOpen;
	private JMenu menuSave;
	private JMenu menuFile;
	private JMenuItem menuItemNew;
	private JMenuItem menuItemOpenSerialized;
	private JMenuItem menuItemOpenTextual;
	private JMenuItem menuItemSave;
	private JMenuItem menuItemSaveAsSerialized;
	private JMenuItem menuItemSaveAsTextual;
	private Controller controller;
	private FilesController filesController;

	public MenuFiles() {
	}

	public JMenuBar createMenu() {
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 70, 0));

		menuFile = new JMenu("File");
		menuFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(menuFile);

		menuItemNew = new JMenuItem("New");
		menuItemNew.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuFile.add(menuItemNew);

		menuOpen = new JMenu("Open");
		menuOpen.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(menuOpen);

		menuItemOpenSerialized = new JMenuItem("Open serialized");
		menuItemOpenSerialized.setHorizontalAlignment(SwingConstants.LEFT);
		menuItemOpenSerialized.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuOpen.add(menuItemOpenSerialized);

		menuItemOpenTextual = new JMenuItem("Open textual");
		menuItemOpenTextual.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuOpen.add(menuItemOpenTextual);

		menuSave = new JMenu("Save");
		menuSave.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(menuSave);

		menuItemSave = new JMenuItem("Save");
		menuItemSave.setHorizontalAlignment(SwingConstants.LEFT);
		menuSave.add(menuItemSave);
		menuItemSave.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuItemSave.setEnabled(false);

		menuItemSaveAsSerialized = new JMenuItem("Save as serialized...");
		menuItemSaveAsSerialized.setHorizontalAlignment(SwingConstants.LEFT);
		menuSave.add(menuItemSaveAsSerialized);
		menuItemSaveAsSerialized.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		menuItemSaveAsTextual = new JMenuItem("Save as textual...");
		menuItemSaveAsTextual.setHorizontalAlignment(SwingConstants.LEFT);
		menuSave.add(menuItemSaveAsTextual);
		menuItemSaveAsTextual.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		return menuBar;
	}

	public void menuListeners() {
		menuItemSaveAsTextual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filesController.saveFileAsTextual();
			}
		});

		menuItemSaveAsSerialized.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filesController.saveFileAsSerialized();
			}
		});

		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filesController.saveFile();
			}
		});

		menuItemOpenSerialized.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filesController.openFileAsSerialized();
				controller.setMode(Constants.NORMAL);
			}
		});

		menuItemOpenTextual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filesController.openFileAsTextual();
				controller.setMode(Constants.NORMAL);
			}
		});

		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filesController.newFile();
				controller.setMode(Constants.NORMAL);
			}
		});
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setFilesController(FilesController filesController) {
		this.filesController = filesController;
	}

	public void setSaveButtonEnabled(boolean enabled) {
		this.menuItemSave.setEnabled(enabled);
	}
}
