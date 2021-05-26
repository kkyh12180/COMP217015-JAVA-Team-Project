import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JAVAMemoCalendar extends JAVACalendar implements ActionListener {
	// main에 모두 붙임
	JFrame main;
	//////////////////////
	JPanel OperationPanel;

	JLabel TodayLabel;
	JButton DMonBtn; // ◀
	JButton UMonBtn; // ▶

	JLabel MMYYYY;
	OperationBtnListener BtnListener1 = new OperationBtnListener();
	///////////////////////////
	final String YOILName[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
	//////////////////////////
	JPanel CalendarPanel;
	JButton YOIL[];
	JButton DateBtns[][] = new JButton[6][7];
	DateBtnListener BtnListener2 = new DateBtnListener();
	/////////////////////////////

	JLabel status = new JLabel("OK.");

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JAVAMemoCalendar();
			}
		});
	}

	public JAVAMemoCalendar() {
		main = new JFrame("Our Calendar");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(700, 400);
		main.setLocationRelativeTo(null); // 가운데에 창이 뜨게 함
		main.setResizable(false);

		OperationPanel = new JPanel();

		TodayLabel = new JLabel(Today.get(Calendar.YEAR) + "/" + Today.get((Calendar.MONTH) + 1) + "/"
				+ Today.get(Calendar.DAY_OF_MONTH));

		DMonBtn = new JButton("◀");
		DMonBtn.addActionListener(BtnListener1);

		MMYYYY = new JLabel((Month + 1) + "/" + Year);
		MMYYYY.setHorizontalAlignment(JLabel.CENTER);

		UMonBtn = new JButton("▶");
		UMonBtn.addActionListener(BtnListener1);

		OperationPanel.setLayout(new GridLayout(1, 3));
		OperationPanel.add(DMonBtn);
		OperationPanel.add(MMYYYY);
		OperationPanel.add(UMonBtn);

		CalendarPanel = new JPanel();
		YOIL = new JButton[7];
		for (int i = 0; i < 7; i++) {
			YOIL[i] = new JButton(YOILName[i]);
			YOIL[i].setBackground(Color.WHITE);

			if (i == 0) {
				YOIL[i].setBackground(Color.RED);
				YOIL[i].setForeground(Color.WHITE);
			} else if (i == 6) {
				YOIL[i].setBackground(Color.BLUE);
				YOIL[i].setForeground(Color.WHITE);
			}
			CalendarPanel.add(YOIL[i]);
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				DateBtns[i][j] = new JButton();
				DateBtns[i][j].setBackground(Color.WHITE);
				DateBtns[i][j].addActionListener(BtnListener2);
				DateBtns[i][j].setVisible(true);
				CalendarPanel.add(DateBtns[i][j]);
			}
		}

		CalendarPanel.setLayout(new GridLayout(0, 7, 1, 1));
		CalendarPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		visibleCal();

		main.setLayout(new BorderLayout());
		main.add(OperationPanel, BorderLayout.NORTH);
		main.add(CalendarPanel, BorderLayout.CENTER);
		main.setVisible(true);

		focusToday();
	}

	private void visibleCal() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {

				if (j == 0) {
					DateBtns[i][j].setText("<html><font color= red>" + Dates[i][j] + "</font></html>");
				} else if (j == 6) {
					DateBtns[i][j].setText("<html><font color= blue>" + Dates[i][j] + "</font></html>");
				} else {
					DateBtns[i][j].setText("<html><font color= black>" + Dates[i][j] + "</font></html>");
				}

				DateBtns[i][j].removeAll();

				if (Dates[i][j] == 0)
					DateBtns[i][j].setVisible(false);
				else
					DateBtns[i][j].setVisible(true);
			}
		}
	}

	private void focusToday() {
		if (Today.get(Calendar.DAY_OF_WEEK) == 1)
			DateBtns[Today.get(Calendar.WEEK_OF_MONTH)][Today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		else
			DateBtns[Today.get(Calendar.WEEK_OF_MONTH) - 1][Today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
	}

	private class OperationBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Today) {
				setDay();
				BtnListener1.actionPerformed(e);
				focusToday();
			} else if (e.getSource() == DMonBtn)
				moveMonth(-1);
			else if (e.getSource() == UMonBtn)
				moveMonth(1);

			MMYYYY.setText((Month + 1) + "/" + Year);
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
		}
	}

	class newWindow extends JFrame {
		public newWindow() {
			// 일기장이 나오던가 하는 방식으로
			JFrame newwindow = new JFrame();
			Panel newWindowPanel = new Panel();
			TextArea Diary = new TextArea(10,10);

			newWindowPanel.add(Diary);
			newWindowPanel.setVisible(true);
			
			newwindow.add(newWindowPanel);
			newwindow.setVisible(true);
			newwindow.setSize(500, 500);
		}
	}

	/*
	class successWindow extends JFrame {
		successWindow() {
			Smilely smile = new Smilely();
			smile.getsmile();
			BaseballGame newgame = new BaseballGame();
			JOptionPane.showMessageDialog(this,
					"3 strike!" + "\n" + "Game over. You win." + "\n" + "Your Score: " + newgame.getscore() + " points.");
		}
	}
	*/
	public void actionPerformed(ActionEvent e) {}
}