package ReadAndWrite;

import Product.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteSellProduct {
    List<Product> products = new ArrayList<>();

    ReadAndWriteProduct readAndWriteProduct = new ReadAndWriteProduct();

    {readAndWriteProduct.readProduct();}

    public List<SellProduct> readFileSellProduct(List<SellProduct> sellProducts){
        try {
            File file = new File("D:\\JAVA1\\Case1\\SellProduct.csv");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String str = bufferedReader.readLine();
            sellProducts.clear();

            while (str != null) {
                String[] arr = str.split(",");
                int id = Integer.parseInt(arr[0]);
                double sellAmount = Double.parseDouble(arr[1]);
                Product product = null;
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getId()==id) {
                        product = products.get(i);
                        break;
                    }
                }

                SellProduct sellproduct = new SellProduct(product, sellAmount);
                sellProducts.add(sellproduct);
                str = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("hiện chưa có hàng");;
        }
        return sellProducts;
    }




    public void writeSellProduct(List<SellProduct> sellProducts) {
        File file = new File("SellProduct.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (SellProduct st : sellProducts) {
                bufferedWriter.write(st.towrite());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
