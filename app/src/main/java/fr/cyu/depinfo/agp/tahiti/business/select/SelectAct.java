package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;
import fr.cyu.depinfo.agp.tahiti.dao.HotelDAOInterface;
import fr.cyu.depinfo.agp.tahiti.dao.SiteDAOInterface;
import fr.cyu.depinfo.agp.tahiti.persistence.SiteDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SelectAct {

    @Autowired
    private SiteDAOInterface siteDAO;

    public int nbActMax(int duration, int comfort) {
        double[][] probability_matrix = {
                {0.01, 0.05, 0.47, 0.47},
                {0.02, 0.04, 0.54, 0.40},
                {0.15, 0.15, 0.50, 0.20},
                {0.40, 0.54, 0.04, 0.02},
                {0.47, 0.47, 0.05, 0.01}
        };
        if (comfort < 1) {
            comfort = 1;
        } else if (comfort > 5) {
            comfort = 5;
        }

        double[] probas = probability_matrix[comfort - 1];
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < probas.length; i++) {
            cumulativeProbability += probas[i];
            if (cumulativeProbability >= randomValue) {
                return i * duration;
            }
        }
        return (probas.length - 1) * duration;
    }

    //a supprimer quand connexion db
    private Site getRandomActivity() {
        // Return some placeholder Site with fake data
        return new Site(
                1,
                "Fake description",
                "Fake address",
                1,
                5.0f,
                "Activity",
                0,
                1,
                new Position(0.0, 0.0)
        );
    }

    // a suppr quand connexion db
    List<Site> getActivities(List<String> keyword) {
        // For testing, return a small list of dummy Sites
        List<Site> dummyList = new ArrayList<>();
        dummyList.add(new Site(
                2,
                "Fake desc 1",
                "Fake address 1",
                1,
                4.5f,
                "Activity",
                0,
                1,
                new Position(-17.5324608, -149.5677151)
        ));
        dummyList.add(new Site(
                3,
                "Fake desc 2",
                "Fake address 2",
                1,
                3.5f,
                "Activity",
                0,
                1,
                new Position(-17.5324608, -149.5677151)
        ));
        return dummyList;
    }

    public List<Site> fillAct(List<Site> activities, int nbActMax) {
        while(activities.size() < nbActMax) {
            activities.add(getRandomActivity());
        }
        return activities;
    }


    public List<Site> SelectAct(int prix, int duration, int comfort, List<String> keyword){
        int nbActMax = nbActMax(duration,comfort);
        List<Site> activities = getActivities(keyword);
        if(activities.size() < nbActMax){
            activities = fillAct(activities, nbActMax);
        } else if (activities.size()>nbActMax) {
            activities = activities.subList(0, nbActMax);
        }
        return activities;
    }


}
