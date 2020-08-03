package serenity.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import serenity.commons.CommonPageObject;
import serenity.objects.Product;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends CommonPageObject {
    @FindBy(xpath = "(//button[@class= 'button btn-proceed-checkout btn-checkout'])[1]")
    public static WebElementFacade btnPay;
    @FindAll(@FindBy(xpath = "//h2[@class='product-name']//a"))
    public static List<WebElementFacade> listItem;


    // Verify navigation to cartPayment page: check btn pay is visible
//    public void waitForCartPageOpen() {
//        waitUntilElementVisible(btnPay);
//    }

    public void deleteCart() {
        List<String> listProductName = getListProductName();
        for (String name : listProductName) {
            waitUntilHTMLReady(10);
            String xpathDel = "(//a[text()='" + name + "']//preceding::a[@class='btn-remove-desktop-cart'])[1]";
            $(xpathDel).waitUntilClickable();
            getElementAndHighlight($(xpathDel)).click();
        }
    }

    public List<String> getListProductName() {
        List<WebElementFacade> listProduct = listItem;
        List<String> listProductName = new ArrayList<>();
        for (WebElementFacade i : listProduct) {
            String listNameItem = toCamelCase(i.getText());
            listProductName.add(listNameItem);
        }
        return listProductName;
    }

    public List<Product> getProductInfo() {
        String xpathPrice, price, xpathQuantity, quantity;
        List<Product> listProduct = new ArrayList<>();
        List<String> listProductName = getListProductName();
        for (int i = 0; i < listProductName.size(); i++) {
            String name = listProductName.get(i);
            xpathPrice = "(//a[text()='" + name + "']//following::div[@class='price-original']//span[@class ='price'])[" + (i + 1) + "]";
            price = $(xpathPrice).getText();
            xpathQuantity = "(//a[text()='" + name + "']//following::input[@class='qty-carts'])[" + (i + 1) + "]";
            quantity = $(xpathQuantity).getAttribute("value");
            listProduct.add(new Product(standardizeString(name), price, quantity));
        }
//        System.out.println(listProduct);
        return listProduct;
    }


    public void clickOnPay() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnPay);
        getElementAndHighlight(btnPay).click();
    }

}
