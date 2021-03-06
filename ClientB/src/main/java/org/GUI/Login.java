package org.GUI;


import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import org.controllers.LoginController;
import org.dtos.LoginDTO;
import org.models.*;
import javax.swing.JTextField;
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;


public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField email;
	public static User newUser;
	public static Token token;
	private JButton btnexit, btnLogIn;
	private JLabel lblPassword;
	private JPasswordField password;

	/**
	 * Creates the GUI of the Login process
	 */
	public Login() {
		newUser = new User();


		BackgroundLogin background = new BackgroundLogin();
		background.setLayout(null);
		getContentPane().add(background, BorderLayout.CENTER);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBackground(new Color(240, 240, 240));
		lblEmail.setFont(new Font("Century Schoolbook", Font.PLAIN, 50));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(219, 203, 272, 125);
		background.add(lblEmail);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		email.setForeground(Color.BLACK);
		email.setBounds(596, 250, 249, 32);

		background.add(email);

		btnLogIn = new JButton("Log in");
		btnLogIn.setBackground(Color.BLACK);
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.addActionListener(this);
		btnLogIn.setFont(new Font("Yu Gothic Light", Font.PLAIN, 50));
		btnLogIn.setBounds(406, 508, 340, 92);
		btnLogIn.requestFocus();
		background.add(btnLogIn);



		btnexit = new JButton("Exit");
		btnexit.setFont(new Font("Yu Gothic", Font.PLAIN, 24));
		btnexit.setBounds(33, 652, 200, 50);
		background.add(btnexit);

		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Schoolbook", Font.PLAIN, 50));
		lblPassword.setBackground(SystemColor.menu);
		lblPassword.setBounds(229, 342, 272, 125);
		background.add(lblPassword);

		password = new JPasswordField();
		password.setForeground(Color.BLACK);
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password.setBounds(596, 394, 249, 32);
		background.add(password);

		password.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					btnLogIn.doClick();
				}
			}

		});

		JLabel lblCreateAnAccount = new JLabel("Create an account");
		lblCreateAnAccount.setForeground(Color.WHITE);
		lblCreateAnAccount.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblCreateAnAccount.setBounds(499, 460, 167, 32);
		lblCreateAnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register rg= new Register(); 
			}

		});

		background.add(lblCreateAnAccount);
		btnexit.addActionListener(this);

		this.setSize(1200, 800);
		this.setResizable(false);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = getSize();
		setLocation((pantalla.width - ventana.width) / 2,
				(pantalla.height - ventana.height) / 2);

		this.setVisible(true);
	}



	public static void main(String[] args) {
		Login x = new Login();
	}

	/**
	 * Identify which is the JButton that received the action and act accordingly
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPulsado = (JButton) e.getSource();

		if (botonPulsado == btnexit) {
			System.exit(0);
		} else if (botonPulsado == btnLogIn) {
			String u = email.getText();
			if (email.getText().equals("")||(password.getText().equals(""))) {
				JOptionPane.showMessageDialog(null,
						"You have to complete both!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				LoginDTO login=new LoginDTO();
				login.setEmail(email.getText());
				login.setEncryptedPassword(password.getText());
				try {
					token = LoginController.login(login);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				if(token.getToken() != null){
					try {
						Booking b= new Booking();
						this.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else{
					JOptionPane.showMessageDialog(null,
							"User not found, please enter a valid credentials", "Information",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} 
		} 
	}
}

class BackgroundLogin extends JPanel {


	private static final long serialVersionUID = 1L;

	/** 
	 * With this method we give the GUI a background image
	 */
	public void paintComponent(Graphics g) {
		Dimension tamaño = getSize();
		ImageIcon imagenFondo = new ImageIcon(new ImageIcon(getClass().getResource("/images/background.jpg")).getImage());
		g.drawImage(imagenFondo.getImage(), 0, 0, tamaño.width, tamaño.height,
				null);

	}
}