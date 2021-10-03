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

import mvc.Model.Donut;
import mvc.Model.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogDonut extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private JTextField txtInner;
	private JTextField txtOutter;
	private JPanel pnlDonutInnerColor;
	private JPanel pnlDonutOutlineColor;
	private boolean ok;

	public DialogDonut() {
		setBounds(100, 100, 730, 492);
		setTitle("Donut");
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCentar = new JLabel("Center");
		lblCentar.setFont(new Font("Tahoma", Font.PLAIN, 21));
		JLabel lblKoordinataX = new JLabel("Coordinate X");
		lblKoordinataX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblKoordinataY = new JLabel("Coordinate Y");
		lblKoordinataY.setFont(new Font("Tahoma", Font.PLAIN, 20));
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

		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 21));
		JLabel lblUnutranji = new JLabel("Inner");
		lblUnutranji.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblSpoljni = new JLabel("Outer");
		lblSpoljni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInner = new JTextField();
		txtInner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInner.setColumns(10);
		txtOutter = new JTextField();
		txtOutter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOutter.setColumns(10);

		pnlDonutInnerColor = new JPanel();
		pnlDonutInnerColor.setBackground(Color.WHITE);
		pnlDonutInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose color", getPnlDonutInnerColor());
				if (color != null) {
					setPnlDonutInnerColor(color);
				}
			}
		});

		pnlDonutOutlineColor = new JPanel();
		pnlDonutOutlineColor.setBackground(Color.BLACK);
		pnlDonutOutlineColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose color", getPnlDonutOutlineColor());
				if (color != null) {
					setPnlDonutOutlineColor(color);
				}
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(33).addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
								.createSequentialGroup().addGroup(gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblRadius, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblKoordinataX, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lblKoordinataY, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addComponent(lblSpoljni, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUnutranji, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE))
								.addGap(40)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtOutter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtInner, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(91)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(pnlDonutInnerColor, GroupLayout.PREFERRED_SIZE,
																77, GroupLayout.PREFERRED_SIZE)
														.addComponent(pnlDonutOutlineColor, GroupLayout.PREFERRED_SIZE,
																81, GroupLayout.PREFERRED_SIZE))
												.addContainerGap(170, Short.MAX_VALUE))))
						.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblCentar).addContainerGap(610,
								Short.MAX_VALUE)))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup()
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
						.createSequentialGroup().addGap(31).addComponent(lblCentar).addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblKoordinataX)
								.addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblKoordinataY)
								.addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(43).addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUnutranji, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtInner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(47)
								.addComponent(pnlDonutInnerColor, GroupLayout.PREFERRED_SIZE, 79,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(pnlDonutOutlineColor, GroupLayout.PREFERRED_SIZE, 74,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSpoljni, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtOutter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(97, Short.MAX_VALUE)));
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
							int i = Integer.parseInt(getTxtCoordinateX());
							int j = Integer.parseInt(getTxtCoordinateY());
							Point point = new Point(i, j);
							Donut donut = new Donut(point, Integer.parseInt(getTxtOutter()),
									Integer.parseInt(getTxtInner()));
							setOk(true);
							dispose();
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(new JFrame(), "Check if all fields are numeric values!",
									"Error", JOptionPane.WARNING_MESSAGE);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Outer and inner radius must be greater than zero and inner radius must be smaller than outer!",
									"Greška", JOptionPane.WARNING_MESSAGE);
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

	public void setTxtCoordinateXEditable(boolean coordinateEditable) {
		this.txtCoordinateX.setEditable(coordinateEditable);
	}

	public void setTxtCoordinateYEditable(boolean coordinateEditable) {
		this.txtCoordinateY.setEditable(coordinateEditable);
	}

	public void setTxtInnerEditable(boolean coordinateEditable) {
		this.txtInner.setEditable(coordinateEditable);
	}

	public void setTxtOutterEditable(boolean coordinateEditable) {
		this.txtOutter.setEditable(coordinateEditable);
	}

	public String getTxtInner() {
		return txtInner.getText();
	}

	public void setTxtInner(String txtInner) {
		this.txtInner.setText(txtInner);
	}

	public String getTxtOutter() {
		return txtOutter.getText();
	}

	public void setTxtOutter(String txtOutter) {
		this.txtOutter.setText(txtOutter);
	}

	public String getTxtCoordinateX() {
		return txtCoordinateX.getText();
	}

	public void setTxtCoordinateX(String txtCoordinateX) {
		this.txtCoordinateX.setText(txtCoordinateX);
	}

	public String getTxtCoordinateY() {
		return txtCoordinateY.getText();
	}

	public void setTxtCoordinateY(String txtCoordinateY) {
		this.txtCoordinateY.setText(txtCoordinateY);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Color getPnlDonutInnerColor() {
		return pnlDonutInnerColor.getBackground();
	}

	public void setPnlDonutInnerColor(Color pnlDonutInnerColor) {
		this.pnlDonutInnerColor.setBackground(pnlDonutInnerColor);
	}

	public Color getPnlDonutOutlineColor() {
		return pnlDonutOutlineColor.getBackground();
	}

	public void setPnlDonutOutlineColor(Color pnlDonutOutlineColor) {
		this.pnlDonutOutlineColor.setBackground(pnlDonutOutlineColor);
	}

}
