package drone.test;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import drone.config.ConfigFileReader;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/Features/DroneScenariosFromFile.feature"},
		//tags= {"@Scenario1_testCase8"},
		glue = {"drone.delivery","drone.utilities","drone.test"},
		plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		monochrome = true,
		dryRun = false)
public class DroneScenariosFromFile {
	 @AfterClass
	 public static void writeExtentReport() throws IOException {
	 Reporter.loadXMLConfig(new File(new ConfigFileReader().getReportConfigPath()));
	 }
}