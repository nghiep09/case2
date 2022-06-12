package Product;

import Login.ManagerLogin;
import ReadAndWrite.ReadAndWriteBill;
import ReadAndWrite.ReadAndWriteProduct;
import ReadAndWrite.ReadAndWriteSellProduct;
import ReadAndWrite.SellProduct;
import validate.ValidateProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerProduct {

    Scanner scanner = new Scanner(System.in);
    List<Product> products = new ArrayList<>();
    List<SellProduct> sellProducts = new ArrayList<>();
    List<SellProduct> bills = new ArrayList<>();

    ValidateProduct validateProduct = new ValidateProduct();
    ReadAndWriteProduct readAndWriteProduct = new ReadAndWriteProduct();
    ReadAndWriteSellProduct readAndWriteSellProduct = new ReadAndWriteSellProduct();
    ReadAndWriteBill readAndWriteBill = new ReadAndWriteBill();
    ManagerLogin managerLogin = new ManagerLogin();
    SellProduct sellProduct;

    public void ProductMenu() {
        System.out.println("MENU");
        System.out.println("1. Thêm Sản phẩm ");
        System.out.println("2. Chỉnh sửa sản phẩm ");
        System.out.println("3. Hiển thị sản phẩm có trong cửa hàng");
        System.out.println("4. Logout");
        System.out.println("5. Thoát");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                addProduct(creatProduct());
                break;
            case 2:
                editProduct();
                break;
            case 3:
                showProduct();
                readAndWriteProduct.writeProduct(products);
                break;
            case 4:
                ManagerLogin.login = null;
                break;
            case 5:
                System.exit(0);
                break;

        }
    }

    public Product creatProduct() {
        int id = validateProduct.validateInt(products);
        String name = validateProduct.validateString("name: ");
        double price = validateProduct.validateDouble("price: ");
        double amount = validateProduct.validateDouble("amount: ");

        return new Product(id, name, amount, price);

    }

    public void addProduct(Product product) {
        products.add(product);

//        editSellAmount();
//        products.add(creatProduct());
//        readAndWriteProduct.writeProduct(products);


    }

    public Product editSellAmount() {
        for (int i = 0; i < products.size(); i++) {
            double sum = 0;
            for (int j = 0; j < sellProducts.size(); j++) {
                if (products.get(i).getId() == (sellProducts.get(j).getProduct().getId())) {
                    SellProduct ip = sellProducts.get(j);
                    double a = ip.getSellAmount();
                    sum += a;
                }
            }
            sellProducts.get(i).setSellAmount(sum);
        }
        return null;
    }



    public void editProduct(){
        System.out.println("Nhập id cần sửa");
        int id = Integer.parseInt(scanner.nextLine());
        int index = validateProduct.getIndexId(id,products);
        if (index != -1){
            products.set(index, creatProduct());
        } else {
            System.err.println("id không tồn tại");
        }
    }

    public void showProduct(){
        for (int i = 0; i < products.size(); i++) {
            if ((i+1) % 5 == 0) {
                System.out.println(products.get(i));
                scanner.nextLine();
            } else {
                System.out.println(products.get(i));
            }
        }
    }

    public void sellProductMenu() {
        System.out.println("MENU:");
        System.out.println("1. Thêm Sản phẩm vào giỏ");
        System.out.println("2. Chỉnh sửa sản phẩm có trong giỏ");
        System.out.println("3. Hiển thị sản phẩm có trong giỏ");
        System.out.println("4. Hiển thị sản phẩm có trong cửa hàng");
        System.out.println("5. Xuất Bill");
        System.out.println("6. Logout");
        System.out.println("7. Thoát");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                addSellProduct();
                break;
            case 2:
                editSellProduct();
                break;
            case 3:
                showSellProductforme();
                break;
            case 4:
                showProduct();
                break;
            case 5:
                showBill();
                break;
            case 6:
              ManagerLogin.login =null;
                break;
            case 7:
                System.exit(0);
                break;
        }
    }

    public void addSellProduct() {
        readAndWriteSellProduct.readFileSellProduct(sellProducts);
        System.out.println(" nhập id: ");
        int id = Integer.parseInt(scanner.nextLine());
        double sellAmount = validateProduct.validateDouble("sô lượng cần mua: ");
        boolean check = true;
        Product product = null;
        for (int i = 0; i < products.size(); i++) {
            if ((products.get(i).getId())==id) {

                if (sellAmount < products.get(i).stockAmount()) {
                    product = products.get(i);
                } else {
                    System.out.println("sản phẩm đã hết hàng. mong quý khách quay lại vào ngày mai");
                    sellProductMenu();
                }
                check=false;
            }
        }

        if (check) {
            System.out.println("sản phẩm này hiện đã hết hàng. quý khách có thể chọn id sản phẩm khác");
            sellProductMenu();
        }
        sellProduct = new SellProduct(product, sellAmount);
        sellProducts.add(sellProduct);
        bills.add(sellProduct);

        readAndWriteSellProduct.writeSellProduct(sellProducts);
        readAndWriteBill.writeBill(bills);
    }

    public void editSellProduct() {
        int id = validateProduct.validateInt(products);
        double amount = validateProduct.validateDouble("amount: ");
        for (int i = 0; i < bills.size(); i++) {
            if ((products.get(i).getId()==id)) {
                for (int j = 0; j < sellProducts.size(); j++) {
                    if ((products.get(i).getId()==id)) {
                        sellProducts.get(j).setSellAmount(sellProducts.get(j).getSellAmount() - bills.get(i).getSellAmount() + amount);
                    }
                }
                bills.get(i).setSellAmount(amount);
            }
        }
        readAndWriteBill.writeBill(bills);
    }
    public void showSellProductforme(){
            readAndWriteSellProduct.readFileSellProduct(sellProducts);
            int totalSell = 0;
            for (int i = 0; i < sellProducts.size(); i++) {
                if (ManagerLogin.login.equals(sellProducts.get(i))) {
                    System.out.println(sellProducts.get(i).toString());
                    totalSell += sellProducts.get(i).totalPrice();
                }
            }
            System.out.println("totalSell: " + totalSell);
        }


    public void showBill() {

        double total = 0;
        for (SellProduct sell : bills) {
            System.out.println(sell.writeBill());
            total += sell.totalPrice();
        }
        System.out.println("total: " + total + " VND");
        readAndWriteBill.writeBill(bills);
        bills.clear();

    }
}


