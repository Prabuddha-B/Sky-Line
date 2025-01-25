public class Passenger {
    private String passengerID;
    private String name;
    private int age;
    private String passportNumber;

    // Constructor
    public Passenger(String passengerID, String name, int age, String passportNumber) {
        setPassengerID(passengerID);
        setName(name);
        setAge(age);
        setPassportNumber(passportNumber);
    }

    // Getter and Setter methods
    public String getPassengerID() { return passengerID; }
    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }

    // Method to display passenger details
    public String getPassengerDetails() {
        return "Passenger ID: " + getPassengerID() + "\nName: " + getName() + "\nAge: " + getAge() + "\nPassport Number: " + getPassportNumber();
    }
}
