package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Rank;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for SelectHotels.
 */
public class SelectHotelsTest {

    @Test
    public void testSelectHotels() {
        // 1) Create dummy "activities" data
        //    Suppose each 'Site' has a name, price, and Position (lat, lng).
        List<Site> activities = new ArrayList<>();
        activities.add(new Site(1,
                "Tahiti Safari Islander",
                "Papeete, Polynésie française, 98714",
                1,
                4.7f,
                "Activity",
                69,
                2,
                new Position(-17.5324608,-149.5677151)
        ));
        activities.add(new Site(2,
                "École De Surf Tura'i Mataare",
                "Punaauia, Polynésie française, 98718",
                1,
                7.2f,
                "Activity",
                69,
                2,
                new Position(-17.5324608,-149.5677151)
        ));
        activities.add(new Site(3,
                "Tahiti Limousine - Service Vip",
                "Papeete, Polynésie française, 98714",
                1,
                3.3f,
                "Activity",
                69,
                2,
                new Position(-17.5324608,-149.5677151)
        ));

        // 2) Create dummy "hotels" data
        //    Suppose each 'Hotel' extends 'Site' or has similar fields.
        //    Just ensure getPricePerNight() and getPosition() are valid.
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel(1,
                "Taharu'u Guest House By The Beach",
                "PK 38,8 C/mer, Lotissement Pitate, Papara, 98712 Papara, Polynésie française",
                1,
                9.0f,
                new Position(-17.7617237,-149.5038687),
                Rank.LOW,
                "black sand beach",
                149
        ));  // Under price, near Museum
        hotels.add(new Hotel(2,
                "Tahiti Natura Lodge",
                "Tahiti Faaa Pamatai Hauaiti iti, 98704 Fa'a'ā, Polynésie française",
                1,
                8.0f,
                new Position(-17.554923,-149.5858433),
                Rank.MEDIUM,
                "natura beach",
                69
        ));  // Under price
        hotels.add(new Hotel(3,
                "Fare purau",
                "Atimaono pk 40,800 côté mer, 98712 Papara, Polynésie française",
                1,
                9.0f,
                new Position(-17.7617237,-149.5038687),
                Rank.LOW,
                "atimaono beach",
                149
        )); // Possibly over budget?

        // 3) Define parameters
        int prix     = 1000;  // Total budget
        int nbHotels = 2;     // We want 2 clusters (centroids)
        int comfort  = 3;     // Not used in code directly, but could be
        int duration = 5;     // e.g. 5 nights

        // 4) Call selectHotels
        SelectHotels selector = new SelectHotels();
        List<Hotel> chosen = selector.selectHotels(
                activities,
                hotels,
                prix,
                nbHotels,
                comfort,
                duration
        );

        // 5) Assertions and logs
        //    Check that we don't get a null or empty list
        assertNotNull(chosen, "Returned list should not be null");

        // We might expect that "Hotel Luxury" is filtered out
        // if its price (120) is above daily budget
        // => daily lodging budget = (1000 - 80 (total act)) / 5 = 920/5 = 184
        // So 120 < 184 => Actually it's still included based on price filter
        // Then the KMeans/1NN might or might not keep it.
        // We'll see what the code does.

        // A basic assertion: we expect at least one or more hotels returned.
        Assertions.assertFalse(chosen.isEmpty(), "At least one hotel should be returned.");

        // Additional checks can be performed if your logic dictates certain hotels MUST or MUST NOT appear.
    }

    /**
     * OPTIONAL:
     * If you want to quickly dump the positions into CSV format for Python,
     * you could do something like:
     */

    /*
    @Test
    public void testSelectHotelsAndExportPositions() {
        List<Site> activities = new ArrayList<>();
        activities.add(new Site("Museum", 50.0, new Position(17.55, -149.56)));
        activities.add(new Site("Beach",  0.0,  new Position(17.48, -149.45)));
        activities.add(new Site("Hiking", 30.0, new Position(17.60, -149.50)));

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Hotel Budget",  60.0, new Position(17.52, -149.55)));
        hotels.add(new Hotel("Hotel Comfort", 80.0, new Position(17.51, -149.45)));
        hotels.add(new Hotel("Hotel Luxury", 120.0, new Position(17.59, -149.49)));

        // Run selection
        SelectHotels selector = new SelectHotels();
        List<Hotel> chosen = selector.selectHotels(activities, hotels, 1000, 2, 3, 5);

        // Export lines to console in CSV: lat, lng, label
        // Then you can copy/paste into a CSV file to plot in Python
        System.out.println("\n=== CSV Export (Activities) ===");
        for (Site s : activities) {
            Position p = s.getPosition();
            System.out.println(p.getLatitude() + "," + p.getLongitude() + "," + s.getName());
        }

        System.out.println("\n=== CSV Export (Chosen Hotels) ===");
        for (Hotel h : chosen) {
            Position p = h.getPosition();
            System.out.println(p.getLatitude() + "," + p.getLongitude() + "," + h.getName());
        }
        // Possibly also print the final centroids if you store them
        // in your KMeansClustering class or have a way to retrieve them.
    }
    */
}
