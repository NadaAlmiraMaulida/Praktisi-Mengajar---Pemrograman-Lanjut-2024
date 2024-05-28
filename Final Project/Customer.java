// public class Customer<T, U, V, W> {
//     private T name;
//     private U ticketNumber;
//     private V movie;
//     private W seatNumber;

//     public Customer(T name, U ticketNumber, V movie, W seatNumber) {
//         this.name = name;
//         this.ticketNumber = ticketNumber;
//         this.movie = movie;
//         this.seatNumber = seatNumber;
//     }

//     @Override
//     public String toString() {
//         return "Customer{name='" + name + "', ticketNumber=" + ticketNumber + ", movie='" + movie + "', seatNumber=" + seatNumber + "}";
//     }

//     // Getters and Setters
//     public T getName() {
//         return name;
//     }

//     public void setName(T name) {
//         this.name = name;
//     }

//     public U getTicketNumber() {
//         return ticketNumber;
//     }

//     public void setTicketNumber(U ticketNumber) {
//         this.ticketNumber = ticketNumber;
//     }

//     public V getMovie() {
//         return movie;
//     }

//     public void setMovie(V movie) {
//         this.movie = movie;
//     }

//     public W getSeatNumber() {
//         return seatNumber;
//     }

//     public void setSeatNumber(W seatNumber) {
//         this.seatNumber = seatNumber;
//     }
// }

class Customer {
    private String name;
    private int ticketNumber;
    private String movie;
    private int seatNumber;
    private String paymentMethod;

    public Customer(String name, int ticketNumber, String movie, int seatNumber, String paymentMethod) {
        this.name = name;
        this.ticketNumber = ticketNumber;
        this.movie = movie;
        this.seatNumber = seatNumber;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', ticketNumber=" + ticketNumber + ", movie='" + movie + "', seatNumber=" + seatNumber + ", paymentMethod='" + paymentMethod + "'}";
    }
}