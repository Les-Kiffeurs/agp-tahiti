package fr.cyu.depinfo.agp.tahiti.servlet;

import fr.cyu.depinfo.agp.tahiti.business.locations.Site;
import fr.cyu.depinfo.agp.tahiti.dao.SiteDAOInterface;
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

        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        SiteDAOInterface siteDAO = wac.getBean(SiteDAO.class);

        ServletContext context = getServletContext();
        String indexPath = context.getRealPath("/WEB-INF/index");
        String descriptionPath = context.getRealPath("/WEB-INF/descriptions");

        BDeAPI bdeAPI = wac.getBean(BDeAPI.class);
        bdeAPI.setTextSearchInfo("site", "id", descriptionPath);
        bdeAPI.createTextIndex(indexPath);

        Site site = siteDAO.getSiteById(siteId);

        if (site == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Site not found");
            return;
        }

        req.setAttribute("site", site);
        req.getRequestDispatcher("/WEB-INF/jsp/site-details.jsp").forward(req, resp);
    }
}
