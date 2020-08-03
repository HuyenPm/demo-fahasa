package serenity.defs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;
import serenity.stepLibraries.OrderSteps;

import java.util.List;
import java.util.Map;

public class OrderDefs {
    @Steps
    OrderSteps order;


    @When("^user clears cart$")
    public void userClearsCart() {
        order.user_clicks_cart();
        order.user_clears_cart();
    }

    @When("user searches product and adds the first item in list result to cart")
    public void userSearchesProductAndAddsTheFirstItemInListResultToCart(DataTable dt) {
        List<Map<String, String>> listKeys = dt.asMaps(String.class, String.class);
        Map<String, String> prd;
        for (int i = 0; i < listKeys.size(); i++) {
            prd = listKeys.get(i);
            order.user_searches_product(prd.get("product"));
            order.verify_navigation_to_listProduct_page(prd.get("expectedTitle"));
            order.user_chooses_first_product();
            order.verify_navigation_to_productDetail_page();
            order.user_chooses_to_buy_product_with_quantity(prd.get("numberProduct"));
        }
    }

    @Then("products in cart should be displayed")
    public void productsInCartShouldBeDisplayed(DataTable dt) {
        order.user_clicks_cart();
        List<Map<String, String>> cart = dt.asMaps(String.class, String.class);
        Map<String, String> product;
        for (int i = 0; i < cart.size(); i++) {
            product = cart.get(i);
            order.verify_information_product_in_cart(product.get("productName"), product.get("price"), product.get("quantity"), i);
        }
    }

    @And("user clicks to pay cart")
    public void userClicksToPayCart() {
        order.user_confirms_to_pay_cart();
    }

    @When("^user edits address of delivery with the following data$")
    public void user_edits_address_of_delivery_with_the_following_data(DataTable dt) {
        List<Map<String, String>> listInfo = dt.asMaps(String.class, String.class);
        Map<String, String> data = listInfo.get(0);
        String name = data.get("name");
        String phone = data.get("phone");
        String country = data.get("country");
        String city = data.get("city");
        String district = data.get("district");
        String ward = data.get("ward");
        String address = data.get("address");

        order.user_edits_address_of_order(name, phone, country, city, district, ward, address);
        order.verify_new_address(name, phone, country, city, district, ward, address);
    }

    @And("^user confirms information of order with notes \"([^\"]*)\" of delivery$")
    public void user_confirms_information_of_order_with_notes_of_delivery(String notes) {
        order.user_confirms_information_of_order_with_notes(notes);
    }



}
