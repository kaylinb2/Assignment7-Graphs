import java.util.*;

public class PathLengthSeven {

    // Edge class represents a directed edge to a destination with a weight
    static class Edge {
        int dest;    // Destination vertex
        int weight;  // Weight of the edge

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    /**
     * Recursive function to find all simple paths from 'current' to 'target'
     * that sum to exactly 7 in total weight.
     */
    static void findPaths(Map<Integer, List<Edge>> graph, int current, int target, int weightRemaining,
                          List<Integer> path, Set<Integer> visited) {

        // If we've exceeded the allowed weight, stop exploring
        if (weightRemaining < 0) return;

        // Add current node to the current path and mark it visited
        path.add(current);
        visited.add(current);

        // If we're at the destination and used up exactly 7 weight, print path
        if (current == target && weightRemaining == 0) {
            System.out.println(path);
        } else {
            // Recurse on all neighbors
            for (Edge edge : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(edge.dest)) {
                    findPaths(graph, edge.dest, target, weightRemaining - edge.weight, path, visited);
                }
            }
        }

        // Backtrack: remove current node from path and visited set
        path.remove(path.size() - 1);
        visited.remove(current);
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Input number of vertices in the graph
        System.out.print("Enter number of vertices: ");
        int V = scnr.nextInt();

        // Use a map to represent the graph as an adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();

        // Input number of edges in the graph
        System.out.print("Enter number of edges: ");
        int E = scnr.nextInt();

        // Read each edge and build the graph
        System.out.println("Enter edges in format: u v weight");
        for (int i = 0; i < E; i++) {
            int u = scnr.nextInt();        // Source vertex
            int v = scnr.nextInt();        // Destination vertex
            int weight = scnr.nextInt();   // Weight of the edge

            // Add edge to the adjacency list (directed)
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new Edge(v, weight));
        }

        // Read start and end vertices for path search
        System.out.print("Enter start vertex u: ");
        int u = scnr.nextInt();

        System.out.print("Enter end vertex w: ");
        int w = scnr.nextInt();

        // Start recursive search from u to w with weight 7
        System.out.println("Simple paths from " + u + " to " + w + " with weight 7:");
        findPaths(graph, u, w, 7, new ArrayList<>(), new HashSet<>());
    }
}
