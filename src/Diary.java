import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Diary extends JFrame implements ActionListener {

	private login_panel lp;
	private sign_up_panel sup;
	private diary_panel dp;
	private schedule_panel sp;
	
	public static final int BOUND = 900;
	
	public Diary() {
		setTitle("My Diary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(BOUND, BOUND);
		setBackground(Color.blue);
		setResizable(false);
		setLayout(null);

		// Test Login Panel
		lp = new login_panel();
		add(lp);
		lp.setVisible(true);
		lp.setBounds(0, 0, 900, 900);
		
		sup = new sign_up_panel();
		add(sup);
		sup.setVisible(false);
		sup.setBounds(0, 0, 900, 900);
		
		dp = new diary_panel();
		add(dp);
		dp.setVisible(false);
		dp.setBounds(0, 0, 900, 900);
		
		sp = new schedule_panel();
		add(sp);
		sp.setVisible(false);
		sp.setBounds(0, 0, 900, 900);
	}
	
	public static void main(String[] args) {
		Diary myDiary = new Diary();
		myDiary.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	// 로그인 창
	public class login_panel extends JPanel implements ActionListener {

		private JTextArea IDArea;
		private JTextArea PWArea;

		public login_panel() {
			setSize(BOUND, BOUND);
			setLayout(null);

			String currentProjPath = "";
			try {
				currentProjPath = new File(".").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}

			JLabel bg = new JLabel();
			String LoginGIFPath = currentProjPath + "/src/img/login_page.gif";
			ImageIcon Login = new ImageIcon(LoginGIFPath);
			bg.setIcon(Login);
			add(bg);
			bg.setBounds(-10, -20, 900, 900);

			IDArea = new JTextArea();
			bg.add(IDArea);
			IDArea.setOpaque(false);
			IDArea.setBounds(258, 500, 400, 100);

			PWArea = new JTextArea();
			bg.add(PWArea);
			PWArea.setOpaque(false);
			PWArea.setBounds(258, 650, 400, 100);

			String EntImgPath = currentProjPath + "/src/img/enter_btn.jpg";
			ImageIcon entIcon = new ImageIcon(EntImgPath);
			JButton ok = new JButton("ok");
			ok.setBackground(Color.white);
			ok.setFocusPainted(false);
			//ok.setContentAreaFilled(false);
			ok.setIcon(entIcon);
			ok.addActionListener(this);
			bg.add(ok);
			ok.setBounds(700, 500, 100, 100);

			String Sign_upImgPath = currentProjPath + "/src/img/sign_up_btn.jpg";
			ImageIcon sign_upIcon = new ImageIcon(Sign_upImgPath);
			JButton sign_up = new JButton("sign_up");
			sign_up.setBackground(Color.white);
			sign_up.setFocusPainted(false);
			//sign_up.setContentAreaFilled(false);
			sign_up.setIcon(sign_upIcon);
			sign_up.addActionListener(this);
			bg.add(sign_up);
			sign_up.setBounds(700, 650, 100, 100);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();

			if (action.equals("ok"))
			{
				String ID = IDArea.getText();
				String PW = PWArea.getText();
				Login user = new Login (ID, PW);
				
				if (user.sign_in(ID, PW)) {
					// 다이어리로
					setVisible(false);
					dp.setVisible(true);
				}
			} else {
				//회원가입으로
				setVisible(false);
				sup.setVisible(true);
			}
		}
	}

	//
	public class sign_up_panel extends JPanel implements ActionListener {
		
		private JTextArea inputIDArea;
		private JTextArea inputPWArea;
		
		public sign_up_panel() {
			setSize(BOUND, BOUND);
			setLayout(null);

			String currentProjPath = "";
			try {
				currentProjPath = new File(".").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			JLabel bg = new JLabel();
			String Sign_up_BG_Path = currentProjPath + "/src/img/Sign_up_BG.jpg";
			ImageIcon Login = new ImageIcon(Sign_up_BG_Path);
			bg.setIcon(Login);
			add(bg);
			bg.setBounds(-10, -20, 900, 900);
			
			inputIDArea = new JTextArea();
			bg.add(inputIDArea);
			inputIDArea.setOpaque(false);
			inputIDArea.setBounds(255, 400, 400, 100);

			inputPWArea = new JTextArea();
			bg.add(inputPWArea);
			inputPWArea.setOpaque(false);
			inputPWArea.setBounds(255, 550, 400, 100);
			
			String EntImgPath = currentProjPath + "/src/img/enter_btn.jpg";
			ImageIcon entIcon = new ImageIcon(EntImgPath);
			JButton ok = new JButton("ok");
			ok.setBackground(Color.white);
			ok.setFocusPainted(false);
			//ok.setContentAreaFilled(false);
			ok.setIcon(entIcon);
			ok.addActionListener(this);
			bg.add(ok);
			ok.setBounds(700, 550, 100, 100);
			
			JButton back = new JButton("back");
			back.setText("");
			back.setBorderPainted(false);
			back.setFocusPainted(false);
			back.setContentAreaFilled(false);
			back.addActionListener(this);
			bg.add(back);
			back.setOpaque(false);
			back.setBounds(54, 53, 100, 100);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();

			if (action.equals("ok")) {
				String inputID = inputIDArea.getText();
				String inputPW = inputPWArea.getText();
				Login inputUser = new Login();
				inputUser.sign_up(inputID, inputPW);
				lp.setVisible(true);
				setVisible(false);
			} else {
				lp.setVisible(true);
				setVisible(false);
			}
		}
	}

	// 일기 창
	public class diary_panel extends JPanel implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	// 일정 창
	public class schedule_panel extends JPanel implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
