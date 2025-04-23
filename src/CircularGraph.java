import java.util.*;

public class CircularGraph {

    // Class to hold a vertex and its offset
    static class Vertex {
        String name;
        int offset;

        Vertex(String name, int offset) {
            this.name = name;
            this.offset = offset;
        }
    }

    public static void main(String[] args) {
        // Sample input
        String input = "[ (I, 2), (A, 5), (E, 4), (F, 2), (T, 2), (S, 3) ]";

        // Remove brackets and split the input
        input = input.replaceAll("[\\[\\]()]", "");
        String[] parts = input.split(",");

        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < parts.length; i += 2) {
            String name = parts[i].trim();
            int value = Integer.parseInt(parts[i + 1].trim());
            vertices.add(new Vertex(name, value));
        }

        int n = vertices.size();

        // Print edges
        for (int i = 0; i < n; i++) {
            Vertex v = vertices.get(i);
            int left = (i - v.offset + n) % n;
            int right = (i + v.offset) % n;

            System.out.println(v.name + " -> " + vertices.get(right).name);
            System.out.println(v.name + " -> " + vertices.get(left).name);
        }
    }
}
