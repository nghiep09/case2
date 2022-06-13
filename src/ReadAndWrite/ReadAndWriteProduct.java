package ReadAndWrite;

import Product.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteProduct {
    File file = new File("D:\\JAVA1\\Case1\\Product.csv");

    public void writeProduct(List<Product> products) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("id,name,amount,sellPrice");
            bufferedWriter.newLine();
            for (Product st : products) {
                bufferedWriter.write(st.write());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Product> readProduct(){

        ArrayList<Product> products =new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            while ((str = bufferedReader.readLine()) != null) {
                String[] arr = str.split(",");
                int id =Integer.parseInt(arr[0]) ;
                String name = arr[1];
                double amount = Double.parseDouble(arr[2]);
                double sellPrice = Double.parseDouble(arr[3]);

               products.add(new Product(id,name,amount,sellPrice));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println("xin chào");;
        }
        return products;
    }
}
