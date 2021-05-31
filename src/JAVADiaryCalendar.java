import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JAVADiaryCalendar extends JAVACalendar implements ActionListener {

	private JLabel main = new JLabel();

	private JPanel OperationPanel;

	private JLabel TodayLabel;
	private JButton DMonBtn; // ◀
	private JButton UMonBtn; // ▶
	private JLabel MMYYYYToday;
	private OperationBtnListener BtnListener1 = new OperationBtnListener();

	///////////////////////////

	final String YOILName[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };

	//////////////////////////

	private JPanel CalendarPanel;
	private JButton YOIL[];
	private JButton DateBtns[][] = new JButton[6][7];
	private DateBtnListener BtnListener2 = new DateBtnListener();
	/////////////////////////////

	private JLabel status = new JLabel("OK.");

	/////////////////////////

	private JTextArea Diary = new JTextArea();
	private JLabel Datedate = new JLabel(); // 오늘 날짜(선택된 날짜)

	Happiness_Today now_Happiness;
	/////////////////////////
	private Font font1 = new Font("DX영화자막 M", Font.PLAIN, 15);
	private Font font2 = new Font("DX영화자막 M", Font.PLAIN, 25);
	private Font font3 = new Font("DX영화자막 M", Font.PLAIN, 20);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JAVADiaryCalendar();
			}
		});
	}

	public JAVADiaryCalendar() {
		main.setSize(700, 400);
		// main.setOpaque(false);

		String currentProPath = "";
		try {
			currentProPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String innerdiarypath = currentProPath + "/src/Img/inner1.jpg";
		ImageIcon innerdiary = new ImageIcon(innerdiarypath);
		main.setIcon(innerdiary);

		OperationPanel = new JPanel();

		TodayLabel = new JLabel(Today.get(Calendar.YEAR) + "/" + Today.get((Calendar.MONTH) + 1) + "/"
				+ Today.get(Calendar.DAY_OF_MONTH));

		DMonBtn = new JButton("◀");
		DMonBtn.addActionListener(BtnListener1);

		MMYYYYToday = new JLabel((Month + 1) + "/" + Year + "(" + (Month + 1) + "월" + Day + "일)");
		MMYYYYToday.setHorizontalAlignment(JLabel.CENTER);

		MMYYYYToday.setFont(font1);

		UMonBtn = new JButton("▶");
		UMonBtn.addActionListener(BtnListener1);

		OperationPanel.setLayout(new GridLayout(1, 3));
		OperationPanel.add(DMonBtn);
		OperationPanel.add(MMYYYYToday);
		OperationPanel.add(UMonBtn);
		OperationPanel.setOpaque(false);

		CalendarPanel = new JPanel();
		YOIL = new JButton[7];

		for (int i = 0; i < 7; i++) {
			YOIL[i] = new JButton(YOILName[i]);
			YOIL[i].setBackground(Color.WHITE);
			YOIL[i].setFont(font1);

			if (i == 0) {
				YOIL[i].setBackground(new Color(255, 86, 117));
				YOIL[i].setForeground(Color.WHITE);

			} else if (i == 6) {
				YOIL[i].setBackground(new Color(80, 200, 255));
				YOIL[i].setForeground(Color.WHITE);
			}
			CalendarPanel.add(YOIL[i]);
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				DateBtns[i][j] = new JButton();
				DateBtns[i][j].setFont(font1);
				DateBtns[i][j].addActionListener(BtnListener2);
				DateBtns[i][j].setVisible(true);

				CalendarPanel.add(DateBtns[i][j]);
			}
		}

		CalendarPanel.setLayout(new GridLayout(0, 7, 1, 1));
		CalendarPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		CalendarPanel.setOpaque(false);
		visibleCal();

		main.setLayout(new BorderLayout());
		main.add(OperationPanel, BorderLayout.NORTH);
		main.add(CalendarPanel, BorderLayout.CENTER);
		main.setVisible(true);
		ToToday();

	}

	private void visibleCal() {

		String currentProPath = "";
		try {
			currentProPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String s;
		Color fontcolor;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {

				// 이 부분이 일부 제외하면 필요 없을수도
				if (j == 0) {
					s = Integer.toString(Dates[i][j]);
					fontcolor = Color.red;
					DateBtns[i][j].setText(s);
					DateBtns[i][j].setForeground(fontcolor);
				} else if (j == 6) {
					s = Integer.toString(Dates[i][j]);
					fontcolor = Color.blue;
					DateBtns[i][j].setText(s);
					DateBtns[i][j].setForeground(fontcolor);
				} else {
					s = Integer.toString(Dates[i][j]);
					fontcolor = Color.black;
					DateBtns[i][j].setText(s);
					DateBtns[i][j].setForeground(fontcolor);
				}

				DateBtns[i][j].removeAll();
				DateBtns[i][j].setBackground(Color.white);

				DateBtns[i][j].setBackground(Color.WHITE);
				if (Dates[i][j] == 0)
					DateBtns[i][j].setVisible(false);
				else
					DateBtns[i][j].setVisible(true);
			}
		}
	}

	private void ToToday() {
		if (Today.get(Calendar.DAY_OF_WEEK) == 1)
			DateBtns[Today.get(Calendar.WEEK_OF_MONTH) - 1][Today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		else
			DateBtns[Today.get(Calendar.WEEK_OF_MONTH) - 1][Today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
	}

	private void readMemo() {
		try {
			String currentProjPath = "";
			try {
				currentProjPath = new File(".").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String currentFilePath = currentProjPath + "/dat/DiaryData/";

			File f = new File(currentFilePath + Year + ((Month + 1) < 10 ? "0" : "") + (Month + 1)
					+ (Day < 10 ? "0" : "") + Day + ".txt");

			// for test
			System.out.println(currentFilePath + Year + ((Month + 1) < 10 ? "0" : "") + (Month + 1)
					+ (Day < 10 ? "0" : "") + Day + ".txt");
			System.out.println(f.exists());

			if (f.exists()) {

				BufferedReader in = new BufferedReader(new FileReader(f));

				String memoAreaText = "";
				String str = null;
				while ((str = in.readLine()) != null) {
					memoAreaText += str + System.getProperty("line.separator");
				}
				Diary.setText(memoAreaText);
				in.close();
			} else
				Diary.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class OperationBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Today) {
				setDay();
				BtnListener1.actionPerformed(e);
				ToToday();
			} else if (e.getSource() == DMonBtn)
				moveMonth(-1);
			else if (e.getSource() == UMonBtn)
				moveMonth(1);

			MMYYYYToday.setText((Month + 1) + "/" + Year + "(" + (Today.get(Calendar.MONTH) + 1) + "월"
					+ Today.get(Calendar.DATE) + "일" + ")");
			MMYYYYToday.setFont(font1);
			visibleCal();
		}
	}

	private class DateBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k = 0, l = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					if (e.getSource() == DateBtns[i][j]) {
						k = i;
						l = j;

					}
				}
			}
			if (!(k == 0 && l == 0))
				Day = Dates[k][l];
			Cal = new GregorianCalendar(Year, Month, Day);

			new newWindow();
			Datedate = new JLabel(Year + "/" + (Month + 1) + "/" + Day);
			// Datedate.setText(Year + "/" + (Month + 1) + "/" + Day);

			readMemo();
		}

	}

	private class newWindow extends JFrame {
		newWindow() {
			// 일기장 프레임 (메인 프레임)
			setTitle((Month + 1) + "/" + Day + "'s Diary");
			setLayout(null);
			setSize(700, 700);
			setVisible(true);
			setResizable(false);

			JLabel main = new JLabel();
			add(main);

			String currentProjPath = "";
			try {
				currentProjPath = new File(".").getCanonicalPath();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			String diarylinepath = currentProjPath + "/src/img/filldiary.jpg";

			ImageIcon diaryicon = new ImageIcon(diarylinepath);
			main.setIcon(diaryicon);

			main.setBounds(0, 0, 700, 700);

			// 날짜가 나오는 패널
			JPanel datepanel = new JPanel();
			datepanel.setLayout(new BorderLayout());
			datepanel.setOpaque(false);

			JLabel Datedate; // 오늘 날짜(선택된 날짜)
			Datedate = new JLabel(Year + " . " + (Month + 1) + " . " + Day + "의 일기");
			Datedate.setHorizontalAlignment(JLabel.CENTER);
			Datedate.setFont(font2);
			datepanel.add(Datedate, BorderLayout.CENTER);
			main.add(datepanel);
			datepanel.setBounds(100, 25, 500, 50);

			// 감정이 나오는 패널
			JPanel emotionpanel = new JPanel();
			emotionpanel.setLayout(new BorderLayout());

			emotionpanel.setBounds(100, 100, 500, 50);
			
			now_Happiness = new Happiness_Today(now_Happiness);
			now_Happiness.view();

			JButton emotion = new JButton();
			emotionpanel.add(emotion, BorderLayout.CENTER);
			main.add(emotionpanel);
			emotionpanel.setBounds(100, 100, 500, 50);

			// 일기쓰는 패널
			JPanel diarypanel = new JPanel();
			diarypanel.setLayout(new BorderLayout());

			Diary = new JTextArea(20, 20);
			Diary.setFont(font2);
			Diary.setLineWrap(true); // 자동 줄바꿈
			Diary.setWrapStyleWord(true);
			// 스크롤 넣기
			JScrollPane scroll = new JScrollPane(Diary);
			// scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			diarypanel.add(scroll);
			main.add(diarypanel);
			diarypanel.setBounds(100, 200, 500, 350);

			// 버튼
			JPanel BtnPanel = new JPanel();
			BtnPanel.setLayout(new FlowLayout());

			JButton saveBtn = new JButton();
			JButton deleteBtn = new JButton();
			JButton resetBtn = new JButton();

			deleteBtn = new JButton("Delete");
			saveBtn = new JButton("Save");
			resetBtn = new JButton("Reset");

			deleteBtn.setFont(font1);
			saveBtn.setFont(font1);
			resetBtn.setFont(font1);

			deleteBtn.setBackground(Color.white);
			saveBtn.setBackground(Color.white);
			resetBtn.setBackground(Color.white);

			BtnPanel.add(deleteBtn);
			BtnPanel.add(saveBtn);
			BtnPanel.add(resetBtn);

			main.add(BtnPanel);

			BtnPanel.setOpaque(false);
			BtnPanel.setBounds(100, 600, 500, 50);

			deleteBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String currentProjPath = "";
					try {
						currentProjPath = new File(".").getCanonicalPath();
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					String currentFilePath = currentProjPath + "/dat/DiaryData/";
					Diary.setText("");
					File f = new File(currentFilePath + Year + ((Month + 1) < 10 ? "0" : "") + (Month + 1)
							+ (Day < 10 ? "0" : "") + Day + ".txt");
					if (f.exists()) {
						f.delete();
						visibleCal();
					} else
						Diary.setText("error");
				}
			});

			saveBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String currentProjPath = "";
					try {
						currentProjPath = new File(".").getCanonicalPath();
					} catch (IOException e) {
						e.printStackTrace();
					}
					String currentFilePath = currentProjPath + "/dat/DiaryData/";
					try {
						String memo = Diary.getText();

						if (memo.length() > 0) {
							BufferedWriter out = new BufferedWriter(
									new FileWriter(new File(currentFilePath + Year + ((Month + 1) < 10 ? "0" : "")
											+ (Month + 1) + (Day < 10 ? "0" : "") + Day + ".txt")));
							String str = Diary.getText();
							out.write(str);
							out.close();
						} else
							Diary.setText("error");
					} catch (IOException e) {
						// bottomInfo.setText(SaveButMsg3);
					}
					visibleCal();
				}
			});

			resetBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Diary.setText("");
				}
			});

		}
	}

	public JLabel getMain() {
		return this.main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
