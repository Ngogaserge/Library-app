package com.jetbrains.service;

import com.jetbrains.Model.Location;
import com.jetbrains.Model.LocationType;
import com.jetbrains.Model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.jetbrains.util.HibernateUtil;

import java.util.List;

public class LocationService {

    // Method to save a location and return status message
    public String saveLocation(Location location) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(location);
            session.getTransaction().commit();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    // Method to retrieve the highest-level location (Province) based on a given village location
    public Location getProvince(Location village) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Location> query = session.createQuery("from Location l where l.locationName = :location", Location.class);
            query.setParameter("location", village.getLocationName());

            List<Location> result = query.getResultList();
            if (result.isEmpty()) {
                return null;
            }

            Location currentLocation = result.get(0); // start with the found location
            while (currentLocation.getParent() != null) {
                currentLocation = currentLocation.getParent();
            }
            return currentLocation;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve the province based on the person's ID
    public Location getProvinceByUserId(String personId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Location location = null;

        try {
            transaction = session.beginTransaction();

            // Query to fetch the user by personId
            Query<User> query = session.createQuery("FROM User WHERE person_id = :personId", User.class);
            query.setParameter("personId", personId);
            User user = query.uniqueResult();

            if (user != null) {
                // Start with the user's village location
                location = user.getVillage_id();

                // Traverse up the location hierarchy to find the province
                while (location != null && location.getLocationType() != LocationType.PROVINCE) {
                    location = location.getParent();  // Move to the parent location
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return location;  // Return the province (or null if not found)
    }

}
