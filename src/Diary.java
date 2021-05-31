import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

import javax.swing.*;

public class Diary extends JFrame implements ActionListener {

	private login_panel lp;
	private sign_up_panel sup;
	private diary_panel dp;
	private schedule_panel sp;

	private boolean is_diary = false;
	private boolean is_sche = false;

	public static final int BOUND = 900;

	Font font4 = new Font("양라벨도트고딕", Font.PLAIN, 30);

	public Diary() {
		setTitle("My Diary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(BOUND, BOUND);
		setBackground(Color.blue);
		setResizable(false);
		setLayout(null);

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
		private JPasswordField PWArea;

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

			String LoginGIFPath = currentProjPath + "/src/img/Main.gif";
			ImageIcon Login = new ImageIcon(LoginGIFPath);
			bg.setIcon(Login);
			add(bg);
			bg.setBounds(-10, -20, 900, 900);

			Font font = new Font("DX유니고딕 30", Font.PLAIN, 50);

			IDArea = new JTextArea();
			bg.add(IDArea);
			IDArea.setFont(font);
			IDArea.setOpaque(false);
			IDArea.setBounds(258, 520, 400, 100);

			PWArea = new JPasswordField();
			PWArea.setEchoChar('*');
			bg.add(PWArea);
			PWArea.setFont(font);
			PWArea.setBorder(BorderFactory.createEmptyBorder());
			PWArea.setOpaque(false);
			PWArea.setBounds(258, 650, 400, 100);

			String EntImgPath = currentProjPath + "/src/img/enter_btn.jpg";
			ImageIcon entIcon = new ImageIcon(EntImgPath);
			JButton ok = new JButton("ok");
			ok.setBackground(Color.white);
			ok.setFocusPainted(false);
			// ok.setContentAreaFilled(false);
			ok.setIcon(entIcon);
			ok.addActionListener(this);
			bg.add(ok);
			ok.setBounds(700, 500, 100, 100);

			String Sign_upImgPath = currentProjPath + "/src/img/sign_up_btn.jpg";
			ImageIcon sign_upIcon = new ImageIcon(Sign_upImgPath);
			JButton sign_up = new JButton("sign_up");
			sign_up.setBackground(Color.white);
			sign_up.setFocusPainted(false);
			// sign_up.setContentAreaFilled(false);
			sign_up.setIcon(sign_upIcon);
			sign_up.addActionListener(this);
			bg.add(sign_up);
			sign_up.setBounds(700, 650, 100, 100);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();

			if (action.equals("ok")) {
				String ID = IDArea.getText();
				String PW = PWArea.getText();
				Login user = new Login(ID, PW);

				if (user.sign_in(ID, PW)) {
					// 다이어리로
					setVisible(false);
					is_diary = true;
					dp.setVisible(true);
				}
			} else {
				// 회원가입으로
				setVisible(false);
				sup.setVisible(true);
			}
		}
	}

	//
	public class sign_up_panel extends JPanel implements ActionListener {

		private JTextArea inputIDArea;
		private JPasswordField inputPWArea;

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
			String Sign_up_BG_Path = currentProjPath + "/src/img/signup2.jpg";
			ImageIcon Login = new ImageIcon(Sign_up_BG_Path);
			bg.setIcon(Login);
			add(bg);
			bg.setBounds(-10, -20, 900, 900);

			Font font = new Font("DX유니고딕 30", Font.PLAIN, 50);

			inputIDArea = new JTextArea();
			bg.add(inputIDArea);
			inputIDArea.setFont(font);
			inputIDArea.setOpaque(false);
			inputIDArea.setBounds(255, 425, 400, 100);

			inputPWArea = new JPasswordField();
			inputPWArea.setEchoChar('*');
			bg.add(inputPWArea);
			inputPWArea.setFont(font);
			inputPWArea.setBorder(BorderFactory.createEmptyBorder());
			inputPWArea.setOpaque(false);
			inputPWArea.setBounds(255, 550, 400, 100);

			String EntImgPath = currentProjPath + "/src/img/enter_btn.jpg";
			ImageIcon entIcon = new ImageIcon(EntImgPath);
			JButton ok = new JButton("ok");
			ok.setBackground(Color.white);
			ok.setFocusPainted(false);
			// ok.setContentAreaFilled(false);
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
				String com = inputUser.sign_up(inputID, inputPW);

