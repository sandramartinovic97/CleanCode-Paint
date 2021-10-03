package mvc.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import mvc.Controller.Controller;
import mvc.Controller.FilesController;
import mvc.Controller.OptionsController;
import mvc.Model.Shape;

public class Frame extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private View view = new View();
	private Controller controller;
	private OptionsController optionsController;
	private JPanel innerColorPanel;
	private JPanel outlineColorPanel;
	private JMenuBar menuBar;
	private JToolBar toolbar;
	private JList list;
	private DefaultListModel<String> logList;
	private Buttons buttons;
	private MenuFiles menuFiles;

	public Frame() {
		buttons = new Buttons();
		menuFiles = new MenuFiles();

		addViewListener();
		createPanels();

		buttons.createButtons(toolbar);
		buttons.createPositionButtons();
		buttons.buttonListeners();
		buttons.positionListeners();
		menuBar = menuFiles.createMenu();
		setJMenuBar(menuBar);
		menuFiles.menuListeners();
	}

	public void addViewListener() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
	}

	public void createPanels() {
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(900, 950));
		getContentPane().add(view, BorderLayout.CENTER);
		toolbar = new JToolBar(null, JToolBar.VERTICAL);
		toolbar.setEnabled(false);
		getContentPane().add(toolbar, BorderLayout.WEST);
		view.setBackground(Color.WHITE);

		JPanel colorsParentPanel = new JPanel();
		colorsParentPanel.setBackground(Color.GRAY);
		getContentPane().add(colorsParentPanel, BorderLayout.SOUTH);

		innerColorPanel = new JPanel();
		innerColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose inner color", getPnlInnerColor());
				if (color != null) {
					setInnerColor(color);
				}
			}
		});
		innerColorPanel.setBackground(Color.WHITE);

		outlineColorPanel = new JPanel();
		outlineColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose line color", getPnlOutlineColor());
				if (color != null) {
					setOutlineColor(color);
				}
			}
		});
		outlineColorPanel.setBackground(Color.BLACK);
		GroupLayout gl_innerColorPanel = new GroupLayout(innerColorPanel);
		gl_innerColorPanel.setHorizontalGroup(
				gl_innerColorPanel.createParallelGroup(Alignment.LEADING).addGap(0, 36, Short.MAX_VALUE));
		gl_innerColorPanel.setVerticalGroup(
				gl_innerColorPanel.createParallelGroup(Alignment.LEADING).addGap(0, 36, Short.MAX_VALUE));
		innerColorPanel.setLayout(gl_innerColorPanel);

		list = new JList();
		logList = new DefaultListModel<String>();
		list.setModel(logList);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_colorsParentPanel = new GroupLayout(colorsParentPanel);
		gl_colorsParentPanel.setHorizontalGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorsParentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(outlineColorPanel, GroupLayout.PREFERRED_SIZE, 36,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(innerColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
						.addContainerGap()));
		gl_colorsParentPanel.setVerticalGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorsParentPanel.createSequentialGroup()
						.addGroup(gl_colorsParentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_colorsParentPanel.createSequentialGroup().addContainerGap().addComponent(
										scrollPane, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_colorsParentPanel.createSequentialGroup().addGap(18)
										.addComponent(innerColorPanel, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(outlineColorPanel,
												GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
						.addGap(13)));
		colorsParentPanel.setLayout(gl_colorsParentPanel);

		scrollPane.setViewportView(list);
	}

	@Override
	public void update(Observable observable, Object object) {
		ArrayList<Shape> shapes = (ArrayList<Shape>) object;
		int numberOfShapes = shapes.size();
		int selectedShapes = optionsController.getSelectedShapes().size();
		buttons.getTglBtnSelect().setEnabled(numberOfShapes > 0);
		buttons.getTglBtnEdit().setEnabled(selectedShapes == 1);
		buttons.getTglBtnDelete().setEnabled(selectedShapes > 0);
		buttons.getTglBtnBringTo().setEnabled(numberOfShapes > 1 && selectedShapes == 1);
		if (selectedShapes == 1 && shapes.get(0).isSelected()) {
			buttons.getToBack().setEnabled(false);
			buttons.getBringToEnd().setEnabled(false);
		} else {
			buttons.getToBack().setEnabled(true);
			buttons.getBringToEnd().setEnabled(true);
		}
		if (selectedShapes == 1 && shapes.get(numberOfShapes - 1).isSelected()) {
			buttons.getToFront().setEnabled(false);
			buttons.getBringToFront().setEnabled(false);
		} else {
			buttons.getToFront().setEnabled(true);
			buttons.getBringToFront().setEnabled(true);
		}
	}

	public void setUndoButtonEnabled(boolean bool) {
		buttons.getTglBtnUndo().setEnabled(bool);
	}

	public void setRedoButtonEnabled(boolean bool) {
		buttons.getTglBtnRedo().setEnabled(bool);
	}

	public View getView() {
		return view;
	}

	public void setController(Controller controller) {
		this.controller = controller;
		buttons.setController(controller);
		menuFiles.setController(controller);
	}

	public void setOptionsController(OptionsController optionsController) {
		this.optionsController = optionsController;
		buttons.setOptionsController(optionsController);
	}

	public void setFilesController(FilesController filesController) {
		buttons.setFilesController(filesController);
		menuFiles.setFilesController(filesController);
	}

	public void setOutlineColor(Color color) {
		outlineColorPanel.setBackground(color);
	}

	public Color getPnlOutlineColor() {
		return outlineColorPanel.getBackground();
	}

	public void setInnerColor(Color color) {
		innerColorPanel.setBackground(color);
	}

	public Color getPnlInnerColor() {
		return innerColorPanel.getBackground();
	}

	public void setList(List<String> commands) {
		logList = new DefaultListModel<String>();
		for (String string : commands) {
			logList.addElement(string);
		}
	}

	public void addToLogList(String element) {
		logList.addElement(element);
	}

	public DefaultListModel<String> getLogList() {
		return logList;
	}

	public void clearLogList() {
		logList.removeAllElements();
	}

	public void setLogList(DefaultListModel<String> dlm) {
		logList.removeAllElements();
		for (int i = 0; i < dlm.size(); i++) {
			logList.addElement(dlm.elementAt(i));
		}
	}

	public MenuFiles getMenuFiles() {
		return menuFiles;
	}
}