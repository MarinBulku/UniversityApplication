package App;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class logIn {

	private JFrame frame;
	private JTextField emField;
	private JPasswordField passwordField;
	private static Connector con = new Connector();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logIn window = new logIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static boolean checkEmail(String email) {
		
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if(mat.matches()) return true;
        
        return false;
	}
	
	public static boolean isRegistered(String email, String password) throws SQLException {
		
		PreparedStatement preparedStatement=con.connection.prepareStatement("select Email, Password from Student where (Email = '" + email +"' AND Password = '" + password + "')");
		ResultSet res = preparedStatement.executeQuery();
		
		return res.next();
	}

	/**
	 * Create the application.
	 */
	public logIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 12));
		frame.setBounds(100, 100, 720, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel userLbl = new JLabel("");
		userLbl.setIcon(new ImageIcon(logIn.class.getResource("/icons/userlogo.png")));
		userLbl.setBounds(24, 32, 50, 50);
		frame.getContentPane().add(userLbl);
		
		JLabel titullUApp = new JLabel("LOG-IN");
		titullUApp.setForeground(Color.DARK_GRAY);
		titullUApp.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		titullUApp.setBounds(311, 53, 85, 29);
		frame.getContentPane().add(titullUApp);
		
		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setForeground(Color.DARK_GRAY);
		emailLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		emailLabel.setBounds(167, 118, 64, 24);
		frame.getContentPane().add(emailLabel);
		
		emField = new JTextField();
		emField.setToolTipText("Type your e-mail !");
		emField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		emField.setBounds(241, 120, 226, 22);
		frame.getContentPane().add(emField);
		emField.setColumns(10);
		
		JLabel pwLabel = new JLabel("Password");
		pwLabel.setForeground(Color.DARK_GRAY);
		pwLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		pwLabel.setBounds(167, 163, 64, 24);
		frame.getContentPane().add(pwLabel);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Type your password!");
		passwordField.setBounds(241, 165, 226, 22);
		frame.getContentPane().add(passwordField);
		
		JButton logInButton = new JButton("Log-in");
		logInButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				if(!checkEmail(emField.getText())) {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "Your e-mail isn't valid!",
							"Check e-email!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					if(!isRegistered(emField.getText(), passwordField.getText() )) {
						JOptionPane.showMessageDialog(new JFrame(), "Incorrect e-mail or password!",
								"Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					else {
						
						PreparedStatement preparedStatement=con.connection.prepareStatement("select * from Student where Email = '" + emField.getText() + "'" );
				        ResultSet resultSet=preparedStatement.executeQuery();
				        resultSet.next();
				        
				        PreparedStatement preparedStatement2 = con.connection.prepareStatement("SELECT name, student.Id_student from major "
								+ "inner JOIN student "
								+ "on major.Id_major = student.id_major "
								+ "WHERE Email = '" + resultSet.getString("Email") + "'");
				        ResultSet resultSet2 = preparedStatement2.executeQuery();
				        resultSet2.next();
				        
				        Major major = new Major(resultSet2.getString("Name"), Integer.parseInt(resultSet.getString("Id_student")));
				        
				        preparedStatement2 = con.connection.prepareStatement("SELECT name, student.Id_student from minor "
								+ "inner JOIN student "
								+ "on minor.Id_minor = student.id_minor "
								+ "WHERE Email = '" + resultSet.getString("Email") + "'");
				        resultSet2 = preparedStatement2.executeQuery();
				        resultSet2.next();
				        
				        Minor minor = new Minor(resultSet2.getString("Name"), Integer.parseInt(resultSet.getString("Id_student")));
				        
				        frame.setVisible(false);
				        UAPP.main( new Student(resultSet.getString("Emri"), resultSet.getString("Mbiemri"), resultSet.getString("Email"), resultSet.getString("Password"), 
				        Integer.parseInt(resultSet.getString("Id_student")),resultSet.getString("Gender"), Integer.parseInt(resultSet.getString("Age")), 
				        Integer.parseInt(resultSet.getString("YearOfStudy")), major, minor, resultSet.getString("friends_list")));
				        
				        
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		logInButton.setBounds(480, 120, 85, 21);
		frame.getContentPane().add(logInButton);
		
		JButton backBtn = new JButton("<Back\n");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HomeApp.main(null);
			}
		});
		backBtn.setBackground(Color.WHITE);
		backBtn.setMnemonic('B');
		backBtn.setBounds(311, 222, 85, 21);
		frame.getContentPane().add(backBtn);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emField.setText(null);
				passwordField.setText(null);
			}
		});
		resetBtn.setBounds(480, 165, 85, 21);
		frame.getContentPane().add(resetBtn);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(24, 92, 647, 2);
		frame.getContentPane().add(separator_1);
		
	}
}
