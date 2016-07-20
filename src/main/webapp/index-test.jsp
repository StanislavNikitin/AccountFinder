
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
