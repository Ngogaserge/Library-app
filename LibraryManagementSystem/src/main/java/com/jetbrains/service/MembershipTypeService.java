package com.jetbrains.service;

import com.jetbrains.Model.Location;
import com.jetbrains.Model.MembershipType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.jetbrains.util.HibernateUtil;

import java.util.List;

public class MembershipTypeService {

    public String saveMembership(MembershipType membership_type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(membership_type);
            session.getTransaction().commit();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Location getProvince(Location village) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Location> query = session.createQuery("from Location l where l.locationName = :location", Location.class);
            query.setParameter("location", village.getLocationName());
            List<Location> result = query.getResultList();

            if (result.isEmpty()) {
                return null;
            }

            Location returnLocation = result.get(0); // use get(0) to get the first element

            while (true) {
                if (returnLocation.getParent() == null) {
                    return returnLocation;
                }
                returnLocation = returnLocation.getParent();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
