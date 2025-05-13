import dao.RoomDAO;
import model.Room;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoomDAO dao = new RoomDAO();
        int choice;

        do {
            System.out.println("\n--- Hostel Management Menu ---");
            System.out.println("1. Check-In");
            System.out.println("2. Check-Out");
            System.out.println("3. View All Rooms");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter guest name: ");
                    String name = sc.nextLine();
                    Room r = new Room(0, name, "Occupied");
                    dao.checkIn(r);
                    break;

                case 2:
                    System.out.print("Enter room number to check-out: ");
                    int roomId = sc.nextInt();
                    dao.checkOut(roomId);
                    break;

                case 3:
                    List<Room> rooms = dao.getAllRooms();
                    System.out.println("\n--- Room Status ---");
                    for (Room room : rooms) {
                        System.out.println("Room " + room.getRoomId() +
                                ": " + (room.getStatus().equals("Empty") ? "Empty" : room.getGuestName()));
                    }
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}