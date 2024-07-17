package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

public class Main {

	private final static MainMenuFrame mainMenuFrame = new MainMenuFrame();
	private final static TeachersMenuFrame teachersMenuFrame = new TeachersMenuFrame();
	private final static TeachersInsertFrame teachersInsertFrame = new TeachersInsertFrame();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					mainMenuFrame.setLocationRelativeTo(null);
					mainMenuFrame.setVisible(true);
					
					teachersMenuFrame.setLocationRelativeTo(null);
					teachersMenuFrame.setVisible(false);
					
					teachersInsertFrame.setLocationRelativeTo(null);
					teachersInsertFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static MainMenuFrame getMainMenuFrame() {
		return mainMenuFrame;
	}

	public static TeachersMenuFrame getTeachersMenuFrame() {
		return teachersMenuFrame;
	}

	public static TeachersInsertFrame getTeachersInsertFrame() {
		return teachersInsertFrame;
	}
	
	
	
	
	

}
