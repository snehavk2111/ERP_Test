package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Enqrylistng {

    WebDriver driver;

    public Enqrylistng(WebDriver driver) {
        this.driver = driver;
    }


    public void navigateToLeads() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("apiloader")
        ));

        
        WebElement crm = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("nav-crm"))
        );
        crm.click();

        
        WebElement leads = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("nav-crm-leads"))
        );
        leads.click();

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table")
        ));
    }

    public boolean verifyCustomerPresent(String customerName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//table")));

            return driver.findElement(
                    By.xpath("//td[contains(text(),'" + customerName + "')]"))
                    .isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}