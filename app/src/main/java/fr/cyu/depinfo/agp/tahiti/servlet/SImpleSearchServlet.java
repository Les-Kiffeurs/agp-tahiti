package fr.cyu.depinfo.agp.tahiti.servlet;

import fr.cyu.depinfo.agp.tahiti.business.locations.Site;
import fr.cyu.depinfo.agp.tahiti.persistence.SiteDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SImpleSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String keywords = req.getParameter("keywords");
        SiteDAO siteDAO = new SiteDAO();
        ServletContext context = getServletContext();

        String indexPath = context.getRealPath("/WEB-INF/index");
        String descriptionPath = context.getRealPath("/WEB-INF/descriptions");

        siteDAO.getBdeAPI().setTextSearchInfo("site", "id", descriptionPath);
        siteDAO.getBdeAPI().createTextIndex(indexPath);
        List<Site> sites = siteDAO.searchByKeyword(keywords);

        req.setAttribute("sites", sites);
        req.getRequestDispatcher("/WEB-INF/jsp/travel-configurator-results.jsp").forward(req, resp);
    }
}
