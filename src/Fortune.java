
import java.io.IOException;
		 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
		 
public class Fortune {
	
	public String getMouse() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=零鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getCow() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=家鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getTiger() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=龋尔捞鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getRabbit() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=配尝鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getDragon() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=侩鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getSnake() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=轨鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getHorse() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=富鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getSheep() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=剧鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getMonkey() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=盔件捞鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getChicken() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=催鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getDog() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=俺鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}
	
	public String getPig() {
		String fortune = "";
		try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=蹬瘤鹅%20款技";
            Connection connect = Jsoup.connect(URL);

            Document html = connect.get();
            Elements block = html.select("p.text._cs_fortune_text");
            
            int cnt = 0;
            for (Element texts : block.select("p"))	{
            	if (cnt > 0) break;
            	fortune = texts.text();
            	cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();           
        }
		return fortune;
	}

	public String getFortune(int year) {
		if (year % 12 == 0) return this.getMonkey();
		else if (year % 12 == 1) return this.getChicken();
		else if (year % 12 == 2) return this.getDog();
		else if (year % 12 == 3) return this.getPig();
		else if (year % 12 == 4) return this.getMouse();
		else if (year % 12 == 5) return this.getCow();
		else if (year % 12 == 6) return this.getTiger();
		else if (year % 12 == 7) return this.getRabbit();
		else if (year % 12 == 8) return this.getDragon();
		else if (year % 12 == 9) return this.getSnake();
		else if (year % 12 == 10) return this.getHorse();
		else return this.getSheep();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fortune test = new Fortune();
		System.out.println(test.getFortune(2000));
	}

}
