package leetcode.ForBiginners.Graph.UnionFind;

/**
 * 
 * @author Pranay Jha
 *
 *         here are n computers numbered from 0 to n-1 connected by ethernet
 *         cables connections forming a network where connections[i] = [a, b]
 *         represents a connection between computers a and b. Any computer can
 *         reach any other computer directly or indirectly through the network.
 * 
 *         Given an initial computer network connections. You can extract
 *         certain cables between two directly connected computers, and place
 *         them between any pair of disconnected computers to make them directly
 *         connected. Return the minimum number of times you need to do this in
 *         order to make all the computers connected. If it's not possible,
 *         return -1.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 								0---2              0---2 
 * 								|  /     ====>>    |   |
 * 								| /                |   |
 * 								 1  3	           1   3
 * 
 *         Input: n = 4, connections = [[0,1],[0,2],[1,2]] Output: 1
 *         Explanation: Remove cable between computer 1 and 2 and place between
 *         computers 1 and 3. 
 *         
 *         Example 2:
 * 							0--2    4 
 * 						    |\/|
 * 						    |/\|
 *                          1  3    5
 * 
 * 							 |
 * 							 |
 * 							 V
 * 
 * 							0--2----4 
 * 						    |  |    |
 * 						    |  |    |
 *                          1  3    5		
 * 
 *         Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]] Output: 2
 *         
 *         Example 3:
 * 
 *         Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]] Output: -1
 *         Explanation: There are not enough cables. 
 *         
 *         Example 4:
 * 
 *         Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 10^5 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 *         connections[i].length == 2 0 <= connections[i][0], connections[i][1]
 *         < n connections[i][0] != connections[i][1] There are no repeated
 *         connections. No two computers are connected by more than one cable.
 *
 */

public class _1319_NumberOfOperationsToMakeConnected {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class UnionFind {
	    int parent[];
	    
	    public UnionFind(int size) {
	        this.parent = new int[size];
	        
	        for (int i = 0; i < size; i++) {
	            parent[i] = i;
	        }
	    }
	    
	    public int find(int node) {
	        if (parent[node] != node) {
	            return find(parent[node]);
	        }
	        return node;
	    }
	    
	    public boolean union(int x, int y) { 
	        int parentX = find(x);
	        int parentY = find(y);
	        
	        if (parentX == parentY) {
	            return false;
	        }
	        parent[parentY] = parentX;
	        return true;
	    }
	}
	
public int makeConnected(int n, int[][] connections) {
        
        if (n - 1 > connections.length) {
            return -1;
        }
        int connectionNotNeeded = 0;
        UnionFind uf = new UnionFind(n);
        
        for (int[] connection : connections) {
            if (!uf.union(connection[0], connection[1])) {
                connectionNotNeeded++;
            }
        }
        int[] parent = uf.parent;
        int parentCount = 0;
        
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                parentCount++;
            }    
        }
        
        if (parentCount <= connectionNotNeeded + 1) {
            return parentCount - 1;
        }
        return -1;
    }
}
