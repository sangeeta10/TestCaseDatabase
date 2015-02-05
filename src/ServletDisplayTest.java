import static org.junit.Assert.*;

public class ServletDisplayTest {

    @org.junit.Test
    public void testconnectiont() throws Exception {
        ServletDisplay s = new ServletDisplay();
        assertEquals(true,s.connection("root","admin"));


    }
    @org.junit.Test
    public void testconnection_neg() throws Exception{
        ServletDisplay p = new ServletDisplay();
        assertEquals(false,p.connection("admin","admin"));

    }
}