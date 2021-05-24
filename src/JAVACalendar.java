/*
 * 2020114670 최희정 
 */

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

class JAVACalendar {
	// 달력에 넣을 칸들
	int Dates[][] = new int[6][7];

	// 계산된 연, 월, 일
	int Year;
	int Month;
	int Day;

	int Lastdays[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	int Lastday;
	
	// 오늘 날짜 받아올 수 있음.
	Calendar Today = Calendar.getInstance();
	Calendar Cal;

	public JAVACalendar() {
		setDay();
	}

	public void setDay() {
		Year = Today.get(Calendar.YEAR);
		Month = Today.get(Calendar.MONTH);
		Day = Today.get(Calendar.DAY_OF_MONTH);

		//오늘 날짜 계산
		calDate(Today);
		}

	public void calDate(Calendar cal) {
		int startDay = (cal.get(Calendar.DAY_OF_WEEK) + 7 - (cal.get(Calendar.DAY_OF_MONTH)) % 7) % 7;

		if (Month == 1) {
			// 2월인 경우 윤년에 따라 마지막 날짜 바뀜.
			Lastday = Lastdays[Month] + leapCheck(Year);
		} else {
			Lastday = Lastdays[Month];
		}

		//달력 초기화
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
		//윤년-> 28+1 / 아니면 -> 28
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