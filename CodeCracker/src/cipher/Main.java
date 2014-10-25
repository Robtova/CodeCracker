package cipher;

import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class Main {
	
	public static String AB_caps =   "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static  String AB_lower = "abcdefghijklmnopqrstuvwxyz";
	
	public int shift = 0, letters;
	public CipherWindow c;
	
	public int[] letter_per = new int[26];
	public float[] letter_per_vals = {8.2f,1.5f, 2.8f,4.3f,12.7f,2.2f,2.0f,2.0f,7.0f,0.2f,0.8f,4.0f,2.4f,6.7f,7.5f,1.9f,0.1f,6.0f,6.3f,9.1f,2.8f,1.0f,2.4f,0.2f,2.0f,0.1f};
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		c = new CipherWindow(this);
	}
	
	public void catchException(Exception e) {
		if(c != null) c.dispose();
		new Error(e.getMessage());
	}
	
	public void decode(JTextArea input, JTextArea output, JTextArea percentage) {
		String out = "";
		String in = input.getText().toUpperCase();
		
		for(int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			int read = AB_caps.indexOf(c);
			
			if(read != -1) {
				out = out + getLetter(read);
			} else {
				out = out + c;
			}
		}
		
		out = out.toUpperCase();
		output.setText(out);
		
		letter_per = new int[26];
		letters = 0;
		
		for(int i = 0; i < out.length(); i++) {
			char c = out.charAt(i);
			
			if(AB_caps.indexOf(c) != -1) {
				letter_per[AB_caps.indexOf(c)]++;
				letters++;
			}
		}
		
		percentage.setText("");
		
		for(int i = 0; i < 26; i++) {
			percentage.append(AB_caps.charAt(i) + ":   " + Maths.round(((float) letter_per[i] / (float) letters) * 100.0f, 1) + "     /     " + letter_per_vals[i] + "\n");
		}
	}
	
	public void decode(JTextArea input, JTextArea output, JTextArea percentage, String[] caps) {
		String out = "";
		String in = input.getText().toUpperCase();
		
		for(int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			int read = AB_caps.indexOf(c);
			
			if(read != -1) {
				out = out + getLetter(read, caps);
			} else {
				out = out + c;
			}
		}
		
		out = out.toUpperCase();
		output.setText(out);
		
		letter_per = new int[26];
		letters = 0;
		
		for(int i = 0; i < out.length(); i++) {
			char c = out.charAt(i);
			
			if(AB_caps.indexOf(c) != -1) {
				letter_per[AB_caps.indexOf(c)]++;
				letters++;
			}
		}
		
		percentage.setText("");
		
		for(int i = 0; i < 26; i++) {
			percentage.append(AB_caps.charAt(i) + ":   " + Maths.round(((float) letter_per[i] / (float) letters) * 100.0f, 1) + "     /     " + letter_per_vals[i] + "\n");
		}
	}
	
	public void decode2(String s, int shift) {
		String out = "";
		String in = s.toUpperCase();
		
		this.shift = shift;
		
		for(int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			int read = AB_caps.indexOf(c);
			
			if(read != -1) {
				out = out + getLetter(read);
			} else {
				out = out + c;
			}
		}
		
		out = out.toUpperCase();
		
		letter_per = new int[26];
		letters = 0;
		
		for(int i = 0; i < out.length(); i++) {
			char c = out.charAt(i);
			
			if(AB_caps.indexOf(c) != -1) {
				letter_per[AB_caps.indexOf(c)]++;
				letters++;
			}
		}
	}
	
	private float[] differences;
	
	public void matchPercentage(JTextArea input, JTextArea output, JTextArea percentage, JSpinner shift) {
		differences = new float[26];
		String in = input.getText().toUpperCase();
		for(int i = 0; i < 26; i++) {
			decode2(in, i);
			float k = 0;
			for(int j = 0; j < 26; j++) {
				k += (((float) letter_per[j] / (float) letters) * 100.0f) / letter_per_vals[j];
			}
			k /= 26.0f;
			k = Math.abs(k - 1);
			differences[i] = k;
		}
		int p = 0;
		for(int i = 0; i < 26; i++) {
			if(differences[i] < differences[p]) p = i;
		}
		this.shift = p;
		shift.setValue(p);
		decode(input, output, percentage);
	}
	
	public void matchPercentage2(JTextArea input, JTextArea output, JTextArea percentage, JSpinner shift, JSpinner[] letts) {
		String[] caps = new String[26];
		int[] cap = new int[26], cap2 = new int[26];
		differences = new float[26];
		String in = input.getText().toUpperCase();
		decode2(in, 0);
		int k1 = 0, k2 = 0;
		for(int i = 0; i < 26; i++) {
			if(letter_per[i] < letter_per[k1]) {
				k1 = i;
			}
			if(letter_per_vals[i] < letter_per_vals[k2]) {
				k2 = i;
			}
		}
		for(int i = 0; i < 26; i++) {
			cap[i] = k1;
			for(int j = 0; j < 26; j++) {
				if(letter_per[j] >= letter_per[cap[i]]) {
					if(i == 0) {
						cap[i] = j;
					} else if(letter_per[j] <= letter_per[cap[i - 1]]) {
						boolean b = true;
						for(int k = 0; k < i; k++) {
							if(j == cap[k]) b = false;
						}
						if(b) cap[i] = j;
					}
				}
			}
		}
		for(int i = 0; i < 26; i++) {
			cap2[i] = k2;
			for(int j = 0; j < 26; j++) {
				if(letter_per_vals[j] >= letter_per_vals[cap2[i]]) {
					if(i == 0) {
						cap2[i] = j;
					} else if(letter_per_vals[j] <= letter_per_vals[cap2[i - 1]] && j != cap2[i - 1]) {
						boolean b = true;
						for(int k = 0; k < i; k++) {
							if(j == cap2[k]) b = false;
						}
						if(b) cap2[i] = j;
					}
				}
			}
		}
		for(int i = 0; i < 26; i++) {
			caps[cap[i]] = "" + AB_caps.substring(cap2[i], cap2[i] + 1);
			letts[cap[i]].setValue(caps[cap[i]]);
		}
		this.shift = 0;
		shift.setValue(0);
		decode(input, output, percentage, caps);
	}
	
	public String getLetter(int i) {
		i += shift;
		
		while(i < 0) i += AB_lower.length();
		while(i >= AB_lower.length()) i -= AB_lower.length();
		
		String s = "";
		
		s = "" + AB_lower.charAt(i);
		
		return s;
	}
	
	public String getLetter(int i, String[] caps) {
		String s = "";
		
		s = "" + caps[i];
		
		return s;
	}
}
