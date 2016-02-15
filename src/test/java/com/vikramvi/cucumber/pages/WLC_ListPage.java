package com.vikramvi.cucumber.pages;

import java.util.List;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class WLC_ListPage extends PageObject {

	@FindBy(xpath="//div[@class='sidebarActions']/a[@class='sidebarActions-addList']")
	private WebElement AddNewList;
	
	@FindBy(xpath="//input[@class='big listOptions-title']")
	private WebElement NameToNewList;
	
	@FindBy(xpath="//div[@class='content']//button[@class='submit full blue']")
	private WebElement EditListDialog_Done;
	
	
	public void clickOnAdd_a_list(){
		element(AddNewList).click();
	}
	
	public boolean giveNametoNewList(String newListName){
		try{
			//if( element(NameToNewList).isEnabled() ){
			if(getDriver().findElements(By.xpath("//input[@class='big listOptions-title']")).size() > 0){	
			     element(NameToNewList).type(newListName);
			     System.out.println("New List:- "+ newListName);
			     return true;
			}else{
				System.out.println("giveNametoNewList ELSE block");
				//fail();
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("giveNametoNewList CATCH block");
			//fail();
			return false;
		}
	}
	
	public void clickDoneButton(){
		element(EditListDialog_Done).click();
	}
	
	public boolean isListView(){
		return getDriver().getCurrentUrl().endsWith("#/lists/inbox");
	}
	
	public int searchList(String listName){
		try{
			System.out.println("searchList : "+ listName);
			//List outside Folders only
			List<WebElement> UserCreatedlists     =  getDriver().findElements(By.xpath("//div[@class='lists-scroll']/ul[@class='lists-collection']/li[contains(@class,'sidebarItem')]//a//span[contains(@class,'title')]"));
			
			List<WebElement> SystemGeneratedlists =  getDriver().findElements( By.xpath("//div[@class='lists-scroll']/ul[2]//span[contains(@class,'title')]") );
			                                                                         
			int count = 0 ;
			
			if ( listName.equals("Completed")){ //As WL3.0 code doesn't have Span[3]. Below hard coding is done
				    return 100007;
			}
			
			for (WebElement list:UserCreatedlists){
				    count++;
				
				if (list.getText().equals(listName) ){
					System.out.println("searchList Counter: "+ count);
					return count;
				}
			}
			
			count = 100000 ; //Work around to find counter for default lists
			
			for (WebElement list:SystemGeneratedlists){
			    count++;
			
				if (list.getText().equals(listName) ){
					System.out.println("searchList Counter: "+ count);
			  	    return count;
				}
		    }
			        System.out.println("searchList FAILED: "+ 0);
		            return 0;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("searchList FAILED: "+ 100100100);
			return 100100100;
		}
	}
	
}
