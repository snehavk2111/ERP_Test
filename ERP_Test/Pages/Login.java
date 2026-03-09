package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    private By companyCode = By.id("companycode");
    private By username = By.id("signin-username");
    private By password = By.id("signin-password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    private By dashboardGreeting = By.xpath("//h1[contains(text(),'Hey')]");

    public void login(String comp, String user, String pass) {
        driver.findElement(companyCode).sendKeys(comp);
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
        
    }
    public boolean verifyLoginSuccessful() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement greeting = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(dashboardGreeting)
            );

            return greeting.getText().contains("Hey");

        } catch (Exception e) {
            return false;
        }
    }
}