package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;

import java.util.ArrayList;
import java.util.List;

public class SelectHotels {

    public List<Hotel> selectHotels(List<Site> activities,List<Hotel> hotels,int prix, int nbHotels, int comfort, int duration) {
        double prixAct = 0;
        for (Site activity : activities) {
            prixAct += activity.getPrice();
        }

        double prixMoyen = (prix - prixAct) / duration;

        hotels = hotels.stream().filter(h -> h.getPricePerNight() < prixMoyen).toList();

        List<Position> activitePos = new ArrayList<>();
        for (Site activity : activities) {
            activitePos.add(activity.getPosition());
        }

        List<Position> centroids = KMeansClustering.kMeans(activitePos, nbHotels,100);

        hotels = KMeansClustering.OneNN(centroids,hotels);

        return hotels;
    }
}
