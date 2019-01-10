package dataBase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Responsible for displaying the results table and top scores 
 */
public class Table {
	private JFrame f;
	private JTable ourScore, allScore;
	private JLabel others,our;
	private JScrollPane scrollPane,scrollPane2;
	private JSplitPane splitPane;
	private boolean ok;

	public Table() {
		f = new JFrame();
		f.setTitle("Table Scores");
		f.setSize(1120, 800);
		f.setLocation(400, 50);
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		ok = false;

		start();
	}

	/**
	 * Extracts the data from MySql and builds the table
	 */
	private void start() {
		Font font = new Font("Tahoma", Font.PLAIN, 14);

		try {
			f.setIconImage(ImageIO.read(new File("Icon\\graph.png")));
		} catch (IOException e) {
			System.out.println("Unable load graph Image!!!");
		}

		final JDialog dialog = new JDialog(f, "Hope you enjoyed :)", ModalityType.APPLICATION_MODAL);
		
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			
			@Override
			protected Void doInBackground() throws Exception {
				MySql mq = new MySql();

				////////////////////  Create Tables ////////////////////
				ourScore = new JTable(new TableModel(mq.Query("315392852",true)));
				allScore = new JTable(new TableModel(mq.Query("315392852",false)));

				ourScore.setAutoCreateRowSorter(true);
				allScore.setAutoCreateRowSorter(true);

				scrollPane = new JScrollPane(ourScore);
				scrollPane2 = new JScrollPane(allScore);
				splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
						scrollPane, scrollPane2);

				///////////////// Pull high scores /////////////////
				String ourS = mq.getScore(true, "315392852");
				String othersS = mq.getScore(false, "315392852");
				our = new JLabel("Our High Scores     "+ourS);
				others = new JLabel("Others High Scores "+othersS);
				mq.closeConnection();
				return null;
			}

			@Override
			protected void done() {
				ok = true;
				dialog.dispose();
			}
		};
		mySwingWorker.execute();

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		Border black =  BorderFactory.createEtchedBorder(EtchedBorder.RAISED); 
		progressBar.setBorder(black);		
		JLabel label = new JLabel("One moment.......");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));

		dialog.add(progressBar);
		dialog.add(label,BorderLayout.PAGE_START);
		dialog.setSize(250, 100);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		if(ok) {
			our.setFont(font);
			others.setFont(font);
			splitPane.setResizeWeight(0.5);
			ourScore.setFont(font);
			allScore.setFont(font);
			allScore.setBackground(Color.LIGHT_GRAY);

			f.add(our);
			f.add(others);
			f.add(splitPane);
			f.setLocationRelativeTo(null);
			f.setVisible (true);
		}
		else f.dispose();

	}

}
