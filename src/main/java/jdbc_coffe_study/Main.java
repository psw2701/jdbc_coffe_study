package jdbc_coffe_study;

import java.awt.EventQueue;

import java_swing_coffee_ui.CoffeeManagementUi;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoffeeManagementUi frame = new CoffeeManagementUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
