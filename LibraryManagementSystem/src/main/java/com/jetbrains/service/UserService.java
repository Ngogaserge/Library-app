package com.jetbrains.service;

import com.jetbrains.Model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.jetbrains.util.HibernateUtil;
import com.jetbrains.util.HashPassword;

public class UserService {

    public String saveUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            user.setPassword(HashPassword.hashPassword(user.getPassword()));
            session.save(user);
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
    public Location getLocationByUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from User u where u.person_id = :person_id ");
            query.setParameter("person_id", user.getPerson_id());
            User user1 = (User) query.uniqueResult();
            if (user1 == null) return null;
            LocationService service = new LocationService();
            session.close();
            return service.getProvince(user1.getVillage_id());
//			return location;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return null;
    }
    public User authenicateUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from User u where u.username = :username");
            query.setParameter("username", user.getUsername());
            User user1 = (User) query.uniqueResult();
            if(user1 != null) {
                if(HashPassword.verifyPassword(user.getPassword(), user1.getPassword())) {
                    return user1;
                }
                else return null;
            }
            session.close();
            return null;
//			return location;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public User findUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            User existingUser = session.get(User.class, user.getPerson_id());
            session.close();
            return existingUser;
//			return location;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return null;
    }

}