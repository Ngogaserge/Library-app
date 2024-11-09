package com.jetbrains.service;

import com.jetbrains.Model.Borrower;
import com.jetbrains.Model.User;
import com.jetbrains.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

public class BorrowService {

    // Save a Borrower instance with pickup date and persist to database
    public String saveBorrower(Borrower borrower) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            borrower.setPickup_date(new Date()); // Setting the pickup date
            session.save(borrower);

            session.getTransaction().commit();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback(); // Rollback on failure
            }
        } finally {
            if (session != null) {
                session.close(); // Always close session
            }
        }
        return null;
    }

    // Validates the user based on the number of books they can borrow
    public boolean validateUser(User user, int books) {
        UserService userService = new UserService();
        User existingUser = userService.findUser(user);

        // Check if user exists
        if (existingUser == null) {
            System.out.println("User does not exist");
            return false;
        }

        // Check if user has memberships
        if (existingUser.getMemberships() == null) {
            System.out.println("No memberships");
            return false;
        }

        // Stream through memberships and check max_books against books argument
        long count = existingUser.getMemberships().stream()
                .filter(membership -> {
                    int maxBooks = membership.getMembership_type().getMax_books(); // Assuming it’s int now
                    return maxBooks >= books; // Validate if membership allows borrowing books or more
                })
                .count();

        System.out.println("Valid memberships for borrowing: " + count);
        return count > 0; // Returns true if there is at least one valid membership
    }
}