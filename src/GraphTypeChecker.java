import java.util.Scanner;

public class GraphTypeChecker {

    /**
     * This method checks whether a given adjacency matrix represents a directed graph.
     * A graph is considered directed if the matrix is not symmetric.
     *
     * @param matrix The adjacency matrix.
     * @param n The size of the matrix (n x n).
     * @return true if the graph is directed, false if undirected.
     */
    public static boolean isDirected(int[][] matrix, int n) {
        // Loop through each cell in the upper triangle of the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // If matrix[i][j] is not equal to matrix[j][i], the graph is directed
                if (matrix[i][j] != matrix[j][i]) {
                    return true; // Matrix is not symmetric
                }
            }
        }
        return false; // Matrix is symmetric, so the graph is undirected
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Prompt the user to enter the size of the matrix (number of nodes)
        System.out.print("Enter matrix size n: ");
        int n = scnr.nextInt();

        // Create a 2D array to store the adjacency matrix
        int[][] matrix = new int[n][n];

        // Prompt the user to enter the matrix values row by row
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scnr.nextInt(); // Read the value for cell [i][j]
            }
        }

        // Use the isDirected method to check the type of the graph
        if (isDirected(matrix, n)) {
            System.out.println("The matrix represents a directed graph.");
        } else {
            System.out.println("The matrix represents an undirected graph.");
        }
    }
}
