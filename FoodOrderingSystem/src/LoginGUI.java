import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Rectangle;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class LoginGUI {

	private JFrame frame;
	private JTextField textUsername;
	private JLabel labelLogo;
	private JPasswordField passwordField;
	private JButton signupbutton;
	private Encryption e;
	
	
	
	public LoginGUI getLoginGUI(){return this;}
	
	public LoginGUI() {
		
		initialize();
		frame.setVisible(true);
	}
    public void setDefault(){
    	textUsername.setText("");
    	passwordField.setText("");
    }
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Food Ordering System");
		JButton loginButton = new JButton("Login");
		Image imgLogin=new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		loginButton.setIcon(new ImageIcon(imgLogin));
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int cnt=0;
				Connection mysql=null;
				try{
					Class.forName("com.mysql.jdbc.Driver");
					 mysql=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/foodorderingsystem", "teyfik", "123456789");
					Statement statement=mysql.createStatement();
					ResultSet result=statement.executeQuery("select * from user");
				     e=new Encryption(String.valueOf(passwordField.getPassword()));
					
					
					while(result.next())
					{
						
						if(textUsername.getText().equals(result.getString("username")) && e.MakeToEncrypted().equals(result.getString("password")))
						{
							//textPassword.getText();
							JOptionPane.showMessageDialog(null, "Access Granted");
							frame.dispose();
							LoginedGUI logined=new LoginedGUI(result.getString("iduser"));
							logined.setVisible(true);
							
							frame.setVisible(false);
							cnt=1;
							break;
						}
						
							//System.out.println(result.getString("iduser")+result.getString("username")+","+result.getString("password"));
					}
					
				}catch(Exception ex){ex.printStackTrace();}
				
				          if(cnt!=1)
				          {
				        	  JOptionPane.showMessageDialog(null, "Access Denied!!!");
				        	  textUsername.setText("");
				        	  passwordField.setText("");
				          }
				            
				
				
				
				
				
			}
		});
		loginButton.setBounds(179, 189, 107, 23);
		frame.getContentPane().add(loginButton);
		
	    setSignupbutton(new JButton("Sign Up"));
		Image imgSignup=new ImageIcon(this.getClass().getResource("/signuplogo.png")).getImage();
		getSignupbutton().setIcon(new ImageIcon(imgSignup));
		getSignupbutton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//frame.dispose();
				SingUpGUI singuped=new SingUpGUI(getLoginGUI());
				singuped.setVisible(true);
				getSignupbutton().setEnabled(false);
				
			}
		});
		getSignupbutton().setBounds(296, 189, 107, 23);
		frame.getContentPane().add(getSignupbutton());
		
		textUsername = new JTextField();
		textUsername.setBounds(179, 115, 224, 20);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Lato Semibold", Font.BOLD, 13));
		lblUsername.setBounds(97, 118, 81, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Lato Semibold", Font.BOLD, 13));
		lblPassword.setBounds(97, 149, 81, 14);
		frame.getContentPane().add(lblPassword);
		
		labelLogo = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/foodorderlogo.png")).getImage();
		labelLogo.setIcon(new ImageIcon(img));
		labelLogo.setBounds(5, 100, 87, 86);
		frame.getContentPane().add(labelLogo);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 146, 224, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("");
		Image imgMainLogo=new ImageIcon(this.getClass().getResource("/foodordering-logo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(imgMainLogo));
		lblNewLabel.setBounds(115, 11, 260, 93);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblForgotPass = new JLabel("Forgot Your Password? Click Here!");
		lblForgotPass.setForeground(new Color(0, 0, 255));
		lblForgotPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ForgotPassword fg=new ForgotPassword(getLoginGUI());
				fg.setVisible(true);
			}
		});
		lblForgotPass.setBounds(179, 170, 224, 13);
		frame.getContentPane().add(lblForgotPass);
	}

	public JButton getSignupbutton() {
		return signupbutton;
	}

	public void setSignupbutton(JButton signupbutton) {
		this.signupbutton = signupbutton;
	}
}
