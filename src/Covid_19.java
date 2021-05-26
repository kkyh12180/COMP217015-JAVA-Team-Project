// TeamProject ���� �ڷγ� Ȯ���� ��
// 2020115001 ȫ����

import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URLEncoder;

public class Covid_19 extends Thread{
	
	private static String ServiceKey = "I5nP4yOLDNajCWegBqdC757NH5uY21Sh%2BQb%2BnzXyc9lFOILSPn%2BQ%2FIGxr6TqAd7fBoNBPMO16z5ExejpCvme7w%3D%3D";
	
	private int Decide;
	private int Death;

	public Covid_19()
	{
		Decide = 0;
		Death = 0;
	}
	
	public void getINFO()
	{
		String before_decideCnt = null;
		String before_deathCnt = null;
		
		String later_decideCnt = null;
		String later_deathCnt = null;
		
		String createDate = null;
		
		int result_decideCnt = 0;
		int result_deathCnt = 0;
		
		try {
			/*���� ��¥*/
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		    String todayDate = date.format(System.currentTimeMillis());
		    int yesterdayDate_I = Integer.parseInt(todayDate) - 1;
		    //String yeseterdayDate_S = Integer.toString(yesterdayDate_I);
		    
		    int pageNum = 1;
		    
		    while(pageNum <= 2)
		    {
		    	String urlStr = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson";
		    	
		    	/*�Ľ��� URL �غ�*/
				urlStr += "?"+ URLEncoder.encode("ServiceKey","UTF-8") +"=" + ServiceKey;
		        urlStr += "&"+ URLEncoder.encode("pageNo","UTF-8") +"=" + pageNum;
		        urlStr += "&"+ URLEncoder.encode("numOfRows","UTF-8") +"=10";
		        urlStr += "&"+ URLEncoder.encode("startCreateDt","UTF-8") + "=" + yesterdayDate_I;
		        urlStr += "&"+ URLEncoder.encode("endCreateDt","UTF-8") + "=" + yesterdayDate_I;
			    
			    /*�������� �������� Document ��ü ����*/
			    // ������ document ��ü�� ���� �Ľ��� url�� ��Ҹ� �о� ���δ�.
			    DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			    DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			    Document doc = dBuilder.parse(urlStr);
			    
			    // root tag
			    doc.getDocumentElement().normalize();
			 
			    /*�Ľ��� ������ �ִ� tag�� ����*/
			    // �Ľ��� ������: <baseinfo>��� tag �ȿ� �ִ�.
			    NodeList nList = doc.getElementsByTagName("item");
			  
			    /*list�� ��� ������ �����ϱ�*/
		    	for(int temp = 0; temp < nList.getLength(); temp++){		
			    	Node nNode = nList.item(temp);
			    	if(nNode.getNodeType() == Node.ELEMENT_NODE)
			    	{
			    		Element eElement = (Element) nNode;
			    		
			    		if (pageNum == 1)
			    		{
				    		// getTagValue : �Է��� tag ����
				    		before_decideCnt = getTagValue("decideCnt", eElement);
				    		before_deathCnt = getTagValue("deathCnt", eElement);
			    		}
			    		else
			    		{
				    		// getTagValue : �Է��� tag ����
				    		later_decideCnt = getTagValue("decideCnt", eElement);
				    		later_deathCnt = getTagValue("deathCnt", eElement);
				    		createDate = getTagValue("createDt", eElement);
			    		}
			    	}	// if end
			    }	// for end
		    	
			    pageNum++;
			    yesterdayDate_I++;
		    }	// while end
		    
		    result_decideCnt = Integer.parseInt(later_decideCnt) - Integer.parseInt(before_decideCnt);
		    result_deathCnt = Integer.parseInt(later_deathCnt) - Integer.parseInt(before_deathCnt);
		   
		    /*
    		System.out.println("Ȯ���� ��: " + result_decideCnt);
    		System.out.println("����� ��: " + result_deathCnt);
    		System.out.println("����Ͻú���: " + createDate);
		    */
		    
    		this.Decide = result_decideCnt;
    		this.Death = result_deathCnt;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public int getDeath() {return this.Death;}
	public int getDecide() {return this.Decide;}
	
	public void run() {
		this.getINFO();
	}
	
	private static String getTagValue(String tag, Element eElement) 
	{
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
}
