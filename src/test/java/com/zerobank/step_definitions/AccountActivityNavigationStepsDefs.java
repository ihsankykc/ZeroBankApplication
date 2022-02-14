package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class AccountActivityNavigationStepsDefs {


    @When("the user clicks on Savings link on the Account Summary page")
    public void the_user_clicks_on_Savings_link_on_the_Account_Summary_page() {
        AccountSummaryPage accountSummaryPage=new AccountSummaryPage();
        BrowserUtils.waitFor(1);
        accountSummaryPage.savingAccount.get(0).click();
        BrowserUtils.waitFor(1);
    }

    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_Account_Summary_page(String link) {
        AccountSummaryPage accountSummaryPage=new AccountSummaryPage();
        BrowserUtils.waitFor(1);
        accountSummaryPage.clickAccountLink(link);
        BrowserUtils.waitFor(1);
    }

    @Then("the Account Activity page should be displayed")
    public void the_Account_Activity_page_should_be_displayed() {
        String actualTitle=Driver.get().getTitle();
        String expectedTitle="Zero - Account Activity";
        Assert.assertEquals("Verify title",expectedTitle,actualTitle);
    }

    @Then("Account drop down should have Savings selected")
    public void account_drop_down_should_have_Savings_selected() {
        AccountActivityPage accountActivityPage=new AccountActivityPage();
        Select select=new Select(accountActivityPage.accountDropdown);
        String actualOption=select.getFirstSelectedOption().getText();
        String expectedOption="Savings";
        Assert.assertEquals("Verify dropdown option",expectedOption,actualOption);
    }

    @Then("Account drop down should have {string} selected")
    public void account_drop_down_should_have_selected(String accountOption) {

        AccountActivityPage accountActivityPage=new AccountActivityPage();
        Select select=new Select(accountActivityPage.accountDropdown);
        String actualOption=select.getFirstSelectedOption().getText();
        String expectedOption=accountOption;
        Assert.assertEquals("Verify default dropdown option",expectedOption,actualOption);
    }

}
