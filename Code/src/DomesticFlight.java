public class DomesticFlight extends Flight {
    private String idRequirement;

    // Constructor
    public DomesticFlight(String flightNumber, String origin, String destination, String schedule, int totalSeats, String idRequirement) {
        super(flightNumber, origin, destination, schedule, totalSeats);
        setIdRequirement(idRequirement);
    }

    // Getter and Setter methods
    public String getIdRequirement() { return idRequirement; }
    public void setIdRequirement(String idRequirement) { this.idRequirement = idRequirement; }

    // Method to display flight details
    @Override
    public String getFlightDetails() {
        return super.getFlightDetails() + "\nID Requirement: " + getIdRequirement() + "\n";
    }
}

