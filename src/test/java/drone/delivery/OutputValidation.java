package drone.delivery;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import drone.config.Constants;
import drone.utilities.FileUtils;
import junit.framework.Assert;

public class OutputValidation {
	
	private String actualFileLocation;
	private String expectedFileLocation;
	private ArrayList<String> actualOrders;
	private ArrayList<String> expectedOrders;
	final static Logger logger = Logger.getLogger(OutputValidation.class);
	
	public void validateOutputFile(String file) {
		
		logger.info("**********Validation started for output file "+file+"***********");
		int passFlag=0;
		actualFileLocation=Constants.outputFileLocationActual+"/processed_"+file;
		expectedFileLocation=Constants.outputFileLocationProcessed+"/processed_"+file;
		actualOrders=new FileUtils(actualFileLocation).getFileContents();
		expectedOrders=new FileUtils(expectedFileLocation).getFileContents();
		
		if(actualOrders.size()-1==expectedOrders.size()-1) {
			logger.info("Total number of processed orders are matching between expected and actual :"+(actualOrders.size()-1));
		}else {
			passFlag=1;
			logger.error("Total number of processed orders are not matching between expected and actual :"
		        +(expectedOrders.size()-1)+" and "+(actualOrders.size()-1));
		}
		
		
		if(actualOrders.get(actualOrders.size()-1).equals(expectedOrders.get(expectedOrders.size()-1))) {
			logger.info("Calculated NPS value is matching between expected and actual :"+actualOrders.get(actualOrders.size()-1));
		}else {
			passFlag=1;
			logger.error("Calculated NPS value is not matching between expected and actual :"
					+(expectedOrders.get(expectedOrders.size()-1))+" and "+(actualOrders.get(actualOrders.size()-1)));
		}
		
		for(int i=0;i<actualOrders.size()-1;i++) {
			if(actualOrders.get(i).equals(expectedOrders.get(i))) {
				logger.info("Order delivery is matching between expected and actual :"+actualOrders.get(i));
			}else {
				passFlag=1;
				if(actualOrders.get(i).split(" ")[0].equals(expectedOrders.get(i).split(" ")[0])) {
					logger.info("delivery time is not matching between expected and actual for the order:"+actualOrders.get(i).split(" ")[0]+
							"and the values are "+expectedOrders.get(i).split(" ")[1]+" and "+actualOrders.get(i).split(" ")[1]);
					
				}else {
					logger.info("order id is not matching between expected and actual :"
				+expectedOrders.get(i).split(" ")[0]+" and "+actualOrders.get(i).split(" ")[0]);
					
				}
			}
		}
		
		if(passFlag==1) {
			logger.info("***********output file validation failed***********");
			Assert.assertTrue("Output file validation failed",false);
		}
		logger.info("**********output file validation passed************");
		
	}
}
