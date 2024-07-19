package gr.aueb.cf.schoolapp;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class TeachersUpdateDeleteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable teachersTable;
	private DefaultTableModel model = new DefaultTableModel();
	private JLabel lastnameSearchLabel;
	private JTextField lastnameSearchText;
	private JButton btnSearch;
	private JLabel idlabel;
	private JTextField idText;
	private JLabel firstnameLabel;
	private JTextField firstnameText;
	private JLabel lastnameLabel;
	private JTextField lastnameText;
	private JLabel errorFirstname;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JButton closeBtn;

	
	public TeachersUpdateDeleteFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		teachersTable = new JTable();
		teachersTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Κωδικός", "Όνομα", "Επώνυνο"}
		));
		
		model = (DefaultTableModel) teachersTable.getModel();
		
		teachersTable.setBounds(53, 53, 387, 498);
		contentPane.add(teachersTable);
		
		JScrollPane scrollPane = new JScrollPane(teachersTable);
		scrollPane.setBounds(53, 53, 387, 498);
		contentPane.add(scrollPane);
		
		lastnameSearchLabel = new JLabel("Επώνυμο");
		lastnameSearchLabel.setForeground(new Color(128, 0, 64));
		lastnameSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameSearchLabel.setBounds(57, 11, 58, 14);
		contentPane.add(lastnameSearchLabel);
		
		lastnameSearchText = new JTextField();
		lastnameSearchText.setBounds(128, 8, 177, 20);
		contentPane.add(lastnameSearchText);
		lastnameSearchText.setColumns(10);
		
		btnSearch = new JButton("Αναζήτηση");
		btnSearch.setForeground(new Color(0, 0, 255));
		btnSearch.setBounds(328, 7, 89, 23);
		contentPane.add(btnSearch);
		
		idlabel = new JLabel("Κωδικός");
		idlabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idlabel.setForeground(new Color(0, 0, 255));
		idlabel.setBounds(497, 80, 49, 14);
		contentPane.add(idlabel);
		
		idText = new JTextField();
		idText.setBounds(556, 77, 96, 20);
		contentPane.add(idText);
		idText.setColumns(10);
		
		firstnameLabel = new JLabel("Όνομα");
		firstnameLabel.setForeground(new Color(0, 0, 255));
		firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameLabel.setBounds(505, 125, 41, 14);
		contentPane.add(firstnameLabel);
		
		firstnameText = new JTextField();
		firstnameText.setBounds(556, 122, 177, 20);
		contentPane.add(firstnameText);
		firstnameText.setColumns(10);
		
		lastnameLabel = new JLabel("Επώνυμο");
		lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameLabel.setForeground(new Color(0, 0, 255));
		lastnameLabel.setBounds(497, 175, 49, 14);
		contentPane.add(lastnameLabel);
		
		lastnameText = new JTextField();
		lastnameText.setBounds(556, 172, 177, 20);
		contentPane.add(lastnameText);
		lastnameText.setColumns(10);
		
		errorFirstname = new JLabel("");
		errorFirstname.setBounds(556, 147, 177, 20);
		contentPane.add(errorFirstname);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(556, 203, 177, 20);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(473, 53, 335, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		updateBtn = new JButton("Ενημέρωση");
		updateBtn.setForeground(new Color(0, 0, 255));
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		updateBtn.setBounds(473, 315, 156, 59);
		contentPane.add(updateBtn);
		
		deleteBtn = new JButton("Διαγραφή");
		deleteBtn.setForeground(Color.BLUE);
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		deleteBtn.setBounds(652, 315, 156, 59);
		contentPane.add(deleteBtn);
		
		closeBtn = new JButton("Κλείσιμο");
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		closeBtn.setBounds(652, 465, 156, 59);
		contentPane.add(closeBtn);
	}
	
	private void buildTable() {
		
		Vector<String> vector;
		
		try {
			
			String sql = "SELECT id, firstname, lastname FROM teachers WHERE lastname LIKE ?";
			PreparedStatement ps = MainMenuFrame.getConnection().prepareStatement(sql);
			ps.setString(1, lastnameSearchText.getText().trim() + "%" );
			
			ResultSet rs = ps.executeQuery();
			
			// Clear model -> clear table - MVVM
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			
			while (rs.next()) {
				vector = new Vector<>(3);
				vector.add(rs.getString("id"));
				vector.add(rs.getString("firstname"));
				vector.add(rs.getString("lastname"));
				
				model.addRow(vector);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
