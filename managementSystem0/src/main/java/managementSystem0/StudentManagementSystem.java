package managementSystem0;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class StudentManagementSystem extends JFrame {
    private JTextField idField, nameField, emailField, phoneField, courseField;
    private JTextArea displayArea;
    private Connection connection;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Database Connection
        connectToDatabase();

        // UI Components
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);

        // Buttons for CRUD operations
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton viewButton = new JButton("View All");
        

        addButton.addActionListener(new AddStudentListener());
        updateButton.addActionListener(new UpdateStudentListener());
        deleteButton.addActionListener(new DeleteStudentListener());
        viewButton.addActionListener(new ViewStudentsListener());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Adding components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "password");
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String course = courseField.getText();

            String query = "INSERT INTO students (id, name, email, phone, course) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement ps = connection.prepareStatement(query)){
            	ps.setInt(1, Integer.parseInt(idField.getText())); // Get ID from the field
            	ps.setString(2, nameField.getText());
            	ps.setString(3, emailField.getText());
            	ps.setString(4, phoneField.getText());
            	ps.setString(5, courseField.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Student added successfully!");
                clearFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Error adding student!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class UpdateStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String course = courseField.getText();

            String query = "UPDATE students SET name = ?, email = ?, phone = ?, course = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, course);
                ps.setInt(5, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Student updated successfully!");
                clearFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Error updating student!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(idField.getText());

            String query = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Student deleted successfully!");
                clearFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Error deleting student!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 // Initialize the UI components
    /*public void initializeUI() {
        JFrame frame = new JFrame("Student Management System");
        frame.setLayout(null);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Button to view all students
        JButton viewButton = new JButton("View Students");
        viewButton.setBounds(20, 20, 150, 30);
        viewButton.addActionListener(e -> ViewStudentsListener());
        frame.add(viewButton);

        // Table setup
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Course");

        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(20, 70, 740, 400);  // Table takes up a large portion of the frame
        frame.add(scrollPane);

        frame.setVisible(true);
    }*/

	private class ViewStudentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = "SELECT * FROM students";
            try (Statement st = connection.createStatement();
                 ResultSet rs = st.executeQuery(query)) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-5s %-20s %-20s %-15s %-10s\n", "ID", "Name", "Email", "Phone", "Course"));
                sb.append("---------------------------------------------------------------\n");
                while (rs.next()) {
                    sb.append(String.format("%-5d %-20s %-20s %-15s %-10s\n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("course")));
                }
                displayArea.setText(sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Error fetching students!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        /*private JFrame frame;
        private Connection connection;

        public ViewStudentsListener(JFrame frame, Connection connection) {
            this.frame = frame;
            this.connection = connection;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Create SQL query to get all students
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM students");

                // Create table model to store data from the result set
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("ID");
                tableModel.addColumn("Name");
                tableModel.addColumn("Email");
                tableModel.addColumn("Phone");
                tableModel.addColumn("Course");

                // Populate table model with data
                while (rs.next()) {
                    Object[] row = new Object[5];
                    row[0] = rs.getInt("id");
                    row[1] = rs.getString("name");
                    row[2] = rs.getString("email");
                    row[3] = rs.getString("phone");
                    row[4] = rs.getString("course");
                    tableModel.addRow(row);
                }

                // Create JTable with the model
                JTable studentTable = new JTable(tableModel);
                studentTable.setPreferredScrollableViewportSize(new Dimension(frame.getWidth() / 2, frame.getHeight() / 2)); // Table covers half of the frame
                studentTable.setFillsViewportHeight(true);

                // Add table to JScrollPane for scrollable content
                JScrollPane scrollPane = new JScrollPane(studentTable);
                frame.getContentPane().removeAll();  // Remove any existing content
                frame.getContentPane().add(scrollPane, BorderLayout.CENTER);  // Add table to the center
                frame.revalidate();  // Revalidate to ensure layout is updated
                frame.repaint();  // Repaint to display the updated UI
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error loading student data!");
            }
        }*/
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        courseField.setText("");
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}
