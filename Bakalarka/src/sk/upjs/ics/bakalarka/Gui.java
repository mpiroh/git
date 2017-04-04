package sk.upjs.ics.bakalarka;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Gui {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gui() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 466, 308);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField1 = new JTextField();
		textField1.setBounds(10, 11, 194, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(246, 11, 194, 20);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 42, 194, 182);
		frame.getContentPane().add(scrollPane1);
		
		final JTextPane textPane1 = new JTextPane();
		scrollPane1.setViewportView(textPane1);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(246, 42, 194, 182);
		frame.getContentPane().add(scrollPane2);
		
		final JTextPane textPane2 = new JTextPane();
		scrollPane2.setViewportView(textPane2);
		
		JButton compareButton = new JButton("Compare");
		compareButton.setBounds(10, 235, 89, 23);
		frame.getContentPane().add(compareButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(109, 235, 89, 23);
		frame.getContentPane().add(clearButton);
		
		final JLabel resultLabel = new JLabel("");
		resultLabel.setBounds(208, 239, 89, 14);
		frame.getContentPane().add(resultLabel);
		
		compareButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String regExp1 = textField1.getText();
				String regExp2 = textField2.getText();
				Porovnavac porovnavac = new Porovnavac();
				
				boolean result = porovnavac.compare(regExp1, regExp2);
				resultLabel.setText(Boolean.toString(result));
				
				RegularnyVyraz rv1 = new RegularnyVyraz(regExp1);
				textPane1.setText(rv1.toAutomat().determinizuj().minimalizuj().toString());
				
				RegularnyVyraz rv2 = new RegularnyVyraz(regExp2);
				textPane2.setText(rv2.toAutomat().determinizuj().minimalizuj().toString());
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resultLabel.setText("");
				textPane1.setText("");
				textPane2.setText("");
			}
		});
	}
}
