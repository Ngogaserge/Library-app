import com.jetbrains.Model.Room;
import com.jetbrains.Model.Shelf;
import com.jetbrains.service.ShelfService;
import org.junit.Test;

import java.util.Scanner;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class ShelfTests {
    @Test
    public void testCreateShelf() {
        Scanner scanner = new Scanner(System.in);
        Shelf shelf = new Shelf();
        System.out.println("Please enter the available stock: ");
        String availableStock = scanner.nextLine();
        shelf.setAvailable_stock(Integer.parseInt(availableStock));
        System.out.println("Please enter the book category: ");
        String bookCategory = scanner.nextLine();
        shelf.setBook_category(bookCategory);
        System.out.println("Number of books borrowed: ");
        String bookCount = scanner.nextLine();
        shelf.setBorrowed_number(Integer.parseInt(bookCount));
        System.out.println("Please enter the initial stock: ");
        String initialStock = scanner.nextLine();
        shelf.setInitial_stock(Integer.parseInt(initialStock));
        shelf.setRoom(new Room(UUID.fromString("e993d001-c1e4-4eb3-9fc6-7501c4958eda")));
        ShelfService service = new ShelfService();
        assertNotNull(service.saveShelf(shelf));
    }
}
