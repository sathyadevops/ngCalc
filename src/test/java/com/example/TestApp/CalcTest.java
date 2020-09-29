package com.example.TestApp;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class CalcTest 
{   
    @Test
    void testAdd() throws IOException, InterruptedException
    {
		
	    WebDriver driver;
	    
	    Properties prop = new Properties();
	    FileInputStream f = new FileInputStream("./data.properties");
	    prop.load(f);
	    String myIP = prop.getProperty("public_ip");
	    
	    if(System.getenv("MY_IP")!=null){
		    myIP=System.getenv("MY_IP");
	    }
	    
	    String myPort = prop.getProperty("tomcat_port");
	    String myAppName = prop.getProperty("app_name");
	    String myURL = "http://" + myIP + ":" + myPort + "/" + myAppName;
	    System.out.println("Opening " + myURL);
	    
	    FirefoxOptions options = new FirefoxOptions();
	    
	    options.addArguments("--headless");
	    options.setCapability("requireWindowFocus", true);
	    //String mygecko=System.getenv("HOME") + "/Downloads/geckodriver";
	    String mygecko= prop.getProperty("webdriver_path") + "geckodriver";
	    
	    System.setProperty("webdriver.gecko.driver",mygecko);
	    
	    System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
	    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
	    
	    driver = new FirefoxDriver(options);
	    
	    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	    
	    driver.get(myURL);
	    
	    Thread.sleep(5000);
	    
	    String text = prop.getProperty("exp_add_text");
	    
	    String bodyText = driver.findElement(By.xpath("/html/body")).getText();
	    
	    System.out.println(bodyText);
	    driver.findElement(By.id("f1")).sendKeys("12");
	    driver.findElement(By.xpath("/html/body/form/input[2]")).sendKeys("38");
        
	    driver.findElement(By.name("r1")).click();
	    
	    //WebDriverWait wait = new WebDriverWait(driver, 10);
	    //WebElement myElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("s1")));
	    
	    //myElement.click();
	    driver.findElement(By.id("s1")).click();
	    Thread.sleep(5000);
	    
	    bodyText = driver.findElement(By.xpath("/html/body")).getText();
	    
	    System.out.println(bodyText);
	    
	    Assert.assertTrue(bodyText.contains(text), text + " not found!" );
	    
	    Assert.assertTrue(bodyText.contains("50"), "50 not found!" );
	    
	    Thread.sleep(5000);
	    
	    driver.quit();
	}
    
    
    @Test
    void testMul() throws IOException, InterruptedException
    {
		
	    WebDriver driver;
	    
	    Properties prop = new Properties();
	    FileInputStream f = new FileInputStream("./data.properties");
	    prop.load(f);
	    String myIP = prop.getProperty("public_ip");
	    
	    if(System.getenv("MY_IP")!=null){
		    myIP=System.getenv("MY_IP");
	    }
	    
	    String myPort = prop.getProperty("tomcat_port");
	    String myAppName = prop.getProperty("app_name");
	    String myURL = "http://" + myIP + ":" + myPort + "/" + myAppName;
	    System.out.println("Opening " + myURL);
	    
	    FirefoxOptions options = new FirefoxOptions();
	    
	    options.addArguments("--headless");
	    options.setCapability("requireWindowFocus", true);
	    String mygecko= prop.getProperty("webdriver_path") + "geckodriver";
	    
	    System.setProperty("webdriver.gecko.driver",mygecko);
	    
	    System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
	    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
	    
	    driver = new FirefoxDriver(options);
	    
	    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	    
	    driver.get(myURL);
	    
	    Thread.sleep(5000);
	    
        driver.findElement(By.id("f1")).sendKeys("6");
	    driver.findElement(By.xpath("/html/body/form/input[2]")).sendKeys("4");
        
	    driver.findElement(By.name("r3")).click();
	
	    driver.findElement(By.id("s1")).click();
	    
	    Thread.sleep(5000);
	    
	    String bodyText = driver.findElement(By.xpath("/html/body")).getText();
	    
	    System.out.println(bodyText);
	    
	    Assert.assertTrue(bodyText.contains("24"), "24 not found!" );
	    
	    Thread.sleep(5000);
	    
	    driver.quit();
	}
}
