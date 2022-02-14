package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class AccountActivityPage extends BasePage{

    @FindBy (xpath = "//select[@name='accountId']")
    public WebElement accountDropdown;

    @FindBy (xpath = "//a[text()='Find Transactions']")
    public WebElement findTransactionsTab;

    @FindBy (xpath = "//a[text()='Show Transactions]")
    public WebElement showTransactionsTab;

    @FindBy(id = "aa_fromDate")
    public WebElement dateFrom;

    @FindBy(id = "aa_toDate")
    public WebElement dateTo;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement findButton;

    @FindBy(id = "aa_description")
    public WebElement descriptionInput;

    @FindBy(id = "aa_type")
    public WebElement typeDropdownOptions;

    @FindBy(xpath = "(//div[@id='filtered_transactions_for_account']//tbody/tr)/td[1]")
    public List<WebElement> resultTableDates;

    @FindBy(xpath = "(//div[@id='filtered_transactions_for_account']//tbody/tr)/td[2]")
    public List<WebElement> resultTableDescriptions;

    @FindBy(xpath = "(//div[@id='filtered_transactions_for_account']//tbody/tr)/td[3]")
    public List<WebElement> resultTableDeposits;

    @FindBy(xpath = "(//div[@id='filtered_transactions_for_account']//tbody/tr)/td[4]")
    public List<WebElement> resultTableWithdrawals;

    public void enterDate(String date1,String date2){
        BrowserUtils.waitFor(1);
        dateFrom.clear();
        dateFrom.sendKeys(date1);
        dateTo.clear();
        dateTo.sendKeys(date2);
        BrowserUtils.waitFor(1);
    }

    public List<String> getDates(){

        List<String> dateList=new ArrayList<String>();

        for (WebElement dates : resultTableDates) {
            dateList.add(dates.getText());
        }
        return dateList;
    }

    public void setDescriptionInput(String str){
        descriptionInput.clear();
        descriptionInput.sendKeys(str);
        findButton.click();
    }

    public List<String> getResultTableDescriptions(){
        List<String> list=new ArrayList<>();
        for (WebElement resultTableDescription : resultTableDescriptions) {
            list.add(resultTableDescription.getText());
        }
        return list;
    }

    public List<String> getResultTableDeposits() {
        List<String> list = new ArrayList<>();
        for (WebElement resultTableDeposit : resultTableDeposits) {
            list.add(resultTableDeposit.getText());
        }
        return list;
    }

    public List<String> getResultTableWithdrawals(){
        List<String> list=new ArrayList<>();
        for (WebElement resultTableWithdrawal : resultTableWithdrawals) {
            list.add(resultTableWithdrawal.getText());
        }
        return list;
    }
}
