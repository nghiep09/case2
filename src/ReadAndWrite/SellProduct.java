package ReadAndWrite;

import Product.Product;

public class SellProduct {
    private Product product;
    private double sellAmount;

    public SellProduct() {

    }

    public SellProduct(Product product, double sellAmount) {
        this.product = product;
        this.sellAmount = sellAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(double sellAmount) {
        this.sellAmount = sellAmount;
    }

    public String towrite(){
        return  product + " , " + sellAmount;
    }
    public String writeBill() {
        int totalPrice = (int) totalPrice();

        return product.getId() +
                "," + product.getName() +
                "," + product.getPrice() +
                "," + sellAmount +
                "," + totalPrice ;
    }

    public double totalPrice() {
        return product.getPrice() * sellAmount;
    }

    @Override
    public String toString() {
        return "SellProduct{" +
                "name "+ product.getName() +
                ", totalPrice= " + totalPrice() +
                ", sellAmount= " + sellAmount +
                '}';
    }

}
