package dataBase;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class Table {
	private JFrame f;
	
	public Table() {
		f = new JFrame();
		f.setTitle("Table Scores");
		f.setSize(1000, 800);
		start();
	}
	
	private void start() {
		MySql mq = new MySql();
		try {
			f.setIconImage(ImageIO.read(new File("Icon\\graph.png")));
		} catch (IOException e) {
			System.out.println("Unable load graph Image!!!");
		}

		JTable ourScore = new JTable(new TableModel(mq.QueryWhereId("5555")));
		JTable allScore = new JTable(new TableModel(mq.QueryAll()));
		ourScore.setAutoCreateRowSorter(true);
		allScore.setAutoCreateRowSorter(true);

		ourScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
		allScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
		allScore.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane = new JScrollPane(ourScore);
		JScrollPane scrollPane2 = new JScrollPane(allScore);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				scrollPane, scrollPane2);
		splitPane.setResizeWeight(0.5);
		
		f.add(splitPane);
		f.setVisible(true);
	}

}
