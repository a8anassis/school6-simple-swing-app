package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel footer = new JPanel();
	private static Connection connection;

	public MainMenuFrame() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuFrame.class.getResource("/resources/eduv2.png")));
		
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent e) {
//				
//				String sql = "jdbc:mysql://localhost:3306/school6db?serverTimeZone=UTC";
//				String username = "userdb6";
//				String password = "12345";		// Password should not appear (not be visible) in code-base.
//				
//				try {
//					connection = DriverManager.getConnection(sql, username, password);
//					System.out.println("Connection Success");
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				
//			}
//		});
		
		setBackground(new Color(255, 255, 255));
		setTitle("Ποιότητα στην Εκπαίδευση");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator headerSeparator = new JSeparator();
		headerSeparator.setBackground(new Color(0, 255, 255));
		headerSeparator.setBounds(-1, 57, 437, 3);
		contentPane.add(headerSeparator);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0, 0, 164));
		header.setBounds(-1, 0, 437, 60);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel codingFactoryLabel = new JLabel("Coding Factory");
		codingFactoryLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		codingFactoryLabel.setForeground(new Color(255, 255, 255));
		codingFactoryLabel.setBounds(151, 11, 131, 25);
		header.add(codingFactoryLabel);
		
		JButton teachersBtn = new JButton("");
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenuFrame().setVisible(true);
				Main.getMainMenuFrame().setEnabled(false);
			}
		});
		teachersBtn.setBounds(10, 108, 40, 40);
		contentPane.add(teachersBtn);
		
		JLabel teachersLabel = new JLabel("Εκπαιδευτές");
		teachersLabel.setForeground(new Color(0, 0, 255));
		teachersLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		teachersLabel.setBounds(54, 108, 97, 40);
		contentPane.add(teachersLabel);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		studentsBtn.setBounds(10, 171, 40, 40);
		contentPane.add(studentsBtn);
		
		JLabel studentsLabel = new JLabel("Εκπαιδευόμενοι");
		studentsLabel.setForeground(Color.BLUE);
		studentsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		studentsLabel.setBounds(54, 171, 111, 40);
		contentPane.add(studentsLabel);
		footer.setBackground(new Color(217, 217, 217));
		footer.setBounds(-1, 271, 438, 65);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JButton btnManual = new JButton("Εγχειρίδιο Χρήσης");
		btnManual.setForeground(new Color(13, 0, 255));
		btnManual.setBounds(10, 11, 139, 32);
		footer.add(btnManual);
	}
}
