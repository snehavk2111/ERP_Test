package Pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.Keys;

public class Enqry {

    WebDriver driver;

    public Enqry(WebDriver driver) {
        this.driver = driver;
    }

   
    private By customerPhone = By.id("customer-phone");
    private By customerName = By.id("TxtCustomer");
    private By assignTo = By.id("assignto");

   

    private By categoryDropdown = By.xpath("//select[option[@title='ERP']]");


   
    private By quantityField = By.xpath("//input[@type='number']");
    

    private By businessValue = By.id("business-value");


    public void enterCustomerPhone(String phone) {
        WebElement phoneField = driver.findElement(customerPhone);
        phoneField.clear();
        phoneField.sendKeys(phone);
    }
    
    
    public void fillCustomerName(String name) {
        driver.findElement(customerName).sendKeys(name);
    }

    public void selectAssignTo(String user) {
        Select select = new Select(driver.findElement(assignTo));
        select.selectByVisibleText(user);
    }

    public void selectNextFollowupDate() {

        WebElement dateField = driver.findElement(By.id("next-followup-date"));

        String futureDate = java.time.LocalDateTime.now()
                .plusDays(1)
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].value = arguments[1];", 
                               dateField, futureDate);

        
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].dispatchEvent(new Event('change'));", 
                               dateField);

        
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].dispatchEvent(new Event('blur'));", 
                               dateField);
    }
    

    public void selectBusinessType() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By businessTypeLocator = By.xpath("//input[@placeholder='Choose..']");

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(businessTypeLocator));

        
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", field);

        
        try { Thread.sleep(600); } catch (InterruptedException e) {}

        
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", field);

        
        By container = By.xpath("//div[contains(@class,'filter-container')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(container));

        
        WebElement grocery = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("multiselect-switch-105")));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", grocery);

        
        WebElement doneBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'filter-container')]//input[@value='Done']")));

        doneBtn.click();

        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(container));
    }

    public void selectCategory(String category) {
        Select select = new Select(driver.findElement(categoryDropdown));
        select.selectByVisibleText(category);
    }

    public void selectServiceTypeFreelance() {

        
        ((JavascriptExecutor) driver)
                .executeScript("document.activeElement.blur();");

        WebElement freelanceRadio = driver.findElement(
                By.xpath("//label[text()='Freelance']/preceding-sibling::input"));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", freelanceRadio);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", freelanceRadio);
    }
    
    public void selectFollowUpStatus(String status) {

        Select select = new Select(driver.findElement(By.id("followup")));
        select.selectByVisibleText(status);

        driver.findElement(By.id("followup")).sendKeys(Keys.TAB);
        
        ((JavascriptExecutor) driver)
        .executeScript("document.activeElement.blur();");
    }

    public void selectLeadSource(String source) {

        Select select = new Select(driver.findElement(By.id("leadsource")));
        select.selectByVisibleText(source);

        
        driver.findElement(By.id("leadsource")).sendKeys(Keys.TAB);
        
        ((JavascriptExecutor) driver)
        .executeScript("document.activeElement.blur();");
    }

    public void enterBusinessValue(String value) {
        WebElement valueField = driver.findElement(businessValue);
        valueField.clear();
        valueField.sendKeys(value);
        
        ((JavascriptExecutor) driver)
        .executeScript("document.activeElement.blur();");
    }

    public void selectEnquiredItem(String itemName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        WebElement mainInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("item-search-input")));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", mainInput);

        try { Thread.sleep(500); } catch (InterruptedException e) {}

        
        By itemSearchIcon = By.xpath(
            "//input[@id='item-search-input']/parent::div//div[contains(@class,'input-group-text')]");

        WebElement icon = wait.until(
                ExpectedConditions.elementToBeClickable(itemSearchIcon));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", icon);

        
        By priceHeader = By.xpath("//th[text()='Price']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceHeader));

        
        WebElement modalInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("item-search-modal-input")));

        modalInput.clear();
        modalInput.sendKeys(itemName);

        
        WebElement firstRow = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("popup-item-row-0")));

        firstRow.click();
    }
    
    public void enterQuantity(String qty) {
        WebElement quantity = driver.findElement(quantityField);
        quantity.clear();
        quantity.sendKeys(qty);
        ((JavascriptExecutor) driver)
        .executeScript("document.activeElement.blur();");
    }

    public void clickSave() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        ((JavascriptExecutor) driver)
                .executeScript("document.activeElement.blur();");

        
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement saveBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("btn-save-enquiry")));

        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));

        try { Thread.sleep(800); } catch (InterruptedException e) {}

        saveBtn.click();
    }
}