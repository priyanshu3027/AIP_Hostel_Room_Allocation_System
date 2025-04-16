<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hostel.model.User" %>
<%@ page import="com.hostel.model.Application" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hostel Management System - Applications</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#"><i class="fas fa-building me-2"></i>Hostel Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="dashboard.jsp"><i class="fas fa-home me-1"></i>Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="applications.jsp"><i class="fas fa-file-alt me-1"></i>Applications</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="logout"><i class="fas fa-sign-out-alt me-1"></i>Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4 fade-in">
        <div class="row mb-4">
            <div class="col">
                <h2 class="mb-3"><i class="fas fa-file-alt me-2"></i>Room Applications</h2>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="dashboard.jsp">Dashboard</a></li>
                        <li class="breadcrumb-item active">Applications</li>
                    </ol>
                </nav>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-3">
                <div class="card bg-primary text-white">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-clock me-2"></i>Pending</h5>
                        <h2 class="mb-0">
                            <% 
                            int pendingCount = 0;
                            if (request.getAttribute("applications") != null) {
                                List<Application> apps = (List<Application>) request.getAttribute("applications");
                                for (Application app : apps) {
                                    if ("PENDING".equals(app.getStatus())) {
                                        pendingCount++;
                                    }
                                }
                            }
                            %>
                            <%= pendingCount %>
                        </h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-success text-white">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-check me-2"></i>Approved</h5>
                        <h2 class="mb-0">
                            <% 
                            int approvedCount = 0;
                            if (request.getAttribute("applications") != null) {
                                List<Application> apps = (List<Application>) request.getAttribute("applications");
                                for (Application app : apps) {
                                    if ("APPROVED".equals(app.getStatus())) {
                                        approvedCount++;
                                    }
                                }
                            }
                            %>
                            <%= approvedCount %>
                        </h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-danger text-white">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-times me-2"></i>Rejected</h5>
                        <h2 class="mb-0">
                            <% 
                            int rejectedCount = 0;
                            if (request.getAttribute("applications") != null) {
                                List<Application> apps = (List<Application>) request.getAttribute("applications");
                                for (Application app : apps) {
                                    if ("REJECTED".equals(app.getStatus())) {
                                        rejectedCount++;
                                    }
                                }
                            }
                            %>
                            <%= rejectedCount %>
                        </h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-info text-white">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-file-alt me-2"></i>Total</h5>
                        <h2 class="mb-0">
                            <% 
                            int totalCount = 0;
                            if (request.getAttribute("applications") != null) {
                                List<Application> apps = (List<Application>) request.getAttribute("applications");
                                totalCount = apps.size();
                            }
                            %>
                            <%= totalCount %>
                        </h2>
                    </div>
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5 class="mb-0"><i class="fas fa-list me-2"></i>Application List</h5>
                <div class="btn-group">
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="filterApplications('all')">All</button>
                    <button type="button" class="btn btn-outline-warning btn-sm" onclick="filterApplications('pending')">Pending</button>
                    <button type="button" class="btn btn-outline-success btn-sm" onclick="filterApplications('approved')">Approved</button>
                    <button type="button" class="btn btn-outline-danger btn-sm" onclick="filterApplications('rejected')">Rejected</button>
                </div>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Application ID</th>
                                <th>Student ID</th>
                                <th>Room ID</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                            List<Application> applications = (List<Application>) request.getAttribute("applications");
                            if (applications != null && !applications.isEmpty()) {
                                for (Application app : applications) {
                            %>
                                <tr class="application-row" data-status="<%= app.getStatus().toLowerCase() %>">
                                    <td>#<%= app.getAppId() %></td>
                                    <td><%= app.getStudentId() %></td>
                                    <td><%= app.getRoomId() %></td>
                                    <td>
                                        <span class="badge <%= 
                                            "PENDING".equals(app.getStatus()) ? "badge-pending" : 
                                            "APPROVED".equals(app.getStatus()) ? "badge-approved" : "badge-rejected" 
                                        %>">
                                            <%= app.getStatus() %>
                                        </span>
                                    </td>
                                    <td>
                                        <% if ("PENDING".equals(app.getStatus())) { %>
                                            <div class="btn-group">
                                                <form action="application" method="post" style="display: inline;">
                                                    <input type="hidden" name="action" value="update">
                                                    <input type="hidden" name="appId" value="<%= app.getAppId() %>">
                                                    <input type="hidden" name="status" value="APPROVED">
                                                    <button type="submit" class="btn btn-success btn-sm">
                                                        <i class="fas fa-check me-1"></i>Approve
                                                    </button>
                                                </form>
                                                <form action="application" method="post" style="display: inline;">
                                                    <input type="hidden" name="action" value="update">
                                                    <input type="hidden" name="appId" value="<%= app.getAppId() %>">
                                                    <input type="hidden" name="status" value="REJECTED">
                                                    <button type="submit" class="btn btn-danger btn-sm">
                                                        <i class="fas fa-times me-1"></i>Reject
                                                    </button>
                                                </form>
                                            </div>
                                        <% } else { %>
                                            <button class="btn btn-secondary btn-sm" disabled>
                                                <i class="fas fa-lock me-1"></i>Locked
                                            </button>
                                        <% } %>
                                    </td>
                                </tr>
                            <% 
                                }
                            } else {
                            %>
                                <tr>
                                    <td colspan="5" class="text-center py-4">
                                        <i class="fas fa-inbox fa-3x text-muted mb-3"></i>
                                        <p class="text-muted mb-0">No applications found</p>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function filterApplications(status) {
            const rows = document.querySelectorAll('.application-row');
            rows.forEach(row => {
                if (status === 'all' || row.dataset.status === status) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        }
    </script>
</body>
</html> 