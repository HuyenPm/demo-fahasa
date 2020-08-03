package serenity.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import serenity.commons.CommonPageObject;

public class DetailProductPage extends CommonPageObject {
    public static String prdName;
    public static String prdPrice;

    @FindBy(xpath = "(//button[@title = 'Thêm vào giỏ hàng'])[1]")
    public static WebElementFacade btnAddToCart;
    @FindBy(id = "continue_shopping")
    public static WebElementFacade btnContinueShopping;
    @FindBy(xpath = "//div[@class='product-view-image-product']//img")
    public static WebElementFacade itemImg;
    @FindBy(xpath = "(//p[@class='special-price'])[1]")
    public static WebElementFacade itemPrice;
    @FindBy(id = "qty")
    public static WebElementFacade itemQuantity;
    @FindBy(id = "NC_CLOSE_ICON")
    public static WebElementFacade btnCloseAds;
    @FindBy(id = "preview-notification-frame")
    public static WebElementFacade iframeAds;

    public void getCurrentProductInfo() {
        prdName = itemImg.getAttribute("title");
        prdPrice = itemPrice.getText().replaceAll("\\s+", "");
    }

    public void setQuantity(String qty) {
        itemQuantity.clear();
        getElementAndHighlight(itemQuantity).typeAndEnter(qty);
    }

    public void clickAddToCart() {
        waitUntilHTMLReady(5);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnAddToCart);
        getElementAndHighlight(btnAddToCart).click();
    }

    public void continueShopping() {
        waitUntilElementVisible(btnContinueShopping);
        btnContinueShopping.waitUntilClickable();
        getElementAndHighlight(btnContinueShopping).click();
    }

    public static boolean isIframeAdsDisplayed() {
        return iframeAds.isPresent();
    }

    public void closeAds() {
        try {
            waitAdsByFluentWait(iframeAds);
            if (isIframeAdsDisplayed()) {
                getDriver().switchTo().defaultContent();
                getDriver().switchTo().frame(iframeAds);
                getElementAndHighlight(btnCloseAds).click();
            }
        } catch (Exception e) {
            System.out.println("Khong tim thay quang cao goi y danh rieng cho ban");
        }
    }

}
