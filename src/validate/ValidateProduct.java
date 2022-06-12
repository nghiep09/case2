package validate;

import Product.Product;

import java.util.List;
import java.util.Scanner;

public class ValidateProduct {
    Scanner scanner =new Scanner(System.in);



    public  int validateInt(List<Product> products){
        while (true){
            try {
                System.out.println("Nhập id: ");
                int id=Integer.parseInt(scanner.nextLine());
                if (getIndexId(id, products) != -1) {
                    throw new Exception();
                }
                return id;
            } catch (Exception e) {
                System.err.println("Nhập sai id rồi");
            }
        }
    }
    public  int getIndexId(int id, List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId()==id){
                return i;
            }

        }
        return -1;
    }
    public  String validateString(String name) {
        while (true) {
            System.out.println("Nhập " + name);
            String str = scanner.nextLine();
            if (str.equals("")) {
                System.err.println("không để trống");
                continue;
            } else {
                return str;
            }
        }
    }
    public  double validateDouble(String b) {
        while (true) {
            try {
                System.out.println("Nhập " + b);
                double c= Integer.parseInt(scanner.nextLine());
                return c;
            } catch (Exception e) {
                System.err.println("Nhập sai rồi, nhập số thập phân");
            }
        }
    }

}
