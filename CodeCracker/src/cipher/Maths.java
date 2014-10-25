package cipher;

public class Maths {
	
	public static float round(float f, int round) {
		int i = 1;
		for(int j = 0; j < round; j++) i *= 10;
		boolean up = false;
		
		float k = round1(f, i / 10);
		float k1 = round1(f, i);
		
		float p = k1 - k;
		
		if(p >= 0.5) up = true;

		if(!up) return (float) ((int) (f * i)) / (float) i;
		else return (float) ((int) (f * i) + 1) / (float) i;
	}
	
	public static float round1(float f, float round) {
		return (float) ((int) (f * round)) / (float) round;
	}
}
