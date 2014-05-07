package com.adaranet.util;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.adaranet.service.DeviceService;
import com.adaranet.service.PortsService;

public class AppUtils {

	private static Logger logger = Logger.getLogger(AppUtils.class);
	
	private static final int NUM_CHARS = 20;
	private static String chars = "abcdefghijklmonpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static Random random = new Random();
	
	@Autowired
	private static Neo4jTemplate template;
	@Autowired
	private static DeviceService deviceService;
	@Autowired
	private static PortsService portsService;
	
	/**
	 * Method which takes in number of chars.(length of new unique string),
	 * and the chars String.. with all the alphabets.. upper/lower case and numbers.
	 * Generates a random string using the char string length!
	 * @return unique string
	 */
	public static String getUniqueID() {
		char[] buf = new char[NUM_CHARS];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = chars.charAt(random.nextInt(chars.length()));
		}
		return new String(buf);
	}
	
	/**
	 * Returns a psuedo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimim value
	 * @param max Maximim value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int generateRandomInt(int max) {

	    // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
		int randomNum = 0;
		try {
			if(max <= 0) {
				logger.info("Max == 0 .. Returning 0 : Max Value : "+max);
				return 0;
			}
			randomNum = random.nextInt(max - 0) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

	    return randomNum;
	}
	
	
	/**
	 * Method to clean the whole Neo4j Graph Database using
	 * device/ports/neo4jTemplate service dependency injection.
	 * Helpful for JUnit Test.
	 * @return void
	 */
	public static void cleanWholeNeo4jGraphDatabase() {
		try {
			logger.info("***** Cleaning Neo4jDatabase! *****");
			if(deviceService.count() > 0) {
	    		logger.info("Devices present in the Graph : Cleaning them! : Count : "+deviceService.count());
				deviceService.deleteAll();
			} else {
				logger.info("No Devices Present in the Graph!");
			}
	    	if(portsService.count() > 0) {
	    		logger.info("Ports present in the Graph : Cleaning them! : Count : "+portsService.count());
	    		portsService.deleteAll();
	    	} else {
	    		logger.info("No Ports Present in the Graph!");
	    	}
			logger.info("***** Neo4jDatabase Cleaned Successfully! *****");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method to replace 'Device1-em0' type portnames,
	 * where 'Device1-' will be removed.
	 * @param portName
	 * @return portName/substring'd(filtered) portName
	 */
	public static String replacePorts(String portName) {
		try {
			if(!portName.trim().isEmpty()) {
				portName = portName.substring(portName.lastIndexOf('-') + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return portName;
	}

}
