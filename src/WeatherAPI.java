
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;

public class WeatherAPI {
	
	public class WeatherInfo {
		private String tag;
		private String value;
		
		public WeatherInfo (String tag, String value) {
			this.tag = tag;
			this.value = value;
		}
		
		public String getTag() {
			return tag;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	private final String URL;
	private final String key;
	private int temperature;
	private boolean isRain;
	private boolean isSnow;
	private boolean isCloud;
	private boolean isSunny;
	
	public WeatherAPI() {
		URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		key = "xDhD67tm%2BztLeTKXUd%2F2gTYy8d%2BKUCSIR8ejj%2BvxdhdWdNqEjDEvkxYv1anr1qv16UoabjXKFu9mbebwg%2FXvwg%3D%3D";
		temperature = 0;
		isRain = false;
		isSnow = false;
		isCloud = false;
		isSunny = false;
	}
		
	public void getINFO() {
		
		//오늘 날짜
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String todayDate = date.format(System.currentTimeMillis());
		
		Calendar c = Calendar.getInstance();
		int nowHour = c.get(Calendar.HOUR_OF_DAY);
		int hour = 0;
		
		//세 시간 단위의 날짜와 시간을 구함 (정보 갱신 주기가 세 시간)
		if (nowHour >= 0 && nowHour < 2) { 
			//이 경우 어제 날짜 11시의 정보를 가져와야 함
			int dateNum = Integer.parseInt(todayDate);
			if (dateNum % 100 == 1) // 1일인 경우
			{
				if (((dateNum % 10000) / 100) == 1) // 1월 1일
					dateNum -= 8870;
				else if (((dateNum % 10000) / 100) == 3) // 3월 1일
					dateNum -= 73;
				else if (((dateNum % 10000) / 100) == 5 || ((dateNum % 10000) / 100) == 7 ||
						 ((dateNum % 10000) / 100) == 8 ||((dateNum % 10000) / 100) == 10 ||((dateNum % 10000) / 100) == 12)
					dateNum -= 71;
				else
					dateNum -= 70;
			}
			todayDate = Integer.toString(dateNum);
			hour = 23;
		}
		else if (nowHour >= 2 && nowHour < 5) hour = 2;
		else if (nowHour >= 5 && nowHour < 8) hour = 5;
		else if (nowHour >= 8 && nowHour < 11) hour = 8;
		else if (nowHour >= 11 && nowHour < 14) hour = 11;
		else if (nowHour >= 14 && nowHour < 17) hour = 14;
		else if (nowHour >= 17 && nowHour < 20) hour = 17;
		else if (nowHour >= 20 && nowHour < 23) hour = 20;
		
		String URLHour;
		if (hour < 10)
			URLHour = "0" + Integer.toString(hour); 
		else URLHour = Integer.toString(hour);
		URLHour += "00";
		
		try { //형식에 맞는 URL 제작
			StringBuilder URLMaker = new StringBuilder(URL);
			URLMaker.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);
			URLMaker.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(todayDate, "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(URLHour, "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("89", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("91", "UTF-8"));
			// System.out.println(todayDate); 날짜 테스트
			// System.out.println(URLHour); 시간 테스트
			
			//만들어진 URL로 연결
			URL MadeURL = new URL(URLMaker.toString());
			HttpURLConnection connect = (HttpURLConnection) MadeURL.openConnection(); 
			connect.setRequestMethod("GET");
			connect.setRequestProperty("Connect-type", "application/json");
			
			String results = ""; //결과를 저장할 문자열
			if (connect.getResponseCode() >= 200 && connect.getResponseCode() <= 300) {
				// 200 ~ 300일 때 정상 연결
				StringBuilder info = new StringBuilder();

				// 버퍼 리더를 통한 input 방식 UTF-8이 아닐 시 정보 깨짐
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
				String infoLine;
				while ((infoLine = br.readLine()) != null)
					info.append(infoLine);
				// System.out.println(info.toString()); // 정보를 받았는지 테스트
				
				br.close();
				connect.disconnect();
				results = info.toString();
			}
			
			// 가져온 문자열 데이터를 객체로 변환
			JSONParser ps = new JSONParser();
			JSONObject object = (JSONObject)ps.parse(results);
			
			// response 키로 파싱 -> body -> items
			JSONObject response = (JSONObject)object.get("response");
			JSONObject body = (JSONObject)response.get("body");			
			JSONObject items = (JSONObject)body.get("items");
			
			JSONArray item = (JSONArray)items.get("item");
			String tag;
			JSONObject weather;
			
			ArrayList<WeatherInfo> infos = new ArrayList<WeatherInfo>();
			//ArrayList에 값 넣어주기
			for (int i = 0; i < item.size(); i++) {
				weather = (JSONObject) item.get(i);
				tag = (String)weather.get("category");
				String value = (String)weather.get("fcstValue");
				
				WeatherInfo wi = new WeatherInfo (tag, value);
				infos.add(wi);
				/* 정보가 잘 들어갔는지 검토
				System.out.print("Category: " + tag);
				System.out.println(" Value: " + value);
				*/
			}
			
			/** 참고 표
			 * POP : 강수 확률 (사용 X)
			 * PTY : 강수 상태 | 0: 없음 | 1: 비 | 2: 비/눈 | 3: 눈/비 | 4: 눈
			 * RO6 : 6시간 강수량 (사용 X)
			 * REH : 습도 (사용 X)
			 * SO6 : 6시간 적설량 (사용 X)
			 * SKY : 하늘 상태 | 1: 맑음 | 2: 구름 조금 | 3: 구름 많음 | 4: 흐림
			 * T3H : 3시간 기온
			 * UUU : 바람 관련 (사용 X)
			 * VEC : 바람 관련 (사용 X)
			 * VVV : 바람 관련 (사용 X)
			 */
			
			for (WeatherInfo wis : infos) {
				//forEach 문을 통해 필요한 정보만 추출
				if (wis.getTag().equals("PTY")) {
					if (wis.getValue().equals("1") || wis.getValue().equals("2"))
						isRain = true;
					else if (wis.getValue().equals("3") || wis.getValue().equals("4"))
						isSnow = true;
				} else if (wis.getTag().equals("SKY")) {
					if (wis.getValue().equals("1") || wis.getValue().equals("2"))
						isSunny = true;
					else if (wis.getValue().equals("3") || wis.getValue().equals("4"))
						isCloud = true;
				} else if (wis.getTag().equals("T3H"))
					temperature = Integer.parseInt(wis.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getWeather() {
		this.getINFO();
		if ((isCloud && !isRain) || (isCloud && !isSnow)) return "cloud " + temperature;
		else if (isRain) return "rain "+ temperature;
		else if (isSnow) return "snow "+ temperature;
		else if (isSunny) return "sunny "+ temperature;
		else return "error";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherAPI test = new WeatherAPI();
		System.out.println(test.getWeather());
	}

}
