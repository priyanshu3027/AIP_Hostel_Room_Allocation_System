# AIP_Hostel_Room_Allocation_System
1. Problem Statement
In many educational institutions, managing hostel room allocation manually leads to inefficiencies, errors, time-consuming, and lack of transparency. Students often face difficulties applying for rooms, tracking their application status, and knowing the availability of accommodations. On the administrative side, manual record-keeping is prone to errors and delays.
Solution: The Hostel Room Allocation System is a web-based Java application developed using Advanced Internet Programming concepts. It streamlines hostel management by offering user registration, secure login, role-based dashboards, real-time room management, and application tracking—all backed by a MySQL database. The use of JavaBeans, Servlets, and JSP ensures maintainable and modular architecture.
2. Functionality of Our AIP Application
The system follows the MVC architecture, ensuring clear separation of concerns:
•
Model Layer (JavaBeans): JavaBeans like User, Room, and Application represent entities and encapsulate business data. These beans interact with the database using DAO classes.
•
View Layer (JSP + Bootstrap): Provides an intuitive interface with responsive layouts and role-based dashboards for students and administrators.
•
Controller Layer (Servlets): Handles business logic and user requests. Key servlets include:
o
LoginServlet, RegisterServlet – for authentication and registration
o
ApplicationServlet, RoomManagementServlet – for core application logic
Use Case Highlights:
•
Students register and apply for rooms.
•
Admins manage rooms and approve/reject applications.
•
All operations are stored and retrieved from a structured MySQL database.
Technologies:
•
AIP Frameworks: Jakarta EE (Servlets, JSP), MVC architecture.
•
JavaBeans: User, Room, and Application classes encapsulate data for seamless integration with JSP.
•
Database: MySQL 8.0 with JDBC for CRUD operations.
3. Security Measures
Security is crucial in AIP web applications. This project incorporates:
•
Prepared Statements: Prevent SQL injection by avoiding direct query concatenation.
•
Session Management: Ensures authenticated access and restricts sensitive features based on user roles.
•
Role-Based Access Control (RBAC): Differentiates views and functionalities for students and admins.
•
Logout Functionality: Proper session invalidation ensures users are logged out securely.
Potential Future Improvements:
•
Hashing passwords with BCrypt
•
Centralized credential management
•
CSRF protection on forms
4. Flowchart / Hierarchy
User Flow:
1.
Student: Register → Login → Apply → Track Status.
2.
Admin: Login → Manage Rooms → Review Applications → Update Status.
5. Features
Student Features
•
Secure Registration and Login
•
Room Application Submission
•
Track Application Status
Administrator Features
•
Room Creation and Availability Management
•
Application Review (Approve/Reject)
Shared Features
•
Role-Specific Dashboards
•
Session-based Access Control
•
Responsive UI using Bootstrap
6. Models Used
JavaBeans (Model Layer)
•
User.java – Represents students and admins
•
Room.java – Manages room details
•
Application.java – Stores application data
DAO Layer
•
UserDAO.java – Handles authentication and registration
•
RoomDAO.java – Manages CRUD operations for rooms
•
ApplicationDAO.java – Manages student room applications
MVC Architecture Benefits
•
Enhances modularity and testability
•
Simplifies debugging and maintenance
•
Promotes reusability and scalability
7. References
•
Jakarta EE Documentation
•
MySQL Documentation
•
Bootstrap 5.1 Documentation
•
Maven Official Docs
8. Conclusion
This case study demonstrates the practical application of Advanced Internet Programming concepts in developing a real-world web application. By leveraging JavaBeans, Servlets, JSP, and MySQL, the Hostel Room Allocation System provides a secure, efficient, and user-friendly solution for hostel management. It validates how AIP principles support modularity, scalability, and maintainability in enterprise-level applications. With potential for enhancements in security and automation, this project offers a strong foundation for real-world
