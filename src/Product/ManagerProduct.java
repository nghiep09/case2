package Product;

import Login.ManagerLogin;
import ReadAndWrite.ReadAndWriteBill;
import ReadAndWrite.ReadAndWriteProduct;
import ReadAndWrite.ReadAndWriteSellProduct;
import ReadAndWrite.SellProduct;
import sort.SortByPriceGiam;
import sort.SortByPriceTang;
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
        System.out.println("===Xin Chào ADMIN===");
        System.out.println("========MENU========");
        System.out.println("1. Thêm Sản phẩm ");
        System.out.println("2. Chỉnh sửa sản phẩm ");
        System.out.println("3. tìm kiếm sản phẩm");
        System.out.println("4. xóa");
        System.out.println("5. sắp xếp");
        System.out.println("6. Hiển thị sản phẩm có trong cửa hàng");
        System.out.println("7. Logout");
        System.out.println("8. Thoát");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                addProduct(creatProduct());
                break;
            case 2:
                editProduct();
                break;
            case 3:
                search();
                break;
            case 4:
                delete();
                break;
            case 5:
                sort();
                break;
            case 6:
                showProduct();
                readAndWriteProduct.writeProduct(products);
                break;
            case 7:
                ManagerLogin.login = null;
                break;
            case 8:
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
            System.out.println(sum);
        }
        return null;
    }
    public void delete() {
        System.out.println("Nhập id cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        int index = validateProduct.getIndexId(id, products);
        if (index != -1) {
            products.remove(index);
        } else {
            System.out.println("Không có sản phẩm trùng id");
        }
    }
    public void sort(){
        System.out.println(" chọn cách sắp xếp");
        System.out.println("1. giá tăng dần");
        System.out.println("2. giá giảm dần");
        int choice1 = Integer.parseInt(scanner.nextLine());

        switch (choice1) {
            case 1:
                products.sort(new SortByPriceTang());
                for (Product pt : products) {
                    System.out.println(pt);
                }
                break;

            case 2:
                products.sort(new SortByPriceGiam());
                for (Product pt : products) {
                    System.out.println(pt);
                }
                break;
        }
    }
    public void search() {
        boolean check = false;
        System.out.println("Nhập tên cần tìm kiếm: ");
        String ten = scanner.nextLine();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().contains(ten)) {
                System.out.println(products.get(i));
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy tên sản phẩm");
        }
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
        System.out.println("===Xin Chào "+ ManagerLogin.login.getUserName()+ " đến với cửa hàng ===");
        System.out.println("================MENU================");
        System.out.println("1. Thêm Sản phẩm vào giỏ");
        System.out.println("2. Chỉnh sửa sản phẩm có trong giỏ");
        System.out.println("3. Hiển thị sản phẩm có trong giỏ");
        System.out.println("4. Tìm kiếm sản phẩm trong cửa hàng");
        System.out.println("5. Sắp xếp theo giá");
        System.out.println("6. Hiển thị sản phẩm có trong cửa hàng");
        System.out.println("7. Xuất Bill");
        System.out.println("8. Logout");
        System.out.println("9. Thoát");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                addSellProduct();
                break;
            case 2:
                editSellProduct();
                break;
            case 3:
                showProductforClient();
                break;
            case 4:
                search();
                break;
            case 5:
                sort();
                break;
            case 6:
                showProduct();
                break;
            case 7:
                showBill();
            case 8:
              ManagerLogin.login =null;
                break;
            case 9:
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
    public void showProductforClient() {
        for (SellProduct st: sellProducts) {
            System.out.println(st);
        }
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


