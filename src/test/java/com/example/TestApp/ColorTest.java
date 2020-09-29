package com.example.TestApp;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ColorTest 
{    	
    @Test
    void testSiteColor() throws Exception  {
		
	    WebDriver driver;
	    
	    Properties prop = new Properties();
	    FileInputStream f = new FileInputStream("./data.properties");
	    prop.load(f);
	    String myIP = prop.getProperty("public_ip");
	    String myPort = prop.getProperty("tomcat_port");
	    String myAppName = prop.getProperty("app_name");
	    
	    if(System.getenv("MY_IP")!=null){
		    myIP=System.getenv("MY_IP");
	    }
	    
	    String myURL = "http://" + myIP + ":" + myPort + "/" + myAppName;
	    FirefoxOptions options = new FirefoxOptions();
        
        options.addArguments("--headless");
	    options.setCapability("requireWindowFocus", true);
        String mygecko= prop.getProperty("webdriver_path") + "geckodriver";

        System.setProperty("webdriver.gecko.driver",mygecko);
        
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        
        driver = new FirefoxDriver(options);
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

	    System.out.println("Opening " + myURL);

        driver.get(myURL);
        
        Thread.sleep(5000);
        
	    driver.findElement(By.xpath("/html/body/form/input[1]")).sendKeys("12");
	    driver.findElement(By.xpath("/html/body/form/input[2]")).sendKeys("38");
        
	    driver.findElement(By.name("r1")).click();
	    
	    driver.findElement(By.xpath("/html/body/form/input[3]")).click();
	    Thread.sleep(5000);
	    
        String mycolor = driver.findElement(By.tagName("body")).getAttribute("bgcolor");
	    
        System.out.println("Color is " + mycolor);
        
        String expColor = prop.getProperty("expColor");
        
	    Assert.assertEquals(mycolor, expColor);
	       
	    Thread.sleep(5000);
	    
        driver.quit();
	}
}
