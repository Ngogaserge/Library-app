package com.jetbrains.service;

import com.jetbrains.Model.Membership;
import org.hibernate.Session;
import com.jetbrains.util.HibernateUtil;

public class MembershipService {

    public String saveMembership(Membership membership) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.save(membership);
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

}
