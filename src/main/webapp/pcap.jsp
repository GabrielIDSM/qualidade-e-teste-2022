<%-- 
    Document   : pcap
    Created on : Jun 1, 2022, 11:31:48 AM
    Author     : Gabriel Inácio <gabrielinacio@id.uff.br>
--%>

<%@page import="Util.I18nUtil"%>
<%@page import="CSV.I18nCSV"%>
<%@page import="CSV.I18nCSV"%>
<%@page import="CSV.BpmCSV"%>
<%@page import="CSV.DataCSV"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
String URL = request.getRequestURL().toString().replace("/pcap.jsp", "");

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
        <link rel="stylesheet" href="CSS/pcap.css">
        <link rel="stylesheet" href="CSS/alert.css">

        <title><%=I18nUtil.i18n("PCAPS", Language)%></title>
    </head>
    <body class="pcap-body">
        <%@include file="/Component/administrator.jsp"%>
        <%@include file="/Component/deprecated.jsp"%>
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
                            <li class="nav-item">
                                <a class="nav-link" href="User?a=list"><i class="fas fa-users"></i> <%=I18nUtil.i18n("USERS", Language)%></a>
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
        <div class="pcap-content">
        <%
            List<DataCSV> dataCsv = (List<DataCSV>) request.getAttribute("csvdata");
        %>
            <div class="py-5">
                <div class="container">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col"><%=I18nUtil.i18n("FILE_NAME", Language)%></th>
                                <th scope="col"><%=I18nUtil.i18n("ACTIONS", Language)%></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (!(dataCsv == null)) if (!dataCsv.isEmpty()) for (DataCSV d : dataCsv) {%>
                            <tr>
                                <th colspan="2">
                                    <%=d.getId()%>
                                </th>
                            </tr>
                                <% for (BpmCSV b : d.getBpm()) {%>
                                <tr>
                                    <td>
                                        <%=b.getPcap()%>
                                    </td>
                                    <td>
                                        <a class="btn btn-success text-white" href="/PCAP/<%=b.getPcap()%>"><i class="fa fa-download"></i></a>
                                    </td>
                                </tr>
                                <% }%>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="/Component/common.html" %>
    </body>
</html>
