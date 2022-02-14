package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class AccountSummaryPage extends BasePage{

    @FindBy(xpath = "(//table)[1])")
    public WebElement cashAccountsTable;

    @FindBy(xpath = "(//table)[2])")
    public WebElement investmentAccountTable;

    @FindBy(xpath = "(//table)[3])")
    public WebElement CreditAccountsTable;

    @FindBy(xpath = "(//table)[4])")
    public WebElement LoanAccountsTable;

    @FindBy(xpath = "//a[text()='Savings']")
    public List<WebElement> savingAccount;

    public void clickAccountLink(String linkText){
        String xpath="//table//a[contains(text(),'"+linkText+"')]";
        Driver.get().findElement(By.xpath(xpath)).click();
    }



}
