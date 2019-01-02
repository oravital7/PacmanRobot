package dataBase;

import java.awt.Color;
import java.awt.Font;

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
		
		JTable ourScore = new JTable(new TableModel(mq.QueryWhereId("5555")));
		JTable allScore = new JTable(new TableModel(mq.QueryAll()));
		
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
