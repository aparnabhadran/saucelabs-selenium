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

public class OrderTest {
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
		
		
		WebElement productEl = driver.findElement(By.id("item_4_title_link"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(productEl));
		Assertions.assertEquals("Sauce Labs Backpack", productEl.getText());
		
		WebElement addToCartEl = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button"));
	    addToCartEl.click();
	    Assertions.assertEquals("REMOVE", addToCartEl.getText());
	    
	    WebElement cartNumber = driver.findElement(By.className("shopping_cart_badge"));
	    Assertions.assertEquals("1", cartNumber.getText());
	    
	    cartNumber.click();
	    WebElement displayEl = driver.findElement(By.id("item_4_title_link"));
	    
	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait1.until(ExpectedConditions.visibilityOf(displayEl));
	    Assertions.assertEquals("Sauce Labs Backpack", displayEl.getText());
	    
	    WebElement priceEl = driver.findElement(By.className("inventory_item_price"));
	    Assertions.assertEquals("29.99", priceEl.getText());
	}
	 
	 @Test
	 public void orderProduct() {
		WebElement checkoutButton = driver.findElement(By.className("checkout_button"));
		checkoutButton.click();
		
		WebElement checkoutPageEl = driver.findElement(By.className("subheader"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(checkoutPageEl));
		Assertions.assertEquals("Checkout: Your Information", checkoutPageEl.getText());
		
		WebElement firstNameEl = driver.findElement(By.id("first-name"));
		WebElement lastNameEl = driver.findElement(By.id("last-name"));
		WebElement zipCodeEl = driver.findElement(By.id("postal-code"));
		WebElement continueEl = driver.findElement(By.className("cart_button"));
		firstNameEl.sendKeys("Aparna");
		lastNameEl.sendKeys("Bhadran");
		zipCodeEl.sendKeys("695525");
		continueEl.click(); 
		
		WebElement overviewEl = driver.findElement(By.className("subheader"));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOf(overviewEl));
		Assertions.assertEquals("Checkout: Overview", overviewEl.getText());
		
		WebElement finishEl = driver.findElement(By.className("cart_button"));
		finishEl.click();
		
		WebElement orderMessageEl = driver.findElement(By.className("complete-header"));
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait2.until(ExpectedConditions.visibilityOf(orderMessageEl));
		Assertions.assertEquals("THANK YOU FOR YOUR ORDER", orderMessageEl.getText());
	}
	 
	 @AfterEach
	 public void TearDown(){
		 driver.quit();
	 }
}
