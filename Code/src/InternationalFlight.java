public class InternationalFlight extends Flight {
    private String passportRequirement;

    // Constructor
    public InternationalFlight(String flightNumber, String origin, String destination, String schedule, int totalSeats, String passportRequirement) {
        super(flightNumber, origin, destination, schedule, totalSeats);
        setPassportRequirement(passportRequirement);
    }

    // Getter and Setter methods
    public String getPassportRequirement() { return passportRequirement; }
    public void setPassportRequirement(String passportRequirement) { this.passportRequirement = passportRequirement; }

    // Method to display flight details
    @Override
    public String getFlightDetails() {
        return super.getFlightDetails() + "\nPassport Requirement: " + getPassportRequirement() + "\n";
    }
}
