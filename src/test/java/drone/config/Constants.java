package drone.config;

public class Constants {

	public static final String splitter="##";
	public static final String orderSplitter=" ";
	public static final double speed=1;
	public static final String startTime="06:00:00";
	public static final String endTime="22:00:00";
	public static final String orderStartTime="05:00:00";
	public static final double promotorLimit=1.5*3600;
	public static final double detractorLimit=3.5*3600;
	public static final double orderMaxDistance=10*3600;//equals to 600 blocks
	public static final String inputFileLocation=System.getProperty("user.dir")+"/src/test/resources/data/input";
	public static final String outputFileLocationProcessed=System.getProperty("user.dir")+"/src/test/resources/data/output/expected_output/processed";
	public static final String outputFileLocationRejected=System.getProperty("user.dir")+"/src/test/resources/data/output/expected_output/rejected";
	public static final String outputFileLocationActual=System.getProperty("user.dir")+"/src/test/resources/data/output/actual_output";
	
	

}
