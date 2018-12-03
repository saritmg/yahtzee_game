package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Main extends JFrame  implements ActionListener{
	
	JLabel title;
	JLabel labelUser;
	JTextField lblUserFld;
	JLabel labelPw;
	JPasswordField lblPwFld;
	JButton btnLogin;
	JButton btnExit;
        
	
    
	public Main () {
		
		setTitle("Login Form");
		setSize(400, 500);
		setDefaultCloseOperation(3);
                getContentPane().setBackground(new Color(143, 188, 143));
		setLayout(null);
		setLocationRelativeTo(null);
		
		title = new JLabel("Login");
		labelUser = new JLabel("Username :");
		lblUserFld = new JTextField();
		labelPw = new JLabel("Password :");
		lblPwFld = new JPasswordField();
		btnLogin = new JButton("Login");
		btnExit = new JButton("Exit");
		
		title.setBounds(140, 20, 100, 40);
                title.setFont(new Font("Arial", Font.PLAIN, 30));
                title.setPreferredSize(new Dimension(1390, 50));
                
		labelUser.setBounds(50, 120, 500, 40);
		lblUserFld.setBounds(150, 120, 100, 40);
		labelPw.setBounds(50, 220, 100, 40);
		lblPwFld.setBounds(150, 220, 100, 40);
                
		btnLogin.setBounds(100, 320, 80, 40);
                btnLogin.setBackground(new Color(211, 214, 115));
                btnLogin.setBorder(new MatteBorder(3, 3, 3, 3, (new Color(97, 161, 163))));
                
		btnExit.setBounds(230, 320, 80, 40);
                btnExit.setBackground(new Color(211, 214, 115));
                btnExit.setBorder(new MatteBorder(3, 3, 3, 3, (new Color(97, 161, 163))));
		
		add(title);
		add(labelUser);
		add(lblUserFld);
		add(labelPw);
		add(lblPwFld);
		add(btnLogin);
		add(btnExit);
		
		setVisible(true);
		btnExit.addActionListener(this);
		btnLogin.addActionListener(this);
	}
	
	public static void main(String args[]) {
		
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnLogin) {
			
			if(lblUserFld.getText().equals("") || lblPwFld.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please input correct username or password ",
                                        "Please Fill the Form properly", JOptionPane.ERROR_MESSAGE);
			}
			
			else if(lblUserFld.getText().equals("sarita") && lblPwFld.getText().equals("sarita123")) {
				
				new Yahtzee();
				dispose();
			}
			
			else {
JOptionPane.showMessageDialog(null, "Username or Password is incorrect", "Error", JOptionPane.QUESTION_MESSAGE);
				
			}
		}
		
		if(e.getSource() == btnExit) {
			setDefaultCloseOperation(3);
			setVisible(false);
		}
	}
	
}

