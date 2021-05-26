import java.io.Serializable;

public class Userdata implements Serializable {
		private String ID;
		private String PW;
		
		public Userdata(String ID, String PW) {
			this.ID = ID;
			this.PW = PW;
		}
		
		public Userdata() {
			this.ID = "";
			this.PW = "";
		}
		
		public String getPW() {
			return this.PW;
		}
	}
