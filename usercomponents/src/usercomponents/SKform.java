package usercomponents;

import javax.swing.*;
import java.awt.*;

public class SKform extends JFrame{
	JPanel p1,p2;
	//component declaration for first panel
	JLabel lab, lab2;
	JMenuItem menuItem1, menuItem2, menuItem11, menuItem12;
	JMenu menu1, menu2,menu3;
	JMenuBar menubar;
	
	//component declaration for second panel
	JLabel name, email, password, gender, course, languages, university, contactme;  
	JTextField namefield, emailfield;
	JPasswordField passwordfield;
	JRadioButton male,female,other, linkedin,facebook,instagram,friends,others;
	ButtonGroup genderfield, contactfield;
	String[] courses = {"BSc.CSIT","BCA","BIT","BIM"};
	JComboBox<String> coursefield;
	DefaultListModel<String> languagefield = new DefaultListModel<>(); 
	JList<String> languagelist;
	String[] universities = {"Tribhuvan University","Pokhara University","Purbanchal University"};
	JComboBox<String> universityfield;
	JTextArea othercontacts;
	JButton btn;
	
	SKform(){
		//initialization
	    setSize(1220,720);
	    setLocation(40, 10);
	    setTitle("Form fill up");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(null);
		
	    //Panel1
		p1=new JPanel();
		p1.setBounds(10,10,1185,110);
		p1.setLayout(new GridLayout(3,1,0,0));
		
		//setting header menu bar
		menubar = new JMenuBar();
		
		menu1 = new JMenu("Programs");
		menu2 = new JMenu("Media");
		menu3 = new JMenu("Donate");
		
		menuItem1 = new JMenuItem("Workshop");
		menuItem2 = new JMenuItem("Fellowship programs");
		
		menuItem11 = new JMenuItem("Blogs");
		menuItem12 = new JMenuItem("Event highlights");
		
		menu1.add(menuItem1);
		menu1.add(menuItem2);
		
		menu2.add(menuItem11);
		menu2.add(menuItem12);
		
		menubar.add(menu1);
		menubar.add(menu2);
		menubar.add(menu3);
		p1.add(menubar);
		
		//adding header description
		lab=new JLabel("WebApp WorkShop", JLabel.CENTER);
		lab.setFont(new Font("Arial", Font.PLAIN, 32));
		p1.add(lab);
		lab2=new JLabel("Connecting ideas with technology", JLabel.CENTER);
		p1.add(lab2);
		p1.setBackground(new Color(200, 160, 255));
		
		//adding first panel to the frame
		add(p1);
		
		
		//Panel 2
		p2=new JPanel();
		p2.setBounds(10,150,1200,710);
		p2.setLayout(null);
		
		//labels 
		name=new JLabel("Name: ");
		email=new JLabel("Email: ");
		password=new JLabel("Password");
		gender=new JLabel("Gender: ");
		course=new JLabel("Course currently being pursued: ");
		languages=new JLabel("Programming languages you know: ");
		university=new JLabel("University: ");
		contactme=new JLabel("How did you know about us? ");
		
		//textfields
		namefield=new JTextField(50);
		emailfield=new JTextField(50);
		
		//passwordfield
		passwordfield = new JPasswordField(10);
		
		//for gender using radiobuttons
		genderfield = new ButtonGroup();
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		other = new JRadioButton("Prefer not to say");
		genderfield.add(male);
		genderfield.add(female);
		genderfield.add(other);
		
		//for courses using combobox
		coursefield = new JComboBox<String>(courses);
		
		//for languages using checkboxes
		JCheckBox java = new JCheckBox("Java");
        JCheckBox r = new JCheckBox("R");
        JCheckBox css = new JCheckBox("CSS");
        JCheckBox cs=new JCheckBox("C#");
        JCheckBox py=new JCheckBox("Python");
        JCheckBox js=new JCheckBox("JavaScript");
        JCheckBox rb=new JCheckBox("Ruby");

		//for universities using combobox
		universityfield = new JComboBox<String>(universities);

		//for contact using radiobuttons
		contactfield = new ButtonGroup();
		linkedin = new JRadioButton("LinkedIn");
		facebook = new JRadioButton("Facebook");
		instagram=new JRadioButton("Instagram");
		friends=new JRadioButton("Friends");
		others = new JRadioButton("Others");
		genderfield.add( linkedin);
		genderfield.add(facebook );
		genderfield.add(instagram );
		genderfield.add(friends );
		genderfield.add(others );
		othercontacts=new JTextArea();
		
		//submit
		btn=new JButton("Submit");
		
		//setting bounds for all components
		name.setBounds(160,0,200,30);
		namefield.setBounds(400,0,510,30);
		email.setBounds(160,50,200,30);
		emailfield.setBounds(400,50,510,30);
		password.setBounds(160,100,200,30);
		passwordfield.setBounds(400,100,510,30);
		gender.setBounds(160,150,200,30);
		male.setBounds(400,150,100,30);
		female.setBounds(540,150,100,30);
		other.setBounds(690,150,200,30);
		course.setBounds(160,200,200,30);
		coursefield.setBounds(400,200,510,30);
		university.setBounds(160,250,200,30);
		universityfield.setBounds(400,250,510,30);
		languages.setBounds(160,300,230,30);
		java.setBounds(400,300,70,30);
		r.setBounds(510,300,70,30);
		css.setBounds(590,300,70,30);
		cs.setBounds(700,300,70,30);
		py.setBounds(400,330,70,30);
		js.setBounds(510,330,100,30);
		rb.setBounds(630,330,70,30);
		contactme.setBounds(160,380,200,30);
		linkedin.setBounds(400,380,100,30);
		facebook.setBounds(540,380,100,30);
		instagram.setBounds(690,380,100,30);
		friends.setBounds(400,410,100,30);
		others.setBounds(540,410,90,30);
		othercontacts.setBounds(620,410,200,30);
		
		//button for submitting
		btn.setBounds(520,470,150,30);
		
		
		//now adding all components to panel
		p2.add(name);
		p2.add(namefield);
		p2.add(email);
		p2.add(emailfield);
		p2.add(password);
		p2.add(passwordfield);
		p2.add(gender);
		p2.add(male);
		p2.add(female);
		p2.add(other);
		p2.add(course);
		p2.add(coursefield);
		p2.add(university);
		p2.add(universityfield);
		p2.add(languages);
		p2.add(java);
		p2.add(r);
		p2.add(css);
		p2.add(cs);
		p2.add(py);
		p2.add(js);
		p2.add(rb);
		p2.add(contactme);
		p2.add(linkedin);
		p2.add(facebook);
		p2.add(instagram);
		p2.add(friends);
		p2.add(others);
		p2.add(othercontacts);
		p2.add(btn);
		
		//adding panel 2 to the frame
		add(p2);
		
		//frame visibility
		setVisible(true);
	}
}
