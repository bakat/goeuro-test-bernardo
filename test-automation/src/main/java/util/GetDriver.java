package util;

public class GetDriver {

	private static String firefoxDriver = "/browserDrivers/geckodriver";
	
	public static String fireFox(){
		return System.getProperty("user.dir") + firefoxDriver;
	}
}