
public class Test_thread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherAPI wa = new WeatherAPI();
		wa.start();
		Covid_19 cov = new Covid_19();
		cov.start();
		Fortune lucky = new Fortune(2000);
		lucky.start();
		
		try {
			wa.join();
			cov.join();
			lucky.join();
		} catch (Exception e) {}
		
		System.out.println(wa.getWeather());
		System.out.println(cov.getDecide() + " " + cov.getDeath());
		System.out.println(lucky.getLucky());
	}

}
