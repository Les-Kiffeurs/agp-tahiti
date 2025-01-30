package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Rank;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class KMeansClusteringTest {
    @Test
    void testAlgo() {
        List<Hotel> hotels = List.of(
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.7617237,-149.5038687), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.554923,-149.5858433), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.7754934,-149.4576985), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.6154424,-149.6117248), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.598982,-149.6125455), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.5262805,-149.5362538), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.6168969,-149.6143445), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.5181085,-149.506609), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.7258192,-149.3109865), Rank.LOW, "0", 0),
            new Hotel(2, "h2", "0", 0, 0, new Position(-17.7849988,-149.3028022), Rank.LOW, "0", 0)
        );
        List<Position> hotelPositions = hotels.stream().map(Hotel::getPosition).toList();

        List<Position> centroids = KMeansClustering.kMeans(hotelPositions,3,100);

        List<Hotel> bestHotels = KMeansClustering.OneNN(centroids, hotels);

        System.out.println("Centroids: " + centroids.toString());
        System.out.println("Nearest hotels: " + bestHotels.toString());
    }
}