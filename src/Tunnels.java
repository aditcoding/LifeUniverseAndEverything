import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tunnels {

	static int n = 0; // number of nodes
	static int e = 0; // number of edges
	static int[][] a; // weighted n x n adjacency matrix
	static int[] minCut; // minCut for each source node and target 0
	static int[][] maxMinMinCut;
	// maximum, over all paths from u to v, of
	// minimum, over all nodes in the path, of
	// minCut with source v and target 0

	// for computation of mincut

	static int[][] flow; // flow for each edge

	// for finding augmenting path
	static int[] prev; // prev node in search order
						// if value >= 0
	static int[] capTo; // capacity of path found to node
						// if prev[i] >= 0
	static int[] queue; // nodes still to be visited
						private static int[] useful = new int [105];

	// dump contents of a matrix in rectangular form
	static void dumpMatrix(String name, int[][] M) {
		log("-------" + name + "---------");
		for (int i = 0; i < M.length; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < M[i].length; j++) {
				System.out.print(M[i][j] + " ");
			}
			log();
		}
	}

	// dump contents of a matrix in list form
	static void dumpMat(String name, int[][] M) {
		log("-------" + name + "---------");
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				if (M[i][j] > 0)
					log("M[" + i + "][" + j + "] = " + M[i][j]);
				else if (M[i][j] != -M[j][i])
					log("M[" + i + "][" + j + "] = " + M[i][j]);
			}
		}
	}

	// breadth-first search for shortest augmenting path
	// i.e, from s to t with positive residual capacity
	// This phase is O(V^2).
	// It could be made O(E) if we used an adjacency list,
	// rather than a matrix.

	static int findAugmentingPath(int s, int t) {

		// initialize arrays
		for (int i = 0; i < n; i++) {
			prev[i] = -1;
			capTo[i] = Integer.MAX_VALUE;
		}

		// insert source node into empty queue
		int first = 0, last = 0;
		queue[last++] = s;
		prev[s] = -2; // to indicate it has been visited

		// repeat until all nodes have been visited
		while (first != last) {
			int u = queue[first++];
			// consider each edge (u,v)
			for (int v = 0; v < n; v++) {
				if (a[u][v] > 0) {
					// if v not visited yet and (u,v) has residual capacity
					int edgeCap = a[u][v] - flow[u][v];
					if ((prev[v] == -1) && (edgeCap > 0)) {
						capTo[v] = Math.min(capTo[u], edgeCap);
						prev[v] = u;
						// return soon as augmenting path found
						if (v == t)
							return capTo[v];
						queue[last++] = v;
					}
				}
			}
		}
		return 0;
	}

	// find maximum flow from s to t
	// Edmonds-Karp variation of Ford-Fulkerson Algorithm
	// This version appears to be O(V^3), if the maximum flow value
	// O(V). Since we are working with integers, each
	// augmenting path increases the flow by at least one,
	// and so findAugmentingPath is called O(V) times.
	static int findMaxFlow(int s, int t) {
		int result = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				flow[i][j] = 0;
			}
		}

		for (;;) {
			// Each augmenting path must increase the flow
			// by at least one.
			int Increment = findAugmentingPath(s, t);
			if (Increment == 0) {
				return result;
			}
			result += capTo[t];
			int v = t, u;
			// Augment the flow along the path
			// This part is O(p), which is O(V).
			while (v != s) {
				u = prev[v];
				flow[u][v] += capTo[t];
				flow[v][u] -= capTo[t]; // ??????
				v = u;
			}
		}
	}

	// find maximum of minimum value of mincut among all paths
	// from 0 to 1

	// Method: dynamic programming
	// For a path p, let minCut(p) be the minimum value of
	// minCut[j] for every edge (i,j) in p.
	// Compute
	// maxMinMinCut[i][j] = x iff
	// i) there is at least one path p from i to j such that
	// minCut(p) = x;
	// ii) for every path p from i to j
	// minCut(p) <= x.

	// Alternate method: graph reduction
	// delete and collapse nodes, starting with those of
	// highest mincut value, until there is no longer a path
	// from 0 to 1

	static void findMaxMinMinCut() {
		int ij, ik, kj, kk;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (a[i][j] > 0)
					maxMinMinCut[i][j] = minCut[j];
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					ij = maxMinMinCut[i][j];
					ik = maxMinMinCut[i][k];
					kj = maxMinMinCut[k][j];
					if ((ik > 0) && (kj > 0)) {
						kk = Math.min(ik, kj);
						if (kk > ij)
							maxMinMinCut[i][j] = kk;
					}
				}
	}
	
	private static void findMaxMinMinCut2() {
		for(int i=1;i<=n;i++)
		{
			int m=2147483647;
			for(int j=1;j<=n;j++)
				if(useful[j]==0 && minCut[j]<m)
					m=minCut[j];
			for(int j=1;j<=n;j++)
				if(m==minCut[j])
				{
					useful[j]=1;
					del(j);
				}
				for(int j=1;j<=n;j++)
					if(useful[j]==0)
					{
						minCut[j]=Math.min(minCut[j],m+findMaxFlow(0,j));
					}
		}
		
	}
	
	static void del(int x)
	{
		for(int i=0;i<=n;i++)
			a[i][x]=a[x][i]=0;
	}

	// main procedure
	public static void main(String arg[]) throws FileNotFoundException {
		String inFileName;

		if (arg.length > 0) {
			inFileName = arg[0];
		} else {
			inFileName = "tunnels.in";
		}
		//FileInputStream inStream = new FileInputStream(inFileName);
		Scanner inScan = new Scanner(System.in);

		for (;;) {
			// try to read in a problem
			n = inScan.nextInt() + 1; // number of nodes
			e = inScan.nextInt(); // number of edges
			if (n == 1 && e == 0)
				break;
			log("--new graph-- " + n + " " + e);
			// allocate & initialize the arrays
			a = new int[105][105];
			minCut = new int[105];
			maxMinMinCut = new int[n][n];
			flow = new int[n][n];
			prev = new int[n];
			capTo = new int[n];
			queue = new int[n];
			// read in list of edges, and compute weights for repeated edges
			int x, y;
			for (int i = 0; i < e; i++) {
				x = inScan.nextInt();
				y = inScan.nextInt();
				a[x][y]++;
				a[y][x]++;
			}
			// find mincut for each node
			for (int i = 1; i < n; i++) {
				minCut[i] = findMaxFlow(0, i);
				log("minCut(" + i + ")= " + minCut[i]);
			}
			// compute maxmincut
			findMaxMinMinCut();
			//findMaxMinMinCut2();
			dumpMat("maxMinMinCut", maxMinMinCut);
			log("maxMinMinCut = " + maxMinMinCut[0][1]);
		}
	}
	
	static void log(Object... obj) {
		for (Object o : obj) {
			System.out.println(o);
		}
	}
}