package project6;

public class Edge implements Comparable{

	String vertexA;
	String vertexB;
	int dist;

	public String getVertexA() {
		return vertexA;
	}

	public void setVertexA(String vertexA) {
		this.vertexA = vertexA;
	}

	public String getVertexB() {
		return vertexB;
	}

	public void setVertexB(String vertexB) {
		this.vertexB = vertexB;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}


	@Override
	public int compareTo(Object edge) {
	    int dist = (int) ((Edge) edge).getDist();
		return this.dist - dist;
	}

	

}
