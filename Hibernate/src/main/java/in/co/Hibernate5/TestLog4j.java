package in.co.Hibernate5;

import org.apache.log4j.Logger;

public class TestLog4j {
	
	static Logger log = Logger.getLogger(TestLog4j.class);
	
	public static void main(String[] args) {
		
		log.debug("This is debug Statemnet");
		log.info("This is Info Statement");
		log.warn("This is warn Statement");
		log.error("This is error Statement");
		log.fatal("This is Fatal Statment");
		
		int i = 10;
		try {
			int x = 5 / i;
		} catch (RuntimeException e) {
			log.error(" My Error ", e);
		}
	}

}
