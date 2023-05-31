package mini_project;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.http.WebSocket.Listener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewStart extends JFrame implements ActionListener {
	JPanel contentPane;
	JLabel imageLabel = new JLabel();
	JLabel headerLabel1 = new JLabel();

	JLabel la = new JLabel();  
	JButton b1 = new JButton("Yes!!");
	JButton b2 = new JButton("No...");
	String s = "";

	public ViewStart() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			contentPane = (JPanel) getContentPane();
			contentPane.setLayout(new BorderLayout());
			setSize(new Dimension(560, 600));
			setTitle("Get In Shape 31");

			headerLabel1.setText("Get In Shape는 31일간 진행되는 운동 챌린지입니다!");

			contentPane.add(headerLabel1);
			headerLabel1.setBounds(140,15,450,20); 


			la.setText("도전하겠습니까?"); 
			contentPane.add(la);
			la.setBounds(140,30,100,30);  

			contentPane.add(b1);
			contentPane.add(b2);

			b1.setBounds(140,60,80,30);  
			b2.setBounds(250,60,80,30); 

			b1.addActionListener(this);
			b2.addActionListener(this);

			ImageIcon ii = new ImageIcon(this.getClass().getResource("test.gif"));
			imageLabel.setIcon(ii);
			contentPane.add(imageLabel);
			contentPane.setBounds(20,50,450,20); 

			this.setLocationRelativeTo(null);
			this.setVisible(true);

			
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		EventClass ec = new EventClass();
		
		if (e.getSource() == b1) {
			s = "true";
		}

		if (e.getSource() == b2) {
			s = "false";
		}
		setVisible(false);
		ec.startProgram(s);

	}

}
