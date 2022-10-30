<%-- 
    Document   : dashboard
    Created on : May 18, 2022, 5:56:53 PM
    Author     : Gabriel Inácio <gabrielinacio@id.uff.br>
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="CSV.I18nCSV"%>
<%@page import="Util.I18nUtil"%>
<%@page import="Util.DashboardUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.FilterDTO"%>
<%@page import="CSV.BpmCSV"%>
<%@page import="Util.AgeUtil"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="CSV.DataCSV"%>
<%@page import="java.util.List"%>
<%
DecimalFormat decimalFormat = new DecimalFormat("0.00");
List<DataCSV> dataCsvList = (List<DataCSV>) session.getAttribute("csvdata");
List<FilterDTO> filterList = (List<FilterDTO>) session.getAttribute("filterlist");
ObjectMapper objectMapper = new ObjectMapper();
String URL = request.getRequestURL().toString().replace("/dashboard.jsp", "");
String Path = getServletContext().getRealPath("/") + "CSV/";
List<I18nCSV> i18nList = I18nUtil.getI18n(Path);

Integer Language = I18nCSV.PT_BR;
UserDTO userAux = (UserDTO) session.getAttribute("user");
if (userAux != null)
    Language = userAux.getLanguage();

if (filterList == null) {
    filterList = new ArrayList<>();
}

