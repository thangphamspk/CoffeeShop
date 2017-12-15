package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Order;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PaidOrder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3436444548637322502L;
	private JPanel contentPane;

	public static String customer;
	public static double total;
	public static List<Order> orders;
	public static String date;

	private JLabel[] lblName, lblAmount, lblPrice;

	/**
	 * Create the frame.
	 */
	public PaidOrder() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AdminFrame();
			}
		});
		setBounds(100, 100, 450, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCoffeeHouse = new JLabel("COFFEE HOUSE");
		lblCoffeeHouse.setOpaque(true);
		lblCoffeeHouse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoffeeHouse.setForeground(Color.WHITE);
		lblCoffeeHouse.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblCoffeeHouse.setBackground(new Color(121, 85, 72));
		lblCoffeeHouse.setBounds(0, 0, 432, 57);
		contentPane.add(lblCoffeeHouse);

		JLabel lblCustomerTitle = new JLabel("Khách hàng:");
		lblCustomerTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerTitle.setBounds(0, 76, 118, 30);
		contentPane.add(lblCustomerTitle);

		JLabel lblCustomer = new JLabel("");
		lblCustomer.setText(customer);
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomer.setBounds(130, 76, 302, 30);
		contentPane.add(lblCustomer);

		JLabel lblTotalTitle = new JLabel("Tổng cộng:");
		lblTotalTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotalTitle.setBounds(0, 111, 118, 30);
		contentPane.add(lblTotalTitle);

		JLabel lblTotal = new JLabel("");
		lblTotal.setText(total + " VND");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotal.setBounds(130, 111, 152, 30);
		contentPane.add(lblTotal);

		JLabel lblNewLabel = new JLabel("Cảm ơn quý khách. Hẹn gặp lại !!!!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 589, 432, 16);
		contentPane.add(lblNewLabel);

		JLabel lblDate = new JLabel("");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDate.setText(date);
		lblDate.setBounds(294, 111, 138, 30);
		contentPane.add(lblDate);

		JPanel panel = new JPanel();
		panel.setBounds(0, 151, 432, 430);
		contentPane.add(panel);
		panel.setLayout(null);

		lblName = new JLabel[10];
		lblAmount = new JLabel[10];
		lblPrice = new JLabel[10];

		lblName[0] = new JLabel("");
		lblName[0].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[0].setBounds(0, 13, 201, 30);
		panel.add(lblName[0]);

		lblAmount[0] = new JLabel("");
		lblAmount[0].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[0].setBounds(213, 13, 52, 30);
		panel.add(lblAmount[0]);

		lblPrice[0] = new JLabel("");
		lblPrice[0].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[0].setBounds(277, 13, 155, 30);
		panel.add(lblPrice[0]);

		lblName[1] = new JLabel("");
		lblName[1].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[1].setBounds(0, 56, 201, 30);
		panel.add(lblName[1]);

		lblAmount[1] = new JLabel("");
		lblAmount[1].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[1].setBounds(213, 56, 52, 30);
		panel.add(lblAmount[1]);

		lblPrice[1] = new JLabel("");
		lblPrice[1].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[1].setBounds(277, 56, 155, 30);
		panel.add(lblPrice[1]);

		lblName[2] = new JLabel("");
		lblName[2].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[2].setBounds(0, 99, 201, 30);
		panel.add(lblName[2]);

		lblAmount[2] = new JLabel("");
		lblAmount[2].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[2].setBounds(213, 99, 52, 30);
		panel.add(lblAmount[2]);

		lblPrice[2] = new JLabel("");
		lblPrice[2].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[2].setBounds(277, 99, 155, 30);
		panel.add(lblPrice[2]);

		lblName[3] = new JLabel("");
		lblName[3].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[3].setBounds(0, 142, 201, 30);
		panel.add(lblName[3]);

		lblAmount[3] = new JLabel("");
		lblAmount[3].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[3].setBounds(213, 142, 52, 30);
		panel.add(lblAmount[3]);

		lblPrice[3] = new JLabel("");
		lblPrice[3].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[3].setBounds(277, 142, 155, 30);
		panel.add(lblPrice[3]);

		lblName[4] = new JLabel("");
		lblName[4].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[4].setBounds(0, 185, 201, 30);
		panel.add(lblName[4]);

		lblAmount[4] = new JLabel("");
		lblAmount[4].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[4].setBounds(213, 185, 52, 30);
		panel.add(lblAmount[4]);

		lblPrice[4] = new JLabel("");
		lblPrice[4].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[4].setBounds(277, 185, 155, 30);
		panel.add(lblPrice[4]);

		lblName[5] = new JLabel("");
		lblName[5].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[5].setBounds(0, 228, 201, 30);
		panel.add(lblName[5]);

		lblAmount[5] = new JLabel("");
		lblAmount[5].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[5].setBounds(213, 228, 52, 30);
		panel.add(lblAmount[5]);

		lblPrice[5] = new JLabel("");
		lblPrice[5].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[5].setBounds(277, 228, 155, 30);
		panel.add(lblPrice[5]);

		lblName[6] = new JLabel("");
		lblName[6].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[6].setBounds(0, 271, 201, 30);
		panel.add(lblName[6]);

		lblAmount[6] = new JLabel("");
		lblAmount[6].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[6].setBounds(213, 271, 52, 30);
		panel.add(lblAmount[6]);

		lblPrice[6] = new JLabel("");
		lblPrice[6].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[6].setBounds(277, 271, 155, 30);
		panel.add(lblPrice[6]);

		lblName[7] = new JLabel("");
		lblName[7].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[7].setBounds(0, 314, 201, 30);
		panel.add(lblName[7]);

		lblAmount[7] = new JLabel("");
		lblAmount[7].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[7].setBounds(213, 314, 52, 30);
		panel.add(lblAmount[7]);

		lblPrice[7] = new JLabel("");
		lblPrice[7].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[7].setBounds(277, 314, 155, 30);
		panel.add(lblPrice[7]);

		lblName[8] = new JLabel("");
		lblName[8].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[8].setBounds(0, 357, 201, 30);
		panel.add(lblName[8]);

		lblAmount[8] = new JLabel("");
		lblAmount[8].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[8].setBounds(213, 357, 52, 30);
		panel.add(lblAmount[8]);

		lblPrice[8] = new JLabel("");
		lblPrice[8].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[8].setBounds(277, 357, 155, 30);
		panel.add(lblPrice[8]);

		lblName[9] = new JLabel("");
		lblName[9].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName[9].setBounds(0, 396, 201, 30);
		panel.add(lblName[9]);

		lblAmount[9] = new JLabel("");
		lblAmount[9].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAmount[9].setBounds(213, 396, 52, 30);
		panel.add(lblAmount[9]);

		lblPrice[9] = new JLabel("");
		lblPrice[9].setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice[9].setBounds(277, 396, 155, 30);
		panel.add(lblPrice[9]);

		setVisible(true);
		setLocationRelativeTo(null);
		loadOrder();

	}

	private void loadOrder() {
		int n = orders.size();
		for (int i = 0; i < n; i++) {
			lblName[i].setText(orders.get(i).getName());
			lblAmount[i].setText(orders.get(i).getNumber() + "");
			lblPrice[i].setText(orders.get(i).getPrice() + " VND");
		}
	}
}
