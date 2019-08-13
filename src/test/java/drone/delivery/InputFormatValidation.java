package drone.delivery;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;

import drone.utilities.StringUtilities;
import junit.framework.Assert;

public class InputFormatValidation {
	
	
	public static ArrayList<String> inValidOrders=new ArrayList<String>();
	public boolean lineValidation(String content) {
		
		boolean validity=true;
		
		StringTokenizer tokens=StringUtilities.getTokens(content);
		if(tokens.countTokens()==3) {
			String ordernum=tokens.nextToken();
			if(!ordernum.startsWith("WM") || ordernum.length()==6 ) {
				validity=false;
				inValidOrders.add(content+" Order cancelled reason:Order number is not valid");
			}
			
			String coordinates = tokens.nextToken();
		    StringTokenizer coorditokens = StringUtilities.getTokens(coordinates, "NWES");
            if(coorditokens.countTokens()==2) {
            	
            }else {
            	validity=false;
            	inValidOrders.add(content+" Order cancelled reason:coordinate field is not valid");
            }
            String orderTime=tokens.nextToken();
               try {
            		LocalTime.parse(orderTime);
            	}catch(Exception e) {
            		validity=false;
            		inValidOrders.add(content+" Order cancelled reason:time is not valid");
            }
            	   
		}else {
			validity=false;
			inValidOrders.add(content+" Order cancelled reason:Invalid number of fields");
		}
		
		return validity;
		
	}
	

}
