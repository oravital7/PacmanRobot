package Play;


public class StringTranslate {
	private String s[];
	
	
	public void setString(String s) {
		this.s=s.split(",");
	}
	
	public String getTimeLeft() {
		return s[3].split(":")[1];
	}
	
	public String getScore() {
		return s[2];
	}
	
	public String getTotalTime() {
		return s[1].split(":")[1];
	}
	
	public String getGhostKill() {
		return s[4].split(":")[1];
	}
	
	public String getOutOfBox() {
		return s[5].split(":")[1];
	}
}
