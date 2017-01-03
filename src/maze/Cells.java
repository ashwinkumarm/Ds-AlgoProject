package maze;

public class Cells {
	
	 char leftWall;
	 char rightWall;
	 char topWall;
	 char bottomWall;
	 int no;
	
	
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	Cells(){
		this.leftWall = 'y';
		this.rightWall = 'y';
		this.topWall = 'y';
		this.bottomWall = 'y';
	}


	public char getLeftWall() {
		return leftWall;
	}


	public void setLeftWall(char leftWall) {
		this.leftWall = leftWall;
	}


	public char getRightWall() {
		return rightWall;
	}


	public void setRightWall(char rightWall) {
		this.rightWall = rightWall;
	}


	public char getTopWall() {
		return topWall;
	}


	public void setTopWall(char topWall) {
		this.topWall = topWall;
	}


	public char getBottomWall() {
		return bottomWall;
	}


	public void setBottomWall(char bottomWall) {
		this.bottomWall = bottomWall;
	}
	
	
	
	
	

	

	
}
