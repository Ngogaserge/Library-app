import com.jetbrains.Model.*;
import com.jetbrains.service.BorrowService;
import com.jetbrains.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.Test;

import java.util.Scanner;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserTests {


    UserService service = new UserService();
    @Test
    public void testCreateUser() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your first name: ");
        String firstName = scanner.nextLine();
        user.setFirst_name(firstName);

        System.out.println("Please enter your last name: ");
        String lastName = scanner.nextLine();
        user.setLast_name(lastName);

        System.out.println("Enter the person id: ");
        String personId = scanner.nextLine();
        user.setPerson_id(personId);

        System.out.println("What's your gender M/F: ");
        String gender = scanner.nextLine();
        if (gender.equalsIgnoreCase("M")) {
            user.setGender(Gender.MALE);
        } else if (gender.equalsIgnoreCase("F")) {
            user.setGender(Gender.FEMALE);
        } else {
            System.out.println("Invalid gender");
            return;
        }

        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        user.setUsername(username);

        System.out.println("Enter your phone number: ");
        String phone = scanner.nextLine();
        user.setPhone_number(phone);

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        user.setPassword(password);

        System.out.println("Enter your role: ");
        String role = scanner.nextLine();
        if (role.equalsIgnoreCase("student")) {
            user.setRole(Role.STUDENT);
        } else if (role.equalsIgnoreCase("teacher")) {
            user.setRole(Role.TEACHER);
        } else if (role.equalsIgnoreCase("manager")) {
            user.setRole(Role.MANAGER);
        } else if (role.equalsIgnoreCase("librarian")) {
            user.setRole(Role.LIBRARIAN);
        } else {
            System.out.println("Invalid role, please enter a valid role!");
            return;  // Exit if invalid input
        }

        // Replace the UUID below with a valid UUID from the database
        user.setVillage_id(new Location(UUID.fromString("246cf762-eceb-4463-bdf5-ee3b4ab7420f")));

        assertNotNull(service.saveUser(user));
    }


    @Test
    public void AuthenticateUser() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter username");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        assertNotNull(service.authenicateUser(user));

    }

    @Transactional
	@Test
    public void ValidateUserBooks() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Please enter the person id to test for: ");
        String personId = sc.nextLine();
        User user = new User(personId);
        BorrowService service = new BorrowService();
        //5 because i expect him to be in the gold membership
        assertTrue(service.validateUser(user, 5));
    }


}
