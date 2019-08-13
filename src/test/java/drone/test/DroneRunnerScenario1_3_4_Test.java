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
		features={"src/test/resources/Features/DroneScenario1_3_4.feature"},
		//tags= {"@Scenario1_testCase8"},
		glue = {"drone.delivery","drone.utilities","drone.test"},
		plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		monochrome = true,
		dryRun = false)
public class DroneRunnerScenario1_3_4_Test {
	 @AfterClass
	 public static void writeExtentReport() throws IOException {
	 Reporter.loadXMLConfig(new File(new ConfigFileReader().getReportConfigPath()));
	 }
}