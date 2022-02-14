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
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PurchaseNewCurrencyStepDefs {
    @Given("the user accesses the Purchase foreign currency cash tab")
    public void the_user_accesses_the_Purchase_foreign_currency_cash_tab() {
        Driver.get().get(ConfigurationReader.get("url"));
        LoginPage loginPage=new LoginPage();
        loginPage.login();
        Driver.get().get("http://zero.webappsecurity.com/bank/account-summary.html");
        AccountSummaryPage accountSummaryPage=new AccountSummaryPage();
        accountSummaryPage.navigateToModule("Pay Bills");
        BrowserUtils.waitFor(1);

        PayBillsPage payBillsPage=new PayBillsPage();
        payBillsPage.purchaseForeignCurrency();
        BrowserUtils.waitFor(1);
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> list) {
        PayBillsPage payBillsPage=new PayBillsPage();
        List<String> actualDropdownOptions =new ArrayList<>();

        actualDropdownOptions=payBillsPage.currencyDropdownOptionsAsString();
        System.out.println("actualDropdownOptions.toString() = " + actualDropdownOptions.toString());
        System.out.println("list.toString() = " + list.toString());

        Assert.assertTrue(actualDropdownOptions.containsAll(list));
    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
      PayBillsPage payBillsPage=new PayBillsPage();
      List<WebElement> elements=new ArrayList<>();
      elements=payBillsPage.currencyDropdownOptions();
      elements.get(0).click();
      BrowserUtils.waitFor(1);
      payBillsPage.calculateCostButton.click();
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
       PayBillsPage payBillsPage=new PayBillsPage();
        Alert alert=Driver.get().switchTo().alert();
        String actualMessage=alert.getText();
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertTrue("Verify there is an js alert",!actualMessage.isEmpty());

    }

    @When("user tries to calculate cost without entering a value")
    public void user_tries_to_calculate_cost_without_entering_a_value() {
        PayBillsPage payBillsPage=new PayBillsPage();
        List<WebElement> elements=new ArrayList<>();
        elements=payBillsPage.currencyDropdownOptions();
        elements.get(1).click();
        BrowserUtils.waitFor(1);
        payBillsPage.calculateCostButton.click();
    }




}
