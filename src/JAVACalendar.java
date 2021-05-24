/*
 * 2020114670 ������ 
 */

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

class JAVACalendar {
	// �޷¿� ���� ĭ��
	int Dates[][] = new int[6][7];

	// ���� ��, ��, ��
	int Year;
	int Month;
	int Day;

	int Lastdays[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	int Lastday;
	
	// ���� ��¥ �޾ƿ� �� ����.
	Calendar Today = Calendar.getInstance();
	Calendar Cal;

	public JAVACalendar() {
		setDay();
	}

	public void setDay() {
		Year = Today.get(Calendar.YEAR);
		Month = Today.get(Calendar.MONTH);
		Day = Today.get(Calendar.DAY_OF_MONTH);

		//���� ��¥ ���
		calDate(Today);
		}

	public void calDate(Calendar cal) {
		int startDay = (cal.get(Calendar.DAY_OF_WEEK) + 7 - (cal.get(Calendar.DAY_OF_MONTH)) % 7) % 7;

		if (Month == 1) {
			// 2���� ��� ���⿡ ���� ������ ��¥ �ٲ�.
			Lastday = Lastdays[Month] + leapCheck(Year);
		} else {
			Lastday = Lastdays[Month];
		}

		//�޷� �ʱ�ȭ
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				Dates[i][j] = 0;
			}
		}
		
		
		for (int i = 0, num = 1, k = 0; i < 6; i++) {
			if (i == 0) {
				k = startDay;
			} else {
				k = 0;
			}

			for (int j = k; j < 7; j++) {
				if (num <= Lastday) {
					Dates[i][j] = num++;
				}
			}
		}

		
	}
	
	public int leapCheck(int year)
	{
		//����-> 28+1 / �ƴϸ� -> 28
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return 1;
		else
			return 0;
	}
	
	public void moveMonth(int month)
	{
		Month += month;
		if (Month > 11)
			while (Month > 11) {
				Year++;
				Month -= 12;
			}
		else if (Month < 0)
			while (Month < 0) {
				Year--;
				Month += 12;
			}
		Cal = new GregorianCalendar(Year, Month, Day);
		calDate(Cal);
	}
}