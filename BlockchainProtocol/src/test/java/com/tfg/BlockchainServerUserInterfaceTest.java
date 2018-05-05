package com.tfg;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BlockchainServerUserInterfaceTest {
	
	static String PathFirefox = "C:\\Firefox46.win\\FirefoxPortable.exe";
	static WebDriver driver = getDriver(PathFirefox); 
	static String URL = "http://localhost:8080";
	
	public static WebDriver getDriver(String PathFirefox) { 
		System.setProperty("webdriver.firefox.bin",PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	@Before
	public void setUp() throws Exception { 
		driver.navigate().to(URL);
	}
	
	@After 
	public void tearDown() throws Exception { 
		driver.manage().deleteAllCookies();
	} 
	
	@AfterClass 
	public static void end() throws Exception { 
		driver.quit();
	}
	@Test 
	public void test() throws InterruptedException { 
	    driver.get(URL + "/");
	    driver.findElement(By.linkText("See all")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]/a")).click();
	    Thread.sleep(2000);	    
	    driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]/a")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]/a")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//table/tbody/tr[14]/td[1]/a")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Home")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("btnLanguage")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#btnSpanish > span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#btnChinesse > span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#btnGerman > span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Haupt")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("btnEnglish")).click();
	}

}
