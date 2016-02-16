package com.vikramvi.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.vikramvi.cucumber.steps.serenity.WL3CSteps;

public class LoginScenarioSteps {

	@Steps
	WL3CSteps wlCucumberSteps;
	
	@Given("User is on log-in page")
	public void gotoWL_LoginPage(){
		wlCucumberSteps.open_WL3_LoginPage();
	}
	
	@When("enters correct email id")
	public void enterCorrectEmailId(){
		wlCucumberSteps.enterEmailID(true);
	}
	
	@When("enters wrong email id")
	public void enterWrongEmailId(){
		wlCucumberSteps.enterEmailID(false);
	}
	
	@When("enters correct password")
	public void enterCorrectPassword(){
		wlCucumberSteps.enterPassword(true);
	}
	
	@When("enters wrong password")
	public void enterWrongPassword(){
		wlCucumberSteps.enterPassword(false);
	}
	
	@When("clicks on Sign In button")
	public void clickSignInButton(){
		wlCucumberSteps.clickSignInButton();
	}
	
	@Then("User is shown error message For Providing Wrong Login Info")
	public void errorMessageForInvalidLoginInfo(){
		wlCucumberSteps.verifyErrorMessageForInvalidLoginInfo();
	}
	
}
