package mvc.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import constants.Constants;
import mvc.Controller.Controller;
import mvc.Controller.FilesController;
import mvc.Controller.OptionsController;

public class Buttons {
	private static final long serialVersionUID = 1L;
	private JToggleButton tglBtnPoint;
	private JToggleButton tglBtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglBtnCircle;
	private JToggleButton tglBtnDonut;
	private JToggleButton tglBtnHexagon;
	private JToggleButton tglBtnSelect;
	private JToggleButton tglBtnEdit;
	private JToggleButton tglBtnDelete;
	private JToggleButton tglBtnUndo;
	private JToggleButton tglBtnRedo;
	private JButton tglBtnBringTo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPopupMenu menuPosition;
	private JMenuItem bringToFront;
	private JMenuItem bringToEnd;
	private JMenuItem toBack;
	private JMenuItem toFront;
	private JPanel innerColorPanel;
	private JPanel outlineColorPanel;
	private Controller controller;
	private OptionsController optionsController;
	private FilesController filesController;

	public Buttons() {
	}

	public void createButtons(JToolBar toolbar) {
		tglBtnPoint = new JToggleButton("");
		buttonGroup.add(tglBtnPoint);
		tglBtnPoint.setIcon(new ImageIcon(getClass().getResource("/point.png")));
		toolbar.add(tglBtnPoint);

		tglBtnLine = new JToggleButton("");
		buttonGroup.add(tglBtnLine);
		tglBtnLine.setIcon(new ImageIcon(getClass().getResource("/line.png")));
		toolbar.add(tglBtnLine);

		tglbtnRectangle = new JToggleButton("");
		buttonGroup.add(tglbtnRectangle);
		tglbtnRectangle.setIcon(new ImageIcon(getClass().getResource("/rectangle.png")));
		toolbar.add(tglbtnRectangle);

		tglBtnCircle = new JToggleButton("");
		buttonGroup.add(tglBtnCircle);
		tglBtnCircle.setIcon(new ImageIcon(getClass().getResource("/circle.png")));
		toolbar.add(tglBtnCircle);

		tglBtnDonut = new JToggleButton("");
		buttonGroup.add(tglBtnDonut);
		tglBtnDonut.setIcon(new ImageIcon(getClass().getResource("/donut.png")));
		toolbar.add(tglBtnDonut);

		tglBtnHexagon = new JToggleButton("");
		buttonGroup.add(tglBtnHexagon);
		tglBtnHexagon.setIcon(new ImageIcon(getClass().getResource("/hexagon.png")));
		toolbar.add(tglBtnHexagon);

		tglBtnSelect = new JToggleButton("");
		buttonGroup.add(tglBtnSelect);
		tglBtnSelect.setIcon(new ImageIcon(getClass().getResource("/select.png")));
		tglBtnSelect.setEnabled(false);
		toolbar.add(tglBtnSelect);

		tglBtnEdit = new JToggleButton("");
		buttonGroup.add(tglBtnEdit);
		tglBtnEdit.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
		tglBtnEdit.setEnabled(false);
		toolbar.add(tglBtnEdit);

		tglBtnDelete = new JToggleButton("");
		buttonGroup.add(tglBtnDelete);
		tglBtnDelete.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
		tglBtnDelete.setEnabled(false);
		toolbar.add(tglBtnDelete);

		tglBtnBringTo = new JButton("");
		tglBtnBringTo.setIcon(new ImageIcon(getClass().getResource("/bringToFront.png")));
		tglBtnBringTo.setEnabled(false);
		toolbar.add(tglBtnBringTo);

		tglBtnUndo = new JToggleButton("");
		buttonGroup.add(tglBtnUndo);
		tglBtnUndo.setIcon(new ImageIcon(getClass().getResource("/undo.png")));
		tglBtnUndo.setEnabled(false);
		toolbar.add(tglBtnUndo);

		tglBtnRedo = new JToggleButton("");
		tglBtnRedo.setFont(new Font("Tahoma", Font.PLAIN, 7));
		buttonGroup.add(tglBtnRedo);
		tglBtnRedo.setIcon(new ImageIcon(getClass().getResource("/redo.png")));
		tglBtnRedo.setEnabled(false);
		toolbar.add(tglBtnRedo);
	}

	public void createPositionButtons() {
		menuPosition = new JPopupMenu("menu");
		bringToFront = new JMenuItem("Bring to Front");
		bringToEnd = new JMenuItem("Bring to end");
		toBack = new JMenuItem("Move to back");
		toFront = new JMenuItem("Move to front");
		menuPosition.add(bringToFront);
		menuPosition.add(bringToEnd);
		menuPosition.add(toBack);
		menuPosition.add(toFront);
	}

	public void buttonListeners() {
		tglBtnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setMode(Constants.POINT);
			}
		});

		tglBtnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setMode(Constants.LINE);
			}
		});

		tglbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setMode(Constants.RECTANGLE);
			}
		});

		tglBtnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setMode(Constants.CIRCLE);
			}
		});

		tglBtnDonut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setMode(Constants.DONUT);
			}
		});

		tglBtnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setMode(Constants.HEXAGON);
			}
		});

		tglBtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setMode(Constants.SELECT);
			}
		});

		tglBtnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglBtnEdit.isEnabled()) {
					controller.handleEdit();
					controller.setMode(Constants.NORMAL);
				}
			}
		});

		tglBtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglBtnDelete.isEnabled()) {
					optionsController.handleDelete();
					controller.setMode(Constants.NORMAL);
				}
			}
		});

		tglBtnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglBtnUndo.isEnabled()) {
					optionsController.undo();
					controller.setMode(Constants.NORMAL);
				}
			}
		});

		tglBtnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglBtnRedo.isEnabled()) {
					optionsController.redo();
					controller.setMode(Constants.NORMAL);
				}
			}
		});
	}

	public void positionListeners() {
		tglBtnBringTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglBtnBringTo.isEnabled() == true) {
					menuPosition.show(tglBtnBringTo, e.getX(), e.getY());
					controller.setMode(Constants.NORMAL);
				}
			}
		});

		bringToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bringToFront.isEnabled()) {
					optionsController.bringToFront();
					controller.setMode(Constants.NORMAL);
				}
			}
		});

		bringToEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bringToEnd.isEnabled()) {
					optionsController.bringToEnd();
					controller.setMode(Constants.NORMAL);
				}
			}
		});

		toFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toFront.isEnabled()) {
					optionsController.toFront();
					controller.setMode(Constants.NORMAL);
				}
			}
		});

		toBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toBack.isEnabled()) {
					optionsController.toBack();
					controller.setMode(Constants.NORMAL);
				}
			}
		});
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

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setOptionsController(OptionsController optionsController) {
		this.optionsController = optionsController;
	}

	public void setFilesController(FilesController filesController) {
		this.filesController = filesController;
	}

	public JToggleButton getTglBtnUndo() {
		return tglBtnUndo;
	}

	public JToggleButton getTglBtnRedo() {
		return tglBtnRedo;
	}

	public JToggleButton getTglBtnEdit() {
		return tglBtnEdit;
	}

	public JToggleButton getTglBtnDelete() {
		return tglBtnDelete;
	}

	public JButton getTglBtnBringTo() {
		return tglBtnBringTo;
	}

	public JMenuItem getBringToFront() {
		return bringToFront;
	}

	public JMenuItem getBringToEnd() {
		return bringToEnd;
	}

	public JMenuItem getToBack() {
		return toBack;
	}

	public JMenuItem getToFront() {
		return toFront;
	}

	public JToggleButton getTglBtnSelect() {
		return tglBtnSelect;
	}
}