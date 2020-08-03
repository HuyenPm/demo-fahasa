package serenity.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import serenity.commons.CommonPageObject;

import java.util.concurrent.TimeUnit;

@DefaultUrl("https://www.fahasa.com/")

public class HomePage extends CommonPageObject {
    @FindBy(className = "fhs_top_account_login")
    public static WebElementFacade loginLink;
    @FindBy(id = "login_username")
    public static WebElementFacade txtUsername;
    @FindBy(id = "login_password")
    public static WebElementFacade txtPassword;
    @FindBy(className = "fhs-btn-login")
    public static WebElementFacade btnLogin;
    @FindBy(xpath = "((//div[@class='cart-top']/following-sibling::div)//div[2])[1]")
    public static WebElementFacade btnBesideCart;
    @FindBy(xpath = "//div[@class='fhs-popup-msg fhs-login-msg']")
    public static WebElementFacade msgError;
    @FindBy(id = "search_desktop")
    public static WebElementFacade txtSearch;
    @FindBy(xpath = "(//div[@class='cart-top'])[1]")
    public static WebElementFacade btnCart;

    /*
        Description: Access website Fahasa
        Author: HuyenPM
        Created Date: 13/7/2020
        Parameters:
        Returns: N/A
    */
    public void accessWebsite() {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        open();
    }

    /*
        Description: Get title of current website to verify with expected title
        Author: HuyenPM
        Created Date: 13/7/2020
        Parameters:
        Returns: String
    */
    public String getTitleOfWebsite() {
        String actualTitle = getDriver().getTitle();
        return actualTitle;
    }

    /*
        Description: Click on login link and enter username, password
        Author: HuyenPM
        Created Date: 13/7/2020
        Parameters:
        Returns: N/A
    */
    public void enterLogin(String username, String password) {
        loginLink.waitUntilClickable();
        getElementAndHighlight(loginLink).click();
        getElementAndHighlight(txtUsername).type(username);
        getElementAndHighlight(txtPassword).type(password);
    }

    /*
        Description: Click button login after entering username and password
        Author: HuyenPM
        Created Date: 13/7/2020
        Parameters:
        Returns: N/A
    */
    public void clickLogin() {
        getElementAndHighlight(btnLogin).click();
    }

    // Verify login successfully
    public String getTextOfBtnBesideCart() {
        waitUntilHTMLReady(20);
//        waitUntilElementVisible(btnBesideCart);
        return btnBesideCart.getText();
    }

    // Verify login fail
    public String getMsgError() {
        return msgError.getText();
    }

    public void clickCart() {
        waitUntilElementVisible(btnCart);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnCart);
        getElementAndHighlight(btnCart).click();
    }

    public void searchProduct(String product) {
        waitUntilHTMLReady(5);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", txtSearch);
        getElementAndHighlight(txtSearch).typeAndEnter(product);
    }


}
