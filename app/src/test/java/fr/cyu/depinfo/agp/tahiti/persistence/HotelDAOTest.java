package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HotelDAOTest {

    private static HotelDAO hotelDAO;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        BDeAPI bDeAPI = new BDeAPI();
        hotelDAO = new HotelDAO(bDeAPI);
    }

    @Test
    void testFindHotelByPrice(){
        int minPrice = 100;
        int maxPrice = 200;

        List<Map<String, String>> results =  hotelDAO.searchByPrice(minPrice, maxPrice);

        assertNotNull(results);

        for (Map<String, String> result : results) {
            System.out.println(result);
            assertTrue(result.containsKey("pricepernight"));
            int pricePerNight = Integer.parseInt(result.get("pricepernight"));
            assertTrue(pricePerNight >= minPrice && pricePerNight <= maxPrice);
            System.out.println(result);
        }

    }

}