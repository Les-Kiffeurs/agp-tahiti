package fr.cyu.depinfo.agp.tahiti.persistence.bde.hibernate;

import fr.cyu.depinfo.agp.tahiti.business.Offer;
import fr.cyu.depinfo.agp.tahiti.dao.StatisticPersistence;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class HibernatePersistence implements StatisticPersistence {
    @Override
    public void dataInit() {
        HibernateDataInit.createTables();
    }

    @Override
    public void persist(Offer offer) {
        Session session = HibernateConnection.getSession();
        Transaction transaction = session.beginTransaction();
        OfferData data = new OfferData(offer.getArrivalDate(), offer.getDepartureDate(), offer.getDestination(), offer.getExcursions(), offer.getScore());

        transaction.commit();

        session.close();
    }

}
