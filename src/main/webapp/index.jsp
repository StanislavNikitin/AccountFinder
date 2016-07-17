<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
    
    </head>
    <body>
        <% if (request.getAttribute("isShowingResult") == null){ %>
            <%@include file="templates/main_page.jsp" %>
        <%} else { %>
            <%@include file="templates/response.jsp" %>
        <% } %>
    </body>
</html>
