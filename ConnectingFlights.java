package algochallenge;

/**
 * @author Junzhang Wang jw4647 
 * 12/05/2018
 * The idea follows directly from Prim's algorithm, I use Adjacency matrix to implement graphs in order to achieve O(N^2) complexity
 */
class AlgoChallenge {  
	
	//Assume zero-based indexing
	
	/**
	 * This method computes the distance between any two cities and construct a graph using adjacency matrix
	 * I assume that the input "N cities with coordinates" can be represented by a Nx2 2-dimensional array where row i is the coordinate of city i 
	 * Complexity: O(N)+O(N^2)+O(N^2)+O(1)=O(N^2)
	 */
	public static double[][] convertToAdjMatrix(double[][] cities){
		int N = cities.length;//O(N)
		double graph[][] = new double[N][N];//O(N^2)
		for (int i = 0; i<N;i++) {//N*O(N)=O(N^2)
			for(int j = 0; j<N;j++) {//N*O(1)=O(N)
				double xDisSquare = Math.pow(cities[i][0]-cities[j][0], 2);//O(1)
				double yDisSquare = Math.pow(cities[i][1]-cities[j][1], 2);//O(1)
				double distance = Math.sqrt(xDisSquare+yDisSquare);//O(1)
				graph[i][j]=distance;//O(1)
			}
		}
		return graph;//O(1)
	}

	/**
	 * This method finds the v-node in F with smallest d[v]
	 * Complexity: O(N)+O(1)+O(1)+O(N)+O(1)=O(N)
	 */
	public static int minD(double d[], boolean F[]) { 
		int N = d.length;//O(N)
		double minDv = Double.MAX_VALUE;//O(1)
		int vIndex=-1; //O(1)
		for (int v = 0; v < N; v++) {//N*O(1)=O(N)
			if (F[v] == true && d[v] < minDv){ //O(1)
				minDv = d[v]; //O(1)
				vIndex = v; //O(1)
			} 
		}
		return vIndex; //O(1)
	} 

	/**
	 * This method prints the list of flights
	 * Complexity: O(N)+O(1)+O(N)=O(N)
	 */
	public static void printFlightsList(int p[]){ 
		int N = p.length;//O(N)
		System.out.println("List of flights:"); //O(1)
		for (int i = 1; i < N; i++) {//N*O(1)=O(N)
			System.out.println("city "+p[i]+" - "+ "city "+i);//O(1)
		}
	} 
 
	/**
	 * This is the Wrapper algorithm that solves the problem
	 * The significance for the p array, d array and F array follows directly from our lecture 
	 * Complexity: O(N^2)+O(N)+O(N)+O(N)+O(N)+O(N)+O(1)+O(N^2)+O(N)= O(N^2)
	 */
	public static void challenge(double cities[][]) { 
		double graph[][] = convertToAdjMatrix(cities);//O(N^2)
		int N = graph.length; //O(N)
		int p[] = new int[N]; //O(N)
		double d[] = new double [N]; //O(N)
		//F represents the set of all vertices, if node i is in F, F[i] is true; otherwise F[i] is false
		boolean F[] = new boolean[N];//O(N)
		for (int i = 0; i < N; i++){ //This for loop takes O(N)
			d[i] = Double.MAX_VALUE; 
			p[i] = -1;
			F[i] = true; 
		} 
		d[0]=0;//O(1)
		/*
		 * The complexity for this nested for loops = N*(O(N)+O(1)+O(N)+O(1)) = O(N^2)
		 */
		for (int k = 0; k < N; k++){ //this is equivalent to "while F is not empty"
			int v = minD(d, F); //O(N)
			//if F is empty, then we exit the for loop
			if(v==-1) {break;} //O(1)
			/*
			 * The following inner for loop loops N iterations, each iteration does constant
			 * work since we use adjacency matrix as an implementation of graph,
			 * thus this inner for loop takes complexity O(N)
			 */
			for (int u = 0; u < N; u++) {
				double edgeDistance = graph[v][u];//O(1)
				if (graph[v][u]!=0 && F[u] == true && d[u]>edgeDistance){//O(1) 
					p[u] = v; //O(1)
					d[u] = edgeDistance;//O(1) 
				} 
			}
			F[v]=false;//O(1)
		} 
		printFlightsList(p);//O(N)
	} 
	
	/**
	 * Sample Input
	 */
	public static void main (String[] args){ 
		double cities[][] = new double[][] {{0, 1}, 
									       {2, 4}, 
									       {0, 3}, 
									       {6, 8}, 
									       {0, 5},
									       {4, 7},
									       {2, 9},
									       {8, 6},
									       {2, 3},
									       {9, 1},
									       {5, 5}}; 
	    challenge(cities);					
	} 
} 

