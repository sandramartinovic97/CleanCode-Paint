package mvc.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mvc.Model.Circle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogCircle extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private boolean ok;
	private JTextField txtCoordinateY;
	private JTextField txtCoordinateX;
	private JPanel pnlCircleOutlineColor;
	private JPanel pnlCircleInnerColor;

	public DialogCircle() {
		setTitle("Circle");
		setBounds(100, 100, 568, 391);
		getContentPane().setLayout(new BorderLayout());
		this.setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblPoluprenik = new JLabel("Radius");
		lblPoluprenik.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRadius = new JTextField();
		txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRadius.setColumns(10);

		JLabel lblKoordinataX = new JLabel("Coordinate X");
		lblKoordinataX.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblCentar = new JLabel("Center");
		lblCentar.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblKoordinataY = new JLabel("Coordinate Y");
		lblKoordinataY.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtCoordinateY = new JTextField();
		txtCoordinateY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoordinateY.setColumns(10);

		txtCoordinateY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == '-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		txtCoordinateX = new JTextField();
		txtCoordinateX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoordinateX.setColumns(10);

		txtCoordinateX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == '-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		pnlCircleOutlineColor = new JPanel();
		pnlCircleOutlineColor.setBackground(Color.BLACK);
		pnlCircleOutlineColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose color", getPnlCircleOutlineColor());
				if (color != null) {
					setPnlCircleOutlineColor(color);
				}
			}
		});

		pnlCircleInnerColor = new JPanel();
		pnlCircleInnerColor.setBackground(Color.WHITE);
		pnlCircleInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose color", getPnlCircleInnerColor());
				if (color != null) {
					setPnlCircleInnerColor(color);
				}
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(32).addGroup(
								gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPanel
										.createSequentialGroup().addGroup(gl_contentPanel
												.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
														.createSequentialGroup().addGroup(gl_contentPanel
																.createParallelGroup(Alignment.LEADING)
																.addComponent(lblKoordinataY,
																		GroupLayout.PREFERRED_SIZE, 115,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblPoluprenik))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(txtCoordinateY,
																		GroupLayout.PREFERRED_SIZE, 176,
																		GroupLayout.PREFERRED_SIZE)
																.addGroup(gl_contentPanel.createSequentialGroup()
																		.addComponent(txtRadius,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(39)
																		.addGroup(gl_contentPanel
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(pnlCircleOutlineColor,
																						GroupLayout.PREFERRED_SIZE, 65,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(pnlCircleInnerColor,
																						GroupLayout.PREFERRED_SIZE, 78,
																						GroupLayout.PREFERRED_SIZE)))))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(lblKoordinataX)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE, 176,
																GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(94, Short.MAX_VALUE)).addComponent(lblCentar,
												Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 102,
												GroupLayout.PREFERRED_SIZE))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(43)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
										.addComponent(lblCentar, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(27).addComponent(lblKoordinataX, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(txtCoordinateX, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlCircleOutlineColor, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 58,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
								.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblKoordinataY, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE))
								.addGap(43)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPoluprenik).addComponent(txtRadius, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING,
										gl_contentPanel.createSequentialGroup().addGap(48).addComponent(
												pnlCircleInnerColor, GroupLayout.PREFERRED_SIZE, 63,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(80, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Circle c = new Circle();
							c.setRadius(Integer.parseInt(getTextRadius()));
							Integer.parseInt(getTxtCoordinateX());
							Integer.parseInt(getTxtCoordinateY());
							setOk(true);
							dispose();
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(new JFrame(), "Check if all fields are numeric values!",
									"Error", JOptionPane.WARNING_MESSAGE);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(new JFrame(), "Radius must be greater than 0!", "Error",
									JOptionPane.WARNING_MESSAGE);
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public String getTxtCoordinateY() {
		return txtCoordinateY.getText();
	}

	public void setTxtCoordinateY(String txtCoordinateY) {
		this.txtCoordinateY.setText(txtCoordinateY);
	}

	public String getTxtCoordinateX() {
		return txtCoordinateX.getText();
	}

	public void setTxtCoordinateX(String txtCoordinateX) {
		this.txtCoordinateX.setText(txtCoordinateX);
	}

	public void setTxtCoordinateXEdit(boolean coordinateEdit) {
		this.txtCoordinateX.setEditable(coordinateEdit);
	}

	public void setTxtCoordinateYEdit(boolean coordinateEdit) {
		this.txtCoordinateY.setEditable(coordinateEdit);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getTextRadius() {
		return txtRadius.getText();
	}

	public void setRadius(String textField) {
		this.txtRadius.setText(textField);
	}

	public Color getPnlCircleOutlineColor() {
		return pnlCircleOutlineColor.getBackground();
	}

	public void setPnlCircleOutlineColor(Color pnlCircleOutlineColor) {
		this.pnlCircleOutlineColor.setBackground(pnlCircleOutlineColor);
	}

	public Color getPnlCircleInnerColor() {
		return pnlCircleInnerColor.getBackground();
	}

	public void setPnlCircleInnerColor(Color pnlCircleInnerColor) {
		this.pnlCircleInnerColor.setBackground(pnlCircleInnerColor);
	}

}
