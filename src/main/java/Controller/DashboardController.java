package Controller;

import CSV.I18nCSV;
import DTO.FilterDTO;
import DTO.UserDTO;
import Util.CsvUtil;
import Util.DashboardUtil;
import Util.I18nUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/Dashboard"})
public class DashboardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher page;
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("a");
        
        if (action == null)
            action = "";
        
        String Path = getServletContext().getRealPath("/") + "CSV/";
        session.setAttribute("csvdata", CsvUtil.getData(Path));
        
        switch(action) {
            case "show":
                page = getServletContext().getRequestDispatcher("/dashboard.jsp");
                page.forward(request, response);
                break;
            
            case "deletefilter":
                Integer id = Integer.parseInt((String) request.getParameter("id"));
                List<FilterDTO> filterList = (List<FilterDTO>) session.getAttribute("filterlist");
                if (filterList != null) if(!filterList.isEmpty())
                    filterList.remove(filterList.get(id));
                session.setAttribute("filterlist", filterList);
                
                page = getServletContext().getRequestDispatcher("/dashboard.jsp");
                page.forward(request, response);
                break;
                
            default:
                page = getServletContext().getRequestDispatcher("/index.jsp");
                page.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher page;
        List<FilterDTO> filterList;
        String message = "";
        
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("a");

        UserDTO user = (UserDTO) session.getAttribute("user");
        Integer languageId = I18nCSV.EN_US;
        if (user != null)
            languageId = user.getLanguage();
        
        String Path = getServletContext().getRealPath("/") + "CSV/";
        session.setAttribute("csvdata", CsvUtil.getData(Path));
        
        if (action == null)
            action = "";
        
        switch(action) {
            case "new":
                String filterType = request.getParameter("filter-type");
                List<String> participantsList = null;
                List<String> waysList = null;
                String[] participantsArray;
                String[] waysArray;
                String sex = request.getParameter("sex");
                String height = (String) request.getParameter("height");
                String heightOperator = (String) request.getParameter("height-operator");
                String weight = (String) request.getParameter("weight");
                String weightOperator = (String) request.getParameter("weight-operator");
                String age = (String) request.getParameter("age");
                String ageOperator = (String) request.getParameter("age-operator");
                String bpm = (String) request.getParameter("bpm");
                String bpmOperator = (String) request.getParameter("bpm-operator");
                String height_2 = (String) request.getParameter("height-2");
                String heightOperator_2 = (String) request.getParameter("height-operator-2");
                String weight_2 = (String) request.getParameter("weight-2");
                String weightOperator_2 = (String) request.getParameter("weight-operator-2");
                String age_2 = (String) request.getParameter("age-2");
                String ageOperator_2 = (String) request.getParameter("age-operator-2");
                String bpmType = (String) request.getParameter("bpm-type");
                String respiratoryrate = (String) request.getParameter("respiratoryrate");
                String respiratoryrateOperator = (String) request.getParameter("respiratoryrate-operator");
                String respiratoryrateType = (String) request.getParameter("respiratoryrate-type");
                String positionType = (String) request.getParameter("position-type");
                String bmi = (String) request.getParameter("bmi");
                String bmiOperator = (String) request.getParameter("bmi-operator");
                String bmi_2 = (String) request.getParameter("bmi-2");
                String bmiOperator_2 = (String) request.getParameter("bmi-operator-2");
                
                participantsArray = (String[]) request.getParameterValues("participants");
                waysArray = (String[]) request.getParameterValues("ways");
                
                if (participantsArray != null)
                    participantsList = Arrays.asList(participantsArray);
                
                if (waysArray != null)
                    waysList = Arrays.asList(waysArray);
                
                if (filterType.equals("")) {
                    message = I18nUtil.i18n("FIELD_NULL_FILTER_TYPE", languageId);
                }
                
                if (heightOperator.equals("") || weightOperator.equals("") ||
                        ageOperator.equals("") || heightOperator_2.equals("") ||
                        weightOperator_2.equals("") || ageOperator_2.equals("") ||
                        bpmOperator.equals("") || bpmType.equals("") ||
                        respiratoryrateOperator.equals("") || respiratoryrateType.equals("") ||
                        bmiOperator.equals("") || bmiOperator_2.equals("")) {
                    message = I18nUtil.i18n("FIELD_NULL_OPERATOR", languageId);
                }
                
                if (message.contentEquals("")) {
                    filterList = (List<FilterDTO>) session.getAttribute("filterlist");
                    
                    if (filterList == null)
                        filterList = new ArrayList<>();
                    
                    FilterDTO newFilter = new FilterDTO();
                    newFilter.setId(DashboardUtil.getNextId(filterList));
                    newFilter.setTag(DashboardUtil.getNextTag(filterList));
                    newFilter.setType(Integer.parseInt(filterType));
                    newFilter.setHeightOperator(Integer.parseInt(heightOperator));
                    newFilter.setWeightOperator(Integer.parseInt(weightOperator));
                    newFilter.setAgeOperator(Integer.parseInt(ageOperator));
                    newFilter.setHeightOperator_2(Integer.parseInt(heightOperator_2));
                    newFilter.setWeightOperator_2(Integer.parseInt(weightOperator_2));
                    newFilter.setAgeOperator_2(Integer.parseInt(ageOperator_2));
                    newFilter.setBpmOperator(Integer.parseInt(bpmOperator));
                    newFilter.setBpmType(Integer.parseInt(bpmType));
                    newFilter.setRespiratoryRateOperator(Integer.parseInt(respiratoryrateOperator));
                    newFilter.setRespiratoryRateType(Integer.parseInt(respiratoryrateType));
                    newFilter.setBmiOperator(Integer.parseInt(bmiOperator));
                    newFilter.setBmiOperator_2(Integer.parseInt(bmiOperator_2));
                    
                    if (!sex.contentEquals("")) {
                        try {
                            Integer intSex = Integer.parseInt(sex);
                            newFilter.setAssignedSex(intSex);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NULL_ASSIGNED_SEX", languageId);
                        }
                    }
                    
                    if (!height.contentEquals("")) {
                        try {
                            Integer intHeight = Integer.parseInt(height);
                            newFilter.setHeight(intHeight);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_HEIGHT", languageId);
                        }
                    }
                    
                    if (!height_2.contentEquals("")) {
                        try {
                            Integer intHeight_2 = Integer.parseInt(height_2);
                            newFilter.setHeight_2(intHeight_2);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_HEIGHT", languageId);
                        }
                    }
                    
                    if (!weight.contentEquals("")) {
                        try {
                            Integer intWeight = Integer.parseInt(weight);
                            newFilter.setWeight(intWeight);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_WEIGHT", languageId);
                        }
                    }
                    
                    if (!weight_2.contentEquals("")) {
                        try {
                            Integer intWeight_2 = Integer.parseInt(weight_2);
                            newFilter.setWeight_2(intWeight_2);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_WEIGHT", languageId);
                        }
                    }
                    
                    if (!age.contentEquals("")) {
                        try {
                            Integer intAge = Integer.parseInt(age);
                            newFilter.setAge(intAge);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_AGE", languageId);
                        }
                    }
                    
                    if (!age_2.contentEquals("")) {
                        try {
                            Integer intAge_2 = Integer.parseInt(age_2);
                            newFilter.setAge_2(intAge_2);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_AGE", languageId);
                        }
                    }
                    
                    if (!bpm.contentEquals("")) {
                        try {
                            Integer intBpm = Integer.parseInt(bpm);
                            newFilter.setBpm(intBpm);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_BPM", languageId);
                        }
                    }
                    
                    if (!respiratoryrate.contentEquals("")) {
                        try {
                            Integer intRespiratoryRate = Integer.parseInt(respiratoryrate);
                            newFilter.setRespiratoryRate(intRespiratoryRate);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_RESPIRATORY_RATE", languageId);
                        }
                    }
                    
                    if (!bmi.contentEquals("")) {
                        try {
                            Integer intBmi = Integer.parseInt(bmi);
                            newFilter.setBmi(intBmi);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_BODY_MASS_INDEX", languageId);
                        }
                    }
                    
                    if (!bmi_2.contentEquals("")) {
                        try {
                            Integer intBmi_2 = Integer.parseInt(bmi_2);
                            newFilter.setBmi_2(intBmi_2);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_BODY_MASS_INDEX", languageId);
                        }
                    }
                    
                    if (!positionType.contentEquals("")) {
                        try {
                            Integer intPositionType = Integer.parseInt(positionType);
                            newFilter.setPositionType(intPositionType);
                        } catch (NumberFormatException e) {
                            message = I18nUtil.i18n("FIELD_NAN_POSITION_TYPE", languageId);
                        }
                    }
                    
                    if (participantsList != null) {
                        List<Integer> intParticipantsList = new ArrayList<>();
                        for (String s : participantsList) intParticipantsList.add(Integer.valueOf(s));
                        newFilter.setParticipants(intParticipantsList);
                    }
                    
                    if (waysList != null) {
                        List<Integer> intWayList = new ArrayList<>();
                        for (String s : waysList) intWayList.add(Integer.valueOf(s));
                        newFilter.setWays(intWayList);
                    }
                    
                    if (!message.contentEquals("")) {
                        session.setAttribute("error", 1);
                        session.setAttribute("message", message);
                    }
                    
                    filterList.add(newFilter);
                    session.setAttribute("filterlist", filterList);
                    response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                } else {
                    session.setAttribute("error", 1);
                    session.setAttribute("message", message);
                    response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                }
                
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                break;
        }
    }
}