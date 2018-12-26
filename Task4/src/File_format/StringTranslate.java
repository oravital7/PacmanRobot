package File_format;


public class StringTranslate {
	private String s[];
	
	
	public void setString(String s) {
		this.s=s.split(",");
	}
	
	public double getTimeLeft() {
		String result[] = s[3].split(":"); 
		return Double.parseDouble(result[1]);
	}
	
	public String getScore() {
		return s[2];
	}
	
	public String getTotalTime() {
		return s[1];
	}
	
	public String getGhostKill() {
		return s[4];
	}
	
	public String getOutOfBox() {
		return s[5];
	}
}
