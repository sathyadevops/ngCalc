package com.example.TestApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubChromeTest {

	@Test
    void testSub() throws IOException, InterruptedException
    {
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
	    System.out.println("Opening " + myURL);
	    
		System.setProperty("webdriver.chrome.driver",prop.getProperty("webdriver_path") + "chromedriver");
		
		System.setProperty("webdriver.chrome.logfile", "chromedriver.log");
	    System.setProperty("webdriver.chrome.verboseLogging", "true");
		System.out.println("System.setProperty!!");
		ChromeOptions chromeOptions = new ChromeOptions(); 
		chromeOptions.addArguments("headless"); 
		chromeOptions.addArguments("--no-sandbox");
	    driver = new ChromeDriver(chromeOptions); 
		System.out.println("driver!!");

        driver.get(myURL);
        System.out.println("baseUrl!!");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        
	    driver.findElement(By.id("f1")).sendKeys("38");
	    driver.findElement(By.xpath("/html/body/form/input[2]")).sendKeys("18");
        driver.findElement(By.xpath("//input[@name='r2']")).click();
        driver.findElement(By.id("s1")).click();
        
        String bodyText = driver.findElement(By.xpath("/html/body")).getText();
        
        Assert.assertTrue(bodyText.contains("20"), "20 not found!" );
        
        Thread.sleep(5000);
        
        System.out.println("Test Succeeded!!");
             
        driver.quit();  
		
    }
}
