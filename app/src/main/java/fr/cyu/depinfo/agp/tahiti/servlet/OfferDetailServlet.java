package fr.cyu.depinfo.agp.tahiti.servlet;

import fr.cyu.depinfo.agp.tahiti.business.Offer;
import fr.cyu.depinfo.agp.tahiti.business.locations.*;
import fr.cyu.depinfo.agp.tahiti.business.select.*;
import fr.cyu.depinfo.agp.tahiti.persistence.HotelDAO;
import fr.cyu.depinfo.agp.tahiti.persistence.SiteDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/offer-detail")
public class OfferDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String keywords = request.getParameter("keywords");
        int numberOfHotels = Integer.parseInt(request.getParameter("number_of_hotels"));
        int comfortLevel = Integer.parseInt(request.getParameter("comfort_level"));
        int minPrice = Integer.parseInt(request.getParameter("min_price"));
        int maxPrice = Integer.parseInt(request.getParameter("max_price"));
        String arrivalDateData = request.getParameter("arrival_date");
        String departureDateData = request.getParameter("departure_date");

        LocalDate arrivalDate = LocalDate.parse(arrivalDateData);
        LocalDate departureDate = LocalDate.parse(departureDateData);
        int daysBetween = (int) ChronoUnit.DAYS.between(arrivalDate, departureDate);

        SiteDAO siteDAO = new SiteDAO();
        HotelDAO hotelDAO = new HotelDAO();
        ServletContext context = getServletContext();

        String indexPath = context.getRealPath("/WEB-INF/index");
        String descriptionPath = context.getRealPath("/WEB-INF/descriptions");

        siteDAO.getBdeAPI().setTextSearchInfo("site", "id", descriptionPath);
        siteDAO.getBdeAPI().createTextIndex(indexPath);

        SelectAct selectAct = new SelectAct();
        SelectHotels selectHotel = new SelectHotels();
        FindSejour findSejour = new FindSejour();

        List<Site> activites = siteDAO.searchByKeyword(keywords);
        List<Hotel> allHotels = hotelDAO.getAllHotels();
        List<Site> sites = selectAct.SelectAct(activites, maxPrice, daysBetween, comfortLevel);
        sites = sites.stream().filter(Objects::nonNull).collect(Collectors.toList());
        List<Hotel> hotels = selectHotel.selectHotels(sites, allHotels, maxPrice, numberOfHotels, comfortLevel, daysBetween);

        Map<Hotel, List<Location>> activitiesMap = KMeansClustering.buildActivitesMap(hotels, sites);
        Destination destination = new Destination("Tahiti");
        Duree duree = new Duree(daysBetween);
        duree.setArrivalDate(java.sql.Date.valueOf(arrivalDate));
        duree.setDepartureDate(java.sql.Date.valueOf(arrivalDate));

        List<Offer> offers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Offer offer = findSejour.findSejour(hotels, activitiesMap, duree, comfortLevel, destination);
            offers.add(offer);
        }


        request.setAttribute("offers", offers);
        request.getRequestDispatcher("/WEB-INF/jsp/offer/offer-detail.jsp").forward(request, resp);
    }

}
