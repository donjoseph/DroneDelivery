package drone.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {


public String getReportConfigPath() throws IOException{
	Properties prop = new Properties();
    InputStream input  = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/configs/Configuration.properties");
    prop.load(input);
	 String reportConfigPath = prop.getProperty("reportConfigPath");
	 if(reportConfigPath!= null) return reportConfigPath;
	 else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath"); 
	}

}
