import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.*;

public class DrawGraph {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to input vertex-distance pairs in a specific format
        System.out.println("Enter input like: (I,2),(A,5),(E,4),(F,2),(T,2),(S,3)");
        String input = scanner.nextLine();

        // Remove unwanted characters: parentheses, brackets, and whitespace
        input = input.replaceAll("[()\\[\\]\\s]", "");

        // Split the input string into individual tokens
        // For example: I,2,A,5,E,4,... becomes [I, 2, A, 5, ...]
        String[] pairs = input.split(",");

        // Lists to store vertex names and their corresponding position values
        List<String> vertices = new ArrayList<>();
        List<Integer> positions = new ArrayList<>();

        // Extract vertex and distance pairs from the parsed array
        for (int i = 0; i < pairs.length; i += 2) {
            vertices.add(pairs[i]);                       // Vertex name (e.g., "I")
            positions.add(Integer.parseInt(pairs[i + 1])); // Position number (e.g., 2)
        }

        // Create a new directed graph
        Graph graph = new SingleGraph("Directed Graph");

        // Add nodes to the graph using the list of vertex names
        for (String vertex : vertices) {
            Node node = graph.addNode(vertex);             // Add node by name
            node.setAttribute("ui.label", vertex);         // Set the label (visible name)
        }

        int n = vertices.size();                           // Total number of vertices

        // Set to keep track of edges that have already been added
        Set<String> edgeSet = new HashSet<>();

        // Add directed edges based on circular distance logic
        for (int i = 0; i < n; i++) {
            String from = vertices.get(i);                 // Current vertex
            int dist = positions.get(i);                   // Distance for this vertex

            // Calculate the index to the right (wrap around using modulus)
            int rightIndex = (i + dist) % n;
            // Calculate the index to the left (handle negative using +n)
            int leftIndex = (i - dist + n) % n;

            // Get the destination vertex names
            String toRight = vertices.get(rightIndex);
            String toLeft = vertices.get(leftIndex);

            // Create and add the edge to the right if it doesn't already exist
            if (!edgeSet.contains(from + "-" + toRight)) {
                graph.addEdge(from + "-" + toRight, from, toRight, true); // true = directed edge
                edgeSet.add(from + "-" + toRight);
            }

            // Create and add the edge to the left if it doesn't already exist
            if (!edgeSet.contains(from + "-" + toLeft)) {
                graph.addEdge(from + "-" + toLeft, from, toLeft, true);
                edgeSet.add(from + "-" + toLeft);
            }
        }

        // Styling rules for nodes and edges to match the desired visual
        graph.setAttribute("ui.stylesheet",
                "node {" +
                        "   fill-color: lightblue;" +               // Light blue fill
                        "   size: 40px;" +                          // Circle size
                        "   shape: circle;" +                       // Make nodes circular
                        "   text-size: 24px;" +                     // Label font size
                        "   text-alignment: center;" +              // Center label inside node
                        "   text-color: black;" +                   // Text color
                        "   text-background-mode: plain;" +         // Enable text background
                        "   text-background-color: lightblue;" +    // Match background color
                        "}" +
                        "edge {" +
                        "   arrow-shape: arrow;" +                  // Display arrowheads
                        "   fill-color: black;" +                   // Edge color
                        "}");

        // Display the graph visually
        graph.display();
    }
}
