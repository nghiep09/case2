package ReadAndWrite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReadAndWriteBill {

    public  void writeBill(List<SellProduct> bills) {
        File file = new File("D:\\JAVA1\\Case1\\Bill.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (SellProduct bi : bills) {
                bufferedWriter.write(bi.writeBill());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
