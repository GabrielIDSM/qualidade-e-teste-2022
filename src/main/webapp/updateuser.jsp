<%-- 
    Document   : updateuser
    Created on : Apr 21, 2022, 7:01:58 PM
    Author     : Gabriel Inácio <gabrielinacio@id.uff.br>
--%>

<%@page import="CSV.I18nCSV"%>
<%@page import="CSV.I18nCSV"%>
<%@page import="Util.I18nUtil"%>
<%
String URL = request.getRequestURL().toString().replace("/updateuser.jsp", "");

Integer Language = I18nCSV.PT_BR;
UserDTO userAux = (UserDTO) session.getAttribute("user");
if (userAux != null)
    Language = userAux.getLanguage();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="FontAwesome/css/all.css">
        <link rel="stylesheet" href="CSS/updateuser.css">
        <link rel="stylesheet" href="CSS/alert.css">

        <title><%=I18nUtil.i18n("UPDATE_USER", Language)%></title>
    </head>
    <body class="updateuser-body">
        <%@include file="/Component/administrator.jsp"%>
        <%
            UserDTO u = (UserDTO) request.getAttribute("u");
        %>
        <nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color:#00166b">
            <a class="navbar-brand" href="index.jsp"><b>eHealth CSI Dashboard</b></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#nav" aria-controls="nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="nav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i> <%=I18nUtil.i18n("HOME", Language)%></a>
                    </li>
                    <%
                        if (!(user == null)) {
                    %>
                        <li class="nav-item">
                            <a class="nav-link" href="Dashboard?a=show"><i class="fa-solid fa-chart-line"></i> Dashboard</a>
                        </li>
                        <%
                            if (user.getRole().getName().compareTo("Administrator") == 0) {
                        %>
                            <li class="nav-item active">
                                <a class="nav-link" href="User?a=list"><i class="fas fa-users"></i> <%=I18nUtil.i18n("USERS", Language)%><span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Csv?a=list"><i class="fas fa-file-csv"></i> <%=I18nUtil.i18n("DATA", Language)%></a>
                            </li>
                    <%
                            }
                    %>
                        <li class="nav-item">
                            <a class="nav-link" href="User?a=settings"><i class="fas fa-gear"></i> <%=I18nUtil.i18n("SETTINGS", Language)%></a>
                        </li>
                    <%
                        }
                    %>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="navbar-brand" href="https://www.uff.br/">
                            <img style="padding:0 0 5px 0;" src="<%=URL%>/Image/logo-uff.png" height="30" alt="">
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="navbar-brand" href="http://www.ic.uff.br/index.php/pt/">
                            <img style="padding:0 0 5px 0;" src="<%=URL%>/Image/logo-ic-new.png" height="30" alt="">
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="navbar-brand" href="http://www.midiacom.uff.br/midiacom/index.php/pt-BR/">
                            <img style="padding:0 0 5px 0;" src="<%=URL%>/Image/logo-midiacom.png" height="30" alt="">
                        </a>
                    </li>
                <%
                    if (user == null) {
                %>
                    <li class="nav-item">
                        <a class="nav-link bg-success text-white rounded" href="User?a=login"><i class="fas fa-sign-in-alt"></i> <%=I18nUtil.i18n("LOGIN", Language)%></a>
                    </li>
                <%
                    } else {
                %>
                    <li class="nav-item">
                        <a class="nav-link bg-danger text-white rounded" href="User?a=logout" onclick="javascript:return confirm('<%=I18nUtil.i18n("EXIT_APPLICATION_CONFIRM", Language)%>')"><i class="fas fa-sign-out-alt"></i> <%=I18nUtil.i18n("EXIT", Language)%></a>
                    </li>
                <%
                    }
                %>
                </ul>
            </div>
        </nav>
        <%                       
            if (request.getAttribute("message") != null) {
        %>
            <div class="d-flex justify-content-center alert-box">
                <div class="alert <%=request.getAttribute("error") != null ? "alert-danger" : "alert-primary"%> w-25 p-3 d-flex justify-content-center" role="alert">
                    <%= (String) request.getAttribute("message") %>
                </div>
            </div>
        <%
            }
        %>
        <div class="updateuser-content">
            <div class="py-5">
                <div class="container">
                    <form method="POST" action="User?a=submit">
                        <input type="hidden" name="id" id="id" value="<%= u.getId() %>">
                        <div class="form-group">
                            <label for="username"><b> <%=I18nUtil.i18n("USERNAME", Language)%> </b></label>
                            <input type="text" class="form-control" name="username" id="username" value="<%= u.getUsername()%>" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="role"> <%=I18nUtil.i18n("ROLE", Language)%> </label>
                            <select class="form-control" name="role" id="role">
                                <option <%=u.getRole().getName().compareTo("Administrator") == 0 ? "selected" : ""%> value="Administrator"> <%=I18nUtil.i18n("ADMINISTRATOR", Language)%> </option>
                                <option <%=u.getRole().getName().compareTo("Operator") == 0 ? "selected" : ""%> value="Operator"> <%=I18nUtil.i18n("OPERATOR", Language)%> </option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="language"><b> <%=I18nUtil.i18n("LANGUAGE", Language)%> </b></label>
                            <select class="form-control" name="language" id="language">
                                <option <%=u.getLanguage() == I18nCSV.EN_US ? "selected" : ""%> value="<%=I18nCSV.EN_US%>"> <%=I18nUtil.i18n("ENGLISH", Language)%> </option>
                                <option <%=u.getLanguage() == I18nCSV.PT_BR ? "selected" : ""%> value="<%=I18nCSV.PT_BR%>"> <%=I18nUtil.i18n("PORTUGUESE", Language)%> </option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="fpassword"> <%=I18nUtil.i18n("FORCE_PASSWORD_EXPIRED", Language)%> </label>
                            <select class="form-control" name="fpassword" id="fpassword">
                                <option <%=u.getForceUpdate() ? "selected" : ""%> value="1"> <%=I18nUtil.i18n("YES", Language)%> </option>
                                <option <%=u.getForceUpdate() ? "" : "selected"%> value="0"> <%=I18nUtil.i18n("NO", Language)%> </option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary text-white"> <%=I18nUtil.i18n("SAVE", Language)%> </button>
                        <a class="btn btn-danger text-white" href="User?a=list"> <%=I18nUtil.i18n("CANCEL", Language)%> </a>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="/Component/common.html" %>
    </body>
</html>