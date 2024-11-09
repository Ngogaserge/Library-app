import com.jetbrains.Model.Room;
import com.jetbrains.service.RoomService;
import org.junit.Test;

import java.util.Scanner;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoomTests {

    @Test
    public void testCreateRoom() {
        Room room = new Room();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the room Code: ");
        String roomCode = scanner.nextLine();
        room.setRoomCode(roomCode);
        RoomService service = new RoomService();
        assertNotNull(service.saveUser(room));


    }

    @Test
    public void NumberOfBookInRoom() {
        Room room = new Room();
        //replace the UUID with a valid room UUID in the database
        room.setRoom_id(UUID.fromString("e993d001-c1e4-4eb3-9fc6-7501c4958eda"));
        RoomService service = new RoomService();
        assertEquals(service.getNumberOfBooks(room),1);

    }
    @Test
    public void RoomWithFewerBooks() {
        RoomService service = new RoomService();

        // Retrieve the room with the fewest books
        Room roomWithFewestBooks = service.getRoomWithFewestBooks();
        // Retrieve the specific room by its ID
        Room specificRoom = service.findRoom(new Room(UUID.fromString("e993d001-c1e4-4eb3-9fc6-7501c4958eda")));

        // Ensure both rooms are not null
        assertNotNull("Room with fewest books should not be null", roomWithFewestBooks);
        assertNotNull("Specific room should not be null", specificRoom);

        // Compare based on the unique room_id to verify they are the same room
        assertEquals("Room IDs should match", roomWithFewestBooks.getRoom_id(), specificRoom.getRoom_id());
    }


}
