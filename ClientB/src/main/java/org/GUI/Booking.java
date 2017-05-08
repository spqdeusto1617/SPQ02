package org.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;

import org.models.Book;
import org.models.User;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class Booking extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static User newUser;
	private JButton btnexit;



	public static User j = null;
	private JTextField tfSearch;
	private JDatePickerImpl datePicker, datePicker2;
	private UtilDateModel model,model2;
	private JDatePanelImpl datePanel, datePanel2;
	private JTable table;
	private JScrollPane scrollTable;
	private JButton btnFind;
	private JButton btnBook;
	private JLabel lblBy;

	public Booking() {
		newUser = new User();

		BackgroundBooking background = new BackgroundBooking();
		background.setLayout(null);
		getContentPane().add(background, BorderLayout.CENTER);

		
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(281, 190, 225, 25);
		background.add(datePicker);
		
		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2);
		datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setBounds(692, 190, 225, 25);
		background.add(datePicker2);
		
		
		btnexit = new JButton("Exit");
		btnexit.setFont(new Font("Yu Gothic", Font.PLAIN, 24));
		btnexit.setBounds(33, 652, 200, 50);
		background.add(btnexit);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBackground(new Color(240, 255, 240));
		lblSearch.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 37));
		lblSearch.setForeground(new Color(255, 255, 255));
		lblSearch.setBounds(281, 27, 127, 40);
		background.add(lblSearch);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(281, 80, 430, 40);
		background.add(tfSearch);
		tfSearch.setColumns(10);
		
		btnFind = new JButton("FIND");
		btnFind.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 60));
		btnFind.setBackground(Color.BLACK);
		btnFind.setForeground(Color.WHITE);
		btnFind.setBounds(485, 250, 209, 68);
		background.add(btnFind);
		
		table = new JTable(new DefaultTableModel());
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		String titles [] = {"ID","Title","Author Name", "Author Surname", "Genre", "Description","Published date","Pages","Age limit","Amount"};
		for (int i = 0; i < titles.length; i++) {
			dtm.addColumn(titles[i]);
		}
		LinkedList<Book>books = new LinkedList<Book>();
		//books.add(new Book("1212", "Romeo y Julieta","William", "Shakespeare","Drama", "They die", 2012190383 , 300, 13, 7));
		for (int i = 0; i <books.size(); i++) {
			Book book = books.get(i);
			
			String fila [] = {book.getId(),book.getTitle(),book.getAuthorFirstName(),book.getAuthorLastName(), book.getGenre(), book.getDescription(), Long.toString(book.getPublishDate()), Integer.toString(book.getPages()), Integer.toString(book.getAgeLimit()), Integer.toString(book.getCount()) };
				dtm.addRow(fila);
		}
		table.setModel(dtm);
		table.setBounds(201, 331, 757, 284);
		background.add("Center",table);
		
		btnBook = new JButton("BOOK IT");
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 33));
		btnBook.setBackground(Color.BLACK);
		btnBook.setForeground(Color.WHITE);
		btnBook.setBounds(457, 690, 273, 50);
		background.add(btnBook);
		
		lblBy = new JLabel("By");
		lblBy.setForeground(Color.WHITE);
		lblBy.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 37));
		lblBy.setBackground(new Color(240, 255, 240));
		lblBy.setBounds(743, 27, 66, 40);
		background.add(lblBy);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Title", "Author", "Genre", "Ranking"}));
		comboBox_1.setBounds(743, 80, 174, 40);
		background.add(comboBox_1);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 37));
		lblFrom.setBackground(new Color(240, 255, 240));
		lblFrom.setBounds(281, 150, 127, 40);
		background.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 37));
		lblTo.setBackground(new Color(240, 255, 240));
		lblTo.setBounds(692, 150, 127, 40);
		background.add(lblTo);
		
		table.setVisible(false);
		btnBook.setVisible(false);
		btnexit.addActionListener(this);
		btnFind.addActionListener(this);
		btnBook.addActionListener(this);

		this.setSize(1200, 800);
		this.setResizable(false);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = getSize();
		setLocation((pantalla.width - ventana.width) / 2,
				(pantalla.height - ventana.height) / 2);

		this.setVisible(true);
	}



	public static void main(String[] args) {
		Booking x = new Booking();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		JButton botonPulsado = (JButton) e.getSource();

		if (botonPulsado == btnexit) {
			System.exit(0);
		} else if (botonPulsado == btnFind) {
			table.setVisible(true);
			btnBook.setVisible(true);
			} else if (botonPulsado== btnBook){
			//Introduce in the database the information and send the confirmation email
			}
		}
	}


class BackgroundBooking extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		Dimension tamaño = getSize();
		ImageIcon imagenFondo = new ImageIcon(new ImageIcon(getClass()
				.getResource("/images/bgbooking.jpg")).getImage());
		g.drawImage(imagenFondo.getImage(), 0, 0, tamaño.width, tamaño.height,
				null);

	}
}
