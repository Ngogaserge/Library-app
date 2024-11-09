
import static org.junit.Assert.assertNotNull;

import com.jetbrains.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;


public class ConnectionTest {
    public String testConnection() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return "connected";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void test() {
        String connection = testConnection();
        assertNotNull(connection);
    }

}
