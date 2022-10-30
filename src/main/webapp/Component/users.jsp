<%-- 
    Document   : users
    Created on : Apr 21, 2022, 6:22:16 PM
    Author     : Gabriel Inácio <gabrielinacio@id.uff.br>
--%>

<%@page import="java.util.List"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<UserDTO> users = (List<UserDTO>) request.getAttribute("users");

    if (users == null)
        response.sendRedirect("User?a=list");
%>