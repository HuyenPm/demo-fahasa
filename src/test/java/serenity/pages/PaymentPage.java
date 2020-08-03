package serenity.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import serenity.commons.CommonPageObject;

public class PaymentPage extends CommonPageObject {
    @FindBy(xpath = "(//li[@class = 'fhs_checkout_block_address_list_item']//child::span)[2]")
    public static WebElementFacade btnEditAddress;
    @FindBy(id = "fhs_address_fullname")
    public static WebElementFacade name;
    @FindBy(id = "fhs_address_telephone")
    public static WebElementFacade phone;
    @FindBy(xpath = "//span[contains(@aria-labelledby,'address_country_select')]")
    public static WebElementFacade inputCountry;
    @FindBy(xpath = "//span[contains(@aria-labelledby,'address_city_select')]")
    public static WebElementFacade inputCity;
    @FindBy(xpath = "//span[contains(@aria-labelledby,'address_district_select')]")
    public static WebElementFacade inputDistrict;
    @FindBy(xpath = "//span[contains(@aria-labelledby,'address_wards_select')]")
    public static WebElementFacade inputWard;
    @FindBy(className = "select2-search__field")
    public static WebElementFacade txtBoxSearch;
    @FindBy(id = "fhs_address_street")
    public static WebElementFacade inputAddress;
    @FindBy(xpath = "//button[@title = 'Lưu địa chỉ']")
    public static WebElementFacade btnSaveAddress;
    @FindBy(xpath = "//input[@id='fhs_checkout_shippingmethod_vietnamshippingnormal_vietnamshippingnormal']//ancestor::label")
    public static WebElementFacade radioBtnStandardDelivery;
    @FindBy(xpath = "//input[@id='fhs_checkout_paymentmethod_zalopayatm']//ancestor::label")
    public static WebElementFacade radioBtnATMPayment;
    @FindBy(xpath = "//label[text() ='Ghi chú']")
    public static WebElementFacade checkboxNote;
    @FindBy(id = "fhs_checkout_note")
    public static WebElementFacade textareaNote;
    @FindBy(xpath = "//ul[@id='fhs_checkout_address']//label")
    public static WebElementFacade newAddress;

    public void clickBtnEdit() {
        waitUntilHTMLReady(10);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnEditAddress);
        getElementAndHighlight(btnEditAddress).click();
    }

    public void enterName(String inputName) {
        waitUntilElementVisible(name);
        getElementAndHighlight(name).clear();
        name.typeAndEnter(inputName);
    }

    public void enterPhone(String inputPhone) {
        getElementAndHighlight(phone).clear();
        phone.typeAndEnter(inputPhone);
    }

    public void enterCountry(String iCountry) {
        getElementAndHighlight(inputCountry).click();
        getElementAndHighlight(txtBoxSearch).typeAndEnter(iCountry);
    }

    public void enterCity(String iCity) {
        getElementAndHighlight(inputCity).click();
        getElementAndHighlight(txtBoxSearch).typeAndEnter(iCity);
    }

    public void enterDistrict(String iDistrict) {
        getElementAndHighlight(inputDistrict).click();
        getElementAndHighlight(txtBoxSearch).typeAndEnter(iDistrict);
    }

    public void enterWard(String iWard) {
        getElementAndHighlight(inputWard).click();
        getElementAndHighlight(txtBoxSearch).typeAndEnter(iWard);
    }

    public void enterAddress(String iAddress) {
        getElementAndHighlight(inputAddress).clear();
        inputAddress.typeAndEnter(iAddress);
    }

    public void saveNewAddress() {
        getElementAndHighlight(btnSaveAddress).click();
    }

    public String getNewAddress() {
        return newAddress.getText();
    }

    public String getCountryInNewAddress() {
        String s = getNewAddress();
        StringBuilder sb = new StringBuilder();
        String a[] = s.split(",");
        sb.append(a[4].substring(1, 3));
        if (sb.toString().equals("VN")) return "Việt Nam";
        return null;
    }

    public void chooseShippingMethod() {
        waitUntilHTMLReady(5);
        waitUntilElementVisible(radioBtnStandardDelivery);
        getElementAndHighlight(radioBtnStandardDelivery).click();
    }

    public void choosePaymentMethod() {
        getElementAndHighlight(radioBtnATMPayment).click();
    }

    public void enterNotes(String notes) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", checkboxNote);
        getElementAndHighlight(checkboxNote).click();
        textareaNote.waitUntilClickable();
        getElementAndHighlight(textareaNote).typeAndEnter(notes);
    }


}
