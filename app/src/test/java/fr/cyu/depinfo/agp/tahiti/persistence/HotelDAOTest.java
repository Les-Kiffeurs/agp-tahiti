package fr.cyu.depinfo.agp.tahiti.persistence;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Location;
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
        hotelDAO = new HotelDAO();
    }

    @Test
    void testFindHotelByPrice(){
        int minPrice = 100;
        int maxPrice = 200;

        List<Hotel> results =  hotelDAO.searchByPrice(minPrice, maxPrice);

        assertNotNull(results);

        for (Hotel result : results) {
            assertNotNull(result);
            assertTrue(result instanceof Hotel);
            System.out.println(result);
            assertTrue(result.getPricePerNight() >= minPrice);
            assertTrue(result.getPricePerNight() <= maxPrice);

        }

    }

}