import java.util.Arrays;

public class FlightTicketBookingSystem {
    private Flight[] flights;
    private Passenger[] passengers;
    private Booking[] bookings;
    private int flightCount;
    private int passengerCount;
    private int bookingCount;

    // Constructor
    public FlightTicketBookingSystem(int flightCapacity, int passengerCapacity, int bookingCapacity) {
        flights = new Flight[flightCapacity];
        passengers = new Passenger[passengerCapacity];
        bookings = new Booking[bookingCapacity];
        flightCount = 0;
        passengerCount = 0;
        bookingCount = 0;
    }

    // Add a new flight
    public void addFlight(Flight flight) {
        if (flightCount < flights.length) {
            flights[flightCount++] = flight;
        } else {
            System.out.println("Flight capacity reached.");
        }
    }

    // Remove a flight
    public void removeFlight(String flightNumber) {
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getFlightNumber().equals(flightNumber)) {
                flights[i] = flights[--flightCount];
                flights[flightCount] = null;
                break;
            }
        }
    }

    // Add a new passenger
    public void addPassenger(Passenger passenger) {
        if (passengerCount < passengers.length) {
            passengers[passengerCount++] = passenger;
        } else {
            System.out.println("Passenger capacity reached.");
        }
    }

    // Remove a passenger
    public void removePassenger(String passengerID) {
        for (int i = 0; i < passengerCount; i++) {
            if (passengers[i].getPassengerID().equals(passengerID)) {
                passengers[i] = passengers[--passengerCount];
                passengers[passengerCount] = null;
                break;
            }
        }
    }

    // Get passengers array (to avoid private access error)
    public Passenger[] getPassengers() {
        return passengers;
    }

    // Book a ticket
    public String bookTicket(String flightNumber, Passenger passenger) {
        for (Flight flight : flights) {
            if (flight != null && flight.getFlightNumber().equals(flightNumber) && flight.bookSeat()) {
                String bookingID = generateBookingID();
                Booking booking = new Booking(bookingID, flightNumber, passenger.getPassengerID(), findAvailableSeat(flight), "Confirmed");
                bookings[bookingCount++] = booking;
                TicketManager.createTicket(booking,passenger,flight);
                return bookingID;
            }
        }
        return null;
    }

    // Cancel a ticket
    public void cancelTicket(String bookingID) {
        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i].getBookingID().equals(bookingID)) {
                bookings[i].setBookingStatus("Cancelled");
                restoreSeat(bookings[i].getFlightNumber(), bookings[i].getSeatNumber());
                TicketManager.cancelTicket(bookingID);
                break;
            }
        }
    }

    // Search for a flight by flight number
    public Flight searchFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight != null && flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    // Search flight by origin, destination, and type
    public Flight[] searchFlightByOriginAndDestination(String origin, String destination, String flightType) {
        Flight[] matchingFlights = new Flight[flightCount];
        int count = 0;
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getOrigin().equalsIgnoreCase(origin) && flights[i].getDestination().equalsIgnoreCase(destination)) {
                if (flightType.equalsIgnoreCase("yes") && flights[i] instanceof InternationalFlight) {
                    matchingFlights[count++] = flights[i];
                } else if (flightType.equalsIgnoreCase("no") && flights[i] instanceof DomesticFlight) {
                    matchingFlights[count++] = flights[i];
                }
            }
        }
        return Arrays.copyOf(matchingFlights, count); // Returns only the non-null elements
    }

    // Search for a passenger by their ID
    public String searchPassenger(String passengerID) {
        Passenger passenger = null;
        for (Passenger p : passengers) {
            if (p != null && p.getPassengerID().equals(passengerID)) {
                passenger = p;
                break;
            }
        }

        if (passenger == null) {
            return "Passenger not found.";
        }

        int ticketCount = 0;
        StringBuilder travelDetails = new StringBuilder();
        for (Booking booking : bookings) {
            if (booking != null && booking.getPassengerID().equals(passengerID)) {
                ticketCount++;
                Flight flight = searchFlight(booking.getFlightNumber());
                if (flight instanceof InternationalFlight) {
                    travelDetails.append("Flight Number: ").append(booking.getFlightNumber())
                            .append(", Destination: ").append(flight.getDestination())
                            .append(", Type: International\n");
                } else if (flight instanceof DomesticFlight) {
                    travelDetails.append("Flight Number: ").append(booking.getFlightNumber())
                            .append(", Destination: ").append(flight.getDestination())
                            .append(", Type: Domestic\n");
                }
            }
        }

        return "Passenger Details:\n" +
                "Name: " + passenger.getName() + "\n" +
                "Age: " + passenger.getAge() + "\n" +
                "Passport Number: " + passenger.getPassportNumber() + "\n" +
                "Number of Tickets: " + ticketCount + "\n" +
                "Travel Details:\n" + travelDetails.toString();
    }

    // Generate a report
    public void generateReport() {
        for (Flight flight : flights) {
            if (flight != null) {
                System.out.println(flight.getFlightDetails());
            }
        }
    }

    // Generate booking ID
    private String generateBookingID() {
        return "BKG" + (bookingCount + 1);
    }

    // Find available seat
    private int findAvailableSeat(Flight flight) {
        boolean[] seats = flight.getSeats();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i]) {
                return i;
            }
        }
        return -1;
    }

    // Restore seat after cancellation
    private void restoreSeat(String flightNumber, int seatNumber) {
        for (Flight flight : flights) {
            if (flight != null && flight.getFlightNumber().equals(flightNumber)) {
                flight.cancelSeat(seatNumber);
                break;
            }
        }
    }
}
