import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login {
	
	private String userID;
	private String userPW;
	
	public Login(String ID, String PW) {
		userID = ID;
		userPW = PW;
	}
	
	public Login() {
		userID = "";
		userPW = "";
	}
	
	public class IDExist extends JFrame implements ActionListener {
		
		public IDExist() {
			setTitle("Error");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(400, 150);
			setBackground(Color.white);
			setLayout(new BorderLayout());
			
			Font font = new Font("³ª´®¼Õ±Û¾¾ Ææ", Font.PLAIN, 30);
			
			JLabel textLabel = new JLabel("ÀÌ¹Ì Á¸ÀçÇÏ´Â IDÀÔ´Ï´Ù!");
			textLabel.setFont(font);
			textLabel.setHorizontalAlignment(JLabel.CENTER);
			textLabel.setBackground(Color.white);
			add(textLabel, BorderLayout.CENTER);
			
			JPanel OKPanel = new JPanel();
			OKPanel.setLayout(new FlowLayout());
			JButton OKBtn = new JButton("OK");
			OKBtn.addActionListener(this);
			OKPanel.add(OKBtn);
			add(OKPanel, BorderLayout.SOUTH);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();
			if (action.equals("OK"))
				dispose();
		}
	}
	
	public class LoginError extends JFrame implements ActionListener {
		
		public LoginError() {
			setTitle("ID or PW Error");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(400, 150);
			setBackground(Color.white);
			setLayout(new BorderLayout());
			
			Font font = new Font("³ª´®¼Õ±Û¾¾ Ææ", Font.PLAIN, 30);
			
			JLabel textLabel = new JLabel("ID°¡ ¾ø°Å³ª ºñ¹Ð¹øÈ£°¡ Æ²·È¾î¿ä!");
			textLabel.setFont(font);
			textLabel.setHorizontalAlignment(JLabel.CENTER);
			textLabel.setBackground(Color.white);
			add(textLabel, BorderLayout.CENTER);
			
			JPanel OKPanel = new JPanel();
			OKPanel.setLayout(new FlowLayout());
			JButton OKBtn = new JButton("OK");
			OKBtn.addActionListener(this);
			OKPanel.add(OKBtn);
			add(OKPanel, BorderLayout.SOUTH);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();
			if (action.equals("OK"))
				dispose();
		}
	}
	
	public class Hi extends JFrame implements ActionListener {
		
		public Hi() {
			setTitle("Hi!");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(400, 150);
			setBackground(Color.white);
			setLayout(new BorderLayout());
			
			Font font = new Font("³ª´®¼Õ±Û¾¾ Ææ", Font.PLAIN, 30);
			
			JLabel textLabel = new JLabel("¾È³çÇÏ¼¼¿ä!");
			textLabel.setFont(font);
			textLabel.setHorizontalAlignment(JLabel.CENTER);
			textLabel.setBackground(Color.white);
			add(textLabel, BorderLayout.CENTER);
			
			JPanel OKPanel = new JPanel();
			OKPanel.setLayout(new FlowLayout());
			JButton OKBtn = new JButton("OK");
			OKBtn.addActionListener(this);
			OKPanel.add(OKBtn);
			add(OKPanel, BorderLayout.SOUTH);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();
			if (action.equals("OK"))
				dispose();
		}
	}
	
	public String sign_up (String id, String pw) {
		String currentProjPath = "";
		try {
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String dataPath = currentProjPath + "/dat/userdata/" + id + ".dat";
		File user = new File(dataPath);
		
		if (user.exists()) {
			IDExist error = new IDExist();
			error.setVisible(true);
		} else {
			Userdata newUser = new Userdata(id, pw);
			ObjectOutputStream outputStream = null;
			
			try {	
				outputStream = new ObjectOutputStream(new FileOutputStream(dataPath));
				outputStream.writeObject(newUser);
				outputStream.close();
			} catch(IOException e) {
				System.err.println("Problem with file output.");
			}
			
			Hi hello = new Hi();
			hello.setVisible(true);
			return "hi";
		}
		return "err";
	}
	
	public boolean sign_in (String id, String pw) {
		String currentProjPath = "";
		try {
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String dataPath = currentProjPath + "/dat/userdata/" + id + ".dat";
		File user = new File(dataPath);
		
		if (user.exists()) {
			ObjectInputStream inputBin = null;
			
			try {
				Userdata hiUser = new Userdata();
			
				inputBin = new ObjectInputStream(new FileInputStream(dataPath));
				hiUser = (Userdata)inputBin.readObject();
				inputBin.close();
				
				if (hiUser.getPW().equals(userPW)) return true;
				else {
					LoginError le = new LoginError();
					le.setVisible(true);
					return false;
				}
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch(ClassNotFoundException e) {
				System.err.println("No Class");
			} catch(IOException e) {
				System.err.println("Problem with file input.");
			}
		} else {
			LoginError le = new LoginError();
			le.setVisible(true);
			return false;
		}
		return false;
	}
}

