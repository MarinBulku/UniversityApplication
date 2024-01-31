package App;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JSeparator;

public class UAPP {
	
	static PreparedStatement stat;
	static ResultSet rs;
	static ResultSet count;
	private JFrame frame;
	private static Student st;
	private JTable friendsTbl;
	private JTable majCourseTbl;
	private JTable usrsTbl;
	static Connector con = new Connector();
	private JTable frTbl;
	private JTable majCTable;
	private JTable minCTable;
	private JTable minCourseTbl;
	private JTable allMajorTable;
	private JTable allMinorTable;
	private JTable majCommTbl;
	private JTable minCommTbl;
	/**
	 * Launch the application.
	 */
	public static void main(Student s) {
		st = s;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UAPP window = new UAPP();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public UAPP() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	@SuppressWarnings("serial")
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 243, 224));
		frame.setBounds(100, 100, 1028, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton signOutBtn = new JButton("Sign out");
		signOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st = null;
				frame.setVisible(false);
				HomeApp.main(null);
			}
		});
		signOutBtn.setForeground(new Color(255, 0, 0));
		signOutBtn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		signOutBtn.setBounds(914, 5, 100, 25);
		frame.getContentPane().add(signOutBtn);
		
		JTabbedPane allTabs = new JTabbedPane(JTabbedPane.TOP);
		allTabs.setSelectedIndex(-1);
		allTabs.setBounds(10, 10, 1004, 582);
		frame.getContentPane().add(allTabs);
		
		JPanel profileTab = new JPanel();
		profileTab.setBackground(new Color(101, 202, 235));
		allTabs.addTab("My Profile", null, profileTab, null);
		allTabs.setBackgroundAt(0, new Color(240, 240, 240));
		profileTab.setLayout(null);
		
		JLabel prfLogo = new JLabel("");
		prfLogo.setBackground(Color.YELLOW);
		prfLogo.setIcon(new ImageIcon(UAPP.class.getResource("/icons/student-128.png")));
		prfLogo.setBounds(10, 10, 138, 135);
		profileTab.add(prfLogo);
		
		JLabel fullNameLbl = new JLabel(st.getEmri() + " " + st.getMbiemri());
		fullNameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fullNameLbl.setBounds(158, 43, 134, 20);
		profileTab.add(fullNameLbl);
		
		JLabel emailLbl = new JLabel(st.getEmail());
		emailLbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		emailLbl.setBounds(288, 43, 166, 20);
		profileTab.add(emailLbl);
		
		JLabel studyLbl = new JLabel("Major: " + st.getMajor().getName() + "/ Minor: " + st.getMinor().getName());
		studyLbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		studyLbl.setBounds(158, 73, 278, 20);
		profileTab.add(studyLbl);
		
		JLabel friendsLbl = new JLabel("My friends:");
		friendsLbl.setFont(new Font("Times New Roman", Font.BOLD, 12));
		friendsLbl.setBounds(26, 297, 120, 13);
		profileTab.add(friendsLbl);
		
		JPanel friendsBdr = new JPanel();
		friendsBdr.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		friendsBdr.setBounds(10, 320, 178, 194);
		profileTab.add(friendsBdr);
		friendsBdr.setLayout(null);
		
		friendsTbl = new JTable();
		friendsTbl.setBackground(new Color(255, 255, 255));
		friendsTbl.setBounds(6, 15, 166, 173);
		friendsBdr.add(friendsTbl);
		friendsTbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		friendsTbl.setModel(new DefaultTableModel(getFriends(), new String[] {"Name"}));
		
		JLabel majCourseLbl = new JLabel(st.getMajor().getName() + " courses this semester:");
		majCourseLbl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		majCourseLbl.setBounds(345, 296, 278, 13);
		profileTab.add(majCourseLbl);
		
		JPanel courseBdr = new JPanel();
		courseBdr.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		courseBdr.setBounds(385, 320, 164, 194);
		profileTab.add(courseBdr);
		courseBdr.setLayout(null);
		
		majCourseTbl = new JTable();
		majCourseTbl.setBackground(new Color(255, 255, 255));
		majCourseTbl.setBounds(6, 15, 152, 169);
		courseBdr.add(majCourseTbl);
		majCourseTbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		majCourseTbl.setModel(new DefaultTableModel(
			getStudentsMajors(),
			new String[] {
				"Name"
			}
		));
		
		JButton rmvMajCourse = new JButton("Remove course");
		rmvMajCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!majCourseTbl.getSelectionModel().isSelectionEmpty()) {
					try {
						st.removeFriend((String) majCourseTbl.getValueAt(majCourseTbl.getSelectedRow(), 0));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JFrame frm = new JFrame();
					JOptionPane.showMessageDialog(frm, "Select course to remove!",
							"No course selected", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		rmvMajCourse.setForeground(new Color(255, 0, 0));
		rmvMajCourse.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rmvMajCourse.setBounds(391, 524, 153, 21);
		profileTab.add(rmvMajCourse);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(788, 320, 178, 194);
		profileTab.add(panel_3);
		panel_3.setLayout(null);
		
		minCourseTbl = new JTable();
		minCourseTbl.setBackground(new Color(255, 255, 255));
		minCourseTbl.setModel(new DefaultTableModel(
			getStudentsMinors(),
			new String[] {
				"Name"
			}
		));
		minCourseTbl.setBounds(6, 10, 166, 177);
		panel_3.add(minCourseTbl);
		
		JLabel minCourseLbl = new JLabel(st.getMinor().getName() + " courses this semester:");
		minCourseLbl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		minCourseLbl.setBounds(694, 298, 295, 12);
		profileTab.add(minCourseLbl);
		
		JButton rmvMinCourse = new JButton("Remove course");
		rmvMinCourse.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rmvMinCourse.setForeground(new Color(255, 0, 0));
		rmvMinCourse.setBounds(789, 524, 177, 21);
		profileTab.add(rmvMinCourse);
		
		JButton removeFriendBtn = new JButton("Remove Friend");
		removeFriendBtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		removeFriendBtn.setForeground(new Color(255, 0, 0));
		removeFriendBtn.setBounds(43, 524, 105, 21);
		profileTab.add(removeFriendBtn);
		
		JPanel allUsersTab = new JPanel();
		allUsersTab.setBackground(new Color(101, 202, 235));
		allTabs.addTab("Find Friends", null, allUsersTab, "");
		allUsersTab.setLayout(null);
		
		JLabel friendsLogo = new JLabel("");
		friendsLogo.setIcon(new ImageIcon(UAPP.class.getResource("/icons/friendsLogo.png")));
		friendsLogo.setBounds(0, 0, 190, 175);
		allUsersTab.add(friendsLogo);
		
		JLabel findFr = new JLabel("Find friends, and discuss together about everything!");
		findFr.setForeground(new Color(128, 0, 128));
		findFr.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 30));
		findFr.setBounds(191, 45, 731, 98);
		allUsersTab.add(findFr);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setForeground(new Color(0, 0, 0));
		tablePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Students", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tablePanel.setBounds(30, 199, 937, 211);
		allUsersTab.add(tablePanel);
		tablePanel.setLayout(null);
		
		JScrollPane studentScroll = new JScrollPane();
		studentScroll.setBounds(10, 40, 290, 161);
		tablePanel.add(studentScroll);
		
		usrsTbl = new JTable();
		studentScroll.setViewportView(usrsTbl);
		usrsTbl.setModel(new DefaultTableModel(getUsers(), new String[] {"Name", "Major", "Minor"}));
		usrsTbl.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JButton frBtn = new JButton("Send friend request");
		frBtn.setBounds(75, 18, 157, 21);
		tablePanel.add(frBtn);
		frBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = usrsTbl.getSelectedRow();
				String friend = (String) usrsTbl.getValueAt(index, 0);
				PreparedStatement ps;
				try {
					ps = con.connection.prepareStatement("SELECT * FROM friend_requests WHERE sender = '"+ st.getEmri() + " " + st.getMbiemri() +"' AND reciever = '"+friend+"'");
					ResultSet rs = ps.executeQuery();
					if(!rs.next()) {
						ps.executeUpdate("INSERT INTO `friend_requests`(`sender`, `reciever`) VALUES ('" +st.getEmri() + " " + st.getMbiemri() + "','"+friend+"')");
					}
					else {
						JFrame isAlready = new JFrame();
						JOptionPane.showMessageDialog(isAlready, "Friend request is already sent!",
								"Notice", JOptionPane.PLAIN_MESSAGE);
						return;
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		frBtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JPanel panel = new JPanel();
		panel.setBounds(499, 22, 348, 179);
		tablePanel.add(panel);
		panel.setBackground(new Color(240, 240, 240));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Friend Requests", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		
		JScrollPane frScroll = new JScrollPane();
		frScroll.setBounds(6, 15, 205, 154);
		panel.add(frScroll);
		
		frTbl = new JTable();
		frTbl.setModel(new DefaultTableModel(getFriendRequests(),new String[] {"Name"}) {@SuppressWarnings("rawtypes")
		Class[] columnTypes = new Class[] {String.class};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		frScroll.setViewportView(frTbl);
		frTbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JButton acceptBtn = new JButton("Accept");
		acceptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!frTbl.getSelectionModel().isSelectionEmpty()) {
					try {
						st.addFriendsList((String) frTbl.getValueAt(frTbl.getSelectedRow(), 0));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JFrame frm = new JFrame();
					JOptionPane.showMessageDialog(frm, "Select request!",
							"No request selected", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		acceptBtn.setBounds(221, 61, 85, 21);
		panel.add(acceptBtn);
		acceptBtn.setForeground(new Color(0, 255, 0));
		acceptBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JButton declineBtn = new JButton("Decline");
		declineBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!frTbl.getSelectionModel().isSelectionEmpty()) {
					try {
						stat = con.connection.prepareStatement("DELETE FROM `friend_requests` WHERE sender = '" + (String) frTbl.getValueAt(frTbl.getSelectedRow(), 0) + "'");
						stat.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JFrame frm = new JFrame();
					JOptionPane.showMessageDialog(frm, "Select request!",
							"No request selected", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		declineBtn.setBounds(221, 92, 85, 21);
		panel.add(declineBtn);
		declineBtn.setForeground(new Color(255, 0, 0));
		declineBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(335, 28, 137, 2);
		tablePanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.controlShadow);
		separator_1.setBounds(335, 58, 137, 2);
		tablePanel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.controlShadow);
		separator_2.setBounds(335, 88, 137, 2);
		tablePanel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(SystemColor.controlShadow);
		separator_3.setBounds(335, 118, 137, 2);
		tablePanel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(SystemColor.controlShadow);
		separator_4.setBounds(335, 148, 137, 2);
		tablePanel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(SystemColor.controlShadow);
		separator_5.setBounds(335, 178, 137, 2);
		tablePanel.add(separator_5);
		
		JPanel courseInfo = new JPanel();
		courseInfo.setBackground(new Color(101, 202, 235));
		courseInfo.setForeground(new Color(101, 202, 235));
		allTabs.addTab("Course Info", null, courseInfo, null);
		courseInfo.setLayout(null);
		
		JPanel majCBorder = new JPanel();
		majCBorder.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Major Course", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		majCBorder.setBounds(25, 21, 277, 257);
		courseInfo.add(majCBorder);
		majCBorder.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 15, 261, 236);
		majCBorder.add(scrollPane);
		
		majCTable = new JTable();
		majCTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		majCTable.setModel(new DefaultTableModel(getMajorCourses(),new String[] {st.getMajor().getName()}));
		scrollPane.setViewportView(majCTable);
		
		JPanel minCBorder = new JPanel();
		minCBorder.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Minor Course", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		minCBorder.setBounds(19, 299, 283, 252);
		courseInfo.add(minCBorder);
		minCBorder.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 15, 267, 231);
		minCBorder.add(scrollPane_1);
		
		minCTable = new JTable();
		minCTable.setModel(new DefaultTableModel(getMinorCourses(),new String[] {st.getMinor().getName()}));
		minCTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane_1.setViewportView(minCTable);
		
		JButton addToMajC = new JButton("Add to MyCourses >>");
		addToMajC.setForeground(SystemColor.textHighlight);
		addToMajC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!majCTable.getSelectionModel().isSelectionEmpty()) {
					
					try {
						if(!st.getMajor().addSubject((String) majCTable.getValueAt(majCTable.getSelectedRow(), 0))) {
							JFrame frm = new JFrame();
							JOptionPane.showMessageDialog(frm, "You can't select more courses now!",
									"Courses full for now", JOptionPane.PLAIN_MESSAGE);
							return;
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JFrame frm = new JFrame();
					JOptionPane.showMessageDialog(frm, "Select request!",
							"No request selected", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		addToMajC.setFont(new Font("Times New Roman", Font.BOLD, 12));
		addToMajC.setBounds(323, 105, 176, 34);
		courseInfo.add(addToMajC);
		
		JButton addToMinC = new JButton("Add to MyCourses >>");
		addToMinC.setForeground(SystemColor.textHighlight);
		addToMinC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!minCTable.getSelectionModel().isSelectionEmpty()) {
					
					try {
						if(!st.getMinor().addSubject((String) minCTable.getValueAt(minCTable.getSelectedRow(), 0))) {
							JFrame frm = new JFrame();
							JOptionPane.showMessageDialog(frm, "You can't select more courses now!",
									"Courses full for now", JOptionPane.PLAIN_MESSAGE);
							return;
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JFrame frm = new JFrame();
					JOptionPane.showMessageDialog(frm, "Select request!",
							"No request selected", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		addToMinC.setFont(new Font("Times New Roman", Font.BOLD, 12));
		addToMinC.setBounds(323, 377, 176, 34);
		courseInfo.add(addToMinC);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.info);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(153, 180, 209), SystemColor.activeCaptionBorder, null, null));
		panel_1.setBounds(779, 26, 210, 174);
		courseInfo.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel majCDetails = new JLabel("Details:");
		majCDetails.setBounds(6, 15, 91, 24);
		panel_1.add(majCDetails);
		majCDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		JLabel majDet1 = new JLabel("Every major course has :");
		majDet1.setBounds(7, 39, 126, 24);
		panel_1.add(majDet1);
		majDet1.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		
		JLabel majDet2 = new JLabel("2 professors and 6 credit each course");
		majDet2.setBounds(7, 60, 197, 24);
		panel_1.add(majDet2);
		majDet2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		
		JLabel minDet1 = new JLabel("Every major course has :");
		minDet1.setBounds(7, 118, 126, 24);
		panel_1.add(minDet1);
		minDet1.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		
		JLabel minDet2 = new JLabel("1 professor and 4 credit each course");
		minDet2.setBounds(6, 140, 197, 24);
		panel_1.add(minDet2);
		minDet2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		
		JPanel cCommentsTab = new JPanel();
		cCommentsTab.setBackground(new Color(101, 202, 235));
		allTabs.addTab("Course's Comments", null, cCommentsTab, null);
		cCommentsTab.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Select Major to view comments", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(15, 40, 324, 251);
		cCommentsTab.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 15, 308, 230);
		panel_4.add(scrollPane_2);
		
		allMajorTable = new JTable();
		scrollPane_2.setViewportView(allMajorTable);
		allMajorTable.setModel(new DefaultTableModel(getAllMajors(),new String[] {"Name"}) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		allMajorTable.getColumnModel().getColumn(0).setResizable(false);
		allMajorTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Select Minor to view comments", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(15, 302, 324, 243);
		cCommentsTab.add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 15, 308, 222);
		panel_5.add(scrollPane_3);
		
		allMinorTable = new JTable();
		scrollPane_3.setViewportView(allMinorTable);
		allMinorTable.setModel(new DefaultTableModel(getAllMinors(),new String[] {"Name"}) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		allMinorTable.getColumnModel().getColumn(0).setResizable(false);
		allMinorTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(502, 40, 479, 251);
		cCommentsTab.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 15, 463, 226);
		panel_6.add(scrollPane_4);
		
		majCommTbl = new JTable();
		majCommTbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane_4.setViewportView(majCommTbl);
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(499, 302, 479, 243);
		cCommentsTab.add(panel_7);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(6, 15, 460, 218);
		panel_7.add(scrollPane_5);
		
		minCommTbl = new JTable();
		scrollPane_5.setViewportView(minCommTbl);
		minCommTbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JButton majComBtn = new JButton("Show Comments");
		majComBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!allMajorTable.getSelectionModel().isSelectionEmpty()) {
					try {
						majCommTbl.setModel(new DefaultTableModel(getMajComments( (String) allMajorTable.getValueAt(allMajorTable.getSelectedRow(), 0)),
								new String[] {"Student", "Comment"}) {
								boolean[] columnEditables = new boolean[] {
									false, false
								};
								public boolean isCellEditable(int row, int column) {
									return columnEditables[column];
								}
							});
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JFrame frm = new JFrame();
					JOptionPane.showMessageDialog(frm, "Select course!",
							"No course selected", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		majComBtn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		majComBtn.setBounds(349, 144, 143, 21);
		cCommentsTab.add(majComBtn);
		
		JButton minComBtn = new JButton("Show Comments");
		minComBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!allMinorTable.getSelectionModel().isSelectionEmpty()) {
					try {
						minCommTbl.setModel(new DefaultTableModel(
								getMinComments((String) allMajorTable.getValueAt(allMajorTable.getSelectedRow(), 0)),
								new String[] {"Student", "Comment"}) {
								boolean[] columnEditables = new boolean[] {
									false, false
								};
								public boolean isCellEditable(int row, int column) {
									return columnEditables[column];
								}
							});
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JFrame frm = new JFrame();
					JOptionPane.showMessageDialog(frm, "Select course!",
							"No course selected", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		minComBtn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		minComBtn.setBounds(349, 417, 143, 21);
		cCommentsTab.add(minComBtn);
		
	}
	
	public static String[][] getMajorCourses() throws SQLException{
		stat = con.connection.prepareStatement("SELECT major.Subjects FROM `major` WHERE major.Name = '"+st.getMajor().getName()+"'");
		rs = stat.executeQuery();
		rs.next();
		String[] row = rs.getString("Subjects").split(",");
		String[][] tblRow = new String[row.length][1];
		int i = 0, j = 0;
		while(i<row.length) { 
			   	if(!st.getMajor().getTakenSubjects().contains(row[i])) {
			   	 tblRow[j][0] = row[i];
			   	 j++;
			   	}
			    i++;
		}
		return tblRow;
	}
	
	public static String[][] getMinorCourses() throws SQLException{
		stat = con.connection.prepareStatement("SELECT minor.Subjects FROM `minor` WHERE minor.Name = '"+st.getMinor().getName()+"'");
		rs = stat.executeQuery();
		rs.next();
		String[] row = rs.getString("Subjects").split(",");
		String[][] tblRow = new String[row.length][1];
		int i = 0, j = 0;
		while(i<row.length) { 
			   	if(!st.getMinor().getTakenSubjects().contains(row[i])) {
			   	 tblRow[j][0] = row[i];
			   	 j++;
			   	}
			    i++;
		}
		return tblRow;
	}
	
	public static String[][] getStudentsMajors(){
		String[][] rows = new String[st.getMajor().getSemesterSubjects().size()][1];
		for(int i = 0; i<st.getMajor().getSemesterSubjects().size(); i++) {
			rows[i][0] = st.getMajor().getSemesterSubjects().get(i);
		}
		
		return rows;
	}
	
	public static String[][] getStudentsMinors(){
		String[][] rows = new String[st.getMinor().getSemesterSubjects().size()][1];
		for(int i = 0; i<st.getMinor().getSemesterSubjects().size(); i++) {
			rows[i][0] = st.getMinor().getSemesterSubjects().get(i);
		}
		
		return rows;
	}
	
	public static String[][] getFriendRequests() throws SQLException{
		stat = con.connection.prepareStatement("SELECT friend_requests.sender as Sender FROM friend_requests WHERE friend_requests.reciever = '"+st.getEmri() + " " + st.getMbiemri()+"'");
		rs = stat.executeQuery();
		stat = con.connection.prepareStatement("SELECT COUNT(*) as Count FROM friend_requests WHERE friend_requests.reciever = '"+st.getEmri() + " " + st.getMbiemri()+"'");
		count  = stat.executeQuery();	count.next();	
		String[][] tblRow = new String[Integer.parseInt(count.getString("Count"))][1];
		int i = 0;
		while(rs.next()) { 
		    	String[] row = new String[1];
			    row[0] = rs.getString("Sender");
			    tblRow[i] = row;
			    i++;
		}
		return tblRow;
	}
	
	public static String[][] getFriends() {
		String[][] friends = new String[st.getFriendsList().size()][1];
		for(int i =0; i<friends.length; i++) {
			friends[i][0] = st.getFriendsList().get(i);
		}
		return friends;
	}

	public static String[][] getUsers(){
		try {
			stat = con.connection.prepareStatement("SELECT student.Emri, student.Mbiemri, major.Name as Major, minor.Name as Minor FROM student INNER JOIN major ON student.id_major = major.Id_major INNER JOIN minor ON student.id_minor = minor.Id_minor");
			rs = stat.executeQuery();
			stat = con.connection.prepareStatement("SELECT COUNT(*) as Count FROM student INNER JOIN major ON student.id_major = major.Id_major INNER JOIN minor ON student.id_minor = minor.Id_minor");
			count  = stat.executeQuery();	
			count.next();
			String[][] tblRow = new String[Integer.parseInt(count.getString("Count"))][3];
			int i = 0;
			while(rs.next()) {
			    if(rs.getString("email").equals(st.getEmail()))  continue;
			    if(st.getFriendsList().contains(rs.getString("Emri") + " " + rs.getString("Mbiemri"))) continue;
			    
			    	String[] row = new String[4];
				    row[0] = rs.getString("Emri") + " " + rs.getString("Mbiemri");
				    row[1] = rs.getString("Major");
				    row[2] = rs.getString("Minor");
				    tblRow[i] = row;
				    i++;
			}
			
			return tblRow;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public String[][] getAllMajors() throws SQLException {
		PreparedStatement ps = con.connection.prepareStatement("SELECT Name FROM `major`");
		ResultSet rs = ps.executeQuery();
		String[][] tblRow = new String[8][1];
		int i = 0;
		while(rs.next()) {
			    tblRow[i][0] = rs.getString("Name");
			    i++;
		}
		
		return tblRow;
	}
	
	public String[][] getAllMinors() throws SQLException {
		PreparedStatement ps = con.connection.prepareStatement("SELECT Name FROM `minor`");
		ResultSet rs = ps.executeQuery();
		String[][] tblRow = new String[5][1];
		int i = 0;
		while(rs.next()) {
			    tblRow[i][0] = rs.getString("Name");
			    i++;
		}
		
		return tblRow;
	}
	
	public static String[][] getMajComments(String course) throws SQLException{
		PreparedStatement ps = con.connection.prepareStatement("SELECT major_course_comments.comment FROM major_course_comments INNER JOIN major ON major.Id_major = major_course_comments.Id_major WHERE major.Name = '"+course+"'");
		ResultSet rs = ps.executeQuery();
		String[][] tblRow = new String[5][2];
		int i = 0;
		while(rs.next()) {
			    String[] comment =  rs.getString("Comment").split("-");
			     tblRow[i][0] = comment[0];
			     tblRow[i][1] = comment[1];
			    i++;
		}
		
		return tblRow;
	}

	public static String[][] getMinComments(String course) throws SQLException{
		PreparedStatement ps = con.connection.prepareStatement("SELECT minor_course_comments.koment FROM minor_course_comments INNER JOIN minor ON minor.Id_minor = minor_course_comments.Id_minor WHERE minor.Name = '"+course+"'");
		ResultSet rs = ps.executeQuery();
		String[][] tblRow = new String[5][2];
		int i = 0;
		while(rs.next()) {
			    String[] comment =  rs.getString("Comment").split("-");
			     tblRow[i][0] = comment[0];
			     tblRow[i][1] = comment[1];
			    i++;
		}
		return tblRow;
	}
}
