package graphs;

import java.util.*;
import java.util.Map.Entry;

/**
 * Implements a graph. We use two maps: one map for adjacency properties
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated
 * with a vertex.
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	/**
	 * Initialized the adjacency and data maps
	 */
	public Graph() {
		adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		dataMap = new HashMap<String, E>();
	}

	/**
	 * Adds a vertex to the graph by adding to the adjacency map an entry for
	 * the vertex. This entry will be an empty map. An entry in the dataMap will
	 * store the provided data
	 * 
	 * @param vertexName
	 * @param data
	 */
	public void addVertex(String vertexName, E data) {
		if (dataMap.containsKey(vertexName)) {
			throw new IllegalArgumentException();
		} else {
			dataMap.put(vertexName, data);
			HashMap<String, Integer> endTemp = new HashMap<String, Integer>();
			adjacencyMap.put(vertexName, endTemp);
		}
	}

	/**
	 * Adds or updates a directed edge with the specified cost
	 * 
	 * @param startVertexName
	 * @param endVertexName
	 * @param cost
	 */
	public void addDirectedEdge(String startVertexName, String endVertexName,
			int cost) {
		if (!dataMap.containsKey(startVertexName)
				|| !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException();
		} else {
			HashMap<String, Integer> endTemp = new HashMap<String, Integer>(
					adjacencyMap.get(startVertexName));
			endTemp.put(endVertexName, cost);
			adjacencyMap.put(startVertexName, endTemp); // one way since its a
														// directed edge
		}
	}

	/**
	 * Returns a string with information about the Graph. Notice that vertices
	 * are printed in sorted order and information about adjacent edges is
	 * printed in sorted order (by vertex name). You may not use
	 * Collections.sort or Arrays.sort in order to implement this method
	 */
	public String toString() {
		TreeMap<String, E> tree = new TreeMap<String, E>();
		for (String vertex : dataMap.keySet()) { // orders the verts
			tree.put(vertex, dataMap.get(vertex));
		}
		StringBuffer graphString = new StringBuffer();
		graphString.append("Vertices: " + tree.keySet().toString() + "\n");
		graphString.append("Edges:" + "\n");
		for (String verts : tree.keySet()) { // iterates through ordered verts
			TreeMap<String, Integer> adjTree = new TreeMap<String, Integer>();
			for (String adj : adjacencyMap.get(verts).keySet()) { // orders the
																	// adjacent
																	// verts of
																	// each
																	// current
																	// vertex
				adjTree.put(adj, adjacencyMap.get(verts).get(adj));
			}
			String temp = "Vertex" + "(" + verts + ")" + "--->"
					+ adjTree.toString() + "\n";
			graphString.append(temp);
		}
		return graphString.toString();

	}

	/**
	 * Returns a map with information about vertices adjacent to vertexName. If
	 * the vertex has no adjacents, an empty map is returned.
	 * 
	 * @param vertexName
	 * @return
	 */
	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		return adjacencyMap.get(vertexName);
	}

	/**
	 * Returns the cost associated with the specified edge.
	 * 
	 * @param startVertexName
	 * @param endVertexName
	 * @return
	 */
	public int getCost(String startVertexName, String endVertexName) {
		if (!dataMap.containsKey(startVertexName)
				|| !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException();
		} else {
			int cost = adjacencyMap.get(startVertexName).get(endVertexName);
			return cost;
		}
	}

	/**
	 * Returns a Set with all the graph vertices.
	 * 
	 * @return
	 */
	public Set<String> getVertices() {
		return dataMap.keySet();
	}

	/**
	 * Returns the data component associated with the specified vertex.
	 * 
	 * @param vertex
	 * @return
	 */
	public E getData(String vertex) {
		if (!dataMap.containsKey(vertex)) {
			throw new IllegalArgumentException();
		} else {
			return dataMap.get(vertex);
		}
	}

	/**
	 * Computes Depth-First Search of the specified graph.
	 * 
	 * @param startVertexName
	 * @param callback
	 */
	public void doDepthFirstSearch(String startVertexName, CallBack<E> callback) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException();
		}
		HashMap<String, HashMap<String, Integer>> visitedNodes = new HashMap<String, HashMap<String, Integer>>();
		Stack<String> stack = new Stack<String>();
		stack.push(startVertexName);

		while (!stack.isEmpty()) {
			String vertex = stack.pop();
			if (!visitedNodes.containsKey(vertex)) { // not discovered
				callback.processVertex(vertex, dataMap.get(vertex));
				visitedNodes.put(vertex, adjacencyMap.get(vertex)); // mark vertex as discovered
				HashMap<String, Integer> adjacentVerts = adjacencyMap
						.get(vertex);
				for (String item : adjacentVerts.keySet()) {
					if (!visitedNodes.containsKey(item)
							&& !stack.contains(item)) {
						stack.push(item);
					}
				}
			}
		}
	}

	/**
	 * Computes Breadth-First Search of the specified graph
	 * 
	 * @param startVertexName
	 * @param callback
	 */
	public void doBreadthFirstSearch(String startVertexName,
			CallBack<E> callback) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException();
		}
		Queue<String> queue = new PriorityQueue<String>();
		HashMap<String, HashMap<String, Integer>> visitedNodes = new HashMap<String, HashMap<String, Integer>>();
		queue.add(startVertexName);
		while (!queue.isEmpty()) {
			String vertex = queue.remove();
			callback.processVertex(vertex, dataMap.get(vertex));
			visitedNodes.put(vertex, adjacencyMap.get(vertex));
			HashMap<String, Integer> adjacentVerts = adjacencyMap.get(vertex);
			for (String item : adjacentVerts.keySet()) {
				if (!visitedNodes.containsKey(item)) {
					queue.add(item);
					visitedNodes.put(item, adjacencyMap.get(item));
				}
			}
		}
	}

	/**
	 * Computes the shortest path and shortest path cost using Dijkstras's
	 * algorithm. It initializes shortestPath with the names of the vertices
	 * corresponding to the shortest path. If there is no shortest path,
	 * shortestPath will be have entry "None".
	 * 
	 * @param startVertexName
	 * @param endVertexName
	 * @param shortestPath
	 */
	public int doDijkstras(String startVertexName, String endVertexName,
			ArrayList<String> shortestPath) {
		if (!dataMap.containsKey(startVertexName)
				|| !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException();
		}
		
		shortestPath.clear();
		
		HashMap<String, Integer> distances = new HashMap<String, Integer>();
		HashMap<String, String> predecessor = new HashMap<String, String>();
		Collection<String> neighbors = new TreeSet<String>();
		ArrayList<String> notVisit = new ArrayList<String>();
		ArrayList<String> toVisit = new ArrayList<String>();
		
		for(String verts : dataMap.keySet()) {
			notVisit.add(verts);
		}
		toVisit.add(startVertexName);
		
		for(String item : notVisit) {
			distances.put(item, Integer.MAX_VALUE);
			predecessor.put(item, null);
		}
		
		distances.replace(startVertexName, 0);
		
		String currVert = startVertexName;
		int smallest = 0;
		
		while(toVisit.size() > 0) {
			neighbors.clear();
			neighbors.addAll(getAdjacentVertices(currVert).keySet());
			
			for (String vertex : neighbors) {
				if (notVisit.contains(vertex)) {
					toVisit.add(vertex);
				}
				if (getCost(currVert, vertex) < distances.get(vertex)) {
					distances.replace(vertex, getCost(currVert, vertex));
					predecessor.replace(vertex, currVert);
				}
			}
			
			notVisit.remove(currVert); //has been visited
			toVisit.remove(currVert); //in case it looks at itself
			
			if (toVisit.size() != 0) {
				smallest = Integer.MAX_VALUE;
				for (String thing : toVisit) {
					if (smallest > distances.get(thing)) {
						smallest = distances.get(currVert);
						currVert = thing;
					}
				}
			}
		}
		
		//now all the info is in the table
		
		if (notVisit.contains(endVertexName)) { //there is no path
			shortestPath.add("None");
			return -1;
		}
		
		smallest = 0;
		currVert = endVertexName;
		String check = endVertexName;
		
		while (!check.equals(startVertexName)) {
			smallest += distances.get(currVert);
			shortestPath.add(0, currVert);
			check = currVert;
			currVert = predecessor.get(currVert);
		}
		if (startVertexName.equals(endVertexName)) {
			shortestPath.add(startVertexName);
		}
		return smallest;
	}
}