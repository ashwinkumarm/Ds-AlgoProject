package project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Kruskals {

	private static void constructEdgeVertices(List<Edge> edgeList, 
			HashMap<String,Integer> vertices, String line) {
			String[] arr = line.split(",");
			vertices.put(arr[0],vertices.size());
			int j =1;
			
			while(j < arr.length){
				Edge edge = new Edge();
				edge.setVertexA(arr[0]);
				edge.setVertexB(arr[j]);
				edge.setDist(Integer.parseInt(arr[j+1]));
				edgeList.add(edge);
			j+=2;}
		
	}
	
	private static List<Edge> kruskal(List<Edge> edgeList, HashMap<String,Integer> vertices){
		
		DisjSets disjSets = new DisjSets(vertices.size());
		
		PriorityQueue<Edge> que = new PriorityQueue<Edge>();
		
		que.addAll(edgeList);
		
		List<Edge> mst = new ArrayList<Edge>();
		
		while(mst.size() != vertices.size()-1){
			Edge e = que.poll();
			int uSet = disjSets.find(vertices.get(e.getVertexA()));
			int vSet = disjSets.find(vertices.get(e.getVertexB()));
			
			if(uSet != vSet){
				mst.add(e);
				disjSets.union(uSet, vSet);
			}
		}
		return mst;
	}

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("D:\\Downloads\\assn9_data.csv");
		Scanner inp = new Scanner(file);
		List<Edge> edgeList = new ArrayList<Edge>();
		HashMap<String,Integer> vertices = new HashMap<String,Integer>();
		List<Edge> mst = new ArrayList<Edge>();
		int totalDist = 0;
		
		while(inp.hasNextLine()){
			constructEdgeVertices(edgeList, vertices, inp.nextLine());
		}
		inp.close();
		
		mst = kruskal(edgeList, vertices);
		
		for(Edge e: mst){
			System.out.println(e.getVertexA() +" "+ e.getVertexB() + " "+ e.getDist());
			totalDist += e.getDist();
		}
		
		System.out.println("Total distance: "+ totalDist);
	}
	

}
