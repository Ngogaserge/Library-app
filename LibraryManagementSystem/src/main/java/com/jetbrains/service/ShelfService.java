package com.jetbrains.service;

import com.jetbrains.Model.Shelf;
import org.hibernate.Session;
import com.jetbrains.util.HibernateUtil;

public class ShelfService {

    public String saveShelf(Shelf shelf) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.save(shelf);
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
    public Shelf getShelf(Shelf shelf) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Shelf existingShelf =  session.get(Shelf.class, shelf.getShelf_id());
            session.close();
            return existingShelf;

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return null;
        }
    }
}
