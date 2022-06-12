package ReadAndWrite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteLogin<E> {

    public void write (List<E> list, String path){
        File file = new File(path);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<E> reader(String path){

        File file = new File(path);
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (ArrayList<E>) objectInputStream.readObject();
        }  catch (IOException| ClassNotFoundException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }
}
