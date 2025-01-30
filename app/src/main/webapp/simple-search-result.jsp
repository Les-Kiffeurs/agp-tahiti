<%@ page import="fr.cyu.depinfo.agp.tahiti.beans.EntriesBeans" %>
<%@ page import="fr.cyu.depinfo.agp.tahiti.business.locations.Location" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.cyu.depinfo.agp.tahiti.persistence.HotelDAO" %>
<%@ page import="fr.cyu.depinfo.agp.tahiti.dao.HotelDAOInterface" %>
<%@ page import="fr.cyu.depinfo.agp.tahiti.business.locations.Hotel" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:useBean id="resultBean"
             class="fr.cyu.depinfo.agp.tahiti.beans.ResultBean"
             scope="session" />
<jsp:setProperty name="resultBean" property="*" />

<%
  String priceStr = request.getParameter("price");
  int minPrice = Integer.parseInt(priceStr.split("-")[0]);
  int maxPrice = Integer.parseInt(priceStr.split("-")[1]);
  HotelDAOInterface hotelDAO = new HotelDAO();
  List<Location> hotels = hotelDAO.searchByPrice(minPrice, maxPrice);
%>

<html>
<head>
  <title>Search Results</title>
</head>
<body>
<h1>Search Results</h1>

<div>
  <p>Hello</p>
  <% for (Location loc : hotels) { %>
<p> <%=loc.getName()%> </p>
  <% } %>
  <p>World</p>
</div>

<a href="index.jsp">Back to Main Menu</a>
</body>
</html>