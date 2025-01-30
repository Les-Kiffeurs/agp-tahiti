package fr.cyu.depinfo.agp.tahiti.business.select;

import java.util.Date;

public class Duree {
    private int nbJours;
    private Date arrivalDate;
    private Date departureDate;
    public Duree(int nbJours) {
        this.nbJours = nbJours;
    }
    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
