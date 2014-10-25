package cipher;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class Cipher extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextArea input, output, percentage;
	public JSpinner shift;

	public Cipher(final Main main) {
		setTitle("Frequency decoder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSpecialOps = new JLabel("Special Ops");
		GridBagConstraints gbc_lblSpecialOps = new GridBagConstraints();
		gbc_lblSpecialOps.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecialOps.gridx = 0;
		gbc_lblSpecialOps.gridy = 0;
		contentPane.add(lblSpecialOps, gbc_lblSpecialOps);
		
		JLabel lblInputCode = new JLabel("Input code");
		GridBagConstraints gbc_lblInputCode = new GridBagConstraints();
		gbc_lblInputCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputCode.gridx = 1;
		gbc_lblInputCode.gridy = 0;
		contentPane.add(lblInputCode, gbc_lblInputCode);
		
		JLabel lblShift = new JLabel("Shift");
		GridBagConstraints gbc_lblShift = new GridBagConstraints();
		gbc_lblShift.insets = new Insets(0, 0, 5, 5);
		gbc_lblShift.gridx = 5;
		gbc_lblShift.gridy = 0;
		contentPane.add(lblShift, gbc_lblShift);
		
		shift = new JSpinner();
		shift.setModel(new SpinnerNumberModel(0, 0, 25, 1));
		GridBagConstraints gbc_shift = new GridBagConstraints();
		gbc_shift.insets = new Insets(0, 0, 5, 5);
		gbc_shift.gridx = 6;
		gbc_shift.gridy = 0;
		contentPane.add(shift, gbc_shift);
		
		JLabel lblOutputCode = new JLabel("Output code");
		GridBagConstraints gbc_lblOutputCode = new GridBagConstraints();
		gbc_lblOutputCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutputCode.gridx = 13;
		gbc_lblOutputCode.gridy = 0;
		contentPane.add(lblOutputCode, gbc_lblOutputCode);
		
		JLabel lblLetterPercentages = new JLabel("Letter Percentages");
		GridBagConstraints gbc_lblLetterPercentages = new GridBagConstraints();
		gbc_lblLetterPercentages.insets = new Insets(0, 0, 5, 0);
		gbc_lblLetterPercentages.gridx = 14;
		gbc_lblLetterPercentages.gridy = 0;
		contentPane.add(lblLetterPercentages, gbc_lblLetterPercentages);
		setVisible(true);
		
		JButton btnNewButton = new JButton("Decode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.shift = (int) shift.getValue();
				main.decode(input, output, percentage);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		output = new JTextArea();
		JScrollPane sp1 = new JScrollPane (output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		output.setWrapStyleWord(true);
		output.setLineWrap(true);
		GridBagConstraints gbc_output = new GridBagConstraints();
		gbc_output.gridheight = 24;
		gbc_output.insets = new Insets(0, 0, 0, 5);
		gbc_output.gridwidth = 6;
		gbc_output.fill = GridBagConstraints.BOTH;
		gbc_output.gridx = 8;
		gbc_output.gridy = 1;
		contentPane.add(sp1, gbc_output);
		
		percentage = new JTextArea();
		GridBagConstraints gbc_percentage = new GridBagConstraints();
		gbc_percentage.gridheight = 17;
		gbc_percentage.fill = GridBagConstraints.BOTH;
		gbc_percentage.gridx = 14;
		gbc_percentage.gridy = 1;
		contentPane.add(percentage, gbc_percentage);
		
		input = new JTextArea();
		JScrollPane sp2 = new JScrollPane (input, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		input.setWrapStyleWord(true);
		input.setLineWrap(true);
		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.gridheight = 19;
		gbc_input.gridwidth = 6;
		gbc_input.insets = new Insets(0, 0, 0, 5);
		gbc_input.fill = GridBagConstraints.BOTH;
		gbc_input.gridx = 1;
		gbc_input.gridy = 1;
		contentPane.add(sp2, gbc_input);
		
		JButton btnMatchPercentage = new JButton("Match Percentage");
		btnMatchPercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.matchPercentage(input, output, percentage, shift);
			}
		});
		GridBagConstraints gbc_btnMatchPercentage = new GridBagConstraints();
		gbc_btnMatchPercentage.anchor = GridBagConstraints.NORTH;
		gbc_btnMatchPercentage.insets = new Insets(0, 0, 5, 5);
		gbc_btnMatchPercentage.gridx = 0;
		gbc_btnMatchPercentage.gridy = 2;
		contentPane.add(btnMatchPercentage, gbc_btnMatchPercentage);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File f = new File("decoded0.txt");
					int i = 0;
					while(f.exists()) {
						i++;
						f = new File("decoded" + i + ".txt");
					}
					FileWriter fw = new FileWriter(f);
					fw.write(output.getText());
					fw.close();
				} catch (Exception ex) {
					main.catchException(ex);
				}
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 3;
		contentPane.add(btnSave, gbc_btnSave);
	}
}
