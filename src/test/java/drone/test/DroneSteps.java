package drone.test;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import drone.delivery.DroneInitiator;
import drone.delivery.OutputValidation;
import drone.delivery.Drone;

public class DroneSteps {

	private static DroneInitiator di=new DroneInitiator();

	@Given("^run the scheduler$")
	public void runScheduler(){
		di.schedulingTest(); 
	}
	
	@Given("^get the input file \"([^\"]*)\"$")
	public void getInputFile(String filename) {
		di.initializeFromFile(filename);
	  
	}

	@Given("^do the input validation$")
	public void validateInputFile() {
		di.inputFormatTest();
	}
	
	@Given("^get the input array with data$")
	public void getArray(DataTable data) {
		di.initializeFromList(data);
	}
	
	@Given("^set the output file to \"([^\"]*)\"$")
	public void setOutputFile(String file) {
		di.setOutputFile(file);
	}
	
	@Then("^validate the output file of \"([^\"]*)\"$")
	public void validateOutputFile(String file) {
		new OutputValidation().validateOutputFile(file);
	}
	

	
}
