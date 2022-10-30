<%-- 
    Document   : index
    Created on : Apr 15, 2022, 6:27:53 PM
    Author     : Gabriel Inácio <gabrielinacio@id.uff.br>
--%>

<%@page import="CSV.I18nCSV"%>
<%@page import="Util.I18nUtil"%>
<%@page import="CSV.RespiratoryRateCSV"%>
<%@page import="CSV.BpmCSV"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Util.AgeUtil"%>
<%@page import="Util.CsvUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CSV.DataCSV"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserDTO"%>
<%
DecimalFormat decimalFormat = new DecimalFormat("0.00");
String URL = request.getRequestURL().toString().replace("/index.jsp", "");
String Path = getServletContext().getRealPath("/") + "CSV/";
List<DataCSV> dataCsvList = CsvUtil.getData(Path);
List<BpmCSV> bpmCsvList = CsvUtil.getBpmFromCsv(Path);
I18nUtil.updateI18n(Path);

Integer Language = I18nCSV.PT_BR;
UserDTO userAux = (UserDTO) session.getAttribute("user");
if (userAux != null)
    Language = userAux.getLanguage();

if (dataCsvList == null)
    dataCsvList = new ArrayList<>();

if (bpmCsvList == null)
    bpmCsvList = new ArrayList<>();

// General
Integer participants = dataCsvList.size();
Integer pickups = bpmCsvList.size();
Integer males = 0;
Integer females = 0;
String malesp;
String femalesp;
Double ageAvg = 0.0;
String ageAvgAsStr;
String numberDecimalSymbol = I18nUtil.i18n("NUMBER_DECIMAL_SYMBOL", Language);

// Participants
String bigAvgBpmId = "000";
Double bigAvgBpmValue = 0.0;
String lowAvgBpmId = "000";
Double lowAvgBpmValue = 100000.0;
String bigBpmId = "000";
Double bigBpmValue = 0.0;
String lowBpmId = "000";
Double lowBpmValue = 100000.0;
String bigAvgRrId = "000";
Double bigAvgRrValue = 0.0;
String lowAvgRrId = "000";
Double lowAvgRrValue = 100000.0;
String bigRrId = "000";
Double bigRrValue = 0.0;
String lowRrId = "000";
Double lowRrValue = 100000.0;

for (DataCSV data : dataCsvList) {
    // General
    if (data.getParticipant().getAssignedSex().contentEquals("Masculino"))
        males++;
    if (data.getParticipant().getAssignedSex().contentEquals("Feminino"))
        females++;
        
    Integer age = AgeUtil.calculateAge(data.getParticipant().getBirthDateJD());
    if (age != 0)
        ageAvg += age;
        
    // Participants
    if (data.getBpm() != null) {
        Double bpmAvg = 0.0;
        Double bpmBig = 0.0;
        Double bpmLow = 100000.0;
        for (BpmCSV bpm : data.getBpm()) {
            bpmAvg += Double.valueOf(bpm.getBpm());
            if (Double.valueOf(bpm.getBpm()) > bpmBig) {
                bpmBig = Double.valueOf(bpm.getBpm());
            }
            if (Double.valueOf(bpm.getBpm()) < bpmLow) {
                bpmLow = Double.valueOf(bpm.getBpm());
            }
        }
        bpmAvg /= data.getBpm().size();
        
        if (bpmAvg > bigAvgBpmValue) {
            bigAvgBpmValue = bpmAvg;
            bigAvgBpmId = data.getId();
        }
        
        if (bpmAvg < lowAvgBpmValue) {
            lowAvgBpmValue = bpmAvg;
            lowAvgBpmId = data.getId();
        }
        
        if (bpmBig > bigBpmValue) {
            bigBpmValue = bpmBig;
            bigBpmId = data.getId();
        }
        
        if (bpmLow < lowBpmValue) {
            lowBpmValue = bpmLow;
            lowBpmId = data.getId();
        }
    }
    
    if (data.getRespiratoryRate() != null) {
        Double rrAvg = 0.0;
        Double rrBig = 0.0;
        Double rrLow = 100000.0;
        for (RespiratoryRateCSV rr : data.getRespiratoryRate()) {
            rrAvg += Double.valueOf(rr.getRespiratoryRate());
            if (Double.valueOf(rr.getRespiratoryRate()) > rrBig) {
                rrBig = Double.valueOf(rr.getRespiratoryRate());
            }
            if (Double.valueOf(rr.getRespiratoryRate()) < rrLow) {
                rrLow = Double.valueOf(rr.getRespiratoryRate());
            }
        }
        rrAvg /= data.getRespiratoryRate().size();
        
        if (rrAvg > bigAvgRrValue) {
            bigAvgRrValue = rrAvg;
            bigAvgRrId = data.getId();
        }
        
        if (rrAvg < lowAvgRrValue) {
            lowAvgRrValue = rrAvg;
            lowAvgRrId = data.getId();
        }
        
        if (rrBig > bigRrValue) {
            bigRrValue = rrBig;
            bigRrId = data.getId();
        }
        
        if (rrLow < lowRrValue) {
            lowRrValue = rrLow;
            lowRrId = data.getId();
        }
    }
    
}

// General
ageAvg /= participants;
ageAvgAsStr = decimalFormat.format(ageAvg).replace(".", numberDecimalSymbol);

