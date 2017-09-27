package login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import draw.frmMain;
import misel.FormStyle;
import sql.ManageDB;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class frmLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public static frmLogin frame;
	ManageDB manageDB = new ManageDB();
	frmMain frm;
	public static String userName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new frmLogin();
					frame.setLocationRelativeTo(null);
					FormStyle.changeFont( frame.contentPane, Font.getFont("Tahoma"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	JButton button1 = new JButton("\u0648\u0631\u0648\u062F");

	/**
	 * Create the frame.
	 */
	public frmLogin() {
		setFont(new Font("Tahoma", Font.PLAIN, 11));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				manageDB.closeConnections();
			}
		});
		setTitle("\u0648\u0631\u0648\u062F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 232, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 139, 20);
		contentPane.add(textField);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int a = arg0.getKeyCode();
		        if (a == 10) {                     
		          passwordField.requestFocus();
		        } 
			}
		});
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u0646\u0627\u0645 \u06A9\u0627\u0631\u0628\u0631\u06CC");
		label.setBounds(159, 14, 46, 14);
		contentPane.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 42, 139, 20);
		contentPane.add(passwordField);
		
		JLabel label_1 = new JLabel("\u06A9\u0644\u0645\u0647 \u0639\u0628\u0648\u0631 ");
		label_1.setBounds(159, 45, 48, 14);
		contentPane.add(label_1);
		button1.setBounds(118, 85, 89, 23);
		contentPane.add(button1);
		
		JButton button_1 = new JButton("\u0627\u0646\u0635\u0631\u0627\u0641");
		button_1.setBounds(10, 85, 89, 23);
		contentPane.add(button_1);
		//		button_1.setFont(UIManager.getFont("Button.font"));
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
		button1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
			}
		});
		
		//JButton button1 = new JButton("\u0648\u0631\u0648\u062F");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ManageDB manageDB = new ManageDB();
				manageDB.run();
				
				try {
					loginProcess();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int a = arg0.getKeyCode();
		        if (a == 10) {   	// for enter key
		        	manageDB.run();
					
					try {
						loginProcess();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        } 
			}
		});
	}
	public void loginProcess() throws HeadlessException, SQLException{
		if(manageDB.getUser(textField.getText(), String.valueOf(passwordField.getPassword()))){
			userName=textField.getText();
			frm = new frmMain();
			frm.setLocationRelativeTo(null);
			FormStyle.changeFont(frm.contentPane , Font.getFont("Tahoma"));
			frm.setTitle(frm.getTitle()+ " - " + userName);
			frm.setVisible(true);
			frame.setVisible(false);
		}
		else
			JOptionPane.showMessageDialog(null, "نام کاربری یا کلمه عبور اشتباه است", "خطا", JOptionPane.CLOSED_OPTION);
	}
}
