package Product;

public class Product {
    private  int id;
    private  String name;
    private double amount;

    private  double price;
    private double sellAmount;

    public Product() {

    }

    public Product(int id, String name, double amount, double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        int stockAmount = (int) stockAmount();

        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", stockAmount " + stockAmount +
                '}';
    }

    public double stockAmount(){
        return this.amount-this.sellAmount;
    }
    public String write(){
        return id + " , " + name +
                " , " + amount +
                " , " + sellAmount +
                " , " + price ;
    }
}