malesp = decimalFormat.format(Double.valueOf(males) / Double.valueOf(participants) * Double.valueOf(100.0)).replace(".", numberDecimalSymbol);
femalesp = decimalFormat.format(Double.valueOf(females) / Double.valueOf(participants) * Double.valueOf(100.0)).replace(".", numberDecimalSymbol);

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <%@include file="/Component/common.html" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="FontAwesome/css/all.css">
        <link rel="stylesheet" href="CSS/index.css">
        <link rel="stylesheet" href="CSS/alert.css">
        <script src="JavaScript/index.js"></script>

        <title><%=I18nUtil.i18n("HOME", Language)%></title>
    </head>
    <body class="index-body">
        <%@include file="/Component/password.jsp"%>
        <nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color:#00166b">
            <a class="navbar-brand" href="index.jsp"><b>eHealth CSI Dashboard</b></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#nav" aria-controls="nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="nav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i> <%=I18nUtil.i18n("HOME", Language)%><span class="sr-only">(current)</span></a>
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
        <div class="index-help">
            <button id="index-help-button" class="btn index-help-button">
                <i class="index-help-icon fas fa-info"></i>
            </button>
            <div id="index-help-content" class="index-help-content" style="display:none;">
                <a><%=I18nUtil.i18n("__ABOUT", Language)%></a>
                <div id="arrow" data-popper-arrow></div>
            </div>
        </div>
        <div class="index-content py-5">
            <div class="container container-fluid">
                <div class="index-title">
                    <p class="index-title-text"><%=I18nUtil.i18n("GENERAL", Language)%></p>
                </div>
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("PARTICIPANTS", Language)%></h5>
                                <p class="card-text index-card-text"><%=participants%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("PCAPS", Language)%></h5>
                                <p class="card-text index-card-text"><%=pickups%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("MALE_PARTICIPANTS", Language)%></h5>
                                <p class="card-text index-card-text"><%=males%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("FEMALE_PARTICIPANTS", Language)%></h5>
                                <p class="card-text index-card-text"><%=females%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("MALE_PARTICIPANTS_PERCENT", Language)%></h5>
                                <p class="card-text index-card-text"><%=malesp%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("FEMALE_PARTICIPANTS_PERCENT", Language)%></h5>
                                <p class="card-text index-card-text"><%=femalesp%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("AGE_AVERAGE", Language)%></h5>
                                <p class="card-text index-card-text"><%=ageAvgAsStr%></p>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <div class="index-title">
                    <p class="index-title-text"><%=I18nUtil.i18n("PARTICIPANTS", Language)%></p>
                </div>
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("BPM_AVERAGE_BIGGER", Language)%> (bpm/pcap)</h5>
                                <p class="card-text index-card-text"><%=decimalFormat.format(bigAvgBpmValue).replace(".", numberDecimalSymbol)%></p>
                                <p class="card-text index-card-sub-text"><%=bigAvgBpmId%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("RESPIRATORY_RATE_AVERAGE_BIGGER", Language)%> (Hz/pcap)</h5>
                                <p class="card-text index-card-text"><%=decimalFormat.format(bigAvgRrValue).replace(".", numberDecimalSymbol)%></p>
                                <p class="card-text index-card-sub-text"><%=bigAvgRrId%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("BPM_AVERAGE_SMALLER", Language)%> (bpm/pcap)</h5>
                                <p class="card-text index-card-text"><%=decimalFormat.format(lowAvgBpmValue).replace(".", numberDecimalSymbol)%></p>
                                <p class="card-text index-card-sub-text"><%=lowAvgBpmId%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("RESPIRATORY_RATE_AVERAGE_SMALLER", Language)%> (Hz/pcap)</h5>
                                <p class="card-text index-card-text"><%=decimalFormat.format(lowAvgRrValue).replace(".", numberDecimalSymbol)%></p>
                                <p class="card-text index-card-sub-text"><%=lowAvgRrId%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("BPM_BIGGER", Language)%> (bpm)</h5>
                                <p class="card-text index-card-text"><%=decimalFormat.format(bigBpmValue).replace(".", numberDecimalSymbol)%></p>
                                <p class="card-text index-card-sub-text"><%=bigBpmId%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("RESPIRATORY_RATE_BIGGER", Language)%> (Hz)</h5>
                                <p class="card-text index-card-text"><%=decimalFormat.format(bigRrValue).replace(".", numberDecimalSymbol)%></p>
                                <p class="card-text index-card-sub-text"><%=bigRrId%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("BPM_SMALLER", Language)%> (bpm)</h5>
                                <p class="card-text index-card-text"><%=decimalFormat.format(lowBpmValue).replace(".", numberDecimalSymbol)%></p>
                                <p class="card-text index-card-sub-text"><%=lowBpmId%></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-6" style="margin-bottom:10px;">
                        <div class="card text-center index-card">
                            <div class="card-body index-card-body">
                                <h5 class="card-title index-card-title"><%=I18nUtil.i18n("RESPIRATORY_RATE_SMALLER", Language)%> (Hz)</h5>
                                <p class="card-text index-card-text"><%=decimalFormat.format(lowRrValue).replace(".", numberDecimalSymbol)%></p>
                                <p class="card-text index-card-sub-text"><%=lowRrId%></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/Component/common.html" %>
    </body>
</html>