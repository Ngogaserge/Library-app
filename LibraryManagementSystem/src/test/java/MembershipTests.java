
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import com.jetbrains.Model.Membership;
import com.jetbrains.Model.MembershipType;
import com.jetbrains.Model.Status;
import com.jetbrains.Model.User;
import com.jetbrains.service.MembershipService;
import com.jetbrains.service.MembershipTypeService;
import org.junit.Test;



public class MembershipTests {

    MembershipTypeService service = new MembershipTypeService();
    MembershipService membershipService = new MembershipService();
    @Test
    public void CreateMembership() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Membership membership = new Membership();
        System.out.println("Enter reader id");
        String reader = scanner.nextLine();

        membership.setReader(new User(reader));
        membership.setMembership_status(Status.PENDING);
        SimpleDateFormat sdf = new SimpleDateFormat("DD-mm-YYYY");
        System.out.println("Enter membership code");
        String membershipCode = scanner.nextLine();
        membership.setMembership_code(membershipCode);
        System.out.println("Enter membership Expiry date");
        String membershipExpiryDate = scanner.nextLine();
        membership.setExpiring_date(sdf.parse(membershipExpiryDate));
        membership.setMembership_type(new MembershipType(UUID.fromString("3ecaa4ba-b4c2-4e91-a36a-74b306d931dd")));

        membership.setRegistration_date(new Date());
        assertNotNull(membershipService.saveMembership(membership));

    }

    @Test
    public void CreateMembershipType() {
        Scanner scanner = new Scanner(System.in);
        MembershipType membership_type = new MembershipType();
        System.out.println("What are the max books in this membership?");
        int maxbooks = scanner.nextInt();
        membership_type.setMax_books(maxbooks);
        System.out.println("What is the membership name?");
        String membership_name = scanner.next();
        membership_type.setMembership_name(membership_name);
        System.out.println("What is the price: ");
        int price = scanner.nextInt();
        membership_type.setPrice(price);
        assertNotNull(service.saveMembership(membership_type));

    }


}
