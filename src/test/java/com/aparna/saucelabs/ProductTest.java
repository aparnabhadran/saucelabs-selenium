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

public class ProductTest {
	private WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().window().maximize();
		
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		
		usernameField.sendKeys("standard_user");
		passwordField.sendKeys("secret_sauce");
		loginButton.click();
	}
	
	@Test
	public void productsDisplayed() {
		
		WebElement productEl = driver.findElement(By.id("item_4_title_link"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(productEl));
		Assertions.assertEquals("Sauce Labs Backpack", productEl.getText());
	}
		
	@Test
	public void addToCart() {
		
		WebElement addToCartEl = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button"));
	    addToCartEl.click();
	    Assertions.assertEquals("REMOVE", addToCartEl.getText());
	    
	    WebElement cartNumber = driver.findElement(By.className("shopping_cart_badge"));
	    Assertions.assertEquals("1", cartNumber.getText());
	    
	    cartNumber.click();
	    WebElement displayEl = driver.findElement(By.id("item_4_title_link"));
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(displayEl));
	    Assertions.assertEquals("Sauce Labs Backpack", displayEl.getText());
	    
	    
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	
	
	

}
