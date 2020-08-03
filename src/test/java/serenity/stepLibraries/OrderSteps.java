package serenity.stepLibraries;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.yecht.Data;
import serenity.objects.Product;
import serenity.pages.HomePage;
import serenity.pages.ListProductPage;
import serenity.pages.*;

import static serenity.pages.ListProductPage.expectedPrdName;
import static serenity.pages.DetailProductPage.*;
import static serenity.pages.ListProductPage.expectedPrdPrice;

public class OrderSteps {
    ListProductPage listProductPage;
    HomePage homePage;
    DetailProductPage detailProductPage;
    CartPage cartPage;
    PaymentPage paymentPage;

    @Step
    public void user_clicks_cart() {
        homePage.clickCart();
//        cartPage.waitForCartPageOpen();
    }

    @Step
    public void user_clears_cart() {
        cartPage.deleteCart();
    }

    @Step
    public void user_searches_product(String product) {
        homePage.searchProduct(product);
        listProductPage.closeImgAds();
    }

    @Step
    public void verify_navigation_to_listProduct_page(String expectedTitle) {
        String actualTitle = listProductPage.getTitleOfListResultPage();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Step
    public void user_chooses_first_product() {
        listProductPage.getExpectedProductInfo();
        listProductPage.chooseProduct();
        detailProductPage.closeAds();
    }

    @Step
    public void verify_navigation_to_productDetail_page() {
        detailProductPage.getCurrentProductInfo();
        Assert.assertEquals(expectedPrdName, prdName);
        Assert.assertEquals(expectedPrdPrice, prdPrice);
    }

    @Step
    public void user_chooses_to_buy_product_with_quantity(String quantity) {
        detailProductPage.setQuantity(quantity);
        detailProductPage.clickAddToCart();
        detailProductPage.continueShopping();
    }

    @Step
    public void verify_information_product_in_cart(String name, String price, String quantity, int i) {
        Product prd = cartPage.getProductInfo().get(i);
        Assert.assertEquals(name, prd.getNamePrdInCart());
        Assert.assertEquals(price, prd.getPricePrdInCart());
        Assert.assertEquals(quantity, prd.getQuantity());
    }

    @Step
    public void user_confirms_to_pay_cart() {
        cartPage.clickOnPay();
    }

    @Step
    public void user_edits_address_of_order(String name, String phone, String country, String city, String district, String ward, String address) {
        paymentPage.clickBtnEdit();
        paymentPage.enterName(name);
        paymentPage.enterPhone(phone);
        paymentPage.enterCountry(country);
        paymentPage.enterCity(city);
        paymentPage.enterDistrict(district);
        paymentPage.enterWard(ward);
        paymentPage.enterAddress(address);
        paymentPage.saveNewAddress();
    }

    @Step
    public void verify_new_address(String name, String phone, String country, String city, String district, String ward, String address) {
        String newAddress = paymentPage.getNewAddress();
        Assert.assertTrue(newAddress.contains(name));
        Assert.assertTrue(newAddress.contains(phone));
        Assert.assertTrue(paymentPage.getCountryInNewAddress().contains(country));
        Assert.assertTrue(newAddress.contains(city));
        Assert.assertTrue(newAddress.contains(district));
        Assert.assertTrue(newAddress.contains(ward));
        Assert.assertTrue(newAddress.contains(address));
    }

    @Step
    public void user_confirms_information_of_order_with_notes(String notes) {
        paymentPage.chooseShippingMethod();
        paymentPage.choosePaymentMethod();
        paymentPage.enterNotes(notes);
    }

}
