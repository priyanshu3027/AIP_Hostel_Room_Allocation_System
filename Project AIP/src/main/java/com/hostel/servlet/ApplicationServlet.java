package com.hostel.servlet;

import com.hostel.dao.ApplicationDAO;
import com.hostel.dao.RoomDAO;
import com.hostel.model.Application;
import com.hostel.model.Room;
import com.hostel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/application/*")
public class ApplicationServlet extends HttpServlet {
    private ApplicationDAO applicationDAO;
    private RoomDAO roomDAO;

    public void init() {
        applicationDAO = new ApplicationDAO();
        roomDAO = new RoomDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        if (pathInfo == null || pathInfo.equals("/")) {
            if ("ADMIN".equals(user.getRole())) {
                List<Application> applications = applicationDAO.getAllApplications();
                request.setAttribute("applications", applications);
                request.getRequestDispatcher("/admin/applications.jsp").forward(request, response);
            } else {
                List<Application> applications = applicationDAO.getApplicationsByStudent(user.getId());
                request.setAttribute("applications", applications);
                request.getRequestDispatcher("/student/applications.jsp").forward(request, response);
            }
        } else if ("/new".equals(pathInfo)) {
            List<Room> availableRooms = roomDAO.getAvailableRooms();
            request.setAttribute("rooms", availableRooms);
            request.getRequestDispatcher("/student/new-application.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        if ("submit".equals(action)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            
            Application application = new Application();
            application.setStudentId(user.getId());
            application.setRoomId(roomId);
            application.setStatus("PENDING");
            
            if (applicationDAO.submitApplication(application)) {
                response.sendRedirect(request.getContextPath() + "/application");
            } else {
                request.setAttribute("error", "Failed to submit application");
                request.getRequestDispatcher("/student/new-application.jsp").forward(request, response);
            }
        } else if ("update".equals(action) && "ADMIN".equals(user.getRole())) {
            int appId = Integer.parseInt(request.getParameter("appId"));
            String status = request.getParameter("status");
            
            if (applicationDAO.updateApplicationStatus(appId, status)) {
                response.sendRedirect(request.getContextPath() + "/application");
            } else {
                request.setAttribute("error", "Failed to update application status");
                request.getRequestDispatcher("/admin/applications.jsp").forward(request, response);
            }
        }
    }
} 