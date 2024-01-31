package App;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class signUp {

	private JFrame frame;
	private JTextField newName;
	private JPasswordField newPassword;
	private JTextField newEmail;
	private JPasswordField newPassword2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField newSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signUp window = new signUp();
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
	
	public static boolean isRegistered(String email) throws SQLException {
		
		Connector con = new Connector();
		
		String exe = "SELECT Email FROM student WHERE Email = '" + email + "'";
		PreparedStatement preparedStatement=con.connection.prepareStatement(exe);
		ResultSet resultSet=preparedStatement.executeQuery();
		
		return resultSet.next();
	}

	/**
	 * Create the application.
	 */
	public signUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 215, 0));
		frame.setBounds(100, 100, 900, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel signUpTitle = new JLabel("Sign Up");
		signUpTitle.setForeground(Color.DARK_GRAY);
		signUpTitle.setFont(new Font("Stencil", Font.ITALIC, 24));
		signUpTitle.setBounds(388, 28, 109, 29);
		frame.getContentPane().add(signUpTitle);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 67, 857, 1);
		frame.getContentPane().add(separator);
		
		JLabel nameLabel = new JLabel("Name :\r\n");
		nameLabel.setForeground(Color.DARK_GRAY);
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		nameLabel.setBounds(345, 103, 79, 20);
		frame.getContentPane().add(nameLabel);
		
		newName = new JTextField();
		newName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		newName.setBounds(411, 105, 140, 19);
		frame.getContentPane().add(newName);
		newName.setColumns(10);
		
		JLabel surnameLabel = new JLabel("Surname :\r\n");
		surnameLabel.setForeground(Color.DARK_GRAY);
		surnameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		surnameLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		surnameLabel.setBounds(638, 103, 79, 20);
		frame.getContentPane().add(surnameLabel);
		
		newSurname = new JTextField();
		newSurname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		newSurname.setColumns(10);
		newSurname.setBounds(727, 105, 140, 19);
		frame.getContentPane().add(newSurname);
		
		JLabel pwLabel = new JLabel("Password :");
		pwLabel.setForeground(Color.DARK_GRAY);
		pwLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		pwLabel.setBounds(33, 146, 79, 20);
		frame.getContentPane().add(pwLabel);
		
		newPassword = new JPasswordField();
		newPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		newPassword.setBounds(128, 148, 140, 19);
		frame.getContentPane().add(newPassword);
		
		JLabel emLabel = new JLabel("E-mail :\r\n");
		emLabel.setForeground(Color.DARK_GRAY);
		emLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		emLabel.setBounds(57, 103, 79, 20);
		frame.getContentPane().add(emLabel);
		
		newEmail = new JTextField();
		newEmail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		newEmail.setColumns(10);
		newEmail.setBounds(128, 106, 140, 19);
		frame.getContentPane().add(newEmail);
		
		JLabel pwLabel2 = new JLabel("Confirm password :");
		pwLabel2.setForeground(Color.DARK_GRAY);
		pwLabel2.setHorizontalAlignment(SwingConstants.LEFT);
		pwLabel2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		pwLabel2.setBounds(10, 186, 140, 20);
		frame.getContentPane().add(pwLabel2);
		
		newPassword2 = new JPasswordField();
		newPassword2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		newPassword2.setBounds(128, 188, 140, 19);
		frame.getContentPane().add(newPassword2);
		
		JLabel vStudLabel = new JLabel("Year of study :\r\n");
		vStudLabel.setForeground(Color.DARK_GRAY);
		vStudLabel.setHorizontalAlignment(SwingConstants.LEFT);
		vStudLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		vStudLabel.setBounds(681, 205, 94, 20);
		frame.getContentPane().add(vStudLabel);
		
		JSpinner vStudimi = new JSpinner();
		vStudimi.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		vStudimi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		vStudimi.setBounds(817, 201, 42, 32);
		frame.getContentPane().add(vStudimi);
		
		JLabel moshaLabel = new JLabel("Age :\r\n");
		moshaLabel.setForeground(Color.DARK_GRAY);
		moshaLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		moshaLabel.setBounds(350, 149, 42, 20);
		frame.getContentPane().add(moshaLabel);
		
		JSpinner mosha = new JSpinner();
		mosha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mosha.setModel(new SpinnerNumberModel(Integer.valueOf(18), Integer.valueOf(18), null, Integer.valueOf(1)));
		mosha.setBounds(445, 147, 42, 29);
		frame.getContentPane().add(mosha);
		
		JLabel lblNewLabel = new JLabel("years old");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(489, 147, 60, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JButton backBtn = new JButton("<Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HomeApp.main(null);
			}
		});
		backBtn.setMnemonic('B');
		backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		backBtn.setBounds(292, 304, 85, 21);
		frame.getContentPane().add(backBtn);
		
		JLabel gjiniaLabel = new JLabel("Gender :\r\n");
		gjiniaLabel.setForeground(Color.DARK_GRAY);
		gjiniaLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		gjiniaLabel.setBounds(629, 147, 53, 20);
		frame.getContentPane().add(gjiniaLabel);
		
		JRadioButton maleRadio = new JRadioButton("Male");
		maleRadio.setMnemonic('M');
		buttonGroup.add(maleRadio);
		maleRadio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		maleRadio.setBounds(712, 148, 60, 21);
		frame.getContentPane().add(maleRadio);
		
		JRadioButton femaleRadio = new JRadioButton("Female");
		femaleRadio.setMnemonic('F');
		buttonGroup.add(femaleRadio);
		femaleRadio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		femaleRadio.setBounds(788, 148, 60, 21);
		frame.getContentPane().add(femaleRadio);
		
		JLabel majorLbl = new JLabel("Choose your major :");
		majorLbl.setForeground(Color.DARK_GRAY);
		majorLbl.setHorizontalAlignment(SwingConstants.LEFT);
		majorLbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		majorLbl.setBounds(345, 186, 127, 20);
		frame.getContentPane().add(majorLbl);
		
		JSpinner majorSp = new JSpinner();
		majorSp.setModel(new SpinnerListModel(new String[] {"Computer Science", "Business administration", "Economixs", "Psychology", "History", "Journalism and Mass communication", "Information Systems", "Literature"}));
		majorSp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		majorSp.setBounds(482, 188, 160, 20);
		frame.getContentPane().add(majorSp);
		
		JLabel minorLbl = new JLabel("Choose your minor :");
		minorLbl.setForeground(Color.DARK_GRAY);
		minorLbl.setHorizontalAlignment(SwingConstants.LEFT);
		minorLbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		minorLbl.setBounds(345, 216, 127, 20);
		frame.getContentPane().add(minorLbl);
		
		JSpinner minorSp = new JSpinner();
		minorSp.setModel(new SpinnerListModel(new String[] {"Languages & Cultures", "Finance", "Computer Science", "Psychology", "Information systems"}));
		minorSp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		minorSp.setBounds(482, 218, 160, 20);
		frame.getContentPane().add(minorSp);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				newName.setText(null);
				newSurname.setText(null);
				newPassword.setText(null);
				newPassword2.setText(null);
				newEmail.setText(null);
				mosha.setValue(18);
				vStudimi.setValue(1);
				majorSp.setValue("Computer science");
				minorSp.setValue("Marketing");
				buttonGroup.clearSelection();			
			}
		});
		resetBtn.setMnemonic('R');
		resetBtn.setBounds(527, 304, 85, 21);
		frame.getContentPane().add(resetBtn);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				
				if(!checkEmail(newEmail.getText())) {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "Your e-mail isn't valid!",
							"Check e-email", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!newPassword.getText().equals(newPassword2.getText())) {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "Your passwords don't match!",
							"Incorrect password", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(buttonGroup.getSelection() == null) {
					JFrame miss = new JFrame();
					JOptionPane.showMessageDialog(miss, "Set your gender!",
							"Fill the information", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				
				try {
					if(isRegistered(newEmail.getText())) {
						JFrame reg = new JFrame();
						JOptionPane.showMessageDialog(reg, "Already registered!",
								"Enter another e-mail! \nThis user is already registered!", JOptionPane.INFORMATION_MESSAGE);
					}
					
					else {
						Connector con = new Connector();
						String gender = (buttonGroup.getSelection().getMnemonic()== (int) 'M') ? "Male" : "Female";
						PreparedStatement preparedStatement=con.connection.prepareStatement("INSERT INTO `student`(`Emri`, `Mbiemri`, `Email`, `Password`, `Age`, `Gender`, `YearOfStudy`,`friends_list`, `id_major`, "
																							+ "`id_minor`, `takenMajorSubjects`, `semesterMajorSubjects`, `takenMinorSubjects`, `semesterMinorSubjects`) "
																							+ "VALUES ('"+newName.getText()+"','"+newSurname.getText()+"','"+newEmail.getText()+"','"+newPassword.getText()+"',"
																							+ "'"+mosha.getValue()+"','"+gender+"','"+vStudimi.getValue()+"', ' ' ,"
																							+ "(SELECT Id_major from major WHERE Name='"+majorSp.getValue()+"'),(SELECT Id_minor from minor WHERE Name='"+minorSp.getValue()+"'),'','','','')");
				        preparedStatement.executeUpdate();
						
						frame.setVisible(false);
						logIn.main(null);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		registerBtn.setForeground(Color.BLACK);
		registerBtn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		registerBtn.setMnemonic('R');
		registerBtn.setBounds(390, 298, 123, 32);
		frame.getContentPane().add(registerBtn);
		
	}
}

