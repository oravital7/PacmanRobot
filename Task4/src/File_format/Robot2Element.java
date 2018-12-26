package File_format;

import java.util.ArrayList;

import Gameboard.Blocks;
import Gameboard.Fruit;
import Gameboard.Game;
import Gameboard.Ghost;
import Gameboard.Me;
import Gameboard.Pacman;
import Geom.Point3D;


/**
 * 
 * This class convert CSV data to java objects 
 *
 */
public class Robot2Element {
	private Game game;

	public Robot2Element(Game game) {
		this.game = game;
	}

	/**
	 * Running on the String array that we got from CsvReader class and 
	 * finally add it to the layer array
	 * 
	 * @return
	 */
	public void MakeElements(ArrayList<String> data) {
		game.clear();
		for(String s : data) {
			String make[] = s.split(",");
			int id = convert2Int(make[1]);
			double x = convert2Double(make[2]);
			double y = convert2Double(make[3]);
			double z = convert2Double(make[4]);
			double attribute = convert2Double(make[5]);

			Point3D p = new Point3D(x,y,z);

			if(make[0].toUpperCase().contains("B")) {
				if(!game.init) {
				double x2 = convert2Double(make[5]);
				double y2 = convert2Double(make[6]);
				double z2 = convert2Double(make[7]);
					Point3D p2 = new Point3D(x2,y2,z2);
					attribute = convert2Double(make[8]);
					Blocks b = new Blocks(id,p,p2,attribute);
					game.addBlock(b);
				}
			}

			else if(make[0].toUpperCase().contains("P")) {
				double radius = convert2Double(make[6]);

				if(game.init) {
					game.updatePacman(id, p);
				}
				else {
					Pacman pc = new Pacman(p, id, attribute, radius);
					game.addPacman(pc);
				}
			}

			else if(make[0].toUpperCase().contains("G")) {
				double radius = convert2Double(make[6]);
				if(game.init) {
					game.updateGhost(id, p);		
				}
				else {
					Ghost g = new Ghost(p, id, attribute, radius);
					game.addGhost(g);
				}

			}

			else if(make[0].toUpperCase().contains("F")) {
				if(game.init) {
					game.updateFruit(id);
				}
				else {
					Fruit f = new Fruit(p, id,attribute);
					game.addFruit(f);
				}
			}

			else  {
				Me me = new Me(p,id,attribute);
				game.setMe(me);
			}

		}
		game.init=true;
	}
	
	public int numOfFruite() {
		return game.numOfFruits();
	}

	/**
	 * Parse to int
	 * @param s
	 * @return
	 */
	private int convert2Int(String s) {
		int x = Integer.parseInt(s);
		return x;
	}

	/**
	 * Parse to double
	 * @param s
	 * @return
	 */
	private double convert2Double(String s) {
		double x = Double.parseDouble(s);
		return x;
	}

}
