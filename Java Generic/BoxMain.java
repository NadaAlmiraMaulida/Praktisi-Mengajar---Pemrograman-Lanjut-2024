public class BoxMain {
    public static void main(String[] args) {
        
Box<String> stringBox = new Box<>("Hello World");
String message = stringBox.getItem();
System.out.println(message);

Box<Integer> integerBox = new Box<>(10);
int number = integerBox.getItem();
System.out.println(number); 
    }
    
}
