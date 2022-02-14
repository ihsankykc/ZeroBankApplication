package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.util.*;

public class FindTransactionsStepDefs {
    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_Find_Transactions_tab() {
        Driver.get().get(ConfigurationReader.get("url"));
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        Driver.get().get("http://zero.webappsecurity.com/bank/account-activity.html");
        BrowserUtils.waitFor(1);
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        accountActivityPage.findTransactionsTab.click();
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String string, String string2) {
        AccountActivityPage accountActivityPage=new AccountActivityPage();
        accountActivityPage.enterDate(string,string2);
    }

    @When("clicks search")
    public void clicks_search() {
       AccountActivityPage accountActivityPage=new AccountActivityPage();
       accountActivityPage.findButton.click();
       BrowserUtils.waitFor(1);
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String dateFrom, String dateTo) throws ParseException {
       AccountActivityPage accountActivityPage=new AccountActivityPage();
        List<String> dateList=new ArrayList<>();
        dateList=accountActivityPage.getDates();

        for (String str : dateList) {
            Assert.assertTrue("Verify date coverage",str.compareTo(dateFrom)>=0 && str.compareTo(dateTo)<=0);
        }
    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        AccountActivityPage accountActivityPage=new AccountActivityPage();
        List<String> actualDates=accountActivityPage.getDates();
        List<String> expectedDates=accountActivityPage.getDates();
        Collections.sort(expectedDates);
        Collections.reverse(expectedDates);

        Assert.assertEquals("Verify that dates are sorted new to old",expectedDates,actualDates);
    }


    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String string) {
       AccountActivityPage accountActivityPage=new AccountActivityPage();
       List<String>actualDates=accountActivityPage.getDates();
       Assert.assertFalse("Verify results not includes "+string,actualDates.contains(string));
    }
    @When("the user enters description {string}")
    public void the_user_enters_description(String string) {
       AccountActivityPage accountActivityPage=new AccountActivityPage();
       accountActivityPage.setDescriptionInput(string);
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String string) {
        AccountActivityPage accountActivityPage=new AccountActivityPage();
        List<String> actualResults=new ArrayList<>();
        actualResults = accountActivityPage.getResultTableDescriptions();
        for (String actualResult : actualResults) {
            Assert.assertTrue("Verify description contains "+string,actualResult.contains(string));
        }
    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String string) {
        AccountActivityPage accountActivityPage=new AccountActivityPage();
        List<String> actualResults=new ArrayList<>();
        actualResults = accountActivityPage.getResultTableDescriptions();
        for (String actualResult : actualResults) {
            Assert.assertFalse("Verify description does not contain "+string,actualResult.contains(string));
        }
    }

    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_Deposit() {
       AccountActivityPage accountActivityPage=new AccountActivityPage();
       List<String> actualList=new ArrayList<>();
       actualList=accountActivityPage.getResultTableDeposits();
       Assert.assertFalse("Verify list is not empty",actualList.isEmpty());
    }

    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_Withdrawal() {
      AccountActivityPage accountActivityPage=new AccountActivityPage();
      List<String> actualList=new ArrayList<>();
      actualList = accountActivityPage.getResultTableWithdrawals();
      Assert.assertFalse(actualList.isEmpty());

    }

    @When("user selects type {string}")
    public void user_selects_type(String string) {
        AccountActivityPage accountActivityPage=new AccountActivityPage();
        Select select=new Select(accountActivityPage.typeDropdownOptions);
        select.selectByVisibleText(string);
        accountActivityPage.findButton.click();
        BrowserUtils.waitFor(1);
    }

    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_Withdrawal() {
        AccountActivityPage accountActivityPage=new AccountActivityPage();
        List<String> actualList=new ArrayList<>();
        actualList = accountActivityPage.getResultTableWithdrawals();

        //why not directly use actualList.isEmpty
        for (String str : actualList) {
            Assert.assertTrue("Verify there is no result for withdrawal",str.isEmpty());
        }
    }

    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_Deposit() {
        AccountActivityPage accountActivityPage=new AccountActivityPage();
        List<String> actualList=new ArrayList<>();
        actualList = accountActivityPage.getResultTableDeposits();

        for (String str : actualList) {
            Assert.assertTrue("Verify there is no result for deposit ",str.isEmpty());
        }
    }

}
