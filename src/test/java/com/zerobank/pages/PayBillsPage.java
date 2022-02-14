package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class PayBillsPage extends BasePage{
    @FindBy(xpath = "//a[text()='Add New Payee']")
    public WebElement addNewPayeeTab;

    @FindBy (xpath = "//a[.='Purchase Foreign Currency']")
    public WebElement foreignCurrencyTab;

    @FindBy(name = "name")
    public WebElement payeeName;

    @FindBy(name = "address")
    public WebElement payeeAddress;

    @FindBy(id = "np_new_payee_account")
    public WebElement payeeAccount;

    @FindBy(name = "details")
    public WebElement payeeDetails;

    @FindBy(id = "add_new_payee")
    public WebElement addButton;

    @FindBy(id = "alert_content")
    public WebElement message;

    @FindBy(id = "pc_currency")
    public WebElement currencyDropdown;

    @FindBy(id="pc_calculate_costs")
    public WebElement calculateCostButton;

    public void addNewPayee(){
        addNewPayeeTab.click();
    }
    public void purchaseForeignCurrency(){
        foreignCurrencyTab.click();
    }

    public List<WebElement> currencyDropdownOptions(){
        Select select=new Select(currencyDropdown);
        List<WebElement> list=new ArrayList<>();
        list=select.getOptions();
        return list;
    }
    public List<String> currencyDropdownOptionsAsString(){
        Select select=new Select(currencyDropdown);
        List<WebElement> list=new ArrayList<>();
        List<String>listAsString=new ArrayList<>();
        list=select.getOptions();
        list.remove(select.getFirstSelectedOption());
        for (WebElement element : list) {
            listAsString.add(element.getText());
        }
        return listAsString;
    }

}
