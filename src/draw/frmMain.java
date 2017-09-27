package draw;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import login.frmLogin;
import misel.ObjectSerialization;
import sql.ManageDB;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmMain extends JFrame {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public JPanel contentPane;
	public static frmMain frame;
	public static ArrayList<Shape> shapeList= new ArrayList<Shape>();
	public static ArrayList<Shape> clearedShapeList= new ArrayList<Shape>();
	public Boolean fill = null;
	public Shape shape;
	public JComboBox<Object> comboBox;
	public JComboBox<Object> comboBox_1;
	public JCheckBox checkBox;
	ManageDB manageDB = new ManageDB();

	public void save(){
		if (shapeList.size()!=0){
			int dialogResult = JOptionPane.showConfirmDialog (null, "آیا مایل به ذخیره تصویر هستید؟","هشدار!",JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				manageDB.saveShapeArraylists(frmLogin.userName,ObjectSerialization.objectToBinary(shapeList)
						, ObjectSerialization.objectToBinary(clearedShapeList));
			}
		}
	}

	public frmMain() {
		addWindowListener(new WindowAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void windowOpened(WindowEvent arg0) {
				manageDB.run();
				if (ObjectSerialization.binaryToObject(manageDB.getByteShapeArraylists(frmLogin.userName).get(0))!=null){
					shapeList=(ArrayList<Shape>) ObjectSerialization.binaryToObject(manageDB.getByteShapeArraylists(frmLogin.userName).get(0));
					clearedShapeList=(ArrayList<Shape>) ObjectSerialization.binaryToObject(manageDB.getByteShapeArraylists(frmLogin.userName).get(1));
					repaint();
				}

			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				save();
			}
		});
		setTitle("\u0646\u0642\u0627\u0634\u06CC");


		JPanel panel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
				if (shapeList!=null){
					for ( int counter=0;counter<shapeList.size(); counter++ )
						shapeList.get(counter).draw(g);

					if (shape!=null){
						shape.draw(g);
						clearedShapeList.clear();
					}

				}
			}
		};
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent event) {
				shape.setX2(event.getX());
				shape.setY2(event.getY());
				repaint();
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				fill=checkBox.isSelected();
				shape=SelectionHandler.shapeType((ShapeType)comboBox_1.getSelectedItem(),event.getX(), event.getY(), 
						event.getX(), event.getY(), SelectionHandler.color((Colors)comboBox.getSelectedItem()),fill);
				repaint();
				//gr.drawLine(event.getX(), event.getY(), event.getX(), event.getY());
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				shape.setX2(event.getX());
				shape.setY2(event.getY());
				shapeList.add(shape);
				repaint();
			}
		});

		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(shapeList.size()!=0){
					clearedShapeList.add(shapeList.get(shapeList.size()-1));
					shapeList.remove(shapeList.size()-1);
					shape=null;
					repaint();
				}
			}
		});

		JButton btnRedo = new JButton("");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (clearedShapeList.size()!=0){
					shapeList.add(clearedShapeList.get(clearedShapeList.size()-1));
					clearedShapeList.remove(clearedShapeList.size()-1);
					shape=null;
					repaint();
				}
			}
		});




		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 500, 500);
		contentPane.add(panel);

		comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(Colors.values()));
		comboBox.setBounds(519, 120, 105, 20);
		contentPane.add(comboBox);


		btnRedo.setToolTipText("Redo");
		btnRedo.setIcon(new ImageIcon(frmMain.class.getResource("/resources/redo.png")));
		btnRedo.setBounds(574, 221, 50, 50);
		contentPane.add(btnRedo);

		comboBox_1 = new JComboBox<Object>();
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(ShapeType.values()));
		comboBox_1.setBounds(519, 50, 105, 20);
		contentPane.add(comboBox_1);

		JLabel label = new JLabel("\u0646\u0648\u0639 \u0634\u06A9\u0644");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(520, 25, 104, 14);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u0631\u0646\u06AF \u0634\u06A9\u0644");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(520, 95, 104, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u062A\u0648 \u067E\u0631");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(520, 170, 104, 14);
		contentPane.add(label_2);

		checkBox = new JCheckBox("");
		checkBox.setBounds(561, 191, 26, 23);
		contentPane.add(checkBox);


		btnUndo.setIcon(new ImageIcon(frmMain.class.getResource("/resources/undo.png")));
		btnUndo.setToolTipText("Undo");
		btnUndo.setBounds(520, 221, 50, 50);
		contentPane.add(btnUndo);

		JButton button_1 = new JButton("\u062E\u0631\u0648\u062C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
				System.exit(0);
			}
		});
		button_1.setBounds(520, 334, 104, 23);
		contentPane.add(button_1);

		JButton button = new JButton("پاک کردن");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "آیا از پاک کردن تصویر مطمئن هستید؟","هشدار!",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					shapeList.clear();
					clearedShapeList.clear();
					manageDB.saveShapeArraylists(frmLogin.userName,ObjectSerialization.objectToBinary(shapeList)
							, ObjectSerialization.objectToBinary(clearedShapeList));
					repaint();
				}
			}
		});
		button.setBounds(520, 297, 104, 23);
		contentPane.add(button);

	}
}
