package cipher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

public class CipherWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	public JTextArea input, output, percentage;
	public JSpinner shift;
	public JSpinner[] letters = new JSpinner[26];

	public CipherWindow(final Main main) {
		super("Frequency decoder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 550);
		setLocationRelativeTo(null);
		
		List<BufferedImage> image = new ArrayList<BufferedImage>();
		try {
			image.add(ImageIO.read(Main.class.getResource("/icon_16.png")));
			image.add(ImageIO.read(Main.class.getResource("/icon_32.png")));
			image.add(ImageIO.read(Main.class.getResource("/icon_64.png")));
			image.add(ImageIO.read(Main.class.getResource("/icon_128.png")));
			image.add(ImageIO.read(Main.class.getResource("/icon_256.png")));
		} catch (Exception e1) {
			main.catchException(e1);
		}
		setIconImages(image);
		
		getContentPane().setLayout(new MigLayout("", "[grow][][grow][][][][][][][][][][][][][grow][][][][][][][][][][][pref!,fill][grow]", "[][][][][][][][][][][][][][][][][][][][grow]"));
		
		JLabel lblSpecialOps = new JLabel("Special Ops");
		getContentPane().add(lblSpecialOps, "cell 1 0");
		
		JLabel lblInputCode = new JLabel("Input code");
		getContentPane().add(lblInputCode, "cell 2 0");
		
		JLabel lblShift = new JLabel("Shift");
		getContentPane().add(lblShift, "cell 11 0");
		
		input = new JTextArea();
		input.setWrapStyleWord(true);
		input.setLineWrap(true);
		
		output = new JTextArea();
		output.setWrapStyleWord(true);
		output.setLineWrap(true);
		
		shift = new JSpinner();
		shift.setModel(new SpinnerNumberModel(0, 0, 25, 1));
		getContentPane().add(shift, "cell 12 0");
		
		JLabel lblOutputCode = new JLabel("Output code");
		getContentPane().add(lblOutputCode, "cell 14 0");
		
		JLabel lblLetterPercentages = new JLabel("Letter Percentages");
		getContentPane().add(lblLetterPercentages, "cell 26 0");
		
		percentage = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(percentage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPane, "cell 26 1 2 19,grow");
		
		JScrollPane scrollPane_1 = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane_1, "cell 14 1 12 19,grow");
		
		JScrollPane scrollPane_2 = new JScrollPane(input, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane_2, "cell 2 1 12 19,grow");
		
		JButton btnDecode = new JButton("Decode");
		getContentPane().add(btnDecode, "cell 1 1,growx");
		btnDecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.shift = (int) shift.getValue();
				main.decode(input, output, percentage);
				int s1 = getWidth();
				int s2 = getHeight();
				setSize(s1 + 200, s2 + 200);
				setSize(s1, s2);
			}
		});
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane_3, "cell 0 0 1 20,grow");
		
		JPanel panel = new JPanel();
		scrollPane_3.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[][]", "[][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		String[] alp = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		for(int i = 0; i < 26; i++) {
			JLabel lblA = new JLabel(alp[i]);
			panel.add(lblA, "cell 0 " + i);
			
			letters[i] = new JSpinner();
			SpinnerModel model = new SpinnerListModel(alp);
			model.setValue(alp[i]);
			letters[i].setModel(model);
			panel.add(letters[i], "cell 1 " + i);
		}

		JButton btnMatchPercentage = new JButton("Match Percentage");
		getContentPane().add(btnMatchPercentage, "cell 1 2,growx");
		btnMatchPercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.matchPercentage(input, output, percentage, shift);
				int s1 = getWidth();
				int s2 = getHeight();
				setSize(s1 + 200, s2 + 200);
				setSize(s1, s2);
			}
		});
		
		JButton btnSetLetters = new JButton("Set letters");
		btnSetLetters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] caps = new String[26];
				for(int i = 0; i < 26; i++) {
					caps[i] = (String) letters[i].getValue();
				}
				main.decode(input, output, percentage, caps);
				int s1 = getWidth();
				int s2 = getHeight();
				setSize(s1 + 200, s2 + 200);
				setSize(s1, s2);
			}
		});
		getContentPane().add(btnSetLetters, "cell 1 4,growx");
		
		JButton btnSave = new JButton("Save");
		getContentPane().add(btnSave, "cell 1 3,growx");
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
		
		JButton btnMatchPercentage_1 = new JButton("Match Percentage 2");
		btnMatchPercentage_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.matchPercentage2(input, output, percentage, shift, letters);
				int s1 = getWidth();
				int s2 = getHeight();
				setSize(s1 + 200, s2 + 200);
				setSize(s1, s2);
			}
		});
		getContentPane().add(btnMatchPercentage_1, "cell 1 5,growx");
		
		setVisible(true);
		
		int s1 = getWidth();
		int s2 = getHeight();
		setSize(s1 + 200, s2 + 200);
		setSize(s1, s2);
	}
}
