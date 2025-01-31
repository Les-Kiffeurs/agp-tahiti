package fr.cyu.depinfo.agp.tahiti.business;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Rank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {
    @Test
    void tenKmTripAt5KpHShouldLast7200s() {
        Hotel h1 = new Hotel(1, "h1", "0", 0, 0, new Position(48.856451985395786,2.3524498461101757), "0", 0);
        Hotel h2 = new Hotel(2, "h2", "0", 0, 0, new Position(48.89489626600102,2.2288385749644704), "0", 0);
        Transport fiveKpHTransportMode = new Transport("5KpHTransport", 0, 5/3.6f);

        Trip t = new Trip(h1, h2).setTransportMode(fiveKpHTransportMode);
        assertEquals(7200, t.duration().getSeconds(), 100);
    }

    @Test
    void tenKmTripAt1EpKmShouldCost10E() {
        Hotel h1 = new Hotel(1, "h1", "0", 0, 0, new Position(48.856451985395786,2.3524498461101757), "0", 0);
        Hotel h2 = new Hotel(2, "h2", "0", 0, 0, new Position(48.89489626600102,2.2288385749644704), "0", 0);
        Transport fiveEpKmTransportMode = new Transport("5EpKmTransport", 1, 5/3.6f);

        Trip t = new Trip(h1, h2).setTransportMode(fiveEpKmTransportMode);
        assertEquals(10, t.getPrice(), 0.01);
    }
}