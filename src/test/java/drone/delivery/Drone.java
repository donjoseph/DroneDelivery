package drone.delivery;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import drone.config.Constants;
import drone.utilities.StringUtilities;;


public class Drone {
	
	private String currTime;
	private double xCordinate;
	private double yCordinate;
	private String id;
	private double detractors=0;
	private double promotors=0;
	private double npf;
	private ArrayList<String> output=new ArrayList<String>();
	private ArrayList<String> pendingOrders=new ArrayList<String>();
	private ArrayList<String> listQueue=new ArrayList<String>();

	
	public ArrayList<String> getOutputList() {
        return this.output;
	}
	
	public ArrayList<String> getPendingOrders() {
        return this.pendingOrders;
	}
	
	public ArrayList<String> getListQueue() {
        return this.listQueue;
	}
	
	
	public void addToOutput(String value) {
        this.output.add(value);
	}
	
	
	public void addToPendingOrders(String value) {
        this.pendingOrders.add(value);
	}
	public void addToListQueue(String value) {
        this.listQueue.add(value);
	}
	public void removeFromOutput(int i) {
        this.output.remove(i);
	}
	public void removeFromPendingOrders(int i) {
        this.pendingOrders.remove(i);
	}
	public void removeFromListQueue(int i) {
        this.listQueue.remove(i);
	}
	
	public void setXCordinate(double x) {
	        this.xCordinate = x;
	}
	public double getXCordinate() {
        return this.xCordinate;
	}
	public void setYCordinate(double y) {
        this.yCordinate = y;
	}
	public double getYCordinate() {
        return this.yCordinate;
	}
	public void setId(String id) {
        this.id = id;
	}
	public String getId() {
        return this.id;
	}
	public void setPromotors(double proms) {
        this.promotors = proms;
	}
	public double getPromotors() {
		return this.promotors;
	}
	public void setDetractors(double detracts) {
        this.detractors = detracts;
	}
	public double getDetractors() {
		return this.detractors;
	}
	public void setCurrTime(String time) {
        this.currTime = time;
	}
	public String getCurrTime() {
		return this.currTime;
	}
	public String getStartTime() {
		return Constants.startTime;
	}
	public String getEndTime() {
		return Constants.endTime;
	}
	
	public String getSplitter() {
		return Constants.splitter;
	}
	
	public void setNpf(double npf) {
		this.npf=npf;
	}
	public double getNpf() {
		return this.npf;
	}
	
	/**
     * function to sort pending list of orders
     */
	public void sortPendingOrdersQueue() {
		Collections.sort(this.pendingOrders, new Comparator<String>() {
		    public int compare(String o1, String o2) {              
		        String[] o1Arr=o1.split(" ");
		        String[] o2Arr=o2.split(" ");
		      	 
				return o1Arr[2].compareTo(o2Arr[2]);
	    
		    }
		});
	}
	
	/**
     * function to sort the filtered list of orders
     */
	public void sortQueue() {
		Collections.sort(this.listQueue, new Comparator<String>() {
		    public int compare(String o1, String o2) {              
		        String[] o1Arr=o1.split(Constants.splitter);
		        String[] o2Arr=o2.split(Constants.splitter);
		        
		        
		        	   if(o1Arr[1].equalsIgnoreCase(o2Arr[1])) {
				        	
				        	return Double.valueOf(Double.parseDouble(o1Arr[2])).
				        			compareTo(Double.valueOf(Double.parseDouble(o2Arr[2])));
				          	
				        }else {
				        	return Double.valueOf(Double.parseDouble(o1Arr[1])).
				        			compareTo(Double.valueOf(Double.parseDouble(o2Arr[1])));
				        }
		        
		        
		    }
		});
	}
	

	/**
     * function to find the total seconds from a HH:mm:ss formatted time
     * @param time - time in HH:mm:ss format
     */
	public double findTotalSeconds(String time) {
		if(time.split(":").length==2)time=time+":00";
		StringTokenizer timeTokens=StringUtilities.getTokens(time,":");
		double hours=Double.parseDouble(timeTokens.nextToken())*3600;
		double mints=Double.parseDouble(timeTokens.nextToken())*60;
		double secods=Double.parseDouble(timeTokens.nextToken());
		double totalSeconds=hours+mints+secods;
		
		return totalSeconds;
		
	}
	
	/**
     * function to create a temporary list of orders to hold it temporarily
     * @return list of orders
     */
	public ArrayList<String> tempOrderCreation(ArrayList<String> orders) {
		ArrayList<String> tempOredrs=new ArrayList<String>();
		for(String s:orders) {
			tempOredrs.add(s);
		}
		return tempOredrs;
		
	}
	
	/**
     * function to calculate npf
     * @return npf
     */
	public double calculateNps() {
		return (this.promotors/this.output.size()) - (this.detractors/this.output.size());
	}
	
	public LocalTime getLocalTime(String time) {
		return LocalTime.parse(time);
	}
	
	public String getIdFromOrder(String order) {
		return order.split(Constants.splitter)[0];
	}
	
	public String getTimeToDeliverAndReturnFromOrder(String order) {
		return order.split(Constants.splitter)[1];
	}
	
	public String getTotalSecondsFromOrder(String order) {
		return order.split(Constants.splitter)[2];
	}
	
	public String getOrderTimeFromOrder(String order) {
		return order.split(Constants.splitter)[3];
	}
	
	public String getCordinatesOrder(String order) {
		return order.split(Constants.splitter)[4];
	}
	

	

}
