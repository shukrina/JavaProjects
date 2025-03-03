package formvalidation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FormValidationServlet extends HttpServlet {
	// Database connection settings
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Set content type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // HTML response structure
        out.println("<html><body>");

        // Validation flags
        boolean isValid = true;

        // Validate Name
        if (name == null || name.isEmpty()) {
            out.println("<p>Name is required.</p>");
            isValid = false;
        }

        // Validate Email with regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (email == null || !emailPattern.matcher(email).matches()) {
            out.println("<p>Invalid email format.</p>");
            isValid = false;
        }

        // Validate Password (minimum 6 characters)
        if (password == null || password.length() < 6) {
            out.println("<p>Password must be at least 6 characters long.</p>");
            isValid = false;
        }

        // Check if validation passed
        if (isValid) {
            // Check against database
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Load JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Check if email already exists
                String query = "SELECT email FROM users WHERE email = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Email already exists
                    out.println("<p>Email is already registered. Please use a different email.</p>");
                } else {
                    // Insert new user into the database
                    String insertQuery = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                    insertStmt.setString(1, name);
                    insertStmt.setString(2, email);
                    insertStmt.setString(3, password);
                    int rowsInserted = insertStmt.executeUpdate();

                    if (rowsInserted > 0) {
                        out.println("<p>Registration successful! Welcome, " + name + ".</p>");
                    } else {
                        out.println("<p>Registration failed. Please try again.</p>");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>An error occurred while connecting to the database: " + e.getMessage() + "</p>");
            }
        } else {
            out.println("<p>Form validation failed. Please correct the errors above and try again.</p>");
        }

        // End HTML
        out.println("</body></html>");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
