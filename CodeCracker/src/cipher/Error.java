package cipher;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Error extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Error(String error) {
		setTitle("Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		contentPane.add(textArea, gbc_textArea);
		
		textArea.setText(error);
		
		setVisible(true);
	}

}
