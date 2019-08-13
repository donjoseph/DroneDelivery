package drone.delivery;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;

import drone.config.Constants;
import drone.utilities.StringUtilities;

public class DroneSchedule {


	private Drone sched = new Drone();
	final static Logger logger = Logger.getLogger(DroneSchedule.class);
	private String splitter=Constants.splitter;
	private String returnTime;
	private String deliveredTime;
	private String currOrder;
	private String startTime;
	private String orderTime;
	private String coordinates;
	private boolean isPromoter;
	
	/**
     * function to process all the orders
     * @param orders - orders are received as a list of raw format of string
     *               which further get processed for delivery 
     */	
	public void schedule(ArrayList<String> orders) {
		
		sched.setCurrTime(sched.getStartTime());
		queueCreation(orders);
		sched.sortQueue();
		innerScheduling(sched.getListQueue());
		loopSchedule();
		logFinalSchedule();
		writeToOutputFile();
		
		
	}
	
	/**
     * function used to schedule the delivery for the filtered orders according to the elapsed time
     * @param innerQueue - filtered list of orders according to the elapsed time
     */
	public void innerScheduling(ArrayList<String> innerQueue) {
		int k=innerQueue.size();
		for(int i=0;i<k;i++) {
				currOrder=innerQueue.get(i);
			    orderTime=sched.getOrderTimeFromOrder(currOrder);
			    coordinates=sched.getCordinatesOrder(currOrder);
				if(checkOverTimeOrder(currOrder)){
					setReturnTime(currOrder);
					setDeliveryTime(currOrder);
					processOrder();
				    innerQueue.remove(i);
			        queueCreation(sched.tempOrderCreation(sched.getPendingOrders()));
			        i=-1;
			        sched.sortQueue();
					
				}else {
					pushToInValidOrders(formatOrder(currOrder)+" Order cancelled reason: Delivery time/Return time exceeds 10 pm");
					innerQueue.remove(i);				
			        i=-1;
				}
				k=innerQueue.size();
		}
	}
	
	 /**
     * function used to filter orders and create the temporary queue 
     *            which should be processed according to the elapsed time
     * @param orders - orders are received as a list of raw format of string
     *               which further get processed for delivery 
     */	
	public void queueCreation(ArrayList<String> orders) {
		sched.getPendingOrders().clear();
		 
		for(int i=0;i<orders.size();i++) {
			currOrder=orders.get(i);
			filterOrderFields(currOrder);
		    if(sched.getLocalTime(sched.getCurrTime()).compareTo(sched.getLocalTime(orderTime))>=0) {
			   if(sched.getLocalTime(orderTime).compareTo(sched.getLocalTime(Constants.orderStartTime))>=0) {
		    		pushToQueue();				
		    	}else {
		    		pushToInValidOrders(orders.get(i)+" Order cancelled reason: Order placed before 5 am");
		    	}
		    }else {
		    	sched.addToPendingOrders(orders.get(i));	
		    }
		}
	}
	
	/**
     * function used to calculate the Rating of the delivery and 
     *            decide whether it is a promoter or detractor
     * @param orderTime - time at which order get placed
     * @param deliveredTime - time at which order get delivered
     */
	private void calculateRating(String orderTime,String deliveredTime) {
		boolean isPromoter=false;
		double timeDiff=sched.findTotalSeconds(deliveredTime)
				-sched.findTotalSeconds(orderTime);
		if(timeDiff<=Constants.promotorLimit) {
			sched.setPromotors(sched.getPromotors()+1);
			isPromoter=true;
		}else if(timeDiff>Constants.detractorLimit) {
			sched.setDetractors(sched.getDetractors()+1);
		}
		this.isPromoter=isPromoter;
	}
	
	/**
     * function used to loop the delivery until the last pending order
     */
	private void loopSchedule() {
		while(sched.getPendingOrders().size()!=0) {
			sched.setCurrTime(sched.getPendingOrders().get(0).split(Constants.orderSplitter)[2]);
			queueCreation(sched.tempOrderCreation(sched.getPendingOrders()));
			innerScheduling(sched.getListQueue());
		}
	}
	
	/**
     * function used to calculate the return time of the drone after delivery
     * @param time - time in seconds required for an order to complete the delivery and return to hub
     * @return return time of the drone
     */
	private String findReturnTime(String time) {
		
		return sched.getLocalTime(sched.getCurrTime()).
				plusSeconds((long) Double.parseDouble(time)).toString();
	}
	
	/**
     * function used to calculate the delivery time of the drone for a particular order
     * @param time - time in seconds required for an order to complete the delivery and return to hub
     * @return delivery time of the order
     */
	private String findDeliveryTime(String time) {
		return sched.getLocalTime(sched.getCurrTime()).
				plusSeconds((long) Double.parseDouble(time)/2).toString();
	}
	
