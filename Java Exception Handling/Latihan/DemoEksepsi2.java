package Latihan;

class DemoEksepsi2{
    public static void testMethod1(){
    int []arr = new int[1];
    System.out.println(arr[1]);
    }
    public static void testMethod2(){
    testMethod1();
    }
    public static void main(String arg[]){
    testMethod2();
    }
    }