package gr.aueb.cf.schoolapp;


import java.sql.Connection;
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

import gr.aueb.cf.schoolapp.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

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
	private JLabel errorLastname;
	private JPanel panel;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JButton closeBtn;

	
	public TeachersUpdateDeleteFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TeachersUpdateDeleteFrame.class.getResource("/resources/eduv2.png")));
		setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				buildTable();	// initial rendering
				idText.setText("");
				firstnameText.setText("");
				lastnameText.setText("");
			}
			@Override
			public void windowActivated(WindowEvent e) {
				buildTable();	// refresh after update / delete
				idText.setText("");
				firstnameText.setText("");
				lastnameText.setText("");
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 870, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		teachersTable = new JTable();
		teachersTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idText.setText((String) model.getValueAt(teachersTable.getSelectedRow(), 0));
				firstnameText.setText((String) model.getValueAt(teachersTable.getSelectedRow(), 1));
				lastnameText.setText((String) model.getValueAt(teachersTable.getSelectedRow(), 2));
			}
		});
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
		lastnameSearchText.setBounds(114, 8, 177, 20);
		contentPane.add(lastnameSearchText);
		lastnameSearchText.setColumns(10);
		
		btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildTable();
			}
		});
		btnSearch.setForeground(new Color(0, 0, 255));
		btnSearch.setBounds(301, 7, 124, 23);
		contentPane.add(btnSearch);
		
		idlabel = new JLabel("Κωδικός");
		idlabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idlabel.setForeground(new Color(0, 0, 255));
		idlabel.setBounds(497, 80, 49, 14);
		contentPane.add(idlabel);
		
		idText = new JTextField();
		idText.setEditable(false);
		idText.setBounds(556, 77, 96, 20);
		contentPane.add(idText);
		idText.setColumns(10);
		
		firstnameLabel = new JLabel("Όνομα");
		firstnameLabel.setForeground(new Color(0, 0, 255));
		firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameLabel.setBounds(505, 125, 41, 14);
		contentPane.add(firstnameLabel);
		
		firstnameText = new JTextField();
		firstnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputFirstname;	
				inputFirstname = firstnameText.getText().trim();	
				validateFirstname(inputFirstname);
				
//				if (inputFirstname.equals("")) {
//					errorFirstname.setText("Το όνομα είναι υποχρεωτικό");
//				}
//				
//				if (!inputFirstname.equals("")) {
//					errorFirstname.setText("");
//				}
			}
		});
		firstnameText.setBounds(556, 122, 177, 20);
		contentPane.add(firstnameText);
		firstnameText.setColumns(10);
		
		lastnameLabel = new JLabel("Επώνυμο");
		lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameLabel.setForeground(new Color(0, 0, 255));
		lastnameLabel.setBounds(497, 175, 49, 14);
		contentPane.add(lastnameLabel);
		
		lastnameText = new JTextField();
		lastnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputLastname;
				inputLastname = lastnameText.getText().trim();
				validateLastname(inputLastname);
			}
		});
		lastnameText.setBounds(556, 172, 177, 20);
		contentPane.add(lastnameText);
		lastnameText.setColumns(10);
		
		errorFirstname = new JLabel("");
		errorFirstname.setForeground(new Color(255, 0, 0));
		errorFirstname.setBounds(556, 147, 177, 20);
		contentPane.add(errorFirstname);
		
		errorLastname = new JLabel("");
		errorLastname.setForeground(new Color(255, 0, 0));
		errorLastname.setBounds(556, 203, 177, 20);
		contentPane.add(errorLastname);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(473, 53, 335, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		updateBtn = new JButton("Ενημέρωση");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Data Binding
				// ToDo if (!idText.getText().trim().isEmpty()) do; else return; 
				int inputId = Integer.parseInt(idText.getText().trim());
				String inputFirstname = firstnameText.getText().trim();
				String inputLastname = lastnameText.getText().trim();
				
				// Validation
				validateFirstname(inputFirstname);
				validateLastname(inputLastname);
				
				if (inputFirstname.isEmpty() || inputLastname.isEmpty()) {
					return;
				}
				
				String sql = "UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?";
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					
					ps.setString(1, inputFirstname);
					ps.setString(2, inputLastname);
					ps.setInt(3, inputId);
					
					int answer = JOptionPane.showConfirmDialog(null, "Είστε σίγουρη/ος", "Ενημέρωση", 
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						int rowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, rowsAffected + " γρααμμή/ες ενημερώθηκαν", "Ενημέρωση", 
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}		
				} catch (SQLException e1) {
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		updateBtn.setForeground(new Color(0, 0, 255));
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		updateBtn.setBounds(473, 315, 156, 59);
		contentPane.add(updateBtn);
		
		deleteBtn = new JButton("Διαγραφή");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "DELETE FROM teachers WHERE id = ?";
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					
					int inputId = Integer.parseInt(idText.getText().trim());
					ps.setInt(1, inputId);
					
					int answer = JOptionPane.showConfirmDialog(null, "Είστε σίγουρη/ος", "Διαγραφή", 
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						int rowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, rowsAffected + " γρααμμή/ες διαγράφηκαν", "Διαγραφή", 
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}							
				} catch (SQLException ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Delete error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteBtn.setForeground(Color.BLUE);
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		deleteBtn.setBounds(652, 315, 156, 59);
		contentPane.add(deleteBtn);
		
		closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenuFrame().setEnabled(true);
				Main.getTeachersUpdateDeleteFrame().setVisible(false);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		closeBtn.setBounds(652, 465, 156, 59);
		contentPane.add(closeBtn);
	}
	
	private void buildTable() {
		
		Vector<String> vector;
		
		String sql = "SELECT id, firstname, lastname FROM teachers WHERE lastname LIKE ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			System.out.println("Build Table entry");
			
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
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,  "Select error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void validateFirstname(String inputFirstname) {
		if (inputFirstname.equals("")) {
			errorFirstname.setText("Το όνομα είναι υποχρεωτικό");
		}
		
		if (!inputFirstname.equals("")) {
			errorFirstname.setText("");
		}
	}
	
	private void validateLastname(String inputLastname) {
		if (inputLastname.equals("")) {
			errorLastname.setText("Το επώνυμο είναι υποχρεωτικό");
		}
		
		if (!inputLastname.equals("")) {
			errorLastname.setText("");
		}
	}
}
