<%-- 
    Document   : user
    Created on : Apr 16, 2022, 10:36:45 AM
    Author     : Gabriel Inácio <gabrielinacio@id.uff.br>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="DTO.UserDTO"%>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    
    if (user != null)
        if (user.getForceUpdate())
            response.sendRedirect("User?a=password");
            
    user = (UserDTO) session.getAttribute("user");
    
    if (user == null)
        response.sendRedirect("User?a=login");
%>