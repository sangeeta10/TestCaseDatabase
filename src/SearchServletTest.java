import org.junit.Test;

import static org.junit.Assert.*;

public class SearchServletTest {

    @Test
    public void testConnection() throws Exception {
        SearchServlet s = new SearchServlet();
        assertEquals(true, s.connection("root", "admin"));
    }

    @Test
    public void testSearchId() throws Exception {
        SearchServlet s = new SearchServlet();
        String res = s.searchByID("1");
        String r = "1#case 0#This is case 0#search#true#true#1994-09-12&";
        assertEquals(r,res);

    }
    @Test
    public void testSearchTag() throws Exception {
        SearchServlet s = new SearchServlet();
        String res = s.searchByTags("lookup");
        String r = "17#Case 17#This is case 17#lookup#true#false#2015-09-02&";
        assertEquals(r,res);

    }
    @Test
    public void testSearchResult() throws Exception {
        SearchServlet s = new SearchServlet();
        String res = s.searchByResult("12");
        String r = "12#Case 12#This is case 12#res def #10#12#2015-09-02&";
        assertEquals(r,res);
    }
    @Test
    public void testSearchDate() throws Exception {
        SearchServlet s = new SearchServlet();
        String res = s.searchByDate("1994-09-12");
        String r = "1#case 0#This is case 0#search#true#true#1994-09-12&";
        assertEquals(r,res);
    }

}