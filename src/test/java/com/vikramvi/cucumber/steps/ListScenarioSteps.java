package com.vikramvi.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.vikramvi.cucumber.steps.serenity.WL3CSteps;

public class ListScenarioSteps {

	@Steps
	WL3CSteps wlCucumberSteps;
		
	@Given("User log-in to WL")
	public void loginToWL(){
		wlCucumberSteps.open_WL3_home_page();
		wlCucumberSteps.login_to_WL3();
	}
	
	@Given("User is in List View")
	public void listView(){
		wlCucumberSteps.isListView();
	}
	
	@When("User Creates New List")
	public void createNewList(){
		////wlCucumberSteps.create_new_list_WL3(); TEMP
	}
	  
	@Then("New List Gets Created At Bottom of List View")
	public void checkListPosition(){
		////assertThat(wlCucumberSteps.isListPresent()).isNotEqualTo(0);
		wlCucumberSteps.selectListAndDoTaskDragAndDrop();
	}
		
}
