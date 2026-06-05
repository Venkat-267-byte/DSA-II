import java.util.*;

class Edge implements Comparable<Edge> {

    int source;
    int destination;
    int weight;

    Edge(int s, int d, int w) {

        source = s;
        destination = d;
        weight = w;
    }

    public int compareTo(Edge e) {

        return this.weight - e.weight;
    }
}

public class casestudy3 {

    static int parent[] = new int[10];

    static int find(int x) {

        if (parent[x] == x)
            return x;

        return find(parent[x]);
    }

    static void union(int x, int y) {

        parent[find(x)] = find(y);
    }

    static void bfs(ArrayList<ArrayList<Integer>> graph,
                    int start) {

        boolean visited[] =
                new boolean[graph.size()];

        Queue<Integer> queue =
                new LinkedList<>();

        visited[start] = true;

        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            int node = queue.poll();

            System.out.print(node + " ");

            for (int next : graph.get(node)) {

                if (!visited[next]) {

                    visited[next] = true;

                    queue.add(next);
                }
            }
        }
    }

    static void dfs(ArrayList<ArrayList<Integer>> graph,
                    int node,
                    boolean visited[]) {

        visited[node] = true;

        System.out.print(node + " ");

        for (int next : graph.get(node)) {

            if (!visited[next]) {

                dfs(graph, next, visited);
            }
        }
    }

    public static void main(String[] args) {

        int vertices = 5;

        ArrayList<ArrayList<Integer>> graph =
                new ArrayList<>();

        for (int i = 0; i < vertices; i++) {

            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(2).add(4);
        graph.get(3).add(4);

        System.out.println(
                "===== Meesho Delivery Network =====");

        bfs(graph, 0);

        System.out.print("\nDFS Traversal: ");

        boolean visited[] =
                new boolean[vertices];

        dfs(graph, 0, visited);

        Edge edges[] = {

                new Edge(0, 1, 10),
                new Edge(0, 2, 6),
                new Edge(0, 3, 5),
                new Edge(1, 3, 15),
                new Edge(2, 3, 4)
        };

        Arrays.sort(edges);

        for (int i = 0; i < vertices; i++) {

            parent[i] = i;
        }

        int totalCost = 0;

        System.out.println("\n\nKruskal MST:");

        for (Edge e : edges) {

            if (find(e.source) !=
                    find(e.destination)) {

                union(e.source,
                        e.destination);

                totalCost += e.weight;

                System.out.println(
                        e.source + " - " +
                        e.destination +
                        " : " + e.weight);
            }
        }

        System.out.println(
                "Kruskal MST Total Cost: "
                        + totalCost);
    }
}