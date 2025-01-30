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

@WebServlet("/sites")
public class SitesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String siteId = req.getParameter("id");

        if (siteId == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "'id' query param not found");
            return;
        }

        SiteDAO siteDAO = new SiteDAO();
        ServletContext context = getServletContext();

        String indexPath = context.getRealPath("/WEB-INF/index");
        String descriptionPath = context.getRealPath("/WEB-INF/descriptions");

        siteDAO.getBdeAPI().setTextSearchInfo("site", "id", descriptionPath);
        siteDAO.getBdeAPI().createTextIndex(indexPath);

        Site site = siteDAO.getSiteById(siteId);

        if (site == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Site not found");
            return;
        }

        req.setAttribute("site", site);
        req.getRequestDispatcher("/WEB-INF/jsp/site-details.jsp").forward(req, resp);


    }
}
