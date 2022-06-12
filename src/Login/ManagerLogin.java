package Login;

import ReadAndWrite.ReadAndWriteLogin;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerLogin {
    ReadAndWriteLogin<Login> readAndWriteLogin = new ReadAndWriteLogin<>();
    Scanner scanner = new Scanner(System.in);

    public static Login login;

    ArrayList<Login> logins=new ArrayList<>();
    {
        logins=readAndWriteLogin.reader("login.csv");
        if(logins.size()==0){
            logins.add(new Login("nghiep","nghiep1409","admin"));
        }
    }
    public void manuLogin(){
        System.out.println("1. Login");
        System.out.println("2. Register");
        int choice=Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                loginss();
                break;
            case 2:
                register();
                break;
        }
    }
    public boolean loginss(){
        System.out.println("Nhập tên: ");
        String userName = scanner.nextLine();
        System.out.println("Nhập password: ");
        String password = scanner.nextLine();

        for (Login lg:logins){
            if(lg.getUserName().equals(userName)&&lg.getPassword().equals(password)){
                ManagerLogin.login=lg;
                return true;
            }
        }
        return false;
    }
    public void register(){
        String user = null;
        while (true){
            System.out.println("Nhập tên: ");
            user=scanner.nextLine();
            if (checkUserName(user)){
                break;
            }else {
                System.err.println("Trùng tên đăng nhập: ");
            }
        }
        System.out.println("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        logins.add(new Login(user,password,"user"));
        readAndWriteLogin.write(logins,"login.csv");
    }
    public boolean checkUserName(String userName){
        for (Login lg:logins) {
            if(lg.getUserName().equals(userName)){
                return false;
            }

        }
        return true;
    }

}
