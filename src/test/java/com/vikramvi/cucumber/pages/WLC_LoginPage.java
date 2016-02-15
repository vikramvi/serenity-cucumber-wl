package com.vikramvi.cucumber.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.wunderlist.com/")
public class WLC_LoginPage extends PageObject {
  
	@FindBy(xpath="//a[@href='/login']")
	private WebElement HomePageLoginButton;
	
	@FindBy(name="email")
	private WebElement LoginField;
	
	@FindBy(name="password")
	private WebElement PasswordField;
	
	@FindBy(xpath="//input[@value='Sign In']")
	private WebElement LoginButton;
	
	@FindBy(xpath="//span[@class='syncing']")
	private WebElement syncIcon;
	
	
	
	public void bringUpLoginDialog(){
		element(HomePageLoginButton).click();
	}
	
	public void enterLoginField(String LoginId){
		element(LoginField).click();
		element(LoginField).type(LoginId);
	}
	
	public void enterPasswordField(String password){
		element(PasswordField).type(password);
	}
	
	public void clickLogInButton(){
		element(LoginButton).click();
		
		if( element(syncIcon).isCurrentlyVisible() ){ //In case log-in is failed not need to wait for sync elemenet
		
				element(syncIcon).waitUntilNotVisible();  //This step will make sure that all lists are loaded properly. Fixed for safari/chrome browser failures
			    
				//This is to take care of post log-in Like App dialog. But it increase script running time , need to find out how to reduce wait time through global variable ?? TBD
				//closeDialog();
		}
	}
		
}
