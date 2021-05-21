
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
		
		//���� ��¥
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String todayDate = date.format(System.currentTimeMillis());
		
		Calendar c = Calendar.getInstance();
		int nowHour = c.get(Calendar.HOUR_OF_DAY);
		int hour = 0;
		
		//�� �ð� ������ ��¥�� �ð��� ���� (���� ���� �ֱⰡ �� �ð�)
		if (nowHour >= 0 && nowHour < 2) { 
			//�� ��� ���� ��¥ 11���� ������ �����;� ��
			int dateNum = Integer.parseInt(todayDate);
			if (dateNum % 100 == 1) // 1���� ���
			{
				if (((dateNum % 10000) / 100) == 1) // 1�� 1��
					dateNum -= 8870;
				else if (((dateNum % 10000) / 100) == 3) // 3�� 1��
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
		
		try { //���Ŀ� �´� URL ����
			StringBuilder URLMaker = new StringBuilder(URL);
			URLMaker.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);
			URLMaker.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(todayDate, "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(URLHour, "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("89", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("91", "UTF-8"));
			// System.out.println(todayDate); ��¥ �׽�Ʈ
			// System.out.println(URLHour); �ð� �׽�Ʈ
			
			//������� URL�� ����
			URL MadeURL = new URL(URLMaker.toString());
			HttpURLConnection connect = (HttpURLConnection) MadeURL.openConnection(); 
			connect.setRequestMethod("GET");
			connect.setRequestProperty("Connect-type", "application/json");
			
			String results = ""; //����� ������ ���ڿ�
			if (connect.getResponseCode() >= 200 && connect.getResponseCode() <= 300) {
				// 200 ~ 300�� �� ���� ����
				StringBuilder info = new StringBuilder();

				// ���� ������ ���� input ��� UTF-8�� �ƴ� �� ���� ����
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
				String infoLine;
				while ((infoLine = br.readLine()) != null)
					info.append(infoLine);
				// System.out.println(info.toString()); // ������ �޾Ҵ��� �׽�Ʈ
				
				br.close();
				connect.disconnect();
				results = info.toString();
			}
			
			// ������ ���ڿ� �����͸� ��ü�� ��ȯ
			JSONParser ps = new JSONParser();
			JSONObject object = (JSONObject)ps.parse(results);
			
			// response Ű�� �Ľ� -> body -> items
			JSONObject response = (JSONObject)object.get("response");
			JSONObject body = (JSONObject)response.get("body");			
			JSONObject items = (JSONObject)body.get("items");
			
			JSONArray item = (JSONArray)items.get("item");
			String tag;
			JSONObject weather;
			
			ArrayList<WeatherInfo> infos = new ArrayList<WeatherInfo>();
			//ArrayList�� �� �־��ֱ�
			for (int i = 0; i < item.size(); i++) {
				weather = (JSONObject) item.get(i);
				tag = (String)weather.get("category");
				String value = (String)weather.get("fcstValue");
				
				WeatherInfo wi = new WeatherInfo (tag, value);
				infos.add(wi);
				/* ������ �� ������ ����
				System.out.print("Category: " + tag);
				System.out.println(" Value: " + value);
				*/
			}
			
			/** ���� ǥ
			 * POP : ���� Ȯ��
			 * PTY : ���� ���� | 0: ���� | 1: �� | 2: ��/�� | 3: ��/�� | 4: ��
			 * RO6 : 6�ð� ������
			 * REH : ����
			 * SO6 : 6�ð� ������
			 * SKY : �ϴ� ���� | 1: ���� | 2: ���� ���� | 3: ���� ���� | 4: �帲
			 * T3H : 3�ð� ���
			 * UUU : �ٶ� ����
			 * VEC : �ٶ� ���� 
			 * VVV : �ٶ� ����
			 */
			
			for (WeatherInfo wis : infos) {
				//forEach ���� ���� �ʿ��� ������ ����
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
