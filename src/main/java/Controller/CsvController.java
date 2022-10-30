package Controller;

import CSV.I18nCSV;
import DTO.UserDTO;
import Util.I18nUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
@MultipartConfig 
@WebServlet(name = "Csv", urlPatterns = {"/Csv"})
public class CsvController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher page;
        String action = (String) request.getParameter("a");
        
        if (action == null)
            action = "";
        
        switch(action) {
            case "bpm":
            case "respiratoryrate":
            case "participants":
                request.setAttribute("csvfile", action);
                
                page = getServletContext().getRequestDispatcher("/updatecsv.jsp");
                page.forward(request, response);
                break;
            case "list":
                page = getServletContext().getRequestDispatcher("/csv.jsp");
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
        
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        Integer languageId = I18nCSV.EN_US;
        if (user != null)
            languageId = user.getLanguage();
        
        File directory = new File(getServletContext().getRealPath("/") + 
                "CSV/");
        if (! directory.exists())
            directory.mkdir();
        
        String message = "";
        RequestDispatcher page;
        
        String action = (String) request.getParameter("a");
        if (action == null)
            action = "";
        
        if (request.getPart("file") == null)
            message = I18nUtil.i18n("FIELD_NULL_FILE", languageId);

        if (message.equals("")) {
            switch(action) {
                case "bpm":
                case "respiratoryrate":
                case "participants":
                    try {
                        Part filePart = request.getPart("file");
                        InputStream fileContent = filePart.getInputStream();

                        File targetFile = new File(getServletContext()
                                .getRealPath("/") + "CSV/" + action + ".csv");
                        OutputStream outStream = new FileOutputStream(targetFile);

                        byte[] buffer = new byte[8 * 1024];
                        int bytesRead;
                        while ((bytesRead = fileContent.read(buffer)) != -1) {
                            outStream.write(buffer, 0, bytesRead);
                        }
                        IOUtils.closeQuietly(fileContent);
                        IOUtils.closeQuietly(outStream);
                    } catch (IOException | ServletException e) {
                        message = e.getMessage();
                    } finally {
                        if (!message.equals(""))
                            request.setAttribute("message", message);
                    }
                    
                    page = getServletContext().getRequestDispatcher("/csv.jsp");
                    page.forward(request, response);
                    break;
                default:
                    request.setAttribute("message", message);

                    page = getServletContext().getRequestDispatcher("/csv.jsp");
                    page.forward(request, response);
            }
        } else {
            request.setAttribute("message", message);

            page = getServletContext().getRequestDispatcher("/csv.jsp");
            page.forward(request, response);
        }
    }
}