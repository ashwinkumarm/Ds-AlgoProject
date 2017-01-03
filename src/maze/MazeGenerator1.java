package maze;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator1 extends Frame{
	
	static ArrayList<Cells> leftBoundary = new ArrayList<Cells>();
	static ArrayList<Cells> upperBoundary = new ArrayList<Cells>();
	static ArrayList<Cells> bottomBoundary = new ArrayList<Cells>();
	static ArrayList<Cells> rightBoundary = new ArrayList<Cells>();
	
	static ArrayList<Cells> list = new ArrayList<Cells>();
	static Cells[] cell = new Cells[25];
	double x = 50;
	double y = 75;
	double Verticalx = 75;
	double Verticaly = 75;
	double lowerHorizontalx = 50;
	double lowerHorizontaly = 50;
	static int row;
	static int col;
	
	public MazeGenerator1(){
		prepareGUI();
	}
	
	 private void prepareGUI(){
	      setSize(400,400);
	      addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      }); 
	   }  

	 @Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Font font = new Font("Serif", Font.PLAIN, 24);
		g2.setFont(font);
		int c = 0;
		int i = 0;
		while (c < row*col) {

			if (i >= col) {
				
				x = 50;
				y = y + 25;
				i = 0;

			}

			Line2D shapeBH = new Line2D.Double();
			shapeBH.setLine(x, y - 25, x + 25, y - 25);
			if (cell[c].getBottomWall() != 'n') {
				g2.draw(shapeBH);
			}
			Line2D shapeLV = new Line2D.Double();
			shapeLV.setLine(x, y, x, y - 25);
			if (cell[c].getLeftWall() != 'n') {
				g2.draw(shapeLV);
			}
			Line2D shapeUH = new Line2D.Double();
			shapeUH.setLine(x + 25, y, x, y);
			if (cell[c].getBottomWall() != 'n') {
				g2.draw(shapeUH);
			}
			Line2D shapeRV = new Line2D.Double();
			shapeRV.setLine(x + 25, y, x + 25, y - 25);
			if (cell[c].getRightWall() != 'n') {
				g2.draw(shapeRV);
			}

			x = x + 25;
			c++;
			i++;

		}

	} 
 
	public static ArrayList<Cells> neighbouringCells(int cellNo){
		ArrayList<Cells> neighbouringCells = new ArrayList<Cells>();
		if(cell[cellNo].getBottomWall() != 'b' && cell[cellNo].getTopWall() != 'b'
				&& cell[cellNo].getLeftWall() != 'b' && cell[cellNo].getRightWall() != 'b'){
			neighbouringCells.add(cell[cellNo-1]);
			neighbouringCells.add(cell[cellNo+1]);
			neighbouringCells.add(cell[cellNo-col]);
			neighbouringCells.add(cell[cellNo+col]);
		}
		if(leftBoundary.contains(cell[cellNo])){
			if(cellNo == 0){
				neighbouringCells.add(cell[1]);
				neighbouringCells.add(cell[col]);
			}
			else if(cellNo == ((row-1)*col)){
				neighbouringCells.add(cell[cellNo-col]);
				neighbouringCells.add(cell[cellNo+1]);
			}
			else{
				neighbouringCells.add(cell[cellNo+1]);
				neighbouringCells.add(cell[cellNo-col]);
				neighbouringCells.add(cell[cellNo+col]);
			}
		}
		else if(rightBoundary.contains(cell[cellNo])){
			if(cellNo == col-1){
				neighbouringCells.add(cell[cellNo-1]);
				neighbouringCells.add(cell[cellNo+col]);
			}
			else if(cellNo == ((row*col)-1)){
				neighbouringCells.add(cell[cellNo-col]);
				neighbouringCells.add(cell[cellNo-1]);
			}
			else{
				neighbouringCells.add(cell[cellNo-1]);
				neighbouringCells.add(cell[cellNo-col]);
				neighbouringCells.add(cell[cellNo+col]);
			}
		}
		else if(bottomBoundary.contains(cell[cellNo])){
			neighbouringCells.add(cell[cellNo+1]);
			neighbouringCells.add(cell[cellNo-col]);
			neighbouringCells.add(cell[cellNo-1]);
		}
		else if(upperBoundary.contains(cell[cellNo])){
			neighbouringCells.add(cell[cellNo+1]);
			neighbouringCells.add(cell[cellNo-1]);
			neighbouringCells.add(cell[cellNo+col]);
		}
		
		return neighbouringCells;
	} 
	 
	 public static void maze(){
		 Random rand = new Random();
			
			row = 5;
		    col = 5;
			int n = 0;

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					cell[n] = new Cells();
					cell[n].setNo((i * 5) + j);
					if (i == 0) {
						cell[n].setTopWall('b');
						upperBoundary.add(cell[n]);
					}
					if (j == 0) {
						cell[n].setLeftWall('b');
						leftBoundary.add(cell[n]);
					}
					if (j == 4) {
						cell[n].setRightWall('b');
						rightBoundary.add(cell[n]);
					}
					if (i == 4) {
						cell[n].setBottomWall('b');
						bottomBoundary.add(cell[n]);
					}
					n++;
				}

			}
			
			
			
			int currentCell = rand.nextInt(row*col);
			int nextCell = -1 ;
			
			ArrayList<Cells> path = new ArrayList<Cells>();
			
			while(!(path.contains(cell[0]) && path.contains(cell[(row*col)-1]))){
			
			int wall = rand.nextInt(3);
			
			switch (wall) {

			case 0:
				if (!leftBoundary.contains(cell[currentCell])) {
					cell[currentCell].setLeftWall('n');
					cell[currentCell - 1].setRightWall('n');
					path.add(cell[currentCell]);
					nextCell = currentCell-1;
					path.add(cell[currentCell-1]);
				}else{
					if(currentCell+1 < row*col){
					if(cell[currentCell].getRightWall() == 'y' && cell[currentCell+1].getLeftWall() == 'y' ){
						cell[currentCell].setRightWall('n');
						cell[currentCell+1].setLeftWall('n');
						path.add(cell[currentCell]);
						nextCell = currentCell+1;
						path.add(cell[currentCell+1]);
					}}
					else if(currentCell-col > 0){
						if(cell[currentCell].getTopWall() == 'y' && cell[currentCell-5].getBottomWall() == 'y' ){
						cell[currentCell].setTopWall('n');
						cell[currentCell-col].setBottomWall('n');
						path.add(cell[currentCell]);
						nextCell = currentCell-col;
						path.add(cell[nextCell]);
					}}
					
					else if(currentCell+col < row*col){
						if(cell[currentCell].getBottomWall() == 'y' && cell[currentCell+5].getTopWall() == 'y' ){
						cell[currentCell].setBottomWall('n');
						cell[currentCell+5].setTopWall('n');
						path.add(cell[currentCell]);
						nextCell = currentCell+col;
						path.add(cell[nextCell]);
					}
					}
					
				}
				break;
			case 1:
				if (!bottomBoundary.contains(cell[currentCell])) {
					cell[currentCell].setBottomWall('n');
					cell[currentCell + col].setTopWall('n');
					path.add(cell[currentCell]);
					nextCell = currentCell+5;
					path.add(cell[nextCell]);
				}else{
					if(currentCell+1 < row*col){
					if(cell[currentCell].getRightWall() == 'y' && cell[currentCell+1].getLeftWall() == 'y' ){
						cell[currentCell].setRightWall('n');
						cell[currentCell+1].setLeftWall('n');
						path.add(cell[currentCell]);
						nextCell = currentCell+1;
						path.add(cell[currentCell+1]);
					}}
					else if(currentCell-col > 0){
						if(cell[currentCell].getTopWall() == 'y' && cell[currentCell-5].getBottomWall() == 'y' ){
						cell[currentCell].setTopWall('n');
						cell[currentCell-col].setBottomWall('n');
						path.add(cell[currentCell]);
						nextCell = currentCell-col;
						path.add(cell[nextCell]);
					}}
					
					else if(currentCell-1 != (row*(col-1))+1){
						if(cell[currentCell].getLeftWall() == 'y' && cell[currentCell -1].getRightWall() == 'y' ){
							cell[currentCell].setLeftWall('n');
							cell[currentCell - 1].setRightWall('n');
							path.add(cell[currentCell]);
							nextCell = currentCell-1;
							path.add(cell[currentCell-1]);
					}
					}
					
				}
				break;
			case 2:
				if (!upperBoundary.contains(cell[currentCell])) {
					cell[currentCell].setTopWall('n');
					cell[currentCell - col].setBottomWall('n');
					path.add(cell[currentCell]);
					nextCell = currentCell-col;
					path.add(cell[nextCell]);
				}else{
					if(currentCell+1 < row*col){
					if(cell[currentCell].getRightWall() == 'y' && cell[currentCell+1].getLeftWall() == 'y' ){
						cell[currentCell].setRightWall('n');
						cell[currentCell+1].setLeftWall('n');
						path.add(cell[currentCell]);
						nextCell = currentCell+1;
						path.add(cell[currentCell+1]);
					}}
					else if(currentCell + col > 0){
						if(cell[currentCell].getBottomWall() == 'y' && cell[currentCell+5].getTopWall() == 'y' ){
							cell[currentCell].setBottomWall('n');
							cell[currentCell + col].setTopWall('n');
							path.add(cell[currentCell]);
							nextCell = currentCell+col;
							path.add(cell[nextCell]);
					}}
					
					else if(currentCell-1 != (row*(col-1))+1){
						if(cell[currentCell].getLeftWall() == 'y' && cell[currentCell-1].getRightWall() == 'y' ){
							cell[currentCell].setLeftWall('n');
							cell[currentCell - 1].setRightWall('n');
							path.add(cell[currentCell]);
							nextCell = currentCell-1;
							path.add(cell[currentCell-1]);
					}
					}
					
				}
				break;
			case 3:
				if (!rightBoundary.contains(cell[currentCell])) {
					cell[currentCell].setRightWall('n');
					cell[currentCell +1].setLeftWall('n');
					path.add(cell[currentCell]);
					nextCell = currentCell+1;
					path.add(cell[nextCell]);
				}else{
					if(currentCell+1 < row*col){
					if(cell[currentCell].getTopWall() == 'y' && cell[currentCell-5].getBottomWall() == 'y' ){
						cell[currentCell].setTopWall('n');
						cell[currentCell - 5].setBottomWall('n');
						path.add(cell[currentCell]);
						nextCell = currentCell-5;
						path.add(cell[nextCell]);
					}}
					else if(currentCell + col > 0){
						if(cell[currentCell].getBottomWall() == 'y' && cell[currentCell+5].getTopWall() == 'y' ){
							cell[currentCell].setBottomWall('n');
							cell[currentCell + col].setTopWall('n');
							path.add(cell[currentCell]);
							nextCell = currentCell+col;
							path.add(cell[nextCell]);
					}}
					
					else if(currentCell-1 != (row*(col-1))+1){
						if(cell[currentCell].getLeftWall() == 'y' && cell[currentCell-1].getRightWall() == 'y' ){
							cell[currentCell].setLeftWall('n');
							cell[currentCell - 1].setRightWall('n');
							path.add(cell[currentCell]);
							nextCell = currentCell-1;
							path.add(cell[currentCell-1]);
					}
					}
					
				}
				break;

			}
			System.out.println(cell[currentCell].getNo());
			System.out.println(cell[nextCell].getNo());
			ArrayList<Cells> nCells = neighbouringCells(nextCell);
			ArrayList<Cells>possCells = new ArrayList<Cells>();
			for(Cells ncell:nCells){
				if(ncell.getNo()!= cell[currentCell].getNo() && !path.contains(ncell)){
					possCells.add(ncell);
				}
			}
			
			if(possCells.size() != 0){
			int l = rand.nextInt(possCells.size());
			currentCell = possCells.get(l).getNo();
			}
			else{
				ArrayList<Cells> pCells = new ArrayList<Cells>();
				int i =1;
				while(pCells.size() == 0){
					int c = rand.nextInt(path.size()-1);
					Cells randCell = path.get(c);
				ArrayList<Cells> nextCells = neighbouringCells(randCell.getNo());
				i++;
				for(Cells ncell:nextCells){
					if(ncell.getNo()!= cell[currentCell].getNo() && !path.contains(ncell)){
						pCells.add(ncell);
					}
				}
				}
				int l = rand.nextInt(pCells.size());
				currentCell = pCells.get(l).getNo();
			}
			
			//currentCell = nextCell;
			}
			
				 
	 }
	
	 
	public static void main(String[] args) {

		maze();
		MazeGenerator1 mGen = new MazeGenerator1();
		mGen.setVisible(true);
		
	}
	
}