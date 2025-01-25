public class Booking {
    private String bookingID;
    private String flightNumber;
    private String passengerID;
    private int seatNumber;
    private String bookingStatus;


    // Constructor
    public Booking(String bookingID, String flightNumber, String passengerID, int seatNumber, String bookingStatus) {
        this.bookingID = bookingID;
        this.flightNumber = flightNumber;
        this.passengerID = passengerID;
        this.seatNumber = seatNumber;
        this.bookingStatus = bookingStatus;
    }

    // Getter and Setter methods
    public String getBookingID() { return bookingID; }
    public String getFlightNumber() { return flightNumber; }
    public String getPassengerID() { return passengerID; }
    public int getSeatNumber() { return seatNumber; }
    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String status) { this.bookingStatus = status; }

    // Method to display booking details
    public String getBookingDetails() {
        return "Booking ID: " + getBookingID() + "\nFlight: " + getFlightNumber() + "\nPassenger: " + getPassengerID() + "\nSeat: " + getSeatNumber() + "\nStatus: " + getBookingStatus();
    }
}

