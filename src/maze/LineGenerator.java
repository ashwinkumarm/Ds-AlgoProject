package maze;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;

import project5.CellWalls;

public class LineGenerator extends Frame{
	
	static int row= 5;
	static int col = 5;
	static CellWalls[] cellWall ;
	double x = 50;
	double y = 75;
	
	LineGenerator(){
		prepareGUI();
	}
	
	public void prepareGUI(){
		setSize(400,400);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
	}
	
	public  void  border(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Font font = new Font("Serif", Font.PLAIN, 24);
		g2.setFont(font);
		Line2D upperLine = new Line2D.Double();
		upperLine.setLine(50, 75, (25*col)+ 50 , 75);
		g2.draw(upperLine);
		Line2D leftLine = new Line2D.Double();
		leftLine.setLine(50, 75, 50 , (25*row)+75);
		g2.draw(leftLine);
		Line2D bottomLine = new Line2D.Double();
		bottomLine.setLine(50, (25*row)+75, 50 + (25*col) , (25*row)+75);
		g2.draw(bottomLine);
		Line2D rightLine = new Line2D.Double();
		rightLine.setLine(50 + (25*col) , 75, 50 + (25*col)  , (25*row)+75);
		g2.draw(rightLine);
		
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Font font = new Font("Serif", Font.PLAIN, 24);
		g2.setFont(font);
		int c = 0; int vCol =0; int hCol = 0;
		border(g);
		while(c < cellWall.length/2){
			if(vCol < col-1){
				x += 25;
				vCol++;	
			}
			else{
				x = 75;
				y += 25;
				vCol = 1;
			}
			Line2D line = new Line2D.Double();
			line.setLine(x, y, x, y+25);
			if(cellWall[c].isVisible()){
				g2.draw(line);
			}
			c++;
		} x = 50; y = 100;
		while( c >= cellWall.length/2 && c < cellWall.length){
			Line2D line = new Line2D.Double();
			line.setLine(x, y, x+25, y);
			if(cellWall[c].isVisible()){
				g2.draw(line);
			}
			
			if(hCol < col-1){
				x += 25;
				hCol++;
			}else{
				x= 50;
				y += 25;
				hCol =0;
			}
			
			c++;
		} 
	}
	
	public static void wallIntialize(int row, int col, CellWalls[] cellWall) {

		int VWallCell = 0;
		int HWallCell = 0;
		int vCol = 0;
		for (int i = 0; i < cellWall.length; i++) {
			cellWall[i] = new CellWalls();
			cellWall[i].setVisible(true);
			if (i < cellWall.length / 2) {
				if (vCol < col - 1) {
					cellWall[i].setnCell1(VWallCell);
					cellWall[i].setnCell2(VWallCell + 1);
					vCol++;
				} else {
					vCol = 0;
					VWallCell += 1;
					cellWall[i].setnCell1(VWallCell);
					cellWall[i].setnCell2(VWallCell + 1);
					vCol++;

				}
				VWallCell++;
			} else {

				cellWall[i].setnCell1(HWallCell);
				cellWall[i].setnCell2(HWallCell + col);
				HWallCell++;
			}

		}

		System.out.println();
	}
	
	public static void main(String[] args){
		
		
		cellWall = new CellWalls[(row * (col - 1)) + ((row - 1) * col)];
		wallIntialize(row, col, cellWall);
		
		LineGenerator l = new LineGenerator();
		l.setVisible(true);

	}

}
