
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    private String flightCode;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String flightCode, String airline, String source,
                  String destination, int availableSeats, double baseFare) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getAirline() {
        return airline;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public boolean hasAvailableSeats(int tickets) {
        return tickets > 0 && tickets <= availableSeats;
    }

    public void reserveSeats(int tickets) {
        if (hasAvailableSeats(tickets)) {
            availableSeats -= tickets;
        }
    }

    public void displayFlight() {
        System.out.printf("%-8s %-15s %-15s %-15s %-10d Rs. %.2f%n",
                flightCode, airline, source, destination,
                availableSeats, baseFare);
    }
}

abstract class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double getDiscountPercentage();

    public abstract String getPassengerType();
}

class RegularPassenger extends Passenger {
    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 0;
    }

    @Override
    public String getPassengerType() {
        return "Regular";
    }
}

class StudentPassenger extends Passenger {
    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 10;
    }

    @Override
    public String getPassengerType() {
        return "Student";
    }
}

class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int numberOfTickets;
    private double subtotal;
    private double discount;
    private double finalAmount;

    public Booking(String bookingId, Flight flight,
                   Passenger passenger, int numberOfTickets) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.numberOfTickets = numberOfTickets;

        subtotal = flight.getBaseFare() * numberOfTickets;
        discount = subtotal * passenger.getDiscountPercentage() / 100;
        finalAmount = subtotal - discount;
    }

    public void displaySummary() {
        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight: " + flight.getFlightCode()
                + " | " + flight.getSource()
                + " to " + flight.getDestination());
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Age: " + passenger.getAge());
        System.out.println("Passenger Type: "
                + passenger.getPassengerType());
        System.out.println("Tickets: " + numberOfTickets);

        System.out.printf("Base Fare per Ticket: Rs. %.2f%n",
                flight.getBaseFare());
        System.out.printf("Subtotal: Rs. %.2f%n", subtotal);
        System.out.printf("%s Discount (%.0f%%): Rs. %.2f%n",
                passenger.getPassengerType(),
                passenger.getDiscountPercentage(), discount);
        System.out.printf("Final Amount: Rs. %.2f%n", finalAmount);
        System.out.println("=====================================");
    }
}

public class FlightBookingSystem {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Flight> flights = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    private int nextBookingNumber = 1001;

    public FlightBookingSystem() {
        flights.add(new Flight(
                "PN101", "Air India", "Pune", "Delhi", 20, 4000));

        flights.add(new Flight(
                "MB202", "IndiGo", "Mumbai", "Bengaluru", 30, 3500));

        flights.add(new Flight(
                "PN303", "Akasa Air", "Pune", "Hyderabad", 25, 3000));
    }

    private int readInteger(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a whole number.");
            }
        }
    }

    public void displayFlights() {
        System.out.println("\n================ AVAILABLE FLIGHTS ================");
        System.out.printf("%-8s %-15s %-15s %-15s %-10s %s%n",
                "Code", "Airline", "Source", "Destination",
                "Seats", "Base Fare");

        for (Flight flight : flights) {
            flight.displayFlight();
        }

        System.out.println("====================================================");
    }

    private Flight findFlight(String flightCode) {
        for (Flight flight : flights) {
            if (flight.getFlightCode().equalsIgnoreCase(flightCode)) {
                return flight;
            }
        }
        return null;
    }

    public void bookFlight() {
        System.out.println("\n========== BOOK A FLIGHT ==========");

        System.out.print("Enter flight code: ");
        String flightCode = scanner.nextLine().trim();

        Flight flight = findFlight(flightCode);

        if (flight == null) {
            System.out.println("Booking failed: Invalid flight code.");
            return;
        }

        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Booking failed: Name cannot be blank.");
            return;
        }

        int age = readInteger("Enter age: ");

        if (age <= 0) {
            System.out.println(
                    "Booking failed: Age must be greater than 0.");
            return;
        }

        System.out.print("Passenger type (Regular/Student): ");
        String type = scanner.nextLine().trim();

        Passenger passenger;

        if (type.equalsIgnoreCase("Regular")) {
            passenger = new RegularPassenger(name, age);
        } else if (type.equalsIgnoreCase("Student")) {
            passenger = new StudentPassenger(name, age);
        } else {
            System.out.println(
                    "Booking failed: Enter Regular or Student.");
            return;
        }

        int tickets = readInteger("Enter number of tickets: ");

        if (tickets <= 0) {
            System.out.println(
                    "Booking failed: Tickets must be greater than 0.");
            return;
        }

        if (!flight.hasAvailableSeats(tickets)) {
            System.out.println(
                    "Booking failed: Not enough seats available.");
            System.out.println(
                    "No booking was created. Available seats remain unchanged.");
            return;
        }

        String bookingId = "BK" + nextBookingNumber;

        Booking booking = new Booking(
                bookingId, flight, passenger, tickets);

        flight.reserveSeats(tickets);
        bookings.add(booking);
        nextBookingNumber++;

        System.out.println("\nBooking confirmed!");
        booking.displaySummary();
    }

    public void viewBooking() {
        if (bookings.isEmpty()) {
            System.out.println("\nNo confirmed bookings available.");
            return;
        }

        bookings.get(bookings.size() - 1).displaySummary();
    }

    public void start() {
        System.out.println("Welcome to Kiran Air Booking!");

        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");

            int choice = readInteger("Enter choice: ");

            switch (choice) {
                case 1:
                    displayFlights();
                    break;

                case 2:
                    bookFlight();
                    break;

                case 3:
                    viewBooking();
                    break;

                case 4:
                    System.out.println(
                            "Thank you for using Kiran Air Booking!");
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1 to 4.");
            }
        }
    }

    public static void main(String[] args) {
        FlightBookingSystem system = new FlightBookingSystem();
        system.start();
    }
}