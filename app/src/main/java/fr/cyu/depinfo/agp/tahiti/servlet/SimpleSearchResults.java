package fr.cyu.depinfo.agp.tahiti.servlet;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;
import fr.cyu.depinfo.agp.tahiti.dao.HotelDAOInterface;
import fr.cyu.depinfo.agp.tahiti.dao.SiteDAOInterface;
import fr.cyu.depinfo.agp.tahiti.persistence.HotelDAO;
import fr.cyu.depinfo.agp.tahiti.persistence.SiteDAO;
import fr.cyu.depinfo.agp.tahiti.persistence.bde.BDeAPI;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/simple-search-results")
public class SimpleSearchResults extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String keywords = req.getParameter("keywords");
        String price = req.getParameter("price");

        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        SiteDAOInterface siteDAO = wac.getBean(SiteDAO.class);
        HotelDAOInterface hotelDAO = wac.getBean(HotelDAO.class);
        BDeAPI bdeAPI = wac.getBean(BDeAPI.class);

        ServletContext context = getServletContext();

        String indexPath = context.getRealPath("/WEB-INF/index");
        String descriptionPath = context.getRealPath("/WEB-INF/descriptions");
        bdeAPI.setTextSearchInfo("site", "id", descriptionPath);
        bdeAPI.createTextIndex(indexPath);


        if (keywords != null) {
            List<Site> sites = siteDAO.searchByKeyword(keywords);
            req.setAttribute("locations", sites);
        }

        if (price != null) {
            int minPrice = Integer.parseInt(price.split("-")[0]);
            int maxPrice = Integer.parseInt(price.split("-")[1]);
            List<Hotel> hotels = hotelDAO.searchByPrice(minPrice, maxPrice);
            System.out.println(hotels);
            req.setAttribute("locations", hotels);
        }

        req.getRequestDispatcher("WEB-INF/jsp/simple-search-results.jsp").forward(req, resp);
    }
}
