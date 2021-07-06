package mainWindow;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logs {
	public final String logFilePath="./Log/";
	
	public Logger startLoggiing(Class whichClass)
	{
		Logger logger = Logger.getLogger("whichClass");  
	    FileHandler fh;  
	    
	    SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyyhhss");
	    
	    try {  
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler(logFilePath+sd.format(new Date())+".log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        //logger.info("My first log");  
	        logger.info("Logs Start");
	        return logger;
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
	    return logger;
	    
	}

}
