package java_swing_coffee_ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import java_swing_product_service.CoffeeManagementService;
import java_swing_product_service.OutputService;
import jdbc_coffe_study.dto.Product;
import jdbc_coffe_study.dto.Sale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


import javax.swing.JButton;


public class CoffeeManagementUi extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfCode;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tflSaleCnt;
	private JTextField tfMarginRate;
	private CoffeeManagementService saleService;
	private JButton btnOk;
	private JButton btnSalePrice;
	private JButton btnMarginPrice;
	private OutputService productService;

	/**
	 * Create the frame.
	 */
	public CoffeeManagementUi() {
		saleService = new CoffeeManagementService();
		productService = new OutputService();
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 4, 10, 10));

		JLabel lblCode = new JLabel("제품코드");
		lblCode.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCode);

		tfCode = new JTextField();
		tfCode.setColumns(10);
		panel.add(tfCode);

		JLabel lblName = new JLabel("제품명");
		panel.add(lblName);
	
		tfName = new JTextField();
		tfName.setEnabled(false);
		tfName.setColumns(10);
		panel.add(tfName);

		JLabel lblPrice = new JLabel("제품단가");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPrice);

		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		panel.add(tfPrice);

		JLabel lbl_1 = new JLabel("");
		panel.add(lbl_1);

		JLabel lbl_2 = new JLabel("");
		panel.add(lbl_2);

		JLabel lblSaleCnt = new JLabel("판매수량");
		lblSaleCnt.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSaleCnt);

		tflSaleCnt = new JTextField();
		tflSaleCnt.setColumns(10);
		panel.add(tflSaleCnt);

		JLabel lbl_3 = new JLabel("");
		panel.add(lbl_3);

		JLabel lbl_4 = new JLabel("");
		panel.add(lbl_4);

		JLabel lblMarginRate = new JLabel("마진율");
		lblMarginRate.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMarginRate);

		tfMarginRate = new JTextField();
		tfMarginRate.setColumns(10);
		panel.add(tfMarginRate);

		JLabel lbl_5 = new JLabel("");
		panel.add(lbl_5);

		JLabel lbl_6 = new JLabel("");
		panel.add(lbl_6);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnOk = new JButton("입력");
		btnOk.addActionListener(this);
		panel_1.add(btnOk);

		btnSalePrice = new JButton("출력1(판매금액순위)");
		btnSalePrice.addActionListener(this);
		panel_1.add(btnSalePrice);

		btnMarginPrice = new JButton("출력2(마진액순위)");
		btnMarginPrice.addActionListener(this);
		panel_1.add(btnMarginPrice);
		
		tfCode.getDocument().addDocumentListener(new MyDocumentListener() {
			
			@Override
			public void warning() {
				if (tfCode.getText().length() == 4) {
					
					Product pdt = new Product(tfCode.getText().trim());
					try {
						Product searchPdt = saleService.searchProduct(pdt);
						
						tfName.setText(searchPdt.getName());
						
					} catch (SQLException e) {
						e.printStackTrace();

					} catch(NullPointerException e) {
						tfName.setText("해당제품이 없습니다.");
					}
				}

			}
		});
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnMarginPrice) {
			do_btnMarginPrice_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnSalePrice) {
			do_btnSalePrice_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnOk) {
			do_btnOk_actionPerformed(arg0);
		}
	}

	protected void do_btnOk_actionPerformed(ActionEvent arg0) {
		Sale sale = getSale();

		int res;
		try {
			res = saleService.registerSale(sale);
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "추가했습니다.");
			}
			clearTf();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private Sale getSale() {
		String code = tfCode.getText().trim();
		int price = Integer.parseInt(tfPrice.getText().trim());
		int saleCnt = Integer.parseInt(tflSaleCnt.getText().trim());
		int marginRate = Integer.parseInt(tfMarginRate.getText().trim());

		return new Sale(0, new Product(code), price, saleCnt, marginRate);
	}

	private void clearTf() {
		tfCode.setText("");
		tfPrice.setText("");
		tfName.setText("");
		tflSaleCnt.setText("");
		tfMarginRate.setText("");

	}

	protected void do_btnSalePrice_actionPerformed(ActionEvent arg0) {
		OutputUi ui = new OutputUi(true);
		ui.setVisible(true);
	}
	
	protected void do_btnMarginPrice_actionPerformed(ActionEvent arg0) {
		OutputUi ui = new OutputUi(false);
		ui.setVisible(true);
	}
}
