package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeachersMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeachersMenuFrame frame = new TeachersMenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeachersMenuFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TeachersMenuFrame.class.getResource("/resources/eduv2.png")));
		setTitle("Μενού Εκπαιδευτών ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton teachersViewBtn = new JButton("Προβολή Εκπαιδευτών");
		teachersViewBtn.setForeground(new Color(0, 0, 255));
		teachersViewBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		teachersViewBtn.setBounds(124, 57, 158, 52);
		contentPane.add(teachersViewBtn);
		
		JButton insertBtn = new JButton("Εισαγωγή Εκπαιδευτή");
		insertBtn.setForeground(Color.BLUE);
		insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		insertBtn.setBounds(124, 147, 158, 52);
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMainMenuFrame().setEnabled(true);
				Main.getTeachersMenuFrame().setVisible(false);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		closeBtn.setBounds(271, 272, 96, 38);
		contentPane.add(closeBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 241, 393, 1);
		contentPane.add(separator);
	}

}
