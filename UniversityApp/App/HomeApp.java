package App;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class HomeApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeApp window = new HomeApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 215, 0));
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 10));
		frame.setBounds(100, 100, 699, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hello fellow student!");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Brush Script MT", Font.ITALIC, 71));
		lblNewLabel.setBounds(75, 0, 534, 97);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel logInLbl = new JLabel("Log-in");
		logInLbl.setForeground(Color.GRAY);
		logInLbl.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 19));
		logInLbl.setBounds(42, 140, 250, 58);
		frame.getContentPane().add(logInLbl);
		
		JLabel lblAre = new JLabel("Are you new here?\r\n");
		lblAre.setForeground(Color.GRAY);
		lblAre.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 19));
		lblAre.setBounds(42, 234, 186, 58);
		frame.getContentPane().add(lblAre);
		
		JLabel lblSignUpTo = new JLabel("Sign up !!!\r\n");
		lblSignUpTo.setForeground(Color.DARK_GRAY);
		lblSignUpTo.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		lblSignUpTo.setBounds(42, 276, 290, 37);
		frame.getContentPane().add(lblSignUpTo);
		
		JButton logInBtn = new JButton("Log-In Here\r\n");
		logInBtn.setIcon(null);
		logInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				logIn.main(null);
			}
		});
		logInBtn.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		logInBtn.setBounds(234, 160, 120, 27);
		frame.getContentPane().add(logInBtn);
		
		JButton signUpBtn = new JButton("Sign-up");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				signUp.main(null);
			}
		});
		signUpBtn.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		signUpBtn.setBounds(234, 254, 120, 27);
		frame.getContentPane().add(signUpBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 105, 960, 2);
		frame.getContentPane().add(separator);
		
		JButton exitBtn = new JButton("QUIT");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame getOut = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(getOut, "Do you want to exit?", "UniversityApplication", 
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
					System.exit(0);
			}
		});
		exitBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		exitBtn.setForeground(Color.RED);
		exitBtn.setMnemonic('E');
		exitBtn.setBounds(568, 356, 107, 27);
		frame.getContentPane().add(exitBtn);
		
	}
}

