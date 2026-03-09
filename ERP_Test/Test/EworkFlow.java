package Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import Base.BaseTest;
import Pages.Dashboard;
import Pages.Enqry;
import Pages.Enqrylistng;
import Pages.Login;

public class EworkFlow extends BaseTest {

    @Test
    public void automateEnquiryWorkflow() throws InterruptedException{

        String customer = "TestCustomer_" + System.currentTimeMillis();

        Login login = new Login(driver);
        login.login("erptest", "John Doe", "123");

        boolean isLoginSuccess = login.verifyLoginSuccessful();

        System.out.println("---------- LOGIN ASSERTION CHECK ----------");
        System.out.println("Expected: Dashboard greeting should contain 'Hey'");
        System.out.println("Actual  : " + isLoginSuccess);
        System.out.println("-------------------------------------------");

        Assert.assertTrue(isLoginSuccess,
                "Login failed. User not redirected to dashboard.");

        Dashboard dashboard = new Dashboard(driver);
        dashboard.navigateToEnquiry();

        Enqry enquiry = new Enqry(driver);
        enquiry.fillCustomerName(customer);
        String time = String.valueOf(System.currentTimeMillis());
        String phone = "9" + time.substring(time.length() - 9);
        enquiry.enterCustomerPhone(phone);
        enquiry.selectAssignTo("Ashique");
        enquiry.selectNextFollowupDate();
        enquiry.selectFollowUpStatus("New Enquiry");
        enquiry.selectLeadSource("Instagram");
        driver.findElement(By.id("leadsource")).sendKeys(Keys.TAB);

        enquiry.selectNextFollowupDate();
        driver.findElement(By.id("next-followup-date")).sendKeys(Keys.TAB);

        enquiry.enterBusinessValue("45000");
        driver.findElement(By.id("business-value")).sendKeys(Keys.TAB);

        
        enquiry.selectCategory("ERP");
        enquiry.selectServiceTypeFreelance();
        enquiry.selectEnquiredItem("Apple");
        enquiry.enterQuantity("1");
        
    
        enquiry.clickSave();
        

        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("success"),
                "Enquiry not saved successfully");

        Enqrylistng listing = new Enqrylistng(driver);

     // Navigate CRM → Leads
     listing.navigateToLeads();

     // Validate record in table
     boolean isPresent = listing.verifyCustomerPresent(customer);

     System.out.println("---------- ASSERTION CHECK ----------");
     System.out.println("Expected: Enquiry should be present");
     System.out.println("Actual  : " + isPresent);
     System.out.println("-------------------------------------");

     Assert.assertTrue(isPresent,
             "New enquiry not found in listing");
    }
}