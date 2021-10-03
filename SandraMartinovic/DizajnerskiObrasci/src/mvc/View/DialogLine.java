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

public class DialogLine extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartCoordX;
	private JTextField txtStartCoordY;
	private JTextField txtEndCoordX;
	private JTextField txtEndCoordY;
	private JPanel pnlLineColor;
	private boolean ok;

	public static void main(String[] args) {
		DialogLine dia = new DialogLine();
		dia.setVisible(true);
	}

	public DialogLine() {
		setBounds(100, 100, 638, 477);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Line");
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblPocetnaTacka = new JLabel("Start point");
		lblPocetnaTacka.setFont(new Font("Tahoma", Font.PLAIN, 21));
		JLabel lblPocKoordX = new JLabel("Coordinate X");
		lblPocKoordX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblPocKoordY = new JLabel("Coordinate Y");
		lblPocKoordY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblKrajnjaTacka = new JLabel("End point");
		lblKrajnjaTacka.setFont(new Font("Tahoma", Font.PLAIN, 21));
		JLabel lblKrKoordX = new JLabel("Coordinate X");
		lblKrKoordX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblKrKoordY = new JLabel("Coordinate Y");
		lblKrKoordY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartCoordX = new JTextField();
		txtStartCoordX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartCoordX.setColumns(10);

		txtStartCoordX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == '-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtStartCoordY = new JTextField();
		txtStartCoordY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartCoordY.setColumns(10);

		txtStartCoordY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == '-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtEndCoordX = new JTextField();
		txtEndCoordX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndCoordX.setColumns(10);

		txtEndCoordX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == '-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtEndCoordY = new JTextField();
		txtEndCoordY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndCoordY.setColumns(10);

		txtEndCoordY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == '-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		pnlLineColor = new JPanel();
		pnlLineColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose line color", getPnlLineColor());
				if (color != null) {
					setPnlLineColor(color);
				}
			}
		});
		pnlLineColor.setBackground(Color.BLACK);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(34)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblPocetnaTacka)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblPocKoordY, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblPocKoordX, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblKrKoordX, Alignment.LEADING)
												.addComponent(lblKrajnjaTacka, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
												.addComponent(lblKrKoordY, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(38)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(txtStartCoordX, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtEndCoordY, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(txtStartCoordY, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(37).addComponent(pnlLineColor,
																GroupLayout.PREFERRED_SIZE, 88,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(txtEndCoordX, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGap(116)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(35)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblPocetnaTacka).addGap(29)
								.addComponent(lblPocKoordX))
						.addComponent(txtStartCoordX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(
						gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlLineColor, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblPocKoordY)
												.addComponent(txtStartCoordY, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(31)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtEndCoordX, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(lblKrajnjaTacka, GroupLayout.PREFERRED_SIZE, 25,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(lblKrKoordX)))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblKrKoordY).addComponent(txtEndCoordY,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(92, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Integer.parseInt(getTxtStartCoordX());
							Integer.parseInt(getTxtStartCoordY());
							Integer.parseInt(getTxtEndCoordX());
							Integer.parseInt(getTxtEndCoordY());
							setOk(true);
							dispose();
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(new JFrame(), "Check if all fields are numeric values!",
									"Error", JOptionPane.WARNING_MESSAGE);
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

	public String getTxtStartCoordX() {
		return txtStartCoordX.getText();
	}

	public void setTxtStartCoordX(String txtStartCoordX) {
		this.txtStartCoordX.setText(txtStartCoordX);
	}

	public String getTxtStartCoordY() {
		return txtStartCoordY.getText();
	}

	public void setTxtStartCoordY(String txtStartCoordY) {
		this.txtStartCoordY.setText(txtStartCoordY);
	}

	public String getTxtEndCoordX() {
		return txtEndCoordX.getText();
	}

	public void setTxtEndCoordX(String txtEndCoordX) {
		this.txtEndCoordX.setText(txtEndCoordX);
	}

	public String getTxtEndCoordY() {
		return txtEndCoordY.getText();
	}

	public void setTxtEndCoordY(String txtEndCoordY) {
		this.txtEndCoordY.setText(txtEndCoordY);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public void setTxtStartCoordXEdt(boolean b) {
		this.txtStartCoordX.setEditable(b);
	}

	public void setTxtStartCoordYEdt(boolean b) {
		this.txtStartCoordY.setEditable(b);
	}

	public void setTxtEndCoordXEdt(boolean b) {
		this.txtEndCoordX.setEditable(b);
	}

	public void setTxtEndCoordYEdt(boolean b) {
		this.txtEndCoordY.setEditable(b);
	}

	public Color getPnlLineColor() {
		return pnlLineColor.getBackground();
	}

	public void setPnlLineColor(Color pnlLineColor) {
		this.pnlLineColor.setBackground(pnlLineColor);
	}

}
