package drone.utilities;

import java.util.StringTokenizer;

public class StringUtilities {

	
	public static StringTokenizer getTokens(String str) {
		return new StringTokenizer(str);
	}
	
	public static StringTokenizer getTokens(String str,String delimter) {
		return new StringTokenizer(str,delimter);
	}
	
	
}
