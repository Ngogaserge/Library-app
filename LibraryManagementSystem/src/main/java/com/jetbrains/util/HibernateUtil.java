package com.jetbrains.util;

import com.jetbrains.Model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {  // Renamed to getSessionFactory
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            Properties property = new Properties();
            property.put(Environment.DRIVER, "org.postgresql.Driver");
            property.put(Environment.URL, "jdbc:postgresql://localhost:5432/auca_library_db");
            property.put(Environment.USER, "postgres");
            property.put(Environment.PASS, "serge");
            property.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            property.put(Environment.SHOW_SQL, true);
            property.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(property);

            // Register annotated classes
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Borrower.class);
            configuration.addAnnotatedClass(Location.class);
            configuration.addAnnotatedClass(Membership.class);
            configuration.addAnnotatedClass(MembershipType.class);
            configuration.addAnnotatedClass(Room.class);
            configuration.addAnnotatedClass(Shelf.class);
            configuration.addAnnotatedClass(Book.class);
            //configuration.addAnnotatedClass(Book2.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
