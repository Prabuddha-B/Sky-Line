import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating Flight Booking System Obj
        FlightTicketBookingSystem ftbs = new FlightTicketBookingSystem(10, 50, 100);

        // Preload some sample data
        loadSampleData(ftbs);

        while (true) {


            System.out.println(AsciiArts.TITLE_RIBBON.getArt());
            try {
                System.out.println("\n------------ Main Menu ------------");
                System.out.println("1. Add Flight");
                System.out.println("2. Add Passenger");
                System.out.println("3. Book Ticket");
                System.out.println("4. Cancel Ticket");
                System.out.println("5. View Flights");
                System.out.println("6. Search Flights");
                System.out.println("7. Search Passenger");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consuming newline

                // Switch Cases
                switch (choice) {
                    case 1:
                        addFlight(ftbs, scanner);
                        break;
                    case 2:
                        addPassenger(ftbs, scanner);
                        break;
                    case 3:
                        bookTicket(ftbs, scanner);
                        break;
                    case 4:
                        cancelTicket(ftbs, scanner);
                        break;
                    case 5:
                        ftbs.generateReport();
                        break;
                    case 6:
                        searchFlights(ftbs, scanner);
                        break;
                    case 7:
                        searchPassenger(ftbs, scanner);
                        break;
                    case 8:
                        System.out.println(AsciiArts.THANK_YOU.getArt());
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    // Method to Use Dummy Objects
    private static void loadSampleData(FlightTicketBookingSystem ftbs) {
        InternationalFlight flight1 = new InternationalFlight("FL001", "New York", "London", "2024-12-01 08:00", 100, "Valid Passport");
        DomesticFlight flight2 = new DomesticFlight("FL002", "Chicago", "San Francisco", "2024-12-01 09:00", 150, "Valid ID");
        ftbs.addFlight(flight1);
        ftbs.addFlight(flight2);
        Passenger passenger1 = new Passenger("P001", "John Doe", 30, "AB1234567");
        Passenger passenger2 = new Passenger("P002", "Jane Smith", 28, "CD7654321");
        ftbs.addPassenger(passenger1);
        ftbs.addPassenger(passenger2);
    }

    private static void addFlight(FlightTicketBookingSystem ftbs, Scanner scanner) {
        try {

            System.out.println(AsciiArts.ADD_PLANE.getArt());
            System.out.print("Enter Flight Number: ");
            String flightNumber = scanner.nextLine();
            System.out.print("Enter Origin: ");
            String origin = scanner.nextLine();
            System.out.print("Enter Destination: ");
            String destination = scanner.nextLine();
            System.out.print("Enter Schedule: ");
            String schedule = scanner.nextLine();
            System.out.print("Enter Total Seats: ");
            int totalSeats = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Is it an International Flight (yes/no)? ");
            String flightType = scanner.nextLine();
            if (flightType.equalsIgnoreCase("yes")) {
                System.out.print("Enter Passport Requirement: ");
                String passportRequirement = scanner.nextLine();
                InternationalFlight flight = new InternationalFlight(flightNumber, origin, destination, schedule, totalSeats, passportRequirement);
                ftbs.addFlight(flight);
            } else {
                System.out.print("Enter ID Requirement: ");
                String idRequirement = scanner.nextLine();
                DomesticFlight flight = new DomesticFlight(flightNumber, origin, destination, schedule, totalSeats, idRequirement);
                ftbs.addFlight(flight);
            }
            System.out.println("Flight added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the details correctly.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void addPassenger(FlightTicketBookingSystem ftbs, Scanner scanner) {
        try {
            System.out.println(AsciiArts.ADD_PASSENGER.getArt());
            System.out.print("Enter Passenger ID: ");
            String passengerID = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Passport Number: ");
            String passportNumber = scanner.nextLine();
            Passenger passenger = new Passenger(passengerID, name, age, passportNumber);
            ftbs.addPassenger(passenger);
            System.out.println("Passenger added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the details correctly.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void bookTicket(FlightTicketBookingSystem ftbs, Scanner scanner) {
        System.out.println(AsciiArts.BOOK_TICKET.getArt());
        try {
            System.out.print("Enter Flight Number: ");
            String flightNumber = scanner.nextLine();
            Flight flight = ftbs.searchFlight(flightNumber);
            if (flight == null) {
                System.out.println("Flight not found.");
                return;
            }
            System.out.print("Enter Passenger ID: ");
            String passengerID = scanner.nextLine();
            Passenger passenger = null;
            for (Passenger p : ftbs.getPassengers()) {
                if (p != null && p.getPassengerID().equals(passengerID)) {
                    passenger = p;
                    break;
                }
            }
            if (passenger == null) {
                System.out.println("Passenger not found.");
                return;
            }
            String bookingID = ftbs.bookTicket(flightNumber, passenger);
            if (bookingID != null) {
                System.out.println("Booking successful. Booking ID: " + bookingID);
            } else {
                System.out.println("Booking failed. No available seats.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the details correctly.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void cancelTicket(FlightTicketBookingSystem ftbs, Scanner scanner) {
        System.out.println(AsciiArts.CANCEL_TICKET.getArt());
        try {
            System.out.print("Enter Booking ID: ");
            String bookingID = scanner.nextLine();
            ftbs.cancelTicket(bookingID);
            System.out.println("Booking canceled successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the details correctly.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void searchFlights(FlightTicketBookingSystem ftbs, Scanner scanner) {
        System.out.println(AsciiArts.SEARCH_FLIGHTS.getArt());
        System.out.print("Enter Origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Is it an International Flight (yes/no)? ");
        String flightType = scanner.nextLine();

        Flight[] flights = ftbs.searchFlightByOriginAndDestination(origin, destination, flightType);
        if (flights.length > 0) {
            System.out.println("Matching flights:");
            for (Flight flight : flights) {
                System.out.println(flight.getFlightDetails());
            }
        } else {
            System.out.println("No matching flights found.");
        }
    }

    private static void searchPassenger(FlightTicketBookingSystem ftbs, Scanner scanner) {
        System.out.println(AsciiArts.SEARCH_PASSENGER.getArt());
        System.out.print("Enter Passenger ID: ");
        String passengerID = scanner.nextLine();
        String result = ftbs.searchPassenger(passengerID);
        System.out.println(result);
    }
}
