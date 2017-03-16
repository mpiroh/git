package sk.upjs.ics.bakalarka;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class Gui extends JFrame {

	private JPanel panel;
	private JTextField exp1TextField;
	private JTextField exp2TextField;
	private JLabel resultLabel;
	private JButton compareButton;
	private boolean afterComparation = false;

	public Gui() {
		panel = new JPanel();
		exp1TextField = new JTextField();
		exp2TextField = new JTextField();
		resultLabel = new JLabel("...");

		compareButton = new JButton("Compare");
		compareButton.setPreferredSize(new Dimension(100, 25));
		compareButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (afterComparation) {
					afterComparation = false;
					resultLabel.setText("...");
					compareButton.setText("Compare");
				} else {
					SwingWorker<Boolean, Void> swingWorker = new SwingWorker<Boolean, Void>() {

						@Override
						protected Boolean doInBackground() throws Exception {
							Porovnavac porovnavac = new Porovnavac();
							return porovnavac.compare(exp1TextField.getText(), exp2TextField.getText());
						}

						@Override
						protected void done() {
							try {
								afterComparation = true;
								resultLabel.setText(get().toString());
								compareButton.setText("Clear");
							} catch (Exception e) {
							}
						}
					};
					swingWorker.execute();
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Regular expression comparator");
		setBounds(0, 0, 399, 110);
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel.setLayout(new FlowLayout());
		setContentPane(panel);
		exp1TextField.setColumns(16);
		exp2TextField.setColumns(16);
		panel.add(exp1TextField);
		panel.add(exp2TextField);
		panel.add(resultLabel);
		panel.add(compareButton);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui gui = new Gui();
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
