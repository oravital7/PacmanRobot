package Play;
/**
 * 
 *This class translate string
 */

public class StringTranslate {
	private String s[];
	
/**
 * 	
 * @param s string
 */
	public void setString(String s) {
		this.s=s.split(",");
	}
/**
 * 	
 * @return how much time is left
 */
	public String getTimeLeft() {
		return s[3].split(":")[1];
	}
/**
 * 	
 * @return the score of the game
 */
	public String getScore() {
		return s[2];
	}
/**
 * 	
 * @return the total time
 */
	public String getTotalTime() {
		return s[1].split(":")[1];
	}
	
	/**
	 * 
	 * @return how many time was the pacman hit by ghosts
	 */
	public String getGhostKill() {
		return s[4].split(":")[1];
	}
	/**
	 * 
	 * @return how many time the pacman crossed the lines of the box
	 */
	public String getOutOfBox() {
		return s[5].split(":")[1];
	}
}
