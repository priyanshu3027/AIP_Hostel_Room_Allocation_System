package com.hostel.dao;

import com.hostel.model.Application;
import com.hostel.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    
    public boolean submitApplication(Application application) {
        String sql = "INSERT INTO applications (student_id, room_id, status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, application.getStudentId());
            pstmt.setInt(2, application.getRoomId());
            pstmt.setString(3, application.getStatus());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Application> getApplicationsByStudent(int studentId) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE student_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Application app = new Application();
                app.setAppId(rs.getInt("app_id"));
                app.setStudentId(rs.getInt("student_id"));
                app.setRoomId(rs.getInt("room_id"));
                app.setStatus(rs.getString("status"));
                applications.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }
    
    public List<Application> getAllApplications() {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Application app = new Application();
                app.setAppId(rs.getInt("app_id"));
                app.setStudentId(rs.getInt("student_id"));
                app.setRoomId(rs.getInt("room_id"));
                app.setStatus(rs.getString("status"));
                applications.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }
    
    public boolean updateApplicationStatus(int appId, String status) {
        String sql = "UPDATE applications SET status = ? WHERE app_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, appId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
} 