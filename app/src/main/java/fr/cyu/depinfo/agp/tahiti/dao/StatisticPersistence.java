package fr.cyu.depinfo.agp.tahiti.dao;

import fr.cyu.depinfo.agp.tahiti.business.Offer;

public interface StatisticPersistence {
    void dataInit();

    void persist(Offer offer);
}