if (dataCsvList == null)
    dataCsvList = new ArrayList<>();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="/Component/common.html" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="FontAwesome/css/all.css">
        <link rel="stylesheet" href="CSS/dashboard.css">
        <link rel="stylesheet" href="CSS/alert.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.js" integrity="sha512-5m2r+g00HDHnhXQDbRLAfZBwPpPCaK+wPLV6lm8VQ+09ilGdHfXV7IVyKPkLOTfi4vTTUVJnz7ELs7cA87/GMA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js" integrity="sha512-sW/w8s4RWTdFFSduOTGtk4isV1+190E/GghVffMA9XczdJ2MDzSzLEubKAs5h0wzgSJOQTRYyaz73L3d6RtJSg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="JavaScript/dashboard.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta3/js/bootstrap-select.min.js" integrity="sha512-yrOmjPdp8qH8hgLfWpSFhC/+R9Cj9USL8uJxYIveJZGAiedxyIxwNw4RsLDlcjNlIRR4kkHaDHSmNHAkxFTmgg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta3/css/bootstrap-select.min.css" integrity="sha512-g2SduJKxa4Lbn3GW+Q7rNz+pKP9AWMR++Ta8fgwsZRCUsawjPvF/BxSMkGS61VsR9yinGoEgrHPGPn2mrj8+4w==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <title>Dashboard</title>
    </head>
    <body class="dashboard-body">
        <%@include file="/Component/user.jsp"%>
        <nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color:#00166b;">
            <a class="navbar-brand" href="index.jsp"><b>eHealth CSI Dashboard</b></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#nav" aria-controls="nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="nav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i> <%=I18nUtil.i18n("HOME", Language)%></a>
                    </li>
                    <%                        if (!(user == null)) {
                    %>
                    <li class="nav-item active">
                        <a class="nav-link" href="Dashboard?a=show"><i class="fa-solid fa-chart-line"></i> Dashboard<span class="sr-only">(current)</span></a>
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
                <%= (String) request.getAttribute("message")%>
            </div>
        </div>
        <%
            }
        %>
        <div class="dashboard-help">
            <button id="dashboard-help-button" class="btn dashboard-help-button">
                <i class="dashboard-help-icon fas fa-info"></i>
            </button>
            <div id="dashboard-help-content" class="dashboard-help-content" style="display:none;">
                <a><b>0  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_0", Language)%></a><br/>
                <a><b>1  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_1", Language)%></a><br/>
                <a><b>2  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_2", Language)%></a><br/>
                <a><b>3  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_3", Language)%></a><br/>
                <a><b>4  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_4", Language)%></a><br/>
                <a><b>5  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_5", Language)%></a><br/>
                <a><b>6  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_6", Language)%></a><br/>
                <a><b>7  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_7", Language)%></a><br/>
                <a><b>8  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_8", Language)%></a><br/>
                <a><b>9  <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_9", Language)%></a><br/>
                <a><b>10 <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_10", Language)%></a><br/>
                <a><b>11 <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_11", Language)%></a><br/>
                <a><b>12 <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_12", Language)%></a><br/>
                <a><b>13 <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_13", Language)%></a><br/>
                <a><b>14 <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_14", Language)%></a><br/>
                <a><b>15 <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_15", Language)%></a><br/>
                <a><b>16 <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_16", Language)%></a><br/>
                <a><b>17 <i class="fas fa-long-arrow-alt-right"></i> </b><%=I18nUtil.i18n("POSITION_17", Language)%></a><br/>
                <div id="arrow" data-popper-arrow></div>
            </div>
        </div>
        <div class="dashboard-content py-5">
            <div class="container">
                <div class="dashboard-filters">
                    <button id="new-filter" class="btn btn-success text-white" onclick="showNewFilter()"><i class="fa fa-plus"></i><b> <%=I18nUtil.i18n("ADD_FILTER", Language)%> </b></button>
                    <div id="dashboard-new-filter" style="display:none;" class="dashboard-new-filter">
                        <form id="dashboard-new-filter-form" method="POST" action="Dashboard?a=new">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("FILTER_TYPE", Language)%> </b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="80%" id="filter-type" name="filter-type" required>
                                                <option selected value="<%=FilterDTO.getPARTICIPANTS()%>"><%=I18nUtil.i18n("FILTER_TYPE_LIST", Language)%></option>
                                                <option value="<%=FilterDTO.getAGGREGATED()%>"><%=I18nUtil.i18n("FILTER_TYPE_AVG", Language)%></option>
                                                <option value="<%=FilterDTO.getGROUPED()%>"><%=I18nUtil.i18n("FILTER_TYPE_BY_PARTICIPANT", Language)%></option>
                                                <option value="<%=FilterDTO.getREPORT()%>"><%=I18nUtil.i18n("FILTER_TYPE_REPORT", Language)%></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="participants" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("PARTICIPANTS", Language)%> </b></label>
                                            <select title="<%=I18nUtil.i18n("PARTICIPANTS", Language)%>" class="form-control selectpicker dashboard-select" data-width="80%" id="participants" name="participants" data-live-search="true" multiple>
                                                <%
                                                    for (DataCSV data : dataCsvList) {
                                                %>
                                                <option value="<%=data.getId()%>"><%=data.getId()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="ways" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("POSITIONS", Language)%> </b></label>
                                            <select title="<%=I18nUtil.i18n("POSITIONS", Language)%>" class="form-control selectpicker dashboard-select" data-width="80%" id="ways" name="ways" data-live-search="true" multiple>
                                                <%
                                                    for (Integer i = 0; i < 18; i++) {
                                                %>
                                                <option value="<%=i%>"><%=i%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("POSTURE_TYPE", Language)%> </b></label>
                                            <select title="<%=I18nUtil.i18n("POSTURE_TYPE", Language)%>" class="form-control selectpicker dashboard-select" data-width="80%" id="position-type" name="position-type">
                                                <option value="<%=FilterDTO.getIN_MOTION()%>"><%=I18nUtil.i18n("IN_MOTION", Language)%></option>
                                                <option value="<%=FilterDTO.getSTATIC()%>"><%=I18nUtil.i18n("STATIC", Language)%></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("ASSIGNED_SEX", Language)%> </b></label>
                                            <select title="<%=I18nUtil.i18n("ASSIGNED_SEX", Language)%>" class="form-control selectpicker dashboard-select" data-width="80%" id="sex" name="sex">
                                                <option value="<%=FilterDTO.getMALE()%>"><%=I18nUtil.i18n("MALE", Language)%></option>
                                                <option value="<%=FilterDTO.getFEMALE()%>"><%=I18nUtil.i18n("FEMALE", Language)%></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("STATURE", Language)%> </b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="height-operator" name="height-operator">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:55%;margin-left:1%;" type="text" class="form-control" name="height" id="height" placeholder="<%=I18nUtil.i18n("STATURE", Language)%>" autocomplete="off">
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b></b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="height-operator-2" name="height-operator-2">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:55%;margin-left:1%;" type="text" class="form-control" name="height-2" id="height-2" placeholder="<%=I18nUtil.i18n("STATURE", Language)%>" autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("WEIGHT", Language)%> </b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="weight-operator" name="weight-operator">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:55%;margin-left:1%;" type="text" class="form-control" name="weight" id="weight" placeholder="<%=I18nUtil.i18n("WEIGHT", Language)%>" autocomplete="off">
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b></b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="weight-operator-2" name="weight-operator-2">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:55%;margin-left:1%;" type="text" class="form-control" name="weight-2" id="weight-2" placeholder="<%=I18nUtil.i18n("WEIGHT", Language)%>" autocomplete="off">
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("AGE", Language)%> </b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="age-operator" name="age-operator">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:55%;margin-left:1%;" type="text" class="form-control" name="age" id="age" placeholder="<%=I18nUtil.i18n("AGE", Language)%>" autocomplete="off">
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b></b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="age-operator-2" name="age-operator-2">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:55%;margin-left:1%;" type="text" class="form-control" name="age-2" id="age-2" placeholder="<%=I18nUtil.i18n("AGE", Language)%>" autocomplete="off">
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("BODY_MASS_INDEX", Language)%> </b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="bmi-operator" name="bmi-operator">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:55%;margin-left:1%;" type="text" class="form-control" name="bmi" id="bmi" placeholder="<%=I18nUtil.i18n("BODY_MASS_INDEX", Language)%>" autocomplete="off">
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b></b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="bmi-operator-2" name="bmi-operator-2">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:55%;margin-left:1%;" type="text" class="form-control" name="bmi-2" id="bmi-2" placeholder="<%=I18nUtil.i18n("BODY_MASS_INDEX", Language)%>" autocomplete="off">
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("BPM", Language)%> </b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="bpm-type" name="bpm-type">
                                                <option selected value="<%=FilterDTO.getAT_LEAST_ONE()%>"><%=I18nUtil.i18n("FILTER_AT_LEAST_ONE", Language)%></option>
                                                <option value="<%=FilterDTO.getAVG()%>"><%=I18nUtil.i18n("FILTER_AVG", Language)%></option>
                                                <option value="<%=FilterDTO.getALL()%>"><%=I18nUtil.i18n("FILTER_ALL", Language)%></option>
                                                <option value="<%=FilterDTO.getNONE()%>"><%=I18nUtil.i18n("FILTER_NONE", Language)%></option>
                                            </select>
                                            <div style="width:0;margin-left:1%;"></div>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="bpm-operator" name="bpm-operator">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:30%;margin-left:1%;" type="text" class="form-control" name="bpm" id="bpm" placeholder="<%=I18nUtil.i18n("VALUE", Language)%>" autocomplete="off">
                                        </div>
                                        <div class="form-group">
                                            <label for="filter-type" class="col-form-label" style="width:20%;"><b> <%=I18nUtil.i18n("RESPIRATORY_RATE", Language)%> </b></label>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="respiratoryrate-type" name="respiratoryrate-type">
                                                <option selected value="<%=FilterDTO.getAT_LEAST_ONE()%>"><%=I18nUtil.i18n("FILTER_AT_LEAST_ONE", Language)%></option>
                                                <option value="<%=FilterDTO.getAVG()%>"><%=I18nUtil.i18n("FILTER_AVG", Language)%></option>
                                                <option value="<%=FilterDTO.getALL()%>"><%=I18nUtil.i18n("FILTER_ALL", Language)%></option>
                                                <option value="<%=FilterDTO.getNONE()%>"><%=I18nUtil.i18n("FILTER_NONE", Language)%></option>
                                            </select>
                                            <div style="width:0;margin-left:1%;"></div>
                                            <select class="form-control selectpicker dashboard-select" data-width="24%" id="respiratoryrate-operator" name="respiratoryrate-operator">
                                                <option selected value="<%=FilterDTO.getEQUAL()%>">=</option>
                                                <option value="<%=FilterDTO.getGREATER()%>">&gt;</option>
                                                <option value="<%=FilterDTO.getGREATER_EQUAL()%>">&gt;=</option>
                                                <option value="<%=FilterDTO.getLESS()%>">&lt;</option>
                                                <option value="<%=FilterDTO.getLESS_EQUAL()%>">&lt;=</option>
                                            </select>
                                            <input style="width:30%;margin-left:1%;" type="text" class="form-control" name="respiratoryrate" id="respiratoryrate" placeholder="<%=I18nUtil.i18n("VALUE", Language)%>" autocomplete="off">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button style="background-color:#00166b;" type="submit" class="btn text-white"> <b><%=I18nUtil.i18n("CREATE", Language)%></b> </button>
                            <button style="background-color:rgba(170, 20, 20, 1);" type="button" class="btn text-white" onclick="hideNewFilter()"> <b><%=I18nUtil.i18n("CANCEL", Language)%></b> </button>
                        </form>
                    </div>
                </div>
                <hr/>
                <div class="dashboard-view">
                    <div class="dashboard-view-line">
                        <%
                            if (filterList.isEmpty()) {
                                for (DataCSV data : dataCsvList) {
                                    String genderIcon, genderColor;
                                    switch (data.getParticipant().getAssignedSex()) {
                                        case "Masculino":
                                            genderIcon = "fas fa-mars";
                                            genderColor = "#3232FF";
                                            break;
                                        case "Feminino":
                                            genderIcon = "fas fa-venus";
                                            genderColor = "#FF3232";
                                            break;
                                        default:
                                            genderIcon = "fas fa-genderless";
                                            genderColor = "#7F7F7F";
                                    }

                        %>
                        <div class="input-group">
                            <div id="line-<%=data.getParticipant().getId()%>" class="dashboard-view-line-title form-control" onclick="titleState('#view-<%=data.getParticipant().getId()%>', '#caret-<%=data.getParticipant().getId()%>', '#line-<%=data.getParticipant().getId()%>')">
                                <div class="dashboard-view-line-title-box">
                                    <div class="dashboard-view-line-title-box-p1">
                                        <i class="dashboard-view-line-title-icon fas fa-caret-down" id="caret-<%=data.getParticipant().getId()%>"></i>
                                        <p class="dashboard-view-line-title-text"><%=data.getParticipant().getId()%></p>
                                    </div>
                                    <div class="dashboard-view-line-title-box-p2">
                                        <i class="dashboard-view-line-gender-icon <%=genderIcon%>" style="color:<%=genderColor%>;" id="caret-<%=data.getParticipant().getId()%>"></i>
                                        <p class="dashboard-view-line-title-text-2"><%=data.getParticipant().i18n(Language)%></p>
                                    </div>
                                </div>
                            </div>
                            <span class="input-group-btn">
                                <button onclick="infoState('#info-<%=data.getParticipant().getId()%>')" class="btn dashboard-info-button">
                                    <i class="dashboard-view-line-info-icon fas fa-info"></i>
                                </button>
                            </span>
                            <span class="input-group-btn">
                                <a target="_blank" href="<%=data.getLink()%>" class="btn btn-success dashboard-download-button">
                                    <i class="dashboard-view-line-download-icon fas fa-file-arrow-down"></i>
                                </a>
                            </span>
                        </div>
                        <div id="view-<%=data.getParticipant().getId()%>" class="dashboard-view-line-content row" state="visible">
                            <div id="info-<%=data.getParticipant().getId()%>" class="col-lg-12 dashboard-info" style="display: none;" state="hidden">
                                <div class="dashboard-info-content card">
                                    <div class="card-header" style="text-align: center; font-weight: bold; border-color: #00166b; background-color: #00166b; color: #FBFBF8;"><%=I18nUtil.i18n("PARTICIPANT_INFO", Language)%></div>
                                    <div class="card-body dashboard-info-content-body" style="padding: 0 10px;">
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_1", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic1().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic1(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_2", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic2().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic2(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_3", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic3().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic3(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_4", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic4().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic4(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_5", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic5().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic5(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_6", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic6().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic6(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_7", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic7().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic7(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_8", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic8().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic8(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_9", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic9().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic9(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_10", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic10().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic10(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_11", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic11().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic11(), Language)%>
                                            </p>
                                        </div>
                                        <div class="dashboard-question-box">
                                            <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_12", Language)%></p>
                                        </div>
                                        <div class="dashboard-answer-box">
                                            <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic12().isBlank() ?
                                                "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                I18nUtil.i18n(data.getParticipant().getCharacteristic12(), Language)%>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="card dashboard-card" style="border-color: rgba(170, 20, 20, 1);">
                                    <div class="card-header" style="text-align: center; font-weight: bold; border-color: rgba(170, 20, 20, 1); background-color: rgba(170, 20, 20, 1); color: #FBFBF8;"><%=I18nUtil.i18n("BPM", Language)%></div>
                                    <div class="card-body" style="padding: 10px;">
                                        <div class="dashboard-view-line-content-chart-box">
                                            <canvas class="dashboard-view-line-content-chart-canvas" id="bpm-<%=data.getParticipant().getId()%>" ></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="card dashboard-card"  style="border-color: rgba(20, 170, 170, 1);">
                                    <div class="card-header" style="text-align: center; font-weight: bold; border-color: rgba(20, 170, 170, 1); background-color: rgba(20, 170, 170, 1); color: #FBFBF8;"><%=I18nUtil.i18n("RESPIRATORY_RATE", Language)%></div>
                                    <div class="card-body" style="padding: 10px;">
                                        <div class="dashboard-view-line-content-chart-box">
                                            <canvas class="dashboard-view-line-content-chart-canvas" id="respiratoryrate-<%=data.getParticipant().getId()%>" ></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script>
                                drawChart('bpm-<%=data.getParticipant().getId()%>', 'BPM', <%=objectMapper.writeValueAsString(data.getBpm())%>, <%=objectMapper.writeValueAsString(data.getWristwatchBpm())%>, false, <%=objectMapper.writeValueAsString(i18nList)%>, <%=Language%>);
                                drawChart('respiratoryrate-<%=data.getParticipant().getId()%>', 'Respiratory Rate', <%=objectMapper.writeValueAsString(data.getRespiratoryRate())%>, null, false, <%=objectMapper.writeValueAsString(i18nList)%>, <%=Language%>);
                            </script>
                        </div>
                        <%
                            }
                        } else {
                            for (FilterDTO f : filterList) {
                                List<DataCSV> filterCsvList = DashboardUtil.filterData(dataCsvList, f);
                        %>
                        <div class="dashboard-view-filter" style="border-color:<%=f.getTag()%>, 1);background-color:<%=f.getTag()%>, 0.075);">
                            <div id="dashboard-view-filter-title-<%=f.getId()%>" onclick="event.stopPropagation(); filterState('#dashboard-view-filter-title-<%=f.getId()%>', '#dashboard-view-filter-content-<%=f.getId()%>', '<%=f.getTag()%>, 0.075)', '#caret-filter-<%=f.getId()%>')" class="dashboard-view-filter-title" style="border-color:<%=f.getTag()%>, 1);">
                                <i class="dashboard-view-filter-title-icon fas fa-caret-down" id="caret-filter-<%=f.getId()%>"></i>
                                <p class="dashboard-view-filter-title-text"><%=f.i18n(Language)%></p>
                                <a style="background-color:rgba(170, 20, 20, 1);" onclick="event.stopPropagation();" class="btn text-white dashboard-view-filter-del" href="Dashboard?a=deletefilter&amp;id=<%=filterList.lastIndexOf(f)%>"><i class="dashboard-view-filter-del-icon fas fa-trash"></i></a>
                                <%
                                    if (f.getType().equals(FilterDTO.getREPORT())) {
                                %>
                                <button onclick="event.stopPropagation();downloadTableAsCSV('table-<%=f.getId()%>')" class="btn btn-success dashboard-view-filter-download">
                                    <i class="fas fa-file-arrow-down"></i>
                                </button>
                                <%
                                    }
                                %>
                            </div>
                            <div id="dashboard-view-filter-content-<%=f.getId()%>" class="dashboard-view-filter-content" style="border-color:<%=f.getTag()%>, 1);" state="visible">
                                <%
                                    if (f.getType().equals(FilterDTO.getPARTICIPANTS())) {
                                %>
                                    <div class="dashboard-view-filter-count" style="border-color:<%=f.getTag()%>, 1);">
                                        <div class="dashboard-view-filter-box">
                                            <p class="dashboard-view-filter-text">
                                                <%=filterCsvList.size() != 1 ? filterCsvList.size() + " " + I18nUtil.i18n("PARTICIPANTS", Language) : filterCsvList.size() + " " + I18nUtil.i18n("PARTICIPANT", Language)%>
                                            </p>
                                        </div>
                                    </div>
                                <%
                                        for (DataCSV data : filterCsvList) {
                                            String genderIcon, genderColor;
                                            switch (data.getParticipant().getAssignedSex()) {
                                                case "Masculino":
                                                    genderIcon = "fas fa-mars";
                                                    genderColor = "#3232FF";
                                                    break;
                                                case "Feminino":
                                                    genderIcon = "fas fa-venus";
                                                    genderColor = "#FF3232";
                                                    break;
                                                default:
                                                    genderIcon = "fas fa-genderless";
                                                    genderColor = "#7F7F7F";
                                            }

                                %>
                                <div class="input-group">
                                    <div id="line-<%=data.getParticipant().getId()%>" class="dashboard-view-line-title form-control" onclick="titleState('#view-<%=data.getParticipant().getId()%>', '#caret-<%=data.getParticipant().getId()%>', '#line-<%=data.getParticipant().getId()%>')">
                                        <div class="dashboard-view-line-title-box">
                                            <div class="dashboard-view-line-title-box-p1">
                                                <i class="dashboard-view-line-title-icon fas fa-caret-down" id="caret-<%=data.getParticipant().getId()%>"></i>
                                                <p class="dashboard-view-line-title-text"><%=data.getParticipant().getId()%></p>
                                            </div>
                                            <div class="dashboard-view-line-title-box-p2">
                                                <i class="dashboard-view-line-gender-icon <%=genderIcon%>" style="color:<%=genderColor%>;" id="caret-<%=data.getParticipant().getId()%>"></i>
                                                <p class="dashboard-view-line-title-text-2"><%=data.getParticipant().i18n(Language)%></p>
                                            </div>
                                        </div>
                                    </div>
                                    <span class="input-group-btn">
                                        <button onclick="infoState('#info-<%=data.getParticipant().getId()%>')" class="btn dashboard-info-button">
                                            <i class="dashboard-view-line-info-icon fas fa-info"></i>
                                        </button>
                                    </span>
                                    <span class="input-group-btn">
                                        <a target="_blank" href="<%=data.getLink()%>" class="btn btn-success dashboard-download-button">
                                            <i class="dashboard-view-line-download-icon fas fa-file-arrow-down"></i>
                                        </a>
                                    </span>
                                </div>
                                <div id="view-<%=data.getParticipant().getId()%>" class="dashboard-view-line-content row" state="visible">
                                    <div id="info-<%=data.getParticipant().getId()%>" class="col-lg-12 dashboard-info" style="display: none;" state="hidden">
                                    <div class="dashboard-info-content card">
                                        <div class="card-header" style="text-align: center; font-weight: bold; border-color: #00166b; background-color: #00166b; color: #FBFBF8;"><%=I18nUtil.i18n("PARTICIPANT_INFO", Language)%></div>
                                        <div class="card-body dashboard-info-content-body" style="padding: 0 10px;">
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_1", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic1().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic1(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_2", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic2().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic2(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_3", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic3().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic3(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_4", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic4().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic4(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_5", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic5().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic5(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_6", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic6().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic6(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_7", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic7().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic7(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_8", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic8().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic8(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_9", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic9().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic9(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_10", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic10().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic10(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_11", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic11().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic11(), Language)%>
                                                </p>
                                            </div>
                                            <div class="dashboard-question-box">
                                                <p class="dashboard-question"><%=I18nUtil.i18n("QUESTION_12", Language)%></p>
                                            </div>
                                            <div class="dashboard-answer-box">
                                                <p class="dashboard-answer"><%=data.getParticipant().getCharacteristic12().isBlank() ?
                                                    "<i>" + I18nUtil.i18n("NO_ANSWER", Language) + "</i>" :
                                                    I18nUtil.i18n(data.getParticipant().getCharacteristic12(), Language)%>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="card dashboard-card" style="border-color: rgba(170, 20, 20, 1);">
                                            <div class="card-header" style="text-align: center; font-weight: bold; border-color: rgba(170, 20, 20, 1); background-color: rgba(170, 20, 20, 1); color: #FBFBF8;"><%=I18nUtil.i18n("BPM", Language)%></div>
                                            <div class="card-body" style="padding: 10px;">
                                                <div class="dashboard-view-line-content-chart-box">
                                                    <canvas class="dashboard-view-line-content-chart-canvas" id="bpm-<%=f.getId()%>-<%=data.getParticipant().getId()%>" ></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="card dashboard-card"  style="border-color: rgba(20, 170, 170, 1);">
                                            <div class="card-header" style="text-align: center; font-weight: bold; border-color: rgba(20, 170, 170, 1); background-color: rgba(20, 170, 170, 1); color: #FBFBF8;"><%=I18nUtil.i18n("RESPIRATORY_RATE", Language)%></div>
                                            <div class="card-body" style="padding: 10px;">
                                                <div class="dashboard-view-line-content-chart-box">
                                                    <canvas class="dashboard-view-line-content-chart-canvas" id="respiratoryrate-<%=f.getId()%>-<%=data.getParticipant().getId()%>" ></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <script>
                                        drawChart('bpm-<%=f.getId()%>-<%=data.getParticipant().getId()%>', 'BPM', <%=objectMapper.writeValueAsString(data.getBpm())%>, <%=objectMapper.writeValueAsString(data.getWristwatchBpm())%>, true, <%=objectMapper.writeValueAsString(i18nList)%>, <%=Language%>);
                                        drawChart('respiratoryrate-<%=f.getId()%>-<%=data.getParticipant().getId()%>', 'Respiratory Rate', <%=objectMapper.writeValueAsString(data.getRespiratoryRate())%>, null, true, <%=objectMapper.writeValueAsString(i18nList)%>, <%=Language%>);
                                    </script>
                                </div>
                                <%
                                    }
                                } else if (f.getType().equals(FilterDTO.getAGGREGATED())) {
                                %>
                                <div class="dashboard-view-filter-count-2" style="border-color:<%=f.getTag()%>, 1);">
                                    <div class="dashboard-view-filter-box">
                                        <p class="dashboard-view-filter-text">
                                            <%=filterCsvList.size() != 1 ? filterCsvList.size() + " " + I18nUtil.i18n("PARTICIPANTS", Language) : filterCsvList.size() + " " + I18nUtil.i18n("PARTICIPANT", Language)%>
                                        </p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="card dashboard-card" style="border-color: rgba(170, 20, 20, 1);">
                                            <div class="card-header" style="text-align: center; font-weight: bold; border-color: rgba(170, 20, 20, 1); background-color: rgba(170, 20, 20, 1); color: #FBFBF8;"><%=I18nUtil.i18n("BPM", Language)%></div>
                                            <div class="card-body" style="padding: 10px;">
                                                <div class="dashboard-view-line-content-chart-box">
                                                    <canvas class="dashboard-view-line-content-chart-canvas" id="bpm-<%=f.getId()%>" ></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="card dashboard-card"  style="border-color: rgba(20, 170, 170, 1);">
                                            <div class="card-header" style="text-align: center; font-weight: bold; border-color: rgba(20, 170, 170, 1); background-color: rgba(20, 170, 170, 1); color: #FBFBF8;"><%=I18nUtil.i18n("RESPIRATORY_RATE", Language)%></div>
                                            <div class="card-body" style="padding: 10px;">
                                                <div class="dashboard-view-line-content-chart-box">
                                                    <canvas class="dashboard-view-line-content-chart-canvas" id="respiratoryrate-<%=f.getId()%>" ></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <script>
                                        drawAggrChart('bpm-<%=f.getId()%>', 'BPM', <%=objectMapper.writeValueAsString(filterCsvList)%>, true, <%=objectMapper.writeValueAsString(i18nList)%>, <%=Language%>);
                                        drawAggrChart('respiratoryrate-<%=f.getId()%>', 'Respiratory Rate', <%=objectMapper.writeValueAsString(filterCsvList)%>, true, <%=objectMapper.writeValueAsString(i18nList)%>, <%=Language%>);
                                </script>
                                <%
                                } else if (f.getType().equals(FilterDTO.getGROUPED())) {
                                %>
                                <div class="dashboard-view-filter-count-2" style="border-color:<%=f.getTag()%>, 1);">
                                    <div class="dashboard-view-filter-box">
                                        <p class="dashboard-view-filter-text">
                                            <%=filterCsvList.size() != 1 ? filterCsvList.size() + " " + I18nUtil.i18n("PARTICIPANTS", Language) : filterCsvList.size() + " " + I18nUtil.i18n("PARTICIPANT", Language)%>
                                        </p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="card dashboard-card" style="border-color: rgba(170, 20, 20, 1);">
                                            <div class="card-header" style="text-align: center; font-weight: bold; border-color: rgba(170, 20, 20, 1); background-color: rgba(170, 20, 20, 1); color: #FBFBF8;"><%=I18nUtil.i18n("BPM", Language)%></div>
                                            <div class="card-body" style="padding: 10px;">
                                                <div class="dashboard-view-line-content-chart-box">
                                                    <canvas class="dashboard-view-line-content-chart-canvas" id="bpm-<%=f.getId()%>" ></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="card dashboard-card"  style="border-color: rgba(20, 170, 170, 1);">
                                            <div class="card-header" style="text-align: center; font-weight: bold; border-color: rgba(20, 170, 170, 1); background-color: rgba(20, 170, 170, 1); color: #FBFBF8;"><%=I18nUtil.i18n("RESPIRATORY_RATE", Language)%></div>
                                            <div class="card-body" style="padding: 10px;">
                                                <div class="dashboard-view-line-content-chart-box">
                                                    <canvas class="dashboard-view-line-content-chart-canvas" id="respiratoryrate-<%=f.getId()%>" ></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <script>
                                        drawGpChart('bpm-<%=f.getId()%>', 'BPM', <%=objectMapper.writeValueAsString(filterCsvList)%>, true, <%=objectMapper.writeValueAsString(i18nList)%>, <%=Language%>);
                                        drawGpChart('respiratoryrate-<%=f.getId()%>', 'Respiratory Rate', <%=objectMapper.writeValueAsString(filterCsvList)%>, true, <%=objectMapper.writeValueAsString(i18nList)%>, <%=Language%>);
                                </script>
                                <%
                                } else if (f.getType().equals(FilterDTO.getREPORT())) {
                                %>
                                <div class="dashboard-view-filter-count-2" style="border-color:<%=f.getTag()%>, 1);">
                                    <div class="dashboard-view-filter-box">
                                        <p class="dashboard-view-filter-text">
                                            <%=filterCsvList.size() != 1 ? filterCsvList.size() + " " + I18nUtil.i18n("PARTICIPANTS", Language) : filterCsvList.size() + " " + I18nUtil.i18n("PARTICIPANT", Language)%>
                                        </p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="dashboard-table-div">
                                        <table class="dashboard-table table table-sm table-bordered" id="table-<%=f.getId()%>">
                                            <thead>
                                                <tr>
                                                    <th scope="col"><%= I18nUtil.i18n("ID", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("BIRTH_DATE", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("AGE", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("STATURE", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("WEIGHT", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("ASSIGNED_SEX", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("POSITION", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("BPM", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("RESPIRATORY_RATE", Language)%></th>
                                                    <th scope="col"><%= I18nUtil.i18n("WRISTWATCH_BPM", Language)%></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (DataCSV dataCsv : filterCsvList) {
                                                        Integer age = AgeUtil.calculateAge(dataCsv.
                                                                getParticipant().getBirthDateJD());
                                                        for (int i = 0; i < dataCsv.getBpm().size(); i++) {
                                                %>
                                                <tr>
                                                    <td>
                                                        <%= dataCsv.getParticipant().getId()%>
                                                    </td>
                                                    <td>
                                                        <%= dataCsv.getParticipant().getBirthDate()%>
                                                    </td>
                                                    <td>
                                                        <%= age%>
                                                    </td>
                                                    <td>
                                                        <%= dataCsv.getParticipant().getHeight()%> cm
                                                    </td>
                                                    <td>
                                                        <%= dataCsv.getParticipant().getWeight()%> kg
                                                    </td>
                                                    <td>
                                                        <%= dataCsv.getParticipant().getAssignedSex()%>
                                                    </td>
                                                    <td>
                                                        <%= dataCsv.getBpm().get(i).getCode()%>
                                                    </td>
                                                    <td>
                                                        <%= dataCsv.getBpm().get(i).getBpm()%>
                                                    </td>
                                                    <td>
                                                        <%= decimalFormat.format(dataCsv.getRespiratoryRate()
                                                            .get(i).getRespiratoryRate()).replace(".", ",")%>
                                                    </td>
                                                    <td>
                                                        <%= dataCsv.getWristwatchBpm().get(i).getBpm() == null
                                                            ? ""
                                                            : decimalFormat.format(dataCsv.getWristwatchBpm().get(i)
                                                                .getBpm()).replace(".", ",")%>
                                                    </td>
                                                </tr>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <%
                                }
                                %>
                            </div>
                        </div>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>