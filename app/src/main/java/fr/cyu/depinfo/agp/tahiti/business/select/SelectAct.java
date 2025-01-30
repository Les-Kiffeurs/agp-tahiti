package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.locations.Site;
import fr.cyu.depinfo.agp.tahiti.dao.SiteDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    public List<Site> fillAct(List<Site> activities, int nbActMax) {
        Map<Integer, Site> siteMap = new HashMap<>();
        for (Site site : activities) {
            siteMap.put(site.getId(), site);
        }

        while(activities.size() < nbActMax) {
            int id = (int) (Math.random() * (31 - 1)) + 1;
            activities.add(siteMap.get(id));
        }
        return activities;
    }


    public List<Site> SelectAct(List<Site> activities, int prix, int duration, int comfort) {
        int nbActMax = nbActMax(duration,comfort);
        if(activities.size() < nbActMax){
            activities = fillAct(activities, nbActMax);
        } else if (activities.size()>nbActMax) {
            Collections.shuffle(activities);
            activities = activities.subList(0, nbActMax);
        }
        return activities;
    }


}
