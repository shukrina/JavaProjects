package managementSystem0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LogIn extends JFrame{
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton btnLogin, btnCancel;
    
    public LogIn() {
    	 setTitle("Student Management Login");
         setSize(500, 400);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         JLabel lblUsername = new JLabel("Username:");
         JLabel lblPassword = new JLabel("Password:");
         txtUsername = new JTextField(15);
         txtPassword = new JPasswordField(15);
         btnLogin = new JButton("Login");
         btnCancel = new JButton("Cancel");
         
         JPanel panel = new JPanel();
         panel.setLayout(null);
         
         lblUsername.setBounds(50, 70, 80, 25);
         txtUsername.setBounds(140, 70, 150, 25);
         lblPassword.setBounds(50, 110, 80, 25);
         txtPassword.setBounds(140, 110, 150, 25);
         btnLogin.setBounds(140, 160, 80, 25);
         btnCancel.setBounds(230, 160, 80, 25);

         // Add components to panel
         panel.add(lblUsername);
         panel.add(txtUsername);
         panel.add(lblPassword);
         panel.add(txtPassword);
         panel.add(btnLogin);
         panel.add(btnCancel);
         
         add(panel);
         
         btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = txtUsername.getText();
                char[] password = txtPassword.getPassword();
                if (username.equals("csit") && new String(password).equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    dispose();  // Close login window
                    new StudentManagementSystem().setVisible(true);  // Open main menu
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
				
			}
         });
         btnCancel.addActionListener(e -> System.exit(0));

    }
    public static void main(String[] args) {
    	//new LogIn().setVisible(true);  
    	new LogIn().setVisible(true);
    }


}
