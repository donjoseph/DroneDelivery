package drone.delivery;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cucumber.api.DataTable;
import drone.config.Constants;
import drone.utilities.FileUtils;
import junit.framework.Assert;

public class DroneInitiator {
	private static ArrayList<String> allOrders = new ArrayList<String>();
	private static ArrayList<String> validOrders = new ArrayList<String>();
	private static ArrayList<String> output;
	private static String npf;
	private static String filename;
	final static Logger logger = Logger.getLogger(DroneInitiator.class);
	

	
	
	public void initializeFromFile(String filename) {
		try {
		DroneInitiator.filename=filename;
		allOrders=new FileUtils(Constants.inputFileLocation+"/"+filename).getFileContents();
		if(allOrders.size()==0) {
			Assert.assertTrue("No orders in file :"+filename,false);
		}
		}catch(Exception e){
		 e.printStackTrace();	
		 allOrders.clear();
		 Assert.assertTrue("Issue in processing the input file"+DroneInitiator.filename,false);	
		}
	}
	
	public void initializeFromList(DataTable data) {
		try {
		List<List<String>> all = data.raw();
		for(int i=0;i<all.size();i++) {
			allOrders.add(all.get(i).get(0).trim());
		}}catch(Exception e){
			 allOrders.clear();
			 e.printStackTrace();	
			 Assert.assertTrue("Issue in processing the input List",false);	
		}
	}
	
	public void setOutputFile(String filename) {
		DroneInitiator.filename=filename;
	}
	
	
	public void inputFormatTest() {
		try {
		InputFormatValidation validation = new InputFormatValidation();
		for(String content:allOrders) {
			if(validation.lineValidation(content.trim())) {
				validOrders.add(content.trim());
			}
		}}catch(Exception e) {
			 allOrders.clear();
			 validOrders.clear();
			 InputFormatValidation.inValidOrders.clear();
			 e.printStackTrace();	
			 Assert.assertTrue("Issue in format Validation",false);	
		}
	
	}
	
	
	public void schedulingTest() {
		logger.info("*************order processing started from inuput file: "+DroneInitiator.filename+"*************");
		//try {
		if(validOrders.size()>0) {
			new DroneSchedule().schedule(validOrders);
			validOrders.clear();
			allOrders.clear();
		}else {
			logger.warn("No valid orders found in input File : "+Constants.inputFileLocation+"/"+DroneInitiator.filename);	
		}
	/*}catch(Exception e) {
			 allOrders.clear();
			 validOrders.clear();
			 InputFormatValidation.inValidOrders.clear();
			 e.printStackTrace();	
			 Assert.assertTrue("Issue in Scheduling",false);
		}*/
		logger.info("**************order processing completed*******************");
	}
	
	public static void writeScheduleToFile(ArrayList<String> output,String nps) {
		try {
		DroneInitiator.output=output;
		DroneInitiator.output.add("NPS "+nps);
		new FileUtils(Constants.outputFileLocationProcessed+"/processed_"+DroneInitiator.filename).writeFileContents(DroneInitiator.output);
		DroneInitiator.output.clear();
		}catch(Exception e) {
			 allOrders.clear();
			 validOrders.clear();
			 InputFormatValidation.inValidOrders.clear();
			 DroneInitiator.output.clear();
			 e.printStackTrace();	
			 Assert.assertTrue("Issue in writing to output file",false);	
		}
	}
	
	
	public static void writeInvalidOrdersToFile() {
		try {
	new FileUtils(Constants.outputFileLocationRejected+"/rejected_"+DroneInitiator.filename).writeFileContents(InputFormatValidation.inValidOrders);
	InputFormatValidation.inValidOrders.clear();
		}catch(Exception e) {
			 allOrders.clear();
			 validOrders.clear();
			 InputFormatValidation.inValidOrders.clear();
			 DroneInitiator.output.clear();
			 e.printStackTrace();	
			 Assert.assertTrue("Issue in writing to output file",false);
		}
	}
	

}
