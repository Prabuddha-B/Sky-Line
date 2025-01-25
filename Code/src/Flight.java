public class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private String schedule;
    private int totalSeats;
    private boolean[] seats;

    // Constructor
    public Flight(String flightNumber, String origin, String destination, String schedule, int totalSeats) {
        setFlightNumber(flightNumber);
        setOrigin(origin);
        setDestination(destination);
        setSchedule(schedule);
        setTotalSeats(totalSeats);
        this.seats = new boolean[totalSeats];
        for (int i = 0; i < totalSeats; i++) {
            seats[i] = true; // True indicates seat is available
        }
    }

    // Getter and Setter methods
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public boolean[] getSeats() { return seats; }
    public void setSeats(boolean[] seats) { this.seats = seats; }

    // Method to display flight details
    public String getFlightDetails() {
        return "Flight: " + getFlightNumber() + "\nFrom: " + getOrigin() + "\nTo: " + getDestination() + "\nSchedule: " + getSchedule() + "\nAvailable Tickets:" + getAvailableSeats();
    }

    // Method to book a seat
    public boolean bookSeat() {
        for (int i = 0; i < seats.length; i++) {
            if (seats[i]) {
                seats[i] = false; // Seat is now booked
                return true;
            }
        }
        return false; // No available seats
    }

    // Method to cancel a seat
    public boolean cancelSeat(int seatNumber) {
        if (seatNumber < totalSeats && !seats[seatNumber]) {
            seats[seatNumber] = true; // Seat is now available
            return true;
        }
        return false; // Invalid seat number or seat already available
    }

    // Method to get the number of available seats
    public int getAvailableSeats() {
        int availableSeats = 0;
        for (boolean seat : seats) {
            if (seat) {
                availableSeats++;
            }
        }
        return availableSeats;
    }
}
