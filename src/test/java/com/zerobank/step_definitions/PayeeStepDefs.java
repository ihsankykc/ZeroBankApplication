package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayeeStepDefs {
    @Given("Add New Payee tab")
    public void add_New_Payee_tab() {
        Driver.get().get(ConfigurationReader.get("url"));
        LoginPage loginPage=new LoginPage();
        loginPage.login();
        Driver.get().get("http://zero.webappsecurity.com/bank/account-summary.html");
        AccountSummaryPage accountSummaryPage=new AccountSummaryPage();
        accountSummaryPage.navigateToModule("Pay Bills");
        BrowserUtils.waitFor(1);

        PayBillsPage payBillsPage=new PayBillsPage();
        payBillsPage.addNewPayee();
        BrowserUtils.waitFor(1);

    }

    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String,String> map) {
        PayBillsPage payBillsPage=new PayBillsPage();
        payBillsPage.payeeName.sendKeys(map.get("Payee Name"));
        payBillsPage.payeeAddress.sendKeys(map.get("Payee Address"));
        payBillsPage.payeeAccount.sendKeys(map.get("Account"));
        payBillsPage.payeeDetails.sendKeys(map.get("Payee details"));
        payBillsPage.addButton.click();
        BrowserUtils.waitFor(1);
    }

    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String expectedMessage) {
        PayBillsPage payBillsPage=new PayBillsPage();
        String actualMessage=payBillsPage.message.getText();
//      System.out.println("actualMessage = " + actualMessage);
        Assert.assertEquals("Verify the message",expectedMessage,actualMessage);
    }

}
