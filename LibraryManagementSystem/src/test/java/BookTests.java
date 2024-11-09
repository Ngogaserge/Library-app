import com.jetbrains.Model.*;

import org.junit.Test;
import com.jetbrains.service.BookService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class BookTests {

	@Test
	public void CreateBook() throws ParseException {
		Book book = new Book();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the edition of the book: ");
		String bookEdition = scanner.nextLine();
		book.setEdition(Integer.parseInt(bookEdition));
		System.out.println("Please enter the ISBN Code: ");
		String bookISBN = scanner.nextLine();
		book.setISBNCode(bookISBN);
		book.setStatus(BookStatus.AVAILABLE);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("Please enter the publication date(DD-MM-YYYY): )");
		String pubDate = scanner.nextLine();
		book.setPublication_year(sdf.parse(pubDate));
		System.out.println("Please enter the publisher name: ");
		String publisherName = scanner.nextLine();
		book.setPublisher_name(publisherName);
		Shelf shelf = new Shelf();
		shelf.setShelf_id(UUID.fromString("54493bca-2dd8-44d4-80ac-4f3c192a5583"));
		book.setShelf(shelf);
		System.out.println("Please enter the book title: ");
		String bookTitle = scanner.nextLine();
		book.setTitle(bookTitle);

		BookService service = new BookService();
		assertNotNull(service.saveBook(book));
	}
	

	


}
