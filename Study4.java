import java.util.*;

public class Study4 {

    static final int INF = 99999;

    // Dijkstra Algorithm
    static void dijkstra(int[][] graph, int src) {
        int V = graph.length;

        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = -1;
            int min = INF;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && dist[v] < min) {
                    min = dist[v];
                    u = v;
                }
            }

            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != INF
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("\n===== Dijkstra Shortest Paths =====");
        for (int i = 0; i < V; i++) {
            System.out.println("Warehouse A -> Location " + i +
                    " = " + dist[i] + " km");
        }
    }

    // Bellman Ford Algorithm
    static void bellmanFord(int[][] edges, int V, int src) {

        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int i = 1; i < V; i++) {

            for (int[] edge : edges) {

                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                if (dist[u] != INF &&
                        dist[u] + weight < dist[v]) {

                    dist[v] = dist[u] + weight;
                }
            }
        }

        System.out.println("\n===== Bellman-Ford Shortest Paths =====");
        for (int i = 0; i < V; i++) {
            System.out.println("Warehouse A -> Location " + i +
                    " = " + dist[i] + " km");
        }
    }

    // Floyd Warshall Algorithm
    static void floydWarshall(int[][] graph) {

        int V = graph.length;

        int[][] dist = new int[V][V];

        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {

                    if (dist[i][k] != INF &&
                            dist[k][j] != INF &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {

                        dist[i][j] =
                                dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        System.out.println("\n===== Floyd-Warshall Matrix =====");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {

                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("===== Meesho Route Optimization System =====");

        // Graph for Dijkstra
        int[][] graph = {
                {0, 4, 7, 0},
                {4, 0, 3, 6},
                {7, 3, 0, 2},
                {0, 6, 2, 0}
        };

        // Edge List for Bellman Ford
        int[][] edges = {
                {0, 1, 4},
                {0, 2, 7},
                {1, 2, 3},
                {1, 3, 6},
                {2, 3, 2}
        };

        dijkstra(graph, 0);

        bellmanFord(edges, 4, 0);

        int[][] fwGraph = {
                {0, 4, 7, INF},
                {4, 0, 3, 6},
                {7, 3, 0, 2},
                {INF, 6, 2, 0}
        };

        floydWarshall(fwGraph);

        System.out.println("\nOptimal Delivery Route Selected Successfully.");
    }
}