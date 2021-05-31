//2020113925
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

public class WeatherAPI extends Thread{
	
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
		
		//�삤�뒛 �궇吏�
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String todayDate = date.format(System.currentTimeMillis());
		
		Calendar c = Calendar.getInstance();
		int nowHour = c.get(Calendar.HOUR_OF_DAY);
		int hour = 0;
		
		//�꽭 �떆媛� �떒�쐞�쓽 �궇吏쒖� �떆媛꾩쓣 援ы븿 (�젙蹂� 媛깆떊 二쇨린媛� �꽭 �떆媛�)
		if (nowHour >= 0 && nowHour < 2) { 
			//�씠 寃쎌슦 �뼱�젣 �궇吏� 11�떆�쓽 �젙蹂대�� 媛��졇���빞 �븿
			int dateNum = Integer.parseInt(todayDate);
			if (dateNum % 100 == 1) // 1�썡 1�씪
			{
				if (((dateNum % 10000) / 100) == 1) // 1占쎌뜞 1占쎌뵬
					dateNum -= 8870;
				else if (((dateNum % 10000) / 100) == 3) // 3�썡 1�씪 -> 2�썡 28�씪 (�쑄�뀈 怨좊젮 X)
					dateNum -= 73;
				else if (((dateNum % 10000) / 100) == 5 || ((dateNum % 10000) / 100) == 7 ||
						 ((dateNum % 10000) / 100) == 8 ||((dateNum % 10000) / 100) == 10 ||((dateNum % 10000) / 100) == 12)
					dateNum -= 71;
				else
					dateNum -= 70;
			}
			else dateNum -= 1;
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
		else if (nowHour >= 23) hour = 23;
		
		String URLHour;
		if (hour < 10)
			URLHour = "0" + Integer.toString(hour); 
		else URLHour = Integer.toString(hour);
		URLHour += "00";
		
		try { //�삎�떇�뿉 留욌뒗 URL �젣�옉
			StringBuilder URLMaker = new StringBuilder(URL);
			URLMaker.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);
			URLMaker.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(todayDate, "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(URLHour, "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("89", "UTF-8"));
			URLMaker.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("91", "UTF-8"));
			// System.out.println(todayDate); �궇吏� �뀒�뒪�듃
			// System.out.println(URLHour); �떆媛� �뀒�뒪�듃
			
			//留뚮뱾�뼱吏� URL濡� �뿰寃�
			URL MadeURL = new URL(URLMaker.toString());
			HttpURLConnection connect = (HttpURLConnection) MadeURL.openConnection(); 
			connect.setRequestMethod("GET");
			connect.setRequestProperty("Connect-type", "application/json");
			
			String results = ""; //寃곌낵瑜� ���옣�븷 臾몄옄�뿴
			if (connect.getResponseCode() >= 200 && connect.getResponseCode() <= 300) {
				// 200 ~ 300�씪 �븣 �젙�긽 �뿰寃�
				StringBuilder info = new StringBuilder();

				// 踰꾪띁 由щ뜑瑜� �넻�븳 input 諛⑹떇 UTF-8�씠 �븘�땺 �떆 �젙蹂� 源⑥쭚
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
				String infoLine;
				while ((infoLine = br.readLine()) != null)
					info.append(infoLine);
				// System.out.println(info.toString()); // �젙蹂대�� 諛쏆븯�뒗吏� �뀒�뒪�듃
				
				br.close();
				connect.disconnect();
				results = info.toString();
			}
			
			// 媛��졇�삩 臾몄옄�뿴 �뜲�씠�꽣瑜� 媛앹껜濡� 蹂��솚
			JSONParser ps = new JSONParser();
			JSONObject object = (JSONObject)ps.parse(results);
			
			// response �궎濡� �뙆�떛 -> body -> items
			JSONObject response = (JSONObject)object.get("response");
			JSONObject body = (JSONObject)response.get("body");			
			JSONObject items = (JSONObject)body.get("items");
			
			JSONArray item = (JSONArray)items.get("item");
			String tag;
			JSONObject weather;
			
			ArrayList<WeatherInfo> infos = new ArrayList<WeatherInfo>();
			//ArrayList�뿉 媛� �꽔�뼱二쇨린
			for (int i = 0; i < item.size(); i++) {
				weather = (JSONObject) item.get(i);
				tag = (String)weather.get("category");
				String value = (String)weather.get("fcstValue");
				
				WeatherInfo wi = new WeatherInfo (tag, value);
				infos.add(wi);
				/* �젙蹂닿� �옒 �뱾�뼱媛붾뒗吏� 寃��넗
				System.out.print("Category: " + tag);
				System.out.println(" Value: " + value);
				*/
			}
			
			/** 李멸퀬 �몴
			 * POP : 媛뺤닔 �솗瑜� (�궗�슜 x)
			 * PTY : 媛뺤닔 �긽�깭 | 0: �뾾�쓬 | 1: 鍮� | 2: 鍮�/�늿 | 3: �늿/鍮� | 4: �늿
			 * RO6 : 6�떆媛� 媛뺤닔�웾 (�궗�슜 x)
			 * REH : �뒿�룄 (�궗�슜 x)
			 * SO6 : 6�떆媛� �쟻�꽕�웾 (�궗�슜 x)
			 * SKY : �븯�뒛 �긽�깭 | 1: 留묒쓬 | 2: 援щ쫫 議곌툑 | 3: 援щ쫫 留롮쓬 | 4: �쓲由�
			 * T3H : 3�떆媛� 湲곗삩
			 * UUU : 諛붾엺 愿��젴 (�궗�슜 x)
			 * VEC : 諛붾엺 愿��젴 (�궗�슜 x)
			 * VVV : 諛붾엺 愿��젴 (�궗�슜 x)
			 */
			
			for (WeatherInfo wis : infos) {
				//forEach 臾몄쓣 �넻�빐 �븘�슂�븳 �젙蹂대쭔 異붿텧
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
	
	public void run() {
		this.getINFO();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherAPI test = new WeatherAPI();
		System.out.println(test.getWeather());
	}
}
