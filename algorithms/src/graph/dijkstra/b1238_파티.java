package algorithms.src.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b1238_파티 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Edge>[] list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Edge(e, w));
        }

        int[][] d = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
            d[i][i] = 0;
            boolean[] visited = new boolean[N+1];

            PriorityQueue<Edge> q = new PriorityQueue<>();
            q.offer(new Edge(i, 0));
            while (!q.isEmpty()) {
                Edge current = q.poll();

                int c_v = current.v;
                int c_w = current.w;
                if(visited[c_v]) continue;
                visited[c_v] = true;
                for (Edge next : list[c_v]) {
                    if (!visited[next.v] && c_w + next.w < d[i][next.v]) {
                        d[i][next.v] = d[i][c_v] + next.w;
                        q.offer(new Edge(next.v, d[i][next.v]));
                    }
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, d[i][X] + d[X][i]);
        }
        System.out.println(max);



    }

    private static class Edge implements Comparable<Edge> {

        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}
