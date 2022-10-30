<%-- 
    Document   : password
    Created on : Apr 21, 2022, 5:26:55 PM
    Author     : Gabriel Inácio <gabrielinacio@id.uff.br>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="DTO.UserDTO"%>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    
    if (user != null)
        if (user.getForceUpdate())
            response.sendRedirect("User?a=password");
%>