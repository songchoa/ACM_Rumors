import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Rumors solver
 * @author SongChao
 *
 */

public class Main {
	
	/**
	 * Set infinity value as 99999
	 */
	final static int INF = 99999;

	public static void main(String[] args) {
			
			/*
			 * Construct a scanner for user input.
			 */
			Scanner scan = new Scanner(System.in);
			String nextLine = scan.nextLine();
			while(Integer.valueOf(nextLine) != 0) {
				/*
				 * Set the value for number of vertices in v
				 */
				int v = Integer.valueOf(nextLine);
				/*
				 * Construct a graph and initialize with INF and 0
				 */
				int[][] graph = new int[v][v];
				for(int i = 0; i < v; i++) {
					for(int j = 0; j < v; j++) {
						if(i == j) {
							graph[i][j] = 0;
						} else {
							graph[i][j] = INF;
						}
					}
				}
				
				/*
				 * Store distance into graph at the first time
				 */
				for(int m = 0; m < v; m++) {
					String[] dataLine = scan.nextLine().split(" ");
					for(int n = 1; n < dataLine.length; n = n + 2) {
						int col = Integer.valueOf(dataLine[n]) - 1;
						int distance = Integer.valueOf(dataLine[n + 1]);
						
						graph[m][col] = distance;
					}
				}
				
				/*
				 * call floy warshall solver
				 */
				floydWarshall(graph,v);
				
				/*
				 * read data for next line
				 */
				nextLine = scan.nextLine();
			}
			
			scan.close();

	}
	
	/**
	 * Floyd Warshall algorithm
	 * @param graph - input graph
	 * @param V - number of vertices
	 */
	static void floydWarshall(int graph[][], int V)
	{
		int dist[][] = new int[V][V];
		int i, j, k;

		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];

		for (k = 0; k < V; k++)
		{
			for (i = 0; i < V; i++)
			{
				for (j = 0; j < V; j++)
				{
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}

		// Print the shortest distance matrix
		printSolution(dist, V);
	}

	/**
	 * print solution
	 * @param dist - matrix with distance information
	 * @param V - number of vertices
	 */
	static void printSolution(int dist[][], int V)
	{
		int[] maxs = new int[V];
		
		for(int i = 0; i < V; i++) {
			int max = dist[0][0];
			for(int j = 0; j < V; j++) {
				max = Math.max(max, dist[i][j]);
			}
			maxs[i] = max;
		}
		
		int mindex = 0;
		int min = maxs[0];
		for(int i = 1; i < maxs.length; i++) {
			if(maxs[i] < min) {
				min = maxs[i];
				mindex = i;
			}
		}
		if(min == INF) {
			System.out.println("disjoint");
		} else {
			System.out.println(mindex + 1 + " " + min);
		}
		
	}
}
