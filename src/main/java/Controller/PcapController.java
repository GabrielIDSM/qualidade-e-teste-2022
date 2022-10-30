package Controller;

import CSV.I18nCSV;
import DTO.UserDTO;
import Util.CsvUtil;
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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
@MultipartConfig 
@WebServlet(name = "Pcap", urlPatterns = {"/Pcap"})
public class PcapController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ArrayList<String> files;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher page;
        String action = (String) request.getParameter("a");
        
        String Path = getServletContext().getRealPath("/") + "CSV/";
        request.setAttribute("csvdata", CsvUtil.getData(Path));
        
        if (action == null)
            action = "";
        
        switch(action) {
            case "create":
                page = getServletContext().getRequestDispatcher("/newpcap.jsp");
                page.forward(request, response);
                break;
            
            case "delete":
                String fileName = (String) request.getParameter("id");
                File file = new File(getServletContext()
                        .getRealPath("/") + "PCAP/" + fileName);
                file.delete();
                
                updateFileList();
                request.setAttribute("filelist", files);
                
                page = getServletContext().getRequestDispatcher("/pcap.jsp");
                page.forward(request, response);
                break;
                
            case "list":
                updateFileList();
                request.setAttribute("filelist", files);
                
                page = getServletContext().getRequestDispatcher("/pcap.jsp");
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

        String message = "";
        RequestDispatcher page;
        
        UserDTO user = (UserDTO) session.getAttribute("user");
        Integer languageId = I18nCSV.EN_US;
        if (user != null)
            languageId = user.getLanguage();
        
        String action = (String) request.getParameter("a");
        if (action == null)
            action = "";
        
        if (request.getParts() == null)
            message = I18nUtil.i18n("FIELD_NULL_FILES", languageId);
        else if (request.getParts().isEmpty())
            message = I18nUtil.i18n("FIELD_NULL_FILES", languageId);
            
        if (message.equals("")) {
            switch(action) {
                case "pcap":
                    if (!request.getParts().isEmpty()) {
                        Stream<Part> fileStreamParts = request.getParts().stream();
                        List<Part> fileParts = fileStreamParts.toList();

                        for (Part filePart : fileParts) {
                            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                            InputStream fileContent = filePart.getInputStream();
                            File targetFile = new File(getServletContext()
                                .getRealPath("/") + "PCAP/" + fileName);
                            OutputStream outStream = new FileOutputStream(targetFile);

                            byte[] buffer = new byte[8 * 1024];
                            int bytesRead;
                            while ((bytesRead = fileContent.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                        }
                        
                        try {
                            Collection<Part> parts = request.getParts();
                            for (Part part : parts) {
                                    part.write(getServletContext()
                                            .getRealPath("/") + "/PCAP" +
                                            part.getSubmittedFileName());
                            }
                        } catch (ServletException | IOException e) {
                            message = e.getMessage();
                        }
                    } else {  
                        message = "Not Multipart";  
                    }
                        
                    if (!message.equals("")) {
                        request.setAttribute("message", message);
                        System.err.println(message);
                    }
                    
                    updateFileList();
                    request.setAttribute("filelist", files);
                    
                    page = getServletContext().getRequestDispatcher("/pcap.jsp");
                    page.forward(request, response);
                    break;
                default:
                    request.setAttribute("message", message);
                    updateFileList();
                    request.setAttribute("filelist", files);

                    page = getServletContext().getRequestDispatcher("/pcap.jsp");
                    page.forward(request, response);
            }
        } else {
            request.setAttribute("message", message);
            updateFileList();
            request.setAttribute("filelist", files);

            page = getServletContext().getRequestDispatcher("/pcap.jsp");
            page.forward(request, response);
        }
    }
    
    private Boolean updateFileList() {
        File directory = new File(getServletContext().getRealPath("/") + "PCAP/");
        if (! directory.exists())
            directory.mkdir();
        File folder = new File(getServletContext().getRealPath("/") + "PCAP/");
        File[] listOfFiles = folder.listFiles();
        files = new ArrayList<>();

        if (!(listOfFiles == null))
            if (listOfFiles.length > 0)
                for (File listOfFile : listOfFiles)
                    if (listOfFile.isFile())
                        files.add(listOfFile.getName());
        
        Collections.sort(files);   
        
        return Boolean.TRUE;
    }
}