package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import dataBase.Table;

public class ButtonsPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -3397611530616842083L;

	private JComboBox<String> JList;
	private MyFrame frame;
	private JButton openFile,ScoreBoard;
	
	public ButtonsPanel(MyFrame frame) {
		this.frame = frame;
		Border black =  BorderFactory.createEtchedBorder(EtchedBorder.RAISED); 

		String[] FileString = {
				"example1", "example2","example3", 
				"example4", "example5", "example6",
				"example7",	"example8","example9"
		};

		JList = new JComboBox<String>(FileString);
		JList.setBorder(black);
		JList.setBackground(Color.white);
		JList.setFont(new Font("Tahoma", Font.BOLD, 14));

		openFile = new JButton("Quick opening",new ImageIcon("Icon\\disc.png"));
		openFile.addActionListener(this);
		
		ScoreBoard = new JButton("ScoreBoard",new ImageIcon("Icon\\graph.png"));
		ScoreBoard.addActionListener(this);

		add(JList);
		add(openFile);
		add(ScoreBoard);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==openFile) {
			File f = new File("data/Ex4_OOP_"+JList.getSelectedItem()+".csv");
			frame.openGameFile(f);
			return;
		}

		if(o==ScoreBoard) {
			new Table();
		}
	}
}
