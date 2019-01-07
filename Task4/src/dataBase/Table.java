package dataBase;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

/**
 * Responsible for displaying the results table and top scores 
 */
public class Table {
	private JFrame f;
	
	public Table() {
		f = new JFrame();
		f.setTitle("Table Scores");
		f.setSize(1120, 800);
		f.setLocation(400, 50);
		start();
	}
	
	/**
	 * Extracts the data from MySql and builds the table
	 */
	private void start() {
		MySql mq = new MySql();

		try {
			f.setIconImage(ImageIO.read(new File("Icon\\graph.png")));
		} catch (IOException e) {
			System.out.println("Unable load graph Image!!!");
		}
		
		////////////////////  Create Tables ////////////////////
		JTable ourScore = new JTable(new TableModel(mq.Query("315392852",true)));
		JTable allScore = new JTable(new TableModel(mq.Query("315392852",false)));

		ourScore.setAutoCreateRowSorter(true);
		allScore.setAutoCreateRowSorter(true);
		
		Font font = new Font("Tahoma", Font.PLAIN, 14);
		ourScore.setFont(font);
		allScore.setFont(font);
		allScore.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane = new JScrollPane(ourScore);
		JScrollPane scrollPane2 = new JScrollPane(allScore);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				scrollPane, scrollPane2);
		
		///////////////// Pull the high scores /////////////////
		String ourS = mq.getScore(true, "315392852");
		String othersS = mq.getScore(false, "315392852");

		JLabel our = new JLabel("Our High Scores     "+ourS);
		JLabel others = new JLabel("Others High Scores "+othersS);
		our.setFont(font);
		others.setFont(font);

		splitPane.setResizeWeight(0.5);
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		f.add(our);
		f.add(others);
		f.add(splitPane);
		f.setVisible(true);

		mq.closeConnection();
	}

}