				if (com.equals("hi")) {
					lp.setVisible(true);
					setVisible(false);
				}
			} else {
				lp.setVisible(true);
				setVisible(false);
			}
		}
	}

	// 일기 창
	public class diary_panel extends JPanel implements ActionListener {

		private JAVADiaryCalendar mc = new JAVADiaryCalendar();
		private JTextArea schedule_area = new JTextArea();
		private JButton weather_btn;
		private JButton covid_btn;
		private JButton lucky_btn;
		private JButton sche_btn;
		private JTextField year = new JTextField();

		// 운세 출력하는 페이지
		public class print_fortune extends JFrame implements ActionListener {

			public print_fortune(int year) {
				setTitle("Your fortune");
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setSize(1800, 150);
				setBackground(Color.white);
				setLayout(new BorderLayout());

				Font font = new Font("나눔손글씨 펜", Font.PLAIN, 25);

				Fortune luck = new Fortune(year);
				luck.run();
				try {
					luck.join();
				} catch (Exception e) {
					e.printStackTrace();
				}

				JLabel textLabel = new JLabel(luck.getLucky());
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

		// 운세 오류 페이지
		public class InputError extends JFrame implements ActionListener {

			public InputError() {
				setTitle("Input error");
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setSize(400, 150);
				setBackground(Color.white);
				setLayout(new BorderLayout());

				Font font = new Font("나눔손글씨 펜", Font.PLAIN, 30);

				JLabel textLabel = new JLabel("년도를 입력해주세요!");
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

		// 일기용(1)페이지
		public diary_panel() {
			setSize(BOUND, BOUND);
			setLayout(null);

			String currentProjPath = "";
			try {
				currentProjPath = new File(".").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}

			WeatherAPI wa = new WeatherAPI();
			wa.start();
			Covid_19 cov = new Covid_19();
			cov.start();

			JLabel bg = new JLabel();
			// 일기패널 배경 넣기
			String DiaryPath = currentProjPath + "/src/img/calendarresult.gif";
			ImageIcon Diary = new ImageIcon(DiaryPath);
			bg.setIcon(Diary);
			add(bg);
			bg.setBounds(-10, -20, 900, 900);

			try { // 정보 가져오기가 끝날 때 까지 기다림
				wa.join();
				cov.join();
			} catch (Exception e) {
			}

			String weather_info[] = wa.getWeather().split(" ");
			int temperature = Integer.parseInt(weather_info[1]);

			JLabel mc_Label = mc.getMain();
			bg.add(mc_Label);
			mc_Label.setBounds(100, 50, 700, 400);

			String SchedulePath = currentProjPath + "/dat/ScheduleData/";

			Calendar Today = Calendar.getInstance();
			int Year = Today.get(Calendar.YEAR);
			int Month = Today.get(Calendar.MONTH);
			int Day = Today.get(Calendar.DATE);

			int nextMonth = Month + 1;
			int nextDay = 1;

			File f;
			if ((Month + 1 == 1 && Day == 31) || (Month + 1 == 3 && Day == 31) || (Month + 1 == 5 && Day == 31)
					|| (Month + 1 == 7 && Day == 31) || (Month + 1 == 8 && Day == 31) || (Month + 1 == 10 && Day == 31)
					|| (Month + 1 == 12 && Day == 31)) {
				f = new File(SchedulePath + Year + ((nextMonth + 1) < 10 ? "0" : "") + (nextMonth + 1)
						+ (nextDay < 10 ? "0" : "") + nextDay + ".txt");
				// System.out.println(SchedulePath + Year + ((nextMonth + 1) < 10 ? "0" : "") +
				// (nextMonth + 1) + (nextDay < 10 ? "0" : "")
				// + nextDay + ".txt");
			} else if (Month + 1 == 2 && Day == 28) {
				f = new File(SchedulePath + Year + ((nextMonth + 1) < 10 ? "0" : "") + (nextMonth + 1)
						+ (nextDay < 10 ? "0" : "") + nextDay + ".txt");
				// System.out.println(SchedulePath + Year + ((nextMonth + 1) < 10 ? "0" : "") +
				// (nextMonth + 1) + (nextDay < 10 ? "0" : "")
				// + nextDay + ".txt");
			} else if ((Month + 1 == 4 && Day == 30) || (Month + 1 == 6 && Day == 30) || (Month + 1 == 9 && Day == 30)
					|| (Month + 1 == 11 && Day == 30)) {
				f = new File(SchedulePath + Year + ((nextMonth + 1) < 10 ? "0" : "") + (nextMonth + 1)
						+ (nextDay < 10 ? "0" : "") + nextDay + ".txt");
				// System.out.println(SchedulePath + Year + ((nextMonth + 1) < 10 ? "0" : "") +
				// (nextMonth + 1) + (nextDay < 10 ? "0" : "")
				// + nextDay + ".txt");
			} else {
				f = new File(SchedulePath + Year + ((Month + 1) < 10 ? "0" : "") + (Month + 1)
						+ ((Day + 1) < 10 ? "0" : "") + (Day + 1) + ".txt");
				// System.out.println(SchedulePath + Year + ((Month + 1) < 10 ? "0" : "") +
				// (Month + 1) + ((Day + 1) < 10 ? "0" : "")
				// + (Day + 1) + ".txt");
			}
			JPanel schedule_panel = new JPanel();
			schedule_panel.setLayout(new BorderLayout());
			JScrollPane scroll = new JScrollPane(schedule_area);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			schedule_panel.add(scroll, BorderLayout.CENTER);
			bg.add(schedule_panel);
			schedule_panel.setBounds(100, 475, 700, 50);

			try {
				if (f.exists()) {
					BufferedReader in = new BufferedReader(new FileReader(f));

					String memoAreaText = "";
					String str = null;
					while ((str = in.readLine()) != null) {
						memoAreaText += str + System.getProperty("line.separator");
					}
					schedule_area.setText(memoAreaText);
					in.close();
				} else
					schedule_area.setText("");
			} catch (IOException ie) {
				ie.printStackTrace();
			}

			weather_btn = new JButton("weather");
			weather_btn.setBackground(Color.white);
			weather_btn.setText(weather_info[0] + " " + temperature + "℃");
			weather_btn.addActionListener(this);
			weather_btn.setFont(font4);
			weather_btn.setOpaque(false);

			bg.add(weather_btn);
			weather_btn.setBounds(100, 550, 200, 100);

			covid_btn = new JButton("covid");
			covid_btn.setOpaque(false);
			covid_btn.addActionListener(this);
			covid_btn.setBackground(Color.white);
			covid_btn.setText(Integer.toString(cov.getDecide()));
			covid_btn.setFont(font4);
			bg.add(covid_btn);
			covid_btn.setBounds(350, 550, 200, 100);

			lucky_btn = new JButton("lucky");
			lucky_btn.addActionListener(this);
			lucky_btn.setBackground(Color.WHITE);
			lucky_btn.setOpaque(false);
			lucky_btn.setText("");
			lucky_btn.add(year);

			year.setBorder(BorderFactory.createEmptyBorder());
			year.setHorizontalAlignment(JTextField.CENTER);
			year.setFont(font4);

			bg.add(lucky_btn);
			lucky_btn.setBounds(600, 550, 200, 100);

			String currentProPath = "";
			try {
				currentProPath = new File(".").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//
			JButton happy_btn = new JButton("healing");
			happy_btn.addActionListener(this);
			happy_btn.setText("힐링");

			String happypath = currentProPath + "/src/img/healreal.jpg";
			ImageIcon happyIcon = new ImageIcon(happypath);
			happy_btn.setIcon(happyIcon);
			bg.add(happy_btn);
			happy_btn.setBounds(100, 800, 50, 50);

			//
			JButton study_btn = new JButton("study");
			study_btn.addActionListener(this);
			study_btn.setText("공부");

			String studypath = currentProPath + "/src/img/bookreal.jpg";
			ImageIcon studyIcon = new ImageIcon(studypath);
			study_btn.setIcon(studyIcon);
			bg.add(study_btn);
			study_btn.setBounds(250, 800, 50, 50);

			//
			JButton excited_btn = new JButton("exercise");
			excited_btn.addActionListener(this);
			excited_btn.setText("운동");

			String excpath = currentProPath + "/src/img/healthreal.jpg";
			ImageIcon excIcon = new ImageIcon(excpath);
			excited_btn.setIcon(excIcon);
			bg.add(excited_btn);
			excited_btn.setBounds(600, 800, 50, 50);
			//
			JButton angry_btn = new JButton("love");
			angry_btn.addActionListener(this);
			angry_btn.setText("사랑");

			String angrypath = currentProPath + "/src/img/heartreal.jpg";
			ImageIcon angryIcon = new ImageIcon(angrypath);
			angry_btn.setIcon(angryIcon);
			bg.add(angry_btn);
			angry_btn.setBounds(750, 800, 50, 50);
			//
			sche_btn = new JButton("sche");
			sche_btn.addActionListener(this);
			sche_btn.setText("");
			bg.add(sche_btn);
			sche_btn.setBounds(850, 25, 25, 25);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();

			if (e.getSource() == weather_btn) {
				try {
					Desktop.getDesktop().browse(new URI("https://weather.naver.com/today/06230110"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (e.getSource() == covid_btn) {
				try {
					Desktop.getDesktop().browse(new URI("https://m.news.naver.com/covid19/index.nhn"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (e.getSource() == lucky_btn) {
				if (year.getText().length() == 0) {
					InputError ie = new InputError();
					ie.setVisible(true);
				} else {
					print_fortune pf = new print_fortune(Integer.parseInt(year.getText()));
					pf.setVisible(true);
				}
			} else if (command.equals("힐링")) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.melon.com/dj/tag/djtaghub_list.htm?tagSeq=5"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (command.equals("공부")) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.melon.com/dj/tag/djtaghub_list.htm?tagSeq=365"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (command.equals("운동")) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.melon.com/dj/tag/djtaghub_list.htm?tagSeq=19"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (command.equals("사랑")) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.melon.com/dj/tag/djtaghub_list.htm?tagSeq=6"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (e.getSource() == sche_btn) {
				is_diary = false;
				is_sche = true;
				setVisible(false);
				sp.setVisible(true);
			}
		}
	}

	// 일정 창
	public class schedule_panel extends JPanel implements ActionListener {

		private JAVAScheduleCalendar sc = new JAVAScheduleCalendar();
		private JTextField phrase_area = new JTextField();
		private JButton weather_btn;
		private JButton covid_btn;
		private JButton lucky_btn;
		private JButton diary_btn;
		private JTextField year = new JTextField();

		public class print_fortune extends JFrame implements ActionListener {

			public print_fortune(int year) {
				setTitle("Your fortune");
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setSize(1800, 150);
				setBackground(Color.white);
				setLayout(new BorderLayout());

				Font font = new Font("나눔손글씨 펜", Font.PLAIN, 25);

				Fortune luck = new Fortune(year);
				luck.run();
				try {
					luck.join();
				} catch (Exception e) {
					e.printStackTrace();
				}

				JLabel textLabel = new JLabel(luck.getLucky());
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

		public class InputError extends JFrame implements ActionListener {

			public InputError() {
				setTitle("Input error");
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setSize(400, 150);
				setBackground(Color.white);
				setLayout(new BorderLayout());

				Font font = new Font("나눔손글씨 펜", Font.PLAIN, 30);

				JLabel textLabel = new JLabel("년도를 입력해주세요!");
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

		public schedule_panel() {
			setSize(BOUND, BOUND);
			setLayout(null);

			WeatherAPI wa = new WeatherAPI();
			wa.start();
			Covid_19 cov = new Covid_19();
			cov.start();

			String currentProjPath = "";
			try {
				currentProjPath = new File(".").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}

			JLabel bg = new JLabel();
			String DiaryPath = currentProjPath + "/src/img/schedule.gif";
			ImageIcon Diary = new ImageIcon(DiaryPath);
			bg.setIcon(Diary);
			add(bg);
			bg.setBounds(-10, -20, 900, 900);

			try { // 정보 가져오기가 끝날 때 까지 기다림
				wa.join();
				cov.join();
			} catch (Exception e) {
			}

			String weather_info[] = wa.getWeather().split(" ");
			int temperature = Integer.parseInt(weather_info[1]);

			JLabel scp = sc.getMain();
			bg.add(scp);
			scp.setBounds(100, 50, 700, 400);

			JPanel phrase_panel = new JPanel();
			phrase_panel.setLayout(new BorderLayout());
			phrase_panel.add(phrase_area, BorderLayout.CENTER);

			
			//폰트 바꾸기
			Font gs = new Font("DX세필명조 L", Font.BOLD, 17);
			Phrase p = new Phrase();
			phrase_area.setText(p.getPhrase());
			phrase_area.setFont(gs);
			phrase_area.setHorizontalAlignment(JTextField.CENTER);

			bg.add(phrase_panel);
			phrase_panel.setBounds(100, 475, 700, 50);

			weather_btn = new JButton("weather");
			weather_btn.setBackground(Color.white);
			weather_btn.setText(weather_info[0] + " " + temperature + "℃");
			weather_btn.addActionListener(this);
			weather_btn.setFont(font4);
			weather_btn.setOpaque(false);

			bg.add(weather_btn);
			weather_btn.setBounds(100, 550, 200, 100);

			covid_btn = new JButton("covid");
			covid_btn.setOpaque(false);
			covid_btn.addActionListener(this);
			covid_btn.setBackground(Color.white);
			covid_btn.setText(Integer.toString(cov.getDecide()));
			covid_btn.setFont(font4);
			bg.add(covid_btn);
			covid_btn.setBounds(350, 550, 200, 100);

			lucky_btn = new JButton("lucky");
			lucky_btn.addActionListener(this);
			lucky_btn.setBackground(Color.WHITE);
			lucky_btn.setOpaque(false);
			lucky_btn.setText("");
			lucky_btn.add(year);

			year.setBorder(BorderFactory.createEmptyBorder());
			year.setHorizontalAlignment(JTextField.CENTER);
			year.setFont(font4);

			bg.add(lucky_btn);
			lucky_btn.setBounds(600, 550, 200, 100);

			String currentProPath = "";
			try {
				currentProPath = new File(".").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//
			JButton happy_btn = new JButton("healing");
			happy_btn.addActionListener(this);
			happy_btn.setText("힐링");

			String happypath = currentProPath + "/src/img/healreal.jpg";
			ImageIcon happyIcon = new ImageIcon(happypath);
			happy_btn.setIcon(happyIcon);
			bg.add(happy_btn);
			happy_btn.setBounds(100, 800, 50, 50);

			//
			JButton study_btn = new JButton("study");
			study_btn.addActionListener(this);
			study_btn.setText("공부");

			String studypath = currentProPath + "/src/img/bookreal.jpg";
			ImageIcon studyIcon = new ImageIcon(studypath);
			study_btn.setIcon(studyIcon);
			bg.add(study_btn);
			study_btn.setBounds(250, 800, 50, 50);

			//
			JButton excited_btn = new JButton("exercise");
			excited_btn.addActionListener(this);
			excited_btn.setText("운동");

			String excpath = currentProPath + "/src/img/healthreal.jpg";
			ImageIcon excIcon = new ImageIcon(excpath);
			excited_btn.setIcon(excIcon);
			bg.add(excited_btn);
			excited_btn.setBounds(600, 800, 50, 50);
			//
			JButton angry_btn = new JButton("love");
			angry_btn.addActionListener(this);
			angry_btn.setText("사랑");

			String angrypath = currentProPath + "/src/img/heartreal.jpg";
			ImageIcon angryIcon = new ImageIcon(angrypath);
			angry_btn.setIcon(angryIcon);
			bg.add(angry_btn);
			angry_btn.setBounds(750, 800, 50, 50);
			
			diary_btn = new JButton("diary");
			diary_btn.addActionListener(this);
			diary_btn.setText("");
			bg.add(diary_btn);
			diary_btn.setBounds(825, 25, 25, 25);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();

			if (e.getSource() == weather_btn) {
				try {
					Desktop.getDesktop().browse(new URI("https://weather.naver.com/today/06230110"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (e.getSource() == covid_btn) {
				try {
					Desktop.getDesktop().browse(new URI("https://m.news.naver.com/covid19/index.nhn"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (e.getSource() == lucky_btn) {
				if (year.getText().length() == 0) {
					InputError ie = new InputError();
					ie.setVisible(true);
				} else {
					print_fortune pf = new print_fortune(Integer.parseInt(year.getText()));
					pf.setVisible(true);
				}
			} else if (command.equals("힐링")) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.melon.com/dj/tag/djtaghub_list.htm?tagSeq=5"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (command.equals("공부")) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.melon.com/dj/tag/djtaghub_list.htm?tagSeq=365"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (command.equals("운동")) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.melon.com/dj/tag/djtaghub_list.htm?tagSeq=19"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (command.equals("사랑")) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.melon.com/dj/tag/djtaghub_list.htm?tagSeq=6"));
				} catch (IOException a) {
					a.printStackTrace();
				} catch (URISyntaxException a) {
					a.printStackTrace();
				}
			} else if (e.getSource() == diary_btn) {
				is_diary = true;
				is_sche = false;
				setVisible(false);
				dp.setVisible(true);
			}
		}
	}
}
