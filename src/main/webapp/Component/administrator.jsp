<%-- 
    Document   : administrator
    Created on : Apr 16, 2022, 10:40:21 AM
    Author     : Gabriel Inácio <gabrielinacio@id.uff.br>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="DTO.UserDTO"%>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    
    if (user != null)
        if (user.getForceUpdate())
            response.sendRedirect("User?a=password");
    
    if (user == null)
        response.sendRedirect("User?a=login");
    else
        if (user.getRole().getName().compareTo("Administrator") != 0)
            response.sendRedirect("index.jsp");
%>