package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefs {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("the user enters the username password")
    public void the_user_enters_the_username_password() {
        LoginPage loginPage=new LoginPage();
        loginPage.login();
    }

    @Then("the user should be able to login")
    public void the_user_should_be_able_to_login() throws InterruptedException {
        Driver.get().navigate().back();
        BrowserUtils.waitFor(2);
        Driver.get().get("http://zero.webappsecurity.com/bank/account-summary.html");
        String actualTitle=Driver.get().getTitle();
        String expectedTitle="Zero - Account Summary";

        Assert.assertEquals("Verify logged in",expectedTitle,actualTitle);
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        Driver.get().get(ConfigurationReader.get("url"));
        LoginPage loginPage=new LoginPage();
        loginPage.login();
        Driver.get().navigate().back();
        BrowserUtils.waitFor(2);
        Driver.get().get("http://zero.webappsecurity.com/bank/account-summary.html");

    }



}
