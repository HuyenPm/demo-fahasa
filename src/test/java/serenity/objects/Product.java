package serenity.objects;

public class Product {
    private String namePrdInCart;
    private String pricePrdInCart;
    private String quantity;

    public Product() {}

    public Product(String namePrdInCart, String pricePrdInCart, String quantity) {
        this.namePrdInCart = namePrdInCart;
        this.pricePrdInCart = pricePrdInCart;
        this.quantity = quantity;
    }

    public String getNamePrdInCart() {
        return namePrdInCart;
    }

    public void setNamePrdInCart(String namePrdInCart) {
        this.namePrdInCart = namePrdInCart;
    }

    public String getPricePrdInCart() {
        return pricePrdInCart;
    }

    public void setPricePrdInCart(String pricePrdInCart) {
        this.pricePrdInCart = pricePrdInCart;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return namePrdInCart + " ___ " + pricePrdInCart + " ___ " + quantity + "\n";
    }
}
