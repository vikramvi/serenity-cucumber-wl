package com.vikramvi.cucumber.steps.serenity;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import com.vikramvi.cucumber.pages.*;

public class WL3CSteps extends ScenarioSteps{
    WLC_LoginPage loginPage;
    WLC_ListPage  listPage;
	
    @Step
    public void open_WL3_home_page(){
    	loginPage.open();
    }
    
    @Step
    public void login_to_WL3(){
    	loginPage.bringUpLoginDialog();
    	loginPage.enterLoginField("vikram.playdom11@rocketmail.com");
    	loginPage.enterPasswordField("654321");
    	loginPage.clickLogInButton();
    }
    
    @Step
    public void create_new_list_WL3(){
    	listPage.clickOnAdd_a_list();
    	listPage.giveNametoNewList("Cucumber-list");
    	listPage.clickDoneButton();
    }
    
    @Step
    public void isListView(){
    	listPage.isListView();
    }
    
    @Step
    public int isListPresent(){
    	return listPage.searchList("Cucumber-list");
    }
}
