package com.vikramvi.cucumber.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.JavascriptExecutor;
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
	
	public boolean selectSpecifiedList(String listName){
		try{
				int listCounter = searchList(listName);
				System.out.println("selectSpecifiedList -> listCounter   =   "+ listCounter);
				
				//Completed List Only. WL 3.0 hard coding
				if ( listCounter != 0 && listCounter == 100007){
					getDriver().findElement(By.xpath("//div[@class='lists-scroll']/ul[@class='filters-collection']/li[7]/a/span[@class='title']")).click();
					Thread.sleep(1000);                                 
					return true;
				}
				
				//User Generated Lists
				if ( listCounter != 0 && listCounter < 100000){
					getDriver().findElement(By.xpath("//div[@class='lists-scroll']/ul[@class='lists-collection']/li[contains(@class,'sidebarItem')]["+listCounter+"]//a//span[contains(@class,'title')]")).click();
					Thread.sleep(1000);                                  
					return true;
				}
				
				
				//Smart Lists - System Generated
				if ( listCounter != 0 && listCounter > 100000){
					listCounter = listCounter-100000;
					getDriver().findElement(By.xpath("//div[@class='lists-scroll']/ul[@class='filters-collection']/li["+listCounter+"]/a/span[@class='title']")).click();
					Thread.sleep(1000);                                  
					return true;
				}
				
				System.out.println(">>>>>>>>  selectSpecifiedList ->"+ listName + " NOT Found");
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
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
	
	public void DragAndDrop() throws InterruptedException, IOException{ 
	    	try{
		    	        String basePath = new File("").getAbsolutePath();
			            
			        	//System.setProperty("webdriver.Firefox.driver", "Path_executable");
				        //WebDriver driver= new FirefoxDriver();
				        
		    	        //webDriver.get("http://html5demos.com/drag#");
				
				        //http://stackoverflow.com/questions/29381233/how-to-simulate-html5-drag-and-drop-in-selenium-webdriver
				        //https://gist.github.com/rcorreia/2362544
				        final String JQUERY_LOAD_SCRIPT = (basePath + "/jquery_load_helper.js");
				        String jQueryLoader = readFile(JQUERY_LOAD_SCRIPT);
				        
				        //webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
				        
				        JavascriptExecutor js = (JavascriptExecutor) getDriver();
				        js.executeAsyncScript(
				                jQueryLoader /* , http://localhost:8080/jquery-1.7.2.js */);
				
				        // ready to rock
				        js.executeScript("jQuery(function($) { " + " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); "
				                + " }); ");
				
				
				
				      //http://stackoverflow.com/questions/29381233/how-to-simulate-html5-drag-and-drop-in-selenium-webdriver
				      //"where jquery_load_helper.js contains:"  
				      String filePath = basePath + "/drag_and_drop_helper.js";
				      
				      
				      //JQuery can ONLY work with id and css , xpath does NOT work with it.
				      //String source =  "//section[@id='wrapper']/article/ul/li[4]/a"; 
				      String source = "div.tasks-scroll ol.tasks li:nth-child(4)";
				      String target = "div.tasks-scroll ol.tasks li:nth-child(1)"; //#bin";
				      
				      StringBuffer buffer = new StringBuffer();
				      String line;
				      BufferedReader br = new BufferedReader(new FileReader(filePath));
				      while((line = br.readLine())!=null)
				          buffer.append(line);
				
				      String javaScript = buffer.toString();
				
				      javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
				      ((JavascriptExecutor)getDriver()).executeScript(javaScript);
				      
				      Thread.sleep(1000);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
        }

        private static String readFile(String file) throws IOException {
		    Charset cs = Charset.forName("UTF-8");
		    FileInputStream stream = new FileInputStream(file);
		    try {
		        Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
		        StringBuilder builder = new StringBuilder();
		        char[] buffer = new char[8192];
		        int read;
		        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
		            builder.append(buffer, 0, read);
		        }
		        return builder.toString();
		    } finally {
		        stream.close();
		    }
       }

	
}
