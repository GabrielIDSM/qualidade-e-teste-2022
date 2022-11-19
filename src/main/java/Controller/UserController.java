package Controller;

import CSV.I18nCSV;
import DTO.RoleDTO;
import DTO.UserDTO;
import Repository.RoleRepository;
import Repository.UserRepository;
import Util.I18nUtil;
import Util.PasswordUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
@WebServlet("/User")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher page;
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("a");
        UserRepository userRepository;
        List<UserDTO> users;
        UserDTO user;
        Integer Id;
        
        if (action == null)
            action = "";
        
        switch(action) {
            case "login":
                String pageRef;
                if (session.getAttribute("user") != null) {
                    user = (UserDTO) session.getAttribute("user");
                    session.setAttribute("user", user);
                    pageRef = "/index.jsp";
                } else {
                    pageRef = "/login.jsp";
                }
                page = getServletContext().getRequestDispatcher(pageRef);
                page.forward(request, response);
                break;
            case "logout":
                session.invalidate();
                page = getServletContext().getRequestDispatcher("/index.jsp");
                page.forward(request, response);
                break;
            case "list":
                userRepository = new UserRepository();
                users = userRepository.all();
                request.setAttribute("users", users);
                page = getServletContext().getRequestDispatcher("/users.jsp");
                page.forward(request, response);
                break;
            case "create":
                user = new UserDTO();
                request.setAttribute("u", user);
                page = getServletContext().getRequestDispatcher("/newuser.jsp");
                page.forward(request, response);
                break;
            case "update":
                userRepository = new UserRepository();
                Id = Integer.parseInt(request.getParameter("id"));
                if (Id == 0) {
                    user = new UserDTO();
                    user.setRole(new RoleDTO());
                } else {
                    user = userRepository.getUser(Id);
                }
                request.setAttribute("u", user);
                page = getServletContext().getRequestDispatcher("/updateuser.jsp");
                page.forward(request, response);
                break;
            case "delete":
                if (session.getAttribute("user") != null) {
                    user = (UserDTO) session.getAttribute("user");
                    Id = Integer.parseInt(request.getParameter("id"));
                    if (!Objects.equals(Id, user.getId())) {
                        userRepository = new UserRepository();
                        userRepository.deleteUser(Id);
                    }  
                }
                userRepository = new UserRepository();
                users = userRepository.all();
                request.setAttribute("users", users);
                page = getServletContext().getRequestDispatcher("/users.jsp");
                page.forward(request, response);
            case "password":
                page = getServletContext().getRequestDispatcher("/newpassword.jsp");
                page.forward(request, response);
                break;
            case "settings":
                page = getServletContext().getRequestDispatcher("/settings.jsp");
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
        RequestDispatcher page;
        RoleRepository roleRepository;
        UserRepository userRepository;
        List<UserDTO> users;
        String action = (String) request.getParameter("a");
        String message = "";
        
        UserDTO user = (UserDTO) session.getAttribute("user");
        Integer languageId = I18nCSV.EN_US;
        if (user != null)
            languageId = user.getLanguage();
        
        if (action == null)
            action = "";
        try {
            switch(action) {
                case "submit":
                    String a;
                    if (request.getParameter("id") == null) 
                        a = "create"; 
                    else
                        a = "update";
                    
                    switch(a) {
                        case "create":
                            if (request.getParameter("username").equals(""))
                                message = I18nUtil.i18n("FIELD_NULL_USERNAME", languageId);

                            if (request.getParameter("role").equals(""))
                                message = I18nUtil.i18n("FIELD_NULL_ROLE", languageId);

                            if (request.getParameter("password").equals(""))
                                message = I18nUtil.i18n("FIELD_NULL_PASSWORD", languageId);

                            if (request.getParameter("language").equals(""))
                                message = I18nUtil.i18n("FIELD_NULL_LANGUAGE", languageId);
                            
                            if (!PasswordUtil.isValidPassword(request.getParameter("password")))
                                message = I18nUtil.i18n("FIELD_INVALID_PASSWORD", languageId);
                            
                            if (message.equals("")) {
                                String username = request.getParameter("username");
                                String password = request.getParameter("password");
                                String role = request.getParameter("role");
                                String language = request.getParameter("language");
                                
                                roleRepository = new RoleRepository();
                                RoleDTO r;
                                switch (role) {
                                    case "Administrator":
                                        r = roleRepository.getRole(role);
                                        break;
                                    case "Operator":
                                        r = roleRepository.getRole(role);
                                        break;
                                    default:
                                        r = new RoleDTO();
                                        break;
                                }
                                
                                userRepository = new UserRepository();
                                UserDTO u = new UserDTO();
                                u.setUsername(username);
                                u.setPassword(password);
                                u.setForceUpdate(Boolean.TRUE);
                                u.setLanguage(Integer.parseInt(language));
                                u.setRole(r);
                                
                                if (userRepository.createUser(u)) {
                                    userRepository = new UserRepository();
                                    users = userRepository.all();
                                    request.setAttribute("users", users);
                                    page = getServletContext().getRequestDispatcher("/users.jsp");
                                    page.forward(request, response);
                                } else {
                                    message = I18nUtil.i18n("USER_CREATE_FAILED", languageId);
                                    request.setAttribute("error", 1);
                                    request.setAttribute("message", message);
                                    page = getServletContext().getRequestDispatcher("/users.jsp");
                                    page.forward(request, response);
                                }
                            } else {
                                request.setAttribute("error", 1);
                                request.setAttribute("message", message);
                                page = getServletContext().getRequestDispatcher("/users.jsp");
                                page.forward(request, response);
                            }
                            
                            break;
                        case "update":
                            if (request.getParameter("username").equals(""))
                                message = I18nUtil.i18n("FIELD_NULL_USERNAME", languageId);

                            if (request.getParameter("role").equals(""))
                                message = I18nUtil.i18n("FIELD_NULL_ROLE", languageId);

                            if (request.getParameter("language").equals(""))
                                message = I18nUtil.i18n("FIELD_NULL_LANGUAGE", languageId);

                            if (request.getParameter("fpassword").equals(""))
                                message = I18nUtil.i18n("FIELD_NULL_FORCE_PASSWORD_EXPIRED", languageId);
                            
                            user = (UserDTO) session.getAttribute("user");
                            if (Integer.parseInt(request.getParameter("id")) == user.getId())
                                message = I18nUtil.i18n("INVALID_OPERATION", languageId);
                            
                            if (message.equals("")) {
                                String username = request.getParameter("username");
                                String fpassword = request.getParameter("fpassword");
                                String role = request.getParameter("role");
                                String language = request.getParameter("language");
                                
                                roleRepository = new RoleRepository();
                                RoleDTO r;
                                switch (role) {
                                    case "Administrator":
                                        r = roleRepository.getRole(role);
                                        break;
                                    case "Operator":
                                        r = roleRepository.getRole(role);
                                        break;
                                    default:
                                        r = new RoleDTO();
                                        break;
                                }
                                
                                Boolean bpassword;
                                switch (fpassword) {
                                    case "1":
                                        bpassword = Boolean.TRUE;
                                        break;
                                    case "0":
                                        bpassword = Boolean.FALSE;
                                        break;
                                    default:
                                        bpassword = Boolean.FALSE;
                                        break;
                                }
                                
                                userRepository = new UserRepository();
                                UserDTO u = userRepository.getUser(Integer.parseInt(request.getParameter("id")));
                                u.setUsername(username);
                                u.setForceUpdate(bpassword);
                                u.setLanguage(Integer.parseInt(language));
                                u.setRole(r);
                                
                                if (userRepository.updateUser(u)) {
                                    userRepository = new UserRepository();
                                    users = userRepository.all();
                                    request.setAttribute("users", users);
                                    page = getServletContext().getRequestDispatcher("/users.jsp");
                                    page.forward(request, response);
                                } else {
                                    message = I18nUtil.i18n("USER_UPDATE_FAILED", languageId);
                                    request.setAttribute("error", 1);
                                    request.setAttribute("message", message);
                                    page = getServletContext().getRequestDispatcher("/users.jsp");
                                    page.forward(request, response);
                                }
                            } else {
                                request.setAttribute("error", 1);
                                request.setAttribute("message", message);
                                page = getServletContext().getRequestDispatcher("/users.jsp");
                                page.forward(request, response);
                            }
                            
                            break;
                        default:
                            page = getServletContext().getRequestDispatcher("/users.jsp");
                            page.forward(request, response);
                            break;
                    }
                    
                    break;
                case "login":
                    if (request.getParameter("username") == null) {
                        message = I18nUtil.i18n("FIELD_NULL_USERNAME", languageId);
                        request.setAttribute("error", 1);
                    } else {
                        if (request.getParameter("username").equals("")) {
                            message = I18nUtil.i18n("FIELD_NULL_USERNAME", languageId);
                            request.setAttribute("error", 1);
                        }
                    }

                    if (request.getParameter("password") == null) {
                        message = I18nUtil.i18n("FIELD_NULL_PASSWORD", languageId);
                        request.setAttribute("error", 1);
                    } else {
                        if (request.getParameter("password").equals("")) {
                            message = I18nUtil.i18n("FIELD_NULL_PASSWORD", languageId);
                            request.setAttribute("error", 1);
                        }
                    }

                    if (message.equals("")) {
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        System.err.printf("username: %s", username);
                        System.err.printf("password: %s", password);

                        userRepository = new UserRepository();
                        user = userRepository.login(username, password);

                        if (user != null) {
                            session.setAttribute("user", user);
                            response.sendRedirect(request.getContextPath() + "/index.jsp");
                        } else {
                            message = I18nUtil.i18n("USER_NOT_FOUND", languageId);
                            request.setAttribute("error", 1);  
                            request.setAttribute("message", message);
                            response.sendRedirect(request.getContextPath() + "/login.jsp");
                        }
                    } else {
                        request.setAttribute("error", 1);
                        request.setAttribute("message", message);
                        response.sendRedirect(request.getContextPath() + "/login.jsp");
                    }
                    
                    break;
                case "password":
                    if (request.getParameter("id").equals(""))
                        message = I18nUtil.i18n("INTERNAL_ERROR", languageId);
                    
                    if (request.getParameter("password").equals(""))
                        message = I18nUtil.i18n("FIELD_NULL_PASSWORD", languageId);
                    
                    if (!PasswordUtil.isValidPassword(request.getParameter("password")))
                        message = I18nUtil.i18n("FIELD_INVALID_PASSWORD", languageId);
                    
                    if (message.equals("")) {
                        String password = request.getParameter("password");

                        userRepository = new UserRepository();
                        user = (UserDTO) session.getAttribute("user");
                        user.setPassword(password);
                        user.setForceUpdate(Boolean.FALSE);

                        if (userRepository.updateUserPassword(user)) {
                            user = userRepository.getUser(user.getId());
                            session.setAttribute("user", user);
                            response.sendRedirect(request.getContextPath() + "/index.jsp");
                        } else {
                            message = I18nUtil.i18n("PASSWORD_UPDATE_FAILED", languageId);
                            request.setAttribute("error", 1);
                            request.setAttribute("message", message);
                            page = getServletContext().getRequestDispatcher("/newpassword.jsp");
                            page.forward(request, response);
                        }
                    } else {
                        request.setAttribute("error", 1);
                        request.setAttribute("message", message);
                        page = getServletContext().getRequestDispatcher("/newpassword.jsp");
                        page.forward(request, response);
                    }
                    break;
                case "settings":
                    if (request.getParameter("id").equals(""))
                        message = I18nUtil.i18n("INTERNAL_ERROR", languageId);
                    
                    if (request.getParameter("language").equals(""))
                        message = I18nUtil.i18n("FIELD_NULL_LANGUAGE", languageId);
                    
                    if (message.equals("")) {
                        String language = request.getParameter("language");

                        userRepository = new UserRepository();
                        user = (UserDTO) session.getAttribute("user");
                        user.setLanguage(Integer.parseInt(language));

                        if (userRepository.updateUser(user)) {
                            user = userRepository.getUser(user.getId());
                            session.setAttribute("user", user);
                            response.sendRedirect(request.getContextPath() + "/index.jsp");
                        } else {
                            message = I18nUtil.i18n("PASSWORD_UPDATE_FAILED", languageId);
                            request.setAttribute("error", 1);
                            request.setAttribute("message", message);
                            page = getServletContext().getRequestDispatcher("/settings.jsp");
                            page.forward(request, response);
                        }
                    } else {
                        request.setAttribute("error", 1);
                        request.setAttribute("message", message);
                        page = getServletContext().getRequestDispatcher("/settings.jsp");
                        page.forward(request, response);
                    }
                    break;
                default:
                    page = getServletContext().getRequestDispatcher("/index.jsp");
                    page.forward(request, response);
            }
        } catch(IOException | NumberFormatException | ServletException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}