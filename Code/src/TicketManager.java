import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class TicketManager {
    private static List<File> ticketFiles = new ArrayList<>();

    public static void createTicket(Booking booking, Passenger passenger, Flight flight) {
        File ticketFile = new File("./src/Tickets/"+ booking.getBookingID() + ".txt");

        try (FileWriter writer = new FileWriter(ticketFile)) {
            writer.write(AsciiArts.TITLE_RIBBON.getArt());
            writer.write("\n");
            writer.write("Booking ID: " + booking.getBookingID());
            writer.write("\n\nFlight: " + flight.getFlightNumber() +
                        "\nDeparture: " + flight.getOrigin() +
                        "\nDestination: " + flight.getDestination() +
                        "\nFlight Schedule: " + flight.getSchedule());
            writer.write("\n\n" + passenger.getPassengerDetails());
            writer.write("\n\n");
            writer.write(AsciiArts.THANK_YOU.getArt());
            ticketFiles.add(ticketFile);

        } catch (IOException e) {
            System.out.println("Error occurred : " + e.getMessage());
        }
    }

    public static void cancelTicket(String bookingID) {
        for (File ticketFile : ticketFiles) {
            if (ticketFile.getName().equals(bookingID)) {
                ticketFile.delete();
                ticketFiles.remove(ticketFile);
            }
        }
    }
}
