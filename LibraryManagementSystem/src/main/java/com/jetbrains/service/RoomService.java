package com.jetbrains.service;

import com.jetbrains.Model.Room;
import org.hibernate.Session;
import com.jetbrains.util.HibernateUtil;

public class RoomService {

    public String saveUser(Room room) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.save(room);
            session.beginTransaction().commit();
            session.close();
            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return null;
    }

    public Room findRoom(Room room) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Room existingRoom= session.get(Room.class, room.getRoom_id());
            session.close();
            return existingRoom;
//			return location;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return null;
    }
    public int getNumberOfBooks(Room room) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve the Room entity from the database
            Room existingRoom = session.get(Room.class, room.getRoom_id());
            if (existingRoom == null) {
                return -1; // Room does not exist
            }

            // HQL query to count books in the specified room
            String hql = "select count(b.book_id) from Book b " +
                    "join b.shelf s join s.room r " +
                    "where r.room_id = :roomId";

            // Execute the query
            Long bookCount = (Long) session.createQuery(hql)
                    .setParameter("roomId", room.getRoom_id())
                    .uniqueResult();

            // Return the count as an integer
            return bookCount != null ? bookCount.intValue() : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public Room getRoomWithFewestBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select r from Room r " +
                    "left join r.shelves s " +
                    "left join s.books b " +
                    "group by r.room_id " +
                    "order by count(b.book_id) asc";


            Room roomWithFewestBooks = session.createQuery(hql, Room.class)
                    .setMaxResults(1)
                    .uniqueResult();

            return roomWithFewestBooks;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // or handle the exception as appropriate
        }
    }


}
