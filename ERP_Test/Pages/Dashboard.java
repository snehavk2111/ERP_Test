package Pages;

import org.openqa.selenium.WebDriver;

public class Dashboard {

    WebDriver driver;

    public Dashboard(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToEnquiry() {
        driver.get("https://erptest.prog-biz.com/enquiry");
    }
}