package serenity.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import serenity.commons.CommonPageObject;

import static serenity.pages.DetailProductPage.iframeAds;
import static serenity.pages.DetailProductPage.isIframeAdsDisplayed;

public class ListProductPage extends CommonPageObject {
    public static String expectedPrdName;
    public static String expectedPrdPrice;

    @FindBy(xpath = "(//ul[contains(@class, 'products-grid')]//li)[1]")
    public static WebElementFacade firstProduct;
    @FindBy(xpath = "(//ul[contains(@class, 'products-grid')]//li)[1]//h2//a")
    public static WebElementFacade firstProductName;
    @FindBy(xpath = "(//ul[contains(@class, 'products-grid')]//li)[1]//span[contains(@id,'product-price')]")
    public static WebElementFacade firstProductPrice;
    @FindBy(id = "NC_IMAGE")
    public static WebElementFacade btnCloseImgAds;


    public String getTitleOfListResultPage() {
        String actualTitleSearchPage = getDriver().getTitle();
        return actualTitleSearchPage;
    }

    public void getExpectedProductInfo() {
        expectedPrdName = firstProductName.getAttribute("title");
        expectedPrdPrice = firstProductPrice.getText();
    }

    public void chooseProduct() {
        firstProduct.waitUntilClickable();
        getElementAndHighlight(firstProduct).click();
    }

    public void closeImgAds() {
        try {
            waitAdsByFluentWait(iframeAds);
            if (isIframeAdsDisplayed()) {
                getDriver().switchTo().defaultContent();
                getDriver().switchTo().frame(iframeAds);
                getElementAndHighlight(btnCloseImgAds).click();
            }
        } catch (Exception e) {
            System.out.println("Khong tim thay quang cao mua sach thang 7");
        }


    }

}
