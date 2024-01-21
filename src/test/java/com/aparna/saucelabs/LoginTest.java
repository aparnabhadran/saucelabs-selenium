package com.aparna.saucelabs;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
	private WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().window().maximize();
	}
	
	@Test
	public void testLogin_succesfull() {
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		
		usernameField.sendKeys("standard_user");
		passwordField.sendKeys("secret_sauce");
		loginButton.click();
		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
     	WebElement productLabelEl = driver.findElement(By.className("product_label"));
     	
     	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1));
		wait.until(ExpectedConditions.visibilityOf(productLabelEl));

		Assertions.assertEquals("Products", productLabelEl.getText());
		
	}
	
	@Test
	public void loginFailureOnInvalidPassword(){
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		
		usernameField.sendKeys("standard_user");
		passwordField.sendKeys("secret_sauce1");
		loginButton.click();
		
		WebElement errorMessage = driver.findElement(By.cssSelector("[data-test=\"error\"]"));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage.getText());
		
	}
	
	@Test
	public void loginFailureOnInvalidUsername() {
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		
		usernameField.sendKeys("standard_user1");
		passwordField.sendKeys("secret_sauce");
		loginButton.click();
		
		WebElement errorMessage = driver.findElement(By.cssSelector("[data-test=\"error\"]"));
				
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage.getText());
	}
	
	
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
}
