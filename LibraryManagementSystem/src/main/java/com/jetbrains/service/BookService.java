package com.jetbrains.service;

import com.jetbrains.Model.Book;
import com.jetbrains.Model.Borrower;
import com.jetbrains.Model.User;
import org.hibernate.Session;
import com.jetbrains.util.HibernateUtil;

import java.util.Date;

public class BookService {

    public String saveBook(Book book) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.save(book);
            session.beginTransaction().commit();
            session.close();
            return "success";
//			return location;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return null;
    }
    public Book getBook(Book book) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Book existingBook =  session.get(Book.class, book.getBook_id());
            session.close();
            return existingBook;

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return null;
        }
    }
}

