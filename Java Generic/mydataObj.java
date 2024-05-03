package PRAKTISI;

public class mydataObj {
    public static void main(String[] args) {
        mydata<String> myDataa  = new mydata<>("nada");
        System.out.println(myDataa.getData());

        mydata<Integer> intData = new mydata<>(10);
        mydata<String> strData = new mydata<>("Hello, World!");

        System.out.println("Integer data: " + intData.getData());
        System.out.println("String data: " + strData.getData());

        intData.setData(20);
        strData.setData("Updated");

        System.out.println("Updated Integer data: " + intData.getData());
        System.out.println("Updated String data: " + strData.getData());
    }
}

