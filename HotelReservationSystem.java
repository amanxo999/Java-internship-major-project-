import java.util.ArrayList;
import java.util.List;

class Room {
    private int roomNumber;
    private boolean isAvailable;
    private String guestName;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isAvailable = true;
        this.guestName = "";
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getGuestName() {
        return guestName;
    }

    public void bookRoom(String guestName) {
        this.isAvailable = false;
        this.guestName = guestName;
        System.out.println("Room " + roomNumber + " booked for " + guestName);
    }

    public void checkOut() {
        this.isAvailable = true;
        this.guestName = "";
        System.out.println("Guest checked out of Room " + roomNumber);
    }
}

class Hotel {
    private List<Room> rooms;

    public Hotel(int numRooms) {
        rooms = new ArrayList<>();
        for (int i = 1; i <= numRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void showRoomStatus() {
        System.out.println("Room Status:");
        for (Room room : rooms) {
            System.out.println("Room " + room.getRoomNumber() + ": " +
                    (room.isAvailable() ? "Available" : "Occupied by " + room.getGuestName()));
        }
    }

    public void reserveRoom(int roomNumber, String guestName) {
        Room room = findRoom(roomNumber);
        if (room != null && room.isAvailable()) {
            room.bookRoom(guestName);
        } else {
            System.out.println("Room " + roomNumber + " is not available for reservation.");
        }
    }

    public void checkOut(int roomNumber) {
        Room room = findRoom(roomNumber);
        if (room != null && !room.isAvailable()) {
            room.checkOut();
        } else {
            System.out.println("Room " + roomNumber + " is not occupied or does not exist.");
        }
    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        // Create a hotel with 10 rooms
        Hotel hotel = new Hotel(10);

        // Show initial room status
        hotel.showRoomStatus();

        // Reserve room for a guest
        hotel.reserveRoom(3, "John Doe");

        // Show updated room status
        hotel.showRoomStatus();

        // Check out from the room
        hotel.checkOut(3);

        // Show final room status
        hotel.showRoomStatus();
    }
}