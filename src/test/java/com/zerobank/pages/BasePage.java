package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Locale;

public abstract class BasePage {



    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//a[@href='/bank/redirect.html?url=pay-bills.html']")
    public WebElement PayBillsTab;



    public void navigateToModule(String module) {
        String moduleLocator = "//a[.='"+module+"']";
        Driver.get().findElement(By.xpath(moduleLocator)).click();
        BrowserUtils.waitFor(1);

    }
}
