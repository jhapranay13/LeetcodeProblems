package leetcode.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a list of airline tickets where tickets[i] = [fromi,
 *         toi] represent the departure and the arrival airports of one flight.
 *         Reconstruct the itinerary in order and return it.
 * 
 *         All of the tickets belong to a man who departs from "JFK", thus, the
 *         itinerary must begin with "JFK". If there are multiple valid
 *         itineraries, you should return the itinerary that has the smallest
 *         lexical order when read as a single string.
 * 
 *         For example, the itinerary ["JFK", "LGA"] has a smaller lexical order
 *         than ["JFK", "LGB"]. You may assume all tickets form at least one
 *         valid itinerary. You must use all the tickets once and only once.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: tickets =
 *         [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]] Output:
 *         ["JFK","MUC","LHR","SFO","SJC"] 
 *         
 *         
 *         Example 2:
 * 
 * 
 *         Input: tickets =
 *         [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *         Output: ["JFK","ATL","JFK","SFO","ATL","SFO"] Explanation: Another
 *         possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but
 *         it is larger in lexical order.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= tickets.length <= 300 tickets[i].length == 2 fromi.length == 3
 *         toi.length == 3 fromi and toi consist of uppercase English letters.
 *         fromi != toi
 *
 */

public class _332_ReconstructItenary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> findItinerary(List<List<String>> tickets) {
		Map<String, List<String>> adjacency = new HashMap<>();
		Map<String, boolean[]> visited = new HashMap<>();
		initAdjacencyAndVisited(adjacency, visited, tickets);
		List<String> ans = new ArrayList<>();
		dfs(adjacency, visited, ans, "JFK", tickets.size());
		return ans;
	}

	private boolean dfs(Map<String, List<String>> adjacency, Map<String, boolean[]> visited,
			List<String> ans, String from, int size) {
		ans.add(from);
		//Node size should be one greater than connection
		if (ans.size() == size + 1) {
			return true;
		}

		if (!adjacency.containsKey(from)) {
			ans.remove(ans.size() - 1);
			return false;
		}

		List<String> destination = adjacency.get(from);
		boolean[] usedDestination = visited.get(from);

		for (int i = 0; i < destination.size(); i++) {

			if (!usedDestination[i]) {
				usedDestination[i] = true;
				if (dfs(adjacency, visited, ans, destination.get(i), size)) {
					return true;
				}
				usedDestination[i] = false;
			}
		}
		ans.remove(ans.size() - 1);
		return false;
	}

	private void initAdjacencyAndVisited(Map<String, List<String>> adjacency, 
			Map<String, boolean[]> visited, List<List<String>> tickets) {
		for (List<String> ticket : tickets) {
			String from = ticket.get(0);
			String to = ticket.get(1);
			List<String> destination = adjacency.getOrDefault(from, new ArrayList<>());
			destination.add(to);
			adjacency.put(from, destination);
		}

		for (String key : adjacency.keySet()) {
			List<String> destination = adjacency.get(key);
			Collections.sort(destination);
			visited.put(key, new boolean[destination.size()]);
		}
	}
	//=============================================================================
	//Slight Variation ina adding visited node
	public List<String> findItinerary1(List<List<String>> tickets) {
		Map< String, List< String > > adj = new HashMap<>();
		Map< String, boolean[] > v = new HashMap<>();
		initAdjV( adj, v, tickets );
		List<String> ans = new ArrayList<>();
		ans.add( "JFK" );
		dfs1( adj, v, ans, "JFK", tickets.size() );
		return ans;
	}

	private boolean dfs1( Map< String, List< String > > adj, Map< String, boolean[] > v, 
			List<String> ans, String node, int size ) {
		if( ans.size() == size + 1 ) {
			return true;
		}

		if( !adj.containsKey( node ) ) {
			System.out.println( ans );

			return false;
		}
		List< String > adjList = adj.get( node );
		boolean[] b = v.get( node );


		for( int i = 0; i < adjList.size(); i++ ) {
			String nextNode = adjList.get( i );

			if( !b[ i ] ) {
				b[ i ] = true;
				ans.add( nextNode );

				if( dfs( adj,v, ans, nextNode, size ) ) {
					return true;
				}
				ans.remove( ans.size() - 1 );
				b[ i ] = false;
			}
		}
		return false;
	}

	private void initAdjV(  Map< String, List< String > > adj, Map< String, boolean[] > v, 
			List<List<String>> tickets ) {

		for( List< String > fromTo : tickets ) {
			String from = fromTo.get( 0 );
			String to = fromTo.get( 1 );
			List< String > list = adj.getOrDefault( from, new ArrayList<>() );
			list.add( to );
			adj.put( from, list );
		}
		Set< String > keys = adj.keySet(); 

		for( String key : keys ) {
			List< String > l = adj.get( key );
			boolean[] b = new boolean[ l.size() ];
			Collections.sort( l );
			v.put( key, b );
		}
	}
}