	private void setReturnTime(String time) {
		this.returnTime=findReturnTime(sched.getTimeToDeliverAndReturnFromOrder(time));
	}
	
	private void setDeliveryTime(String time) {
		this.deliveredTime=findDeliveryTime(sched.getTimeToDeliverAndReturnFromOrder(time));
	}
	
	
	/**
     * function used to check the order time or order return time is over 10 pm.
     * @param time -order time in hh:mm:ss format 
     * @return boolean true/false
     */
	private boolean checkOverTimeOrder(String time) {

		String returnTime=sched.getLocalTime(sched.getCurrTime()).
		plusSeconds((long) Double.parseDouble(sched.getTimeToDeliverAndReturnFromOrder(time))).toString();

		return sched.getLocalTime(returnTime).compareTo(
				sched.getLocalTime(sched.getEndTime()))<=0  
				&&  sched.getLocalTime(sched.getOrderTimeFromOrder(time)).compareTo(
						sched.getLocalTime(sched.getEndTime()))<=0;
	}
	
	/**
     * function used to set the current time f the order scheduler
     */
	private void setCurrTimeOfSchedule() {
		sched.setCurrTime(sched.getLocalTime(sched.getCurrTime()).
				plusSeconds((long) Double.parseDouble(sched.getTimeToDeliverAndReturnFromOrder(currOrder))).toString());
	}
	
	/**
     * function used to log the processed order
     */
	private void logProcessedOrder() {
		
		logger.info("==========Order is processing for "+sched.getIdFromOrder(currOrder)+"==========");
		logger.info("          order delivery start time is "+startTime);
		logger.info("          order delivery time is "+deliveredTime);
		logger.info("          Drone return time is "+returnTime);
		logger.info("          Customer is a Promotor: "+isPromoter);

	}
	
	/**
     * function used to process a particular order
     */
	private void processOrder() {
		calculateRating(sched.getOrderTimeFromOrder(currOrder), deliveredTime);
		startTime=sched.getCurrTime();
		setCurrTimeOfSchedule();
		sched.addToOutput(sched.getIdFromOrder(currOrder)+" "+startTime);
		logProcessedOrder();
	}
	
	/**
     * function used to filter out fields from a raw order string
     * @param order - single order line from the input file
     */
	private void filterOrderFields(String order) {
		StringTokenizer tokens=StringUtilities.getTokens(order);
		sched.setId(tokens.nextToken());
		coordinates=tokens.nextToken();
		StringTokenizer cordinateTokens=StringUtilities.getTokens(coordinates,"NEWS");
		sched.setXCordinate(Double.parseDouble(cordinateTokens.nextToken()));
		sched.setYCordinate(Double.parseDouble(cordinateTokens.nextToken()));
		orderTime=tokens.nextToken();
	
	}
	
	/**
     * function used to write the final schedule to an output file
     */
	private void writeToOutputFile() {
		if(sched.getOutputList().size()>0)
		DroneInitiator.writeScheduleToFile(sched.getOutputList(), String.valueOf(sched.calculateNps()*100));
		DroneInitiator.writeInvalidOrdersToFile();
	}
	
	/**
     * function used to log the final schedule
     */
	private void logFinalSchedule() {
		logProcessedRecords();
		logger.info("NPS value for the schedule :"+sched.calculateNps()*100);
	}
	
	/**
     * function used to calculate distance and time for an order and add it to the schedule queue
     */
	private void pushToQueue() {
		double distance=Math.sqrt((sched.getXCordinate()*sched.getXCordinate())+
				(sched.getYCordinate()*sched.getYCordinate()));
		if(distance*60>Constants.orderMaxDistance) {
			pushToInValidOrders(formatOrder(currOrder)+" Order cancelled reason: Distance exceeds city limit");
		}else {
		double timeTodeliverAndReturn=Math.ceil(2*(distance/Constants.speed)*60);
		double totalSecondsOfOrderTime=sched.findTotalSeconds(orderTime);
		sched.addToListQueue(sched.getId()+splitter+timeTodeliverAndReturn+
				splitter+totalSecondsOfOrderTime+splitter+orderTime+splitter+coordinates);
		}
	}
	
	private void pushToInValidOrders(String inValidOrder) {
		InputFormatValidation.inValidOrders.add(inValidOrder);
		logger.warn(inValidOrder);
	}
	
	private String formatOrder(String order) {
		if(order.contains(Constants.splitter)) {
			return sched.getIdFromOrder(order)+" "+coordinates+" "+orderTime;
		}else {
			return order;
		}
		
	}
	
	private void logProcessedRecords() {
		String outputMessage="";
		for(int i=0;i<sched.getOutputList().size();i++) {
			outputMessage=outputMessage+sched.getOutputList().get(i)+"\n";
		}
		logger.info("Expected schedule is :\n"+outputMessage.substring(0, outputMessage.length()-2));
	}
}
