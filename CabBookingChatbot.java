
import java.util.Scanner;
import java.util.Random;

public class CabBookingChatbot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        while (true) {

            System.out.println("\n===== CAB BOOKING CHATBOT =====");
            System.out.println("1. Book Ride");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            if (choice == 2) {
                System.out.println("Thank you for using Cab Booking Chatbot!");
                System.out.println("Goodbye!");
                break;
            }

            if (choice != 1) {
                System.out.println("Invalid choice! Please select 1 or 2.");
                continue;
            }

            // Choose cab type
            System.out.println("\n----- Choose Cab Type -----");
            System.out.println("1. Auto - Rs. 12/km");
            System.out.println("2. Sedan - Rs. 18/km");
            System.out.println("3. SUV - Rs. 25/km");
            System.out.print("Enter cab choice: ");

            int cabChoice;

            try {
                cabChoice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid cab choice!");
                continue;
            }

            String cabType;
            int rate;

            switch (cabChoice) {
                case 1:
                    cabType = "Auto";
                    rate = 12;
                    break;
                case 2:
                    cabType = "Sedan";
                    rate = 18;
                    break;
                case 3:
                    cabType = "SUV";
                    rate = 25;
                    break;
                default:
                    System.out.println("Invalid cab choice!");
                    continue;
            }

            // Choose route
            System.out.println("\n----- Choose Route -----");
            System.out.println("1. Pune Station to Shivajinagar (5 km)");
            System.out.println("2. Swargate to Kothrud (8 km)");
            System.out.println("3. Hinjawadi to Wakad (10 km)");
            System.out.print("Enter route choice: ");

            int routeChoice;

            try {
                routeChoice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid route choice!");
                continue;
            }

            String source;
            String destination;
            int distance;

            switch (routeChoice) {
                case 1:
                    source = "Pune Station";
                    destination = "Shivajinagar";
                    distance = 5;
                    break;
                case 2:
                    source = "Swargate";
                    destination = "Kothrud";
                    distance = 8;
                    break;
                case 3:
                    source = "Hinjawadi";
                    destination = "Wakad";
                    distance = 10;
                    break;
                default:
                    System.out.println("Invalid route choice!");
                    continue;
            }

            // Calculate fare
            int fare = rate * distance;

            System.out.println("\n===== RIDE DETAILS =====");
            System.out.println("Cab Type   : " + cabType);
            System.out.println("Source     : " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Distance   : " + distance + " km");
            System.out.println("Rate       : Rs. " + rate + "/km");
            System.out.println("Total Fare : Rs. " + fare);

            // Confirm or cancel before OTP
            System.out.println("\n----- Booking Confirmation -----");
            System.out.println("1. Confirm & Share OTP");
            System.out.println("2. Cancel Ride");
            System.out.print("Enter your choice: ");

            int confirmChoice;

            try {
                confirmChoice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Ride cancelled.");
                System.out.println("No OTP has been shared.");
                continue;
            }

            if (confirmChoice == 2) {
                System.out.println("Ride cancelled successfully.");
                System.out.println("No OTP has been shared.");
                continue;
            }

            if (confirmChoice != 1) {
                System.out.println("Invalid choice! Ride cancelled.");
                System.out.println("No OTP has been shared.");
                continue;
            }

            // Generate OTP only after confirmation
            int otp = 1000 + random.nextInt(9000);

            System.out.println("\nYour OTP is: " + otp);
            System.out.println("OTP shared successfully.");
            System.out.println("Booking confirmed!");
            System.out.println(
                "IMPORTANT: Ride cancellation is NOT allowed after OTP sharing."
            );
        }

        sc.close();
    }
}
