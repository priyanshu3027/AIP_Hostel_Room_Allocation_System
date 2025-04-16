package com.hostel.servlet;

import com.hostel.dao.RoomDAO;
import com.hostel.model.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/rooms/*")
public class RoomManagementServlet extends HttpServlet {
    private RoomDAO roomDAO;

    public void init() {
        roomDAO = new RoomDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Room> rooms = roomDAO.getAllRooms();
            request.setAttribute("rooms", rooms);
            request.getRequestDispatcher("/admin/rooms.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String roomNumber = request.getParameter("roomNumber");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            
            Room room = new Room();
            room.setRoomNumber(roomNumber);
            room.setCapacity(capacity);
            room.setAvailable(true);
            
            if (roomDAO.addRoom(room)) {
                response.sendRedirect(request.getContextPath() + "/admin/rooms");
            } else {
                request.setAttribute("error", "Failed to add room");
                request.getRequestDispatcher("/admin/rooms.jsp").forward(request, response);
            }
        } else if ("update".equals(action)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            boolean available = Boolean.parseBoolean(request.getParameter("available"));
            
            if (roomDAO.updateRoomAvailability(roomId, available)) {
                response.sendRedirect(request.getContextPath() + "/admin/rooms");
            } else {
                request.setAttribute("error", "Failed to update room status");
                request.getRequestDispatcher("/admin/rooms.jsp").forward(request, response);
            }
        }
    }
} 