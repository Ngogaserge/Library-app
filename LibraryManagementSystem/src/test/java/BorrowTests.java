import com.jetbrains.Model.Book;
import com.jetbrains.Model.Borrower;
import com.jetbrains.Model.User;
import com.jetbrains.service.BorrowService;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowTests {

    @Test
    public void BorrowBook() throws ParseException {
        BorrowService service = new BorrowService();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the return date: ");
        String returnDate = scanner.nextLine();
        Date due_date = sdf.parse(returnDate);
        Borrower borrower = new Borrower();
        //replace the UUID with a valid book id
        borrower.setBook(new Book(UUID.fromString("e6084d43-a4cd-489c-b440-9be7f0a2b1cf")));
        borrower.setDue_date(due_date);
        borrower.setPickup_date(new Date());
        System.out.println("Enter the reader id: ");
        String readerId = scanner.nextLine();
        borrower.setReader(new User(readerId));
        assertNotNull(service.saveBorrower(borrower));
    }
    @Test
    public void testOneDayLateFee() {
        BorrowService service = new BorrowService();
        LocalDate dueDate = LocalDate.of(2023, 11, 1);
        LocalDate returnDate = LocalDate.of(2023, 11, 2);

        double fee = service.calculateLateFee(dueDate, returnDate);
        assertEquals(10, fee, "Fee should be 10 for one day late");
    }


}
