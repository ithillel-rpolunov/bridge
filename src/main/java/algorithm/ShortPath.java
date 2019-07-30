package algorithm;

public class ShortPath {
    private int V ;

    public void setV(int v) {
        V = v;
    }


    private int minDistance(int[] dist, Boolean[] sptSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }

        return minIndex;
    }


    public int dijkstraAlgorithm(int[][] graph, int startNode, int endNode) {
        int[] distance = new int[V];

        Boolean[] startPointSet = new Boolean[V];

        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            startPointSet[i] = false;
        }

        distance[startNode] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(distance, startPointSet);

            startPointSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!startPointSet[v] && graph[u][v] != 0 &&
                        distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v])
                    distance[v] = distance[u] + graph[u][v];
            }
        }

//        printSolution(distance);
        return distance[endNode];
    }


    private void printSolution(int[] dist) {
        System.out.println(String.format("%-15s%15s", "Node", "Distance from start Node"));
        for (int i = 0; i < V; i++)
            System.out.println(String.format("%-15s%15s", i, dist[i]));
    }

    private int printSolution(int[] dist, int v) {
        System.out.println(String.format("%-15s%15s", "Node", "Distance from start Node"));
        System.out.println(String.format("%-15s%15s", v, dist[v-1]));
        return dist[v-1];
    }


}